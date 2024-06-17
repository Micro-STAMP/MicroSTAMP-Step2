package microstamp.step2.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import microstamp.step2.data.Sensor;
import microstamp.step2.dto.SensorDto;
import microstamp.step2.exception.Step2NotFoundException;
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
    public ResponseEntity<List<Sensor>> findAll() {
        return new ResponseEntity<>(sensorService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Sensor> findById(@PathVariable long id) throws Step2NotFoundException {
        return new ResponseEntity<>(sensorService.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = {"control-structure/{id}"})
    public ResponseEntity<List<Sensor>> findByControlStructureId(@PathVariable long id) {
        return new ResponseEntity<>(sensorService.findByControlStructureId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Sensor> insert(@RequestBody SensorDto sensorDto) throws Step2NotFoundException {
        return new ResponseEntity<>(sensorService.insert(sensorDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") long id, @RequestBody SensorDto sensorDto) throws Step2NotFoundException {
        sensorService.update(id, sensorDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Void> delete(@PathVariable long id) throws Step2NotFoundException {
        sensorService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
