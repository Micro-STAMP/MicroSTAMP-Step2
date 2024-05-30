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
@RequestMapping("/controlledProcesses")
@Tag(name = "ControlledProcess")
public class ControlledProcessController {

    @Autowired
    private ControlledProcessService controlledProcessService;

    @GetMapping
    public List findAll() {
        return controlledProcessService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id) {
        return new ResponseEntity<>(controlledProcessService.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = {"cs/{id}"})
    public List findByControlStructureId(@PathVariable long id) {
        return controlledProcessService.findByControlStructureId(id);
    }

    @PostMapping
    public ControlledProcess create(@RequestBody ControlledProcessDto controlledProcessDto) {
        return controlledProcessService.create(controlledProcessDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody ControlledProcessDto controlledProcessDto) {
        controlledProcessService.update(id, controlledProcessDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> delete(@PathVariable long id) {
        controlledProcessService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
