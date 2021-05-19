package Step2FormTest.controllers;

import Step2FormTest.models.Actuator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import Step2FormTest.repositories.ActuatorRepository;

import java.util.List;

@RestController
@RequestMapping("/actuators")
public class ActuatorController {

    @Autowired
    private final ActuatorRepository actuatorRepository;

    @Autowired
    public ActuatorController(ActuatorRepository actuatorRepository) {
        this.actuatorRepository = actuatorRepository;
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


    @PostMapping
    public Actuator create(@RequestBody Actuator actuatorParam){
        Actuator actuator = new Actuator();
        actuator.setName(actuatorParam.getName());
        //actuator.setBorder(actuatorParam.getBorder());
        //actuator.setFather(actuatorParam.getFather());
        //actuator.setVisible(actuatorParam.isIsVisible());
        actuatorRepository.save(actuator);
        return actuator;
    }

    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody Actuator actuatorParam) {
        return actuatorRepository.findById(id)
                .map(record -> {
                    record.setName(actuatorParam.getName());
                    Actuator updated = actuatorRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
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
