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
    public List findAll() {
        return controllerService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id) {
        return new ResponseEntity<>(controllerService.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = {"controlstructure/{id}"})
    public List findByControlStructureId(@PathVariable long id) {
        return controllerService.findByControlStructureId(id);
    }

    @PostMapping
    public Controller create(@RequestBody ControllerDto controllerDto) {
        return controllerService.create(controllerDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody ControllerDto controllerDto) {
        controllerService.update(id, controllerDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> delete(@PathVariable long id) {
        controllerService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
