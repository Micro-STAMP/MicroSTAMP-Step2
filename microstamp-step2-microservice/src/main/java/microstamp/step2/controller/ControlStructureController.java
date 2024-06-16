package microstamp.step2.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import microstamp.step2.data.ControlStructure;
import microstamp.step2.dto.ControlStructureDto;
import microstamp.step2.exception.Step2NotFoundException;
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
    public ResponseEntity<List<ControlStructure>> findAll() {
        return new ResponseEntity<>(controlStructureService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<ControlStructure> findById(@PathVariable long id) throws Step2NotFoundException {
        return new ResponseEntity<>(controlStructureService.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = {"user/{id}"})
    public ResponseEntity<List<ControlStructure>> findByUserId(@PathVariable long id) {
        return new ResponseEntity<>(controlStructureService.findByUserId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ControlStructure> insert(@RequestBody ControlStructureDto controlStructureDto) throws Step2NotFoundException {
        return new ResponseEntity<>(controlStructureService.insert(controlStructureDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") long id, @RequestBody ControlStructureDto controlStructureDto) throws Step2NotFoundException {
        controlStructureService.update(id, controlStructureDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Void> delete(@PathVariable long id) throws Exception {
        controlStructureService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
