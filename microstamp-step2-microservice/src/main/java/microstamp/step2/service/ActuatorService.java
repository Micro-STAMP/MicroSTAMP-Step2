package microstamp.step2.service;

import microstamp.step2.data.Actuator;
import microstamp.step2.data.ControlStructure;
import microstamp.step2.dto.ActuatorDto;
import microstamp.step2.exception.Step2NotFoundException;
import microstamp.step2.exception.Step2OrphanException;
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
    private ComponentService componentService;

    @Autowired
    private ControlStructureService controlStructureService;

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
        ControlStructure controlStructure = controlStructureService.findById(actuatorDto.getControlStructureId());

        Actuator actuator = new Actuator();
        actuator.setName(actuatorDto.getName());
        actuator.setBorder(actuatorDto.getBorder());
        actuator.setIsVisible(actuatorDto.getIsVisible());

        if (actuatorDto.getFatherId() != null) {
            microstamp.step2.data.Component father = componentService.findById(actuatorDto.getFatherId());
            actuator.setFather(father);
            father.setIsControlStructure(true);
        } else {
            actuator.setFather(null);
        }

        controlStructure.getComponents().add(actuator);
        controlStructureService.save(controlStructure);

        return actuator;
    }

    public void update(long id, ActuatorDto actuatorDto) throws Step2NotFoundException {
        microstamp.step2.data.Component actuator = componentService.findById(id);

        if (actuatorDto.getFatherId() != null) {
            microstamp.step2.data.Component father = componentService.findById(actuatorDto.getFatherId());

            List<microstamp.step2.data.Component> children = componentService.getComponentChildren(id);
            if(children.contains(father))
                throw new Step2OrphanException();

            actuator.setFather(father);
        } else {
            actuator.setFather(null);
        }

        actuator.setName(actuatorDto.getName());
        actuator.setBorder(actuatorDto.getBorder());
        actuator.setIsVisible(actuatorDto.getIsVisible());

        componentService.save(actuator);

        if (!actuatorDto.getType().equals("Actuator"))
            componentService.updateType(id, actuatorDto.getType());
    }

    public void delete(long id) throws Step2NotFoundException {
        Actuator actuator = actuatorRepository.findById(id)
                .orElseThrow(() -> new Step2NotFoundException("Actuator not found with id: " + id));
        actuatorRepository.deleteById(actuator.getId());
    }
}
