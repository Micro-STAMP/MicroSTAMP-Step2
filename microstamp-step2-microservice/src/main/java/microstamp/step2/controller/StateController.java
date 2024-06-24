package microstamp.step2.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import microstamp.step2.data.State;
import microstamp.step2.dto.StateDto;
import microstamp.step2.exception.Step2NotFoundException;
import microstamp.step2.service.StateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/states")
@Tag(name = "State")
public class StateController {

    @Autowired
    private StateService stateService;

    @GetMapping
    public ResponseEntity<List<State>> findAll() {
        return new ResponseEntity<>(stateService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<State> findById(@PathVariable long id) throws Step2NotFoundException {
        return new ResponseEntity<>(stateService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<State> insert(@Valid @RequestBody StateDto stateDto) throws Step2NotFoundException {
        return new ResponseEntity<>(stateService.insert(stateDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") long id, @Valid @RequestBody StateDto stateDto) throws Step2NotFoundException {
        stateService.update(id, stateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Void> delete(@PathVariable long id) throws Step2NotFoundException {
        stateService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
