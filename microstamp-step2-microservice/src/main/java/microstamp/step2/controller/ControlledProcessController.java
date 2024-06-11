package microstamp.step2.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import microstamp.step2.data.ControlledProcess;
import microstamp.step2.dto.ControlledProcessDto;
import microstamp.step2.service.ControlledProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/controlledprocesses")
@Tag(name = "ControlledProcess")
public class ControlledProcessController {

    @Autowired
    private ControlledProcessService controlledProcessService;

    @GetMapping
    public ResponseEntity<List<ControlledProcess>> findAll() {
        return new ResponseEntity<>(controlledProcessService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<ControlledProcess> findById(@PathVariable long id) {
        return new ResponseEntity<>(controlledProcessService.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = {"controlstructure/{id}"})
    public ResponseEntity<List<ControlledProcess>> findByControlStructureId(@PathVariable long id) {
        return new ResponseEntity<>(controlledProcessService.findByControlStructureId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ControlledProcess> create(@RequestBody ControlledProcessDto controlledProcessDto) {
        return new ResponseEntity<>(controlledProcessService.create(controlledProcessDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") long id, @RequestBody ControlledProcessDto controlledProcessDto) {
        controlledProcessService.update(id, controlledProcessDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Void> delete(@PathVariable long id) {
        controlledProcessService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
