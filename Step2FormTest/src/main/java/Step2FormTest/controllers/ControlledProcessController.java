package Step2FormTest.controllers;


import Step2FormTest.models.ControlledProcess;
import Step2FormTest.repositories.ControlledProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/controlledProcesses")
public class ControlledProcessController {

    @Autowired
    private final ControlledProcessRepository controlledProcessRepository;

    @Autowired
    public ControlledProcessController(ControlledProcessRepository controlledProcessRepository) {
        this.controlledProcessRepository = controlledProcessRepository;
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
    public ControlledProcess create(@RequestBody ControlledProcess controlledProcessParam){
        ControlledProcess controlledProcess = new ControlledProcess();
        controlledProcess.setName(controlledProcessParam.getName());
        //actuator.setBorder(actuatorParam.getBorder());
        //actuator.setFather(actuatorParam.getFather());
        //actuator.setVisible(actuatorParam.isIsVisible());
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
