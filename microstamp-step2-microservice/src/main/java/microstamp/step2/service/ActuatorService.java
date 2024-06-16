package microstamp.step2.service;

import microstamp.step2.data.Actuator;
import microstamp.step2.data.ControlStructure;
import microstamp.step2.dto.ActuatorDto;
import microstamp.step2.exception.Step2NotFoundException;
import microstamp.step2.repository.ActuatorRepository;
import microstamp.step2.repository.ComponentRepository;
import microstamp.step2.repository.ControlStructureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ActuatorService {

    @Autowired
    private ActuatorRepository actuatorRepository;

    @Autowired
    private ComponentRepository componentRepository;

    @Autowired
    private ControlStructureRepository controlStructureRepository;

    public List<Actuator> findAll() {
        return actuatorRepository.findAll();
    }

    public Actuator findById(Long id) throws Step2NotFoundException {
        return actuatorRepository.findById(id)
                .orElseThrow(() -> new Step2NotFoundException("Actuator not found with id: " + id));
    }

    public List<Actuator> findByControlStructureId(long id) {
        return actuatorRepository.findByControlStructureId(id);
    }

    public Actuator insert(ActuatorDto actuatorDto) throws Step2NotFoundException {
        ControlStructure controlStructure = controlStructureRepository.findById(actuatorDto.getControlStructureId())
                .orElseThrow(() -> new Step2NotFoundException("ControlStructure not found with id: " + actuatorDto.getControlStructureId()));

        Actuator actuator = new Actuator();
        actuator.setName(actuatorDto.getName());
        actuator.setBorder(actuatorDto.getBorder());
        actuator.setIsVisible(actuatorDto.getIsVisible());

        if (actuatorDto.getFatherId() != null) {
            microstamp.step2.data.Component father = componentRepository.findById(actuatorDto.getFatherId())
                    .orElseThrow(() -> new Step2NotFoundException("Father component not found with id: " + actuatorDto.getFatherId()));
            actuator.setFather(father);
            father.setIsControlStructure(true);
        } else {
            actuator.setFather(null);
        }

        controlStructure.getComponents().add(actuator);
        controlStructureRepository.save(controlStructure);

        return actuator;
    }

    public void update(long id, ActuatorDto actuatorDto) throws Step2NotFoundException {
        microstamp.step2.data.Component actuator = componentRepository.findById(id)
                .orElseThrow(() -> new Step2NotFoundException("Actuator not found with id: " + id));

        actuator.setName(actuatorDto.getName());
        actuator.setBorder(actuatorDto.getBorder());
        actuator.setIsVisible(actuatorDto.getIsVisible());

        if (actuatorDto.getFatherId() != null) {
            microstamp.step2.data.Component father = componentRepository.findById(actuatorDto.getFatherId())
                    .orElseThrow(() -> new Step2NotFoundException("Father component not found with id: " + actuatorDto.getFatherId()));
            actuator.setFather(father);
        } else {
            actuator.setFather(null);
        }

        if (!actuatorDto.getType().equals("Actuator"))
            componentRepository.updateComponentType(id, actuatorDto.getType());

        componentRepository.save(actuator);
    }

    public void delete(long id) throws Step2NotFoundException {
        Actuator actuator = actuatorRepository.findById(id)
                .orElseThrow(() -> new Step2NotFoundException("Actuator not found with id: " + id));
        actuatorRepository.deleteById(actuator.getId());
    }
}
