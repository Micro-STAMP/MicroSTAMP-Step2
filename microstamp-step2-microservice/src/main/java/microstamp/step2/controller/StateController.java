package microstamp.step2.controller;

import microstamp.step2.dto.StateDto;
import microstamp.step2.data.*;
import microstamp.step2.repository.StateRepository;
import microstamp.step2.repository.VariableRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/states")
@Tag(name = "State")
public class StateController {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private VariableRepository variableRepository;

    @GetMapping
    public List findAll(){
        return stateRepository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findStateById(@PathVariable long id){
        return stateRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public State create(@RequestBody StateDto stateDto){
        State state = new State();
        state.setName(stateDto.getName());

        Optional<Variable> v = variableRepository.findById(stateDto.getVariableId());
        v.get().getStates().add(state);
        variableRepository.save(v.get());
        return state;
    }

    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody StateDto stateDto) {
        return stateRepository.findById(id)
                .map(record -> {
                    record.setName(stateDto.getName());
                    State updated = stateRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        return stateRepository.findById(id)
                .map(record -> {
                    stateRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
