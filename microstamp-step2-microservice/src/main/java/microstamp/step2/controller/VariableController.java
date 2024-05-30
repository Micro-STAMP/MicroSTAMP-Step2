package microstamp.step2.controller;

import microstamp.step2.dto.VariableDto;
import microstamp.step2.data.*;
import microstamp.step2.repository.ComponentRepository;
import microstamp.step2.repository.VariableRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import microstamp.step2.service.ComponentService;
import microstamp.step2.service.VariableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/variables")
@Tag(name = "Variable")
public class VariableController {

    @Autowired
    private VariableService variableService;

    @GetMapping
    public List findAll(){
        return variableService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id){
        return new ResponseEntity(variableService.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = {"cs/{id}"})
    public List findByControlStructureId(@PathVariable long id){
        return variableService.findByControlStructureId(id);
    }

    @PostMapping
    public Variable create(@RequestBody VariableDto variableDto){
        return variableService.create(variableDto);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody VariableDto variableDto) {
        variableService.update(id, variableDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        variableService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
