package microstamp.step2.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import microstamp.step2.data.Actuator;
import microstamp.step2.dto.ActuatorDto;
import microstamp.step2.service.ActuatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/actuators")
@Tag(name = "Actuator")
public class ActuatorController {

    @Autowired
    private ActuatorService actuatorService;

    @GetMapping
    public List findAll() {
        return actuatorService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id) {
        return new ResponseEntity<>(actuatorService.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = {"cs/{id}"})
    public List findByControlStructureId(@PathVariable long id) {
        return actuatorService.findByControlStructureId(id);
    }

    @PostMapping
    public Actuator create(@RequestBody ActuatorDto actuatorDto) {
        return actuatorService.create(actuatorDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody ActuatorDto actuatorDto) {
        actuatorService.update(id, actuatorDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> delete(@PathVariable long id) {
        actuatorService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
