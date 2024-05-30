package microstamp.step2.service;

import microstamp.step2.data.Actuator;
import microstamp.step2.data.ControlStructure;
import microstamp.step2.dto.ActuatorDto;
import microstamp.step2.repository.ActuatorRepository;
import microstamp.step2.repository.ComponentRepository;
import microstamp.step2.repository.ControlStructureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

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

    public Actuator findById(Long id){
        return actuatorRepository.findById(id)
                .orElseThrow();
    }

    public List<Actuator> findByControlStructureId(long id){
        return actuatorRepository.findActuatorsByControlStructureId(id);
    }

    public Actuator create(ActuatorDto actuatorDto){
        Actuator actuator = new Actuator();
        actuator.setName(actuatorDto.getName());
        try {
            Optional<microstamp.step2.data.Component> father = componentRepository.findById(actuatorDto.getFatherId());
            actuator.setFather(father.get());
            father.get().setIsControlStructure(true);
        }catch (Exception ex){
            actuator.setFather(null);
        }
        actuator.setBorder(actuatorDto.getBorder());
        actuator.setIsVisible(actuatorDto.getIsVisible());
        Optional<ControlStructure> c1 = controlStructureRepository.findById(actuatorDto.getControlStructureId());
        c1.get().getComponents().add(actuator);
        controlStructureRepository.save(c1.get());
        return actuator;
    }

    public void update(long id, ActuatorDto actuatorDto){
        if(actuatorDto.getFatherId() != null) {
            if(actuatorDto.getType() != "Actuator")
                componentRepository.updateComponentType(id,actuatorDto.getType());
            componentRepository.findById(id)
                .map(record -> {
                    record.setName(actuatorDto.getName());
                    record.setBorder(actuatorDto.getBorder());
                    record.setFather(componentRepository.findById(actuatorDto.getFatherId()).get());
                    record.setIsVisible(actuatorDto.getIsVisible());
                    return componentRepository.save(record);
                }).orElseThrow();
        }else{
            if(actuatorDto.getType() != "Actuator")
                componentRepository.updateComponentType(id,actuatorDto.getType());
            componentRepository.findById(id)
                .map(record -> {
                    record.setName(actuatorDto.getName());
                    record.setBorder(actuatorDto.getBorder());
                    record.setFather(null);
                    record.setIsVisible(actuatorDto.getIsVisible());
                    return componentRepository.save(record);
                }).orElseThrow();
        }
    }

    public void delete(long id){
        actuatorRepository.findById(id)
                .map(record -> {
                    actuatorRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElseThrow();
    }
}
