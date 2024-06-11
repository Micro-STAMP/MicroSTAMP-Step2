package microstamp.step2.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import microstamp.step2.data.Controller;
import microstamp.step2.dto.ControllerDto;
import microstamp.step2.service.ControllerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/controllers")
@Tag(name = "Controller")
public class ControllerController {

    @Autowired
    private ControllerService controllerService;

    @GetMapping
    public ResponseEntity<List<Controller>> findAll() {
        return new ResponseEntity<>(controllerService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Controller> findById(@PathVariable long id) {
        return new ResponseEntity<>(controllerService.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = {"controlstructure/{id}"})
    public ResponseEntity<List<Controller>> findByControlStructureId(@PathVariable long id) {
        return new ResponseEntity<>(controllerService.findByControlStructureId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Controller> create(@RequestBody ControllerDto controllerDto) {
        return new ResponseEntity<>(controllerService.create(controllerDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") long id, @RequestBody ControllerDto controllerDto) {
        controllerService.update(id, controllerDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Void> delete(@PathVariable long id) {
        controllerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
