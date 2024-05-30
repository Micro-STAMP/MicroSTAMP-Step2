package microstamp.step2.service;

import microstamp.step2.data.State;
import microstamp.step2.data.Variable;
import microstamp.step2.dto.StateDto;
import microstamp.step2.repository.StateRepository;
import microstamp.step2.repository.VariableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private VariableRepository variableRepository;

    public List<State> findAll(){
        return stateRepository.findAll();
    }

    public State findById(long id){
        return stateRepository.findById(id)
                .orElseThrow();
    }

    public State create(StateDto stateDto){
        State state = new State();
        state.setName(stateDto.getName());

        Optional<Variable> v = variableRepository.findById(stateDto.getVariableId());
        v.get().getStates().add(state);
        variableRepository.save(v.get());
        return state;
    }

    public void update(long id, StateDto stateDto){
        stateRepository.findById(id)
                .map(record -> {
                    record.setName(stateDto.getName());
                    State updated = stateRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElseThrow();
    }

    public void delete(long id){
        stateRepository.findById(id)
                .map(record -> {
                    stateRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElseThrow();
    }

}
