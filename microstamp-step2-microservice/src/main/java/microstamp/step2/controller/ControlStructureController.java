package microstamp.step2.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import microstamp.step2.data.ControlStructure;
import microstamp.step2.dto.ControlStructureDto;
import microstamp.step2.service.ControlStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/controlstructures")
@Tag(name = "ControlStructure")
public class ControlStructureController {

    @Autowired
    private ControlStructureService controlStructureService;

    @GetMapping
    public List findAll() {
        return controlStructureService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id) {
        return new ResponseEntity<>(controlStructureService.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = {"user/{id}"})
    public List findByUserId(@PathVariable long id) {
        return controlStructureService.findByUserId(id);
    }

    @PostMapping
    public ControlStructure create(@RequestBody ControlStructureDto controlStructureDto) {
        return controlStructureService.create(controlStructureDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody ControlStructureDto controlStructureDto) {
        controlStructureService.update(id, controlStructureDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> delete(@PathVariable long id) {
        controlStructureService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
