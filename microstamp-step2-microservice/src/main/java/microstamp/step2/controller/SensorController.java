package microstamp.step2.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import microstamp.step2.data.Sensor;
import microstamp.step2.dto.SensorDto;
import microstamp.step2.service.ComponentService;
import microstamp.step2.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/sensors")
@Tag(name = "Sensor")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @GetMapping
    public List findAll() {
        return sensorService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id) {
        return new ResponseEntity<>(sensorService.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = {"controlstructure/{id}"})
    public List findByControlStructureId(@PathVariable long id) {
        return sensorService.findByControlStructureId(id);
    }

    @PostMapping
    public Sensor create(@RequestBody SensorDto sensorDto) {
        return sensorService.create(sensorDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody SensorDto sensorDto) {
        sensorService.update(id, sensorDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> delete(@PathVariable long id) {
        sensorService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
