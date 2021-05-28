package Step2FormTest.controllers;


import Step2FormTest.domain.ControlledProcessDomain;
import Step2FormTest.models.Component;
import Step2FormTest.models.ControlledProcess;
import Step2FormTest.repositories.ComponentRepository;
import Step2FormTest.repositories.ControlledProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/controlledProcesses")
public class ControlledProcessController {

    @Autowired
    private final ControlledProcessRepository controlledProcessRepository;

    @Autowired
    private final ComponentRepository componentRepository;

    @Autowired
    public ControlledProcessController(ControlledProcessRepository controlledProcessRepository, ComponentRepository componentRepository) {
        this.controlledProcessRepository = controlledProcessRepository;
        this.componentRepository = componentRepository;
    }

    @GetMapping
    public List findAll(){
        return controlledProcessRepository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id){
        return controlledProcessRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ControlledProcess create(@RequestBody ControlledProcessDomain controlledProcessDomain){
        ControlledProcess controlledProcess = new ControlledProcess();
        controlledProcess.setName(controlledProcessDomain.getName());
        try {
            Optional<Component> father = componentRepository.findById(controlledProcessDomain.getFather_id());
            controlledProcess.setFather(father.get());
        }catch (Exception ex){
            controlledProcess.setFather(null);
        }
        controlledProcess.setBorder(controlledProcessDomain.getBorder());
        controlledProcess.setIsVisible(controlledProcessDomain.getIsVisible());
        controlledProcessRepository.save(controlledProcess);
        return controlledProcess;
    }

    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody ControlledProcess controlledProcessParam) {
        return controlledProcessRepository.findById(id)
                .map(record -> {
                    record.setName(controlledProcessParam.getName());
                    ControlledProcess updated = controlledProcessRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        return controlledProcessRepository.findById(id)
                .map(record -> {
                    controlledProcessRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
