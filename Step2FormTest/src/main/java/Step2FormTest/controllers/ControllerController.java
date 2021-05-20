package Step2FormTest.controllers;


import Step2FormTest.domain.ControllerDomain;
import Step2FormTest.models.Controller;
import Step2FormTest.repositories.ControllerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/controllers")
public class ControllerController {

    @Autowired
    private final ControllerRepository controllerRepository;

    @Autowired
    public ControllerController(ControllerRepository controllerRepository) {
        this.controllerRepository = controllerRepository;
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

    @PostMapping
    public Controller create(@RequestBody ControllerDomain controllerDomain){
        Controller controller = new Controller();
        controller.setName(controllerDomain.getName());
        //actuator.setBorder(controllerDomain.getBorder());
        //actuator.setFather(controllerDomain.getFather());
        //actuator.setVisible(controllerDomain.isIsVisible());
        controllerRepository.save(controller);
        return controller;
    }

    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody Controller controllerParam) {
        return controllerRepository.findById(id)
                .map(record -> {
                    record.setName(controllerParam.getName());
                    Controller updated = controllerRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
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
