package microstamp.step2.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import microstamp.step2.data.Variable;
import microstamp.step2.dto.VariableDto;
import microstamp.step2.exception.Step2NotFoundException;
import microstamp.step2.service.VariableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/variables")
@Tag(name = "Variable")
public class VariableController {

    @Autowired
    private VariableService variableService;

    @GetMapping
    public ResponseEntity<List<Variable>> findAll() {
        return new ResponseEntity<>(variableService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Variable> findById(@PathVariable long id) throws Step2NotFoundException {
        return new ResponseEntity<>(variableService.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = {"control-structure/{id}"})
    public ResponseEntity<List<Variable>> findByControlStructureId(@PathVariable long id) {
        return new ResponseEntity<>(variableService.findByControlStructureId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Variable> insert(@RequestBody VariableDto variableDto) throws Step2NotFoundException {
        return new ResponseEntity<>(variableService.insert(variableDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") long id, @RequestBody VariableDto variableDto) throws Step2NotFoundException {
        variableService.update(id, variableDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Void> delete(@PathVariable long id) throws Step2NotFoundException {
        variableService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
