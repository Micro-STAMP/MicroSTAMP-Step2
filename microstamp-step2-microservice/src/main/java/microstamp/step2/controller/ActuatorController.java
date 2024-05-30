package microstamp.step2.controller;

import microstamp.step2.dto.ActuatorDto;
import microstamp.step2.data.Actuator;
import microstamp.step2.data.Component;
import microstamp.step2.data.ControlStructure;
import microstamp.step2.repository.ComponentRepository;
import microstamp.step2.repository.ControlStructureRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import microstamp.step2.repository.ActuatorRepository;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/actuators")
@Tag(name = "Actuator")
public class ActuatorController {

    @Autowired
    private final ActuatorRepository actuatorRepository;

    @Autowired
    private final ComponentRepository componentRepository;

    @Autowired
    private final ControlStructureRepository controlStructureRepository;

    @Autowired
    public ActuatorController(ActuatorRepository actuatorRepository, ComponentRepository componentRepository, ControlStructureRepository controlStructureRepository) {
        this.actuatorRepository = actuatorRepository;
        this.componentRepository = componentRepository;
        this.controlStructureRepository = controlStructureRepository;
    }

    @GetMapping
    public List findAll(){
        return actuatorRepository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id){
        return actuatorRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = {"cs/{id}"})
    public List findByControlStructureId(@PathVariable long id){
        return actuatorRepository.findActuatorsByControlStructureId(id);
    }

    @PostMapping
    public Actuator create(@RequestBody ActuatorDto actuatorDto){
        Actuator actuator = new Actuator();
        actuator.setName(actuatorDto.getName());
        try {
            Optional<Component> father = componentRepository.findById(actuatorDto.getFather_id());
            actuator.setFather(father.get());
            father.get().setControlStructure(true);
        }catch (Exception ex){
            actuator.setFather(null);
        }
        actuator.setBorder(actuatorDto.getBorder());
        actuator.setIsVisible(actuatorDto.getIsVisible());
        Optional<ControlStructure> c1 = controlStructureRepository.findById(actuatorDto.getControl_structure_id());
        c1.get().getComponents().add(actuator);
        controlStructureRepository.save(c1.get());
        return actuator;
    }

    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody ActuatorDto actuatorDto) {
        if(actuatorDto.getFather_id() != null) {
            if(actuatorDto.getType() != "Actuator")
                componentRepository.updateComponentType(id,actuatorDto.getType());
            return componentRepository.findById(id)
                    .map(record -> {
                        record.setName(actuatorDto.getName());
                        record.setBorder(actuatorDto.getBorder());
                        record.setFather(componentRepository.findById(actuatorDto.getFather_id()).get());
                        record.setIsVisible(actuatorDto.getIsVisible());
                        Component updated = componentRepository.save(record);
                        return ResponseEntity.ok().body(updated);
                    }).orElse(ResponseEntity.notFound().build());
        }else{
            if(actuatorDto.getType() != "Actuator")
                componentRepository.updateComponentType(id,actuatorDto.getType());
            return componentRepository.findById(id)
                    .map(record -> {
                        record.setName(actuatorDto.getName());
                        record.setBorder(actuatorDto.getBorder());
                        record.setFather(null);
                        record.setIsVisible(actuatorDto.getIsVisible());
                        Component updated = componentRepository.save(record);
                        return ResponseEntity.ok().body(updated);
                    }).orElse(ResponseEntity.notFound().build());
        }
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        return actuatorRepository.findById(id)
                .map(record -> {
                    actuatorRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
