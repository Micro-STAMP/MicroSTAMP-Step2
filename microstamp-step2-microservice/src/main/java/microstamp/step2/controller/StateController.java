package microstamp.step2.controller;

import microstamp.step2.dto.StateDto;
import microstamp.step2.data.*;
import microstamp.step2.repository.StateRepository;
import microstamp.step2.repository.VariableRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import microstamp.step2.service.StateService;
import microstamp.step2.service.VariableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/states")
@Tag(name = "State")
public class StateController {

    @Autowired
    private StateService stateService;

    @GetMapping
    public List findAll(){
        return stateService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id){
        return new ResponseEntity<>(stateService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public State create(@RequestBody StateDto stateDto){
        return stateService.create(stateDto);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody StateDto stateDto) {
        stateService.update(id, stateDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        stateService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
