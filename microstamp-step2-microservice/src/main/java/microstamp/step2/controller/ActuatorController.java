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
    public ResponseEntity<List<Actuator>> findAll() {
        return new ResponseEntity<>(actuatorService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Actuator> findById(@PathVariable long id) {
        return new ResponseEntity<>(actuatorService.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = {"controlstructure/{id}"})
    public ResponseEntity<List<Actuator>> findByControlStructureId(@PathVariable long id) {
        return new ResponseEntity<>(actuatorService.findByControlStructureId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Actuator> create(@RequestBody ActuatorDto actuatorDto) {
        return new ResponseEntity<>(actuatorService.create(actuatorDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") long id, @RequestBody ActuatorDto actuatorDto) {
        actuatorService.update(id, actuatorDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Void> delete(@PathVariable long id) {
        actuatorService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
