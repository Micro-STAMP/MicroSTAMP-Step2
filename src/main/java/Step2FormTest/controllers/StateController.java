package Step2FormTest.controllers;

import Step2FormTest.domain.StateDomain;
import Step2FormTest.models.*;
import Step2FormTest.repositories.StateRepository;
import Step2FormTest.repositories.VariableRepository;
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
    private final StateRepository stateRepository;

    @Autowired
    private final VariableRepository variableRepository;

    @Autowired
    public StateController(StateRepository stateRepository, VariableRepository variableRepository) {
        this.stateRepository = stateRepository;
        this.variableRepository = variableRepository;
    }

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
    public State create(@RequestBody StateDomain stateDomain){
        State state = new State();
        state.setName(stateDomain.getName());

        Optional<Variable> v = variableRepository.findById(stateDomain.getVariable_id());
        v.get().getStates().add(state);
        variableRepository.save(v.get());
        return state;
    }

    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody StateDomain stateDomain) {
        return stateRepository.findById(id)
                .map(record -> {
                    record.setName(stateDomain.getName());
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
