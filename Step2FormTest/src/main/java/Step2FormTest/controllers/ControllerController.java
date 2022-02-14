package Step2FormTest.controllers;


import Step2FormTest.domain.ControllerDomain;
import Step2FormTest.models.Actuator;
import Step2FormTest.models.Component;
import Step2FormTest.models.ControlStructure;
import Step2FormTest.models.Controller;
import Step2FormTest.repositories.ComponentRepository;
import Step2FormTest.repositories.ControlStructureRepository;
import Step2FormTest.repositories.ControllerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/controllers")
public class ControllerController {

    @Autowired
    private final ControllerRepository controllerRepository;

    @Autowired
    private final ComponentRepository componentRepository;

    @Autowired
    private final ControlStructureRepository controlStructureRepository;

    @Autowired
    public ControllerController(ControllerRepository controllerRepository, ComponentRepository componentRepository, ControlStructureRepository controlStructureRepository) {
        this.controllerRepository = controllerRepository;
        this.componentRepository = componentRepository;
        this.controlStructureRepository = controlStructureRepository;
    }

    @GetMapping
    public List findAll(){
        return controllerRepository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id){
        return controllerRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = {"cs/{id}"})
    public List findByControlStructureId(@PathVariable long id){
        return controllerRepository.findControllersByControlStructureId(id);
    }

    @PostMapping
    public Controller create(@RequestBody ControllerDomain controllerDomain){
        Controller controller = new Controller();
        controller.setName(controllerDomain.getName());
        try {
            Optional<Component> father = componentRepository.findById(controllerDomain.getFather_id());
            controller.setFather(father.get());
            father.get().setControlStructure(true);
        }catch (Exception ex){
            controller.setFather(null);
        }
        controller.setBorder(controllerDomain.getBorder());
        controller.setIsVisible(controllerDomain.getIsVisible());
        Optional<ControlStructure> c1 = controlStructureRepository.findById(controllerDomain.getControl_structure_id());
        c1.get().getComponents().add(controller);
        controlStructureRepository.save(c1.get());
        return controller;
    }

    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody ControllerDomain controllerDomain) {
        if(controllerDomain.getFather_id() != null) {
            if(controllerDomain.getType() != "Controller")
                componentRepository.updateComponentType(id,controllerDomain.getType());
            return componentRepository.findById(id)
                    .map(record -> {
                        record.setName(controllerDomain.getName());
                        record.setBorder(controllerDomain.getBorder());
                        record.setFather(componentRepository.findById(controllerDomain.getFather_id()).get());
                        record.setIsVisible(controllerDomain.getIsVisible());
                        Component updated = componentRepository.save(record);
                        return ResponseEntity.ok().body(updated);
                    }).orElse(ResponseEntity.notFound().build());
        }else{
            if(controllerDomain.getType() != "Controller")
                componentRepository.updateComponentType(id,controllerDomain.getType());
            return componentRepository.findById(id)
                    .map(record -> {
                        record.setName(controllerDomain.getName());
                        record.setBorder(controllerDomain.getBorder());
                        record.setFather(null);
                        record.setIsVisible(controllerDomain.getIsVisible());
                        Component updated = componentRepository.save(record);
                        return ResponseEntity.ok().body(updated);
                    }).orElse(ResponseEntity.notFound().build());
        }
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        return controllerRepository.findById(id)
                .map(record -> {
                    controllerRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
