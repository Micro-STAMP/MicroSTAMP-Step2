package microstamp.step2.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import microstamp.step2.data.ControlledProcess;
import microstamp.step2.dto.ControlledProcessDto;
import microstamp.step2.exception.Step2NotFoundException;
import microstamp.step2.service.ControlledProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/controlled-processes")
@Tag(name = "ControlledProcess")
public class ControlledProcessController {

    @Autowired
    private ControlledProcessService controlledProcessService;

    @GetMapping
    public ResponseEntity<List<ControlledProcess>> findAll() {
        return new ResponseEntity<>(controlledProcessService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<ControlledProcess> findById(@PathVariable long id) throws Step2NotFoundException {
        return new ResponseEntity<>(controlledProcessService.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = {"control-structure/{id}"})
    public ResponseEntity<List<ControlledProcess>> findByControlStructureId(@PathVariable long id) {
        return new ResponseEntity<>(controlledProcessService.findByControlStructureId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ControlledProcess> insert(@Valid @RequestBody ControlledProcessDto controlledProcessDto) throws Step2NotFoundException {
        return new ResponseEntity<>(controlledProcessService.insert(controlledProcessDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") long id, @Valid @RequestBody ControlledProcessDto controlledProcessDto) throws Step2NotFoundException {
        controlledProcessService.update(id, controlledProcessDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Void> delete(@PathVariable long id) throws Step2NotFoundException {
        controlledProcessService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
