package Step2FormTest.controllers;

import Step2FormTest.domain.ActuatorDomain;
import Step2FormTest.models.Actuator;
import Step2FormTest.models.Component;
import Step2FormTest.models.ControlStructure;
import Step2FormTest.repositories.ComponentRepository;
import Step2FormTest.repositories.ControlStructureRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Step2FormTest.repositories.ActuatorRepository;

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
    public Actuator create(@RequestBody ActuatorDomain actuatorDomain){
        Actuator actuator = new Actuator();
        actuator.setName(actuatorDomain.getName());
        try {
            Optional<Component> father = componentRepository.findById(actuatorDomain.getFather_id());
            actuator.setFather(father.get());
            father.get().setControlStructure(true);
        }catch (Exception ex){
            actuator.setFather(null);
        }
        actuator.setBorder(actuatorDomain.getBorder());
        actuator.setIsVisible(actuatorDomain.getIsVisible());
        Optional<ControlStructure> c1 = controlStructureRepository.findById(actuatorDomain.getControl_structure_id());
        c1.get().getComponents().add(actuator);
        controlStructureRepository.save(c1.get());
        return actuator;
    }

    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody ActuatorDomain actuatorDomain) {
        if(actuatorDomain.getFather_id() != null) {
            if(actuatorDomain.getType() != "Actuator")
                componentRepository.updateComponentType(id,actuatorDomain.getType());
            return componentRepository.findById(id)
                    .map(record -> {
                        record.setName(actuatorDomain.getName());
                        record.setBorder(actuatorDomain.getBorder());
                        record.setFather(componentRepository.findById(actuatorDomain.getFather_id()).get());
                        record.setIsVisible(actuatorDomain.getIsVisible());
                        Component updated = componentRepository.save(record);
                        return ResponseEntity.ok().body(updated);
                    }).orElse(ResponseEntity.notFound().build());
        }else{
            if(actuatorDomain.getType() != "Actuator")
                componentRepository.updateComponentType(id,actuatorDomain.getType());
            return componentRepository.findById(id)
                    .map(record -> {
                        record.setName(actuatorDomain.getName());
                        record.setBorder(actuatorDomain.getBorder());
                        record.setFather(null);
                        record.setIsVisible(actuatorDomain.getIsVisible());
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
