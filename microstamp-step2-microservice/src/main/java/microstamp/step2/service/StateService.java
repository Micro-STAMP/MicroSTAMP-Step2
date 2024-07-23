package microstamp.step2.service;

import microstamp.step2.data.State;
import microstamp.step2.data.Variable;
import microstamp.step2.dto.StateDto;
import microstamp.step2.exception.Step2NotFoundException;
import microstamp.step2.repository.StateRepository;
import microstamp.step2.repository.VariableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StateService {

    @Autowired
    private StateRepository stateRepository;

    @Autowired
    private VariableRepository variableRepository;

    public List<State> findAll() {
        return stateRepository.findAll();
    }

    public State findById(long id) throws Step2NotFoundException {
        return stateRepository.findById(id)
                .orElseThrow(() -> new Step2NotFoundException("State not found with id: " + id));
    }

    public State insert(StateDto stateDto) throws Step2NotFoundException {
        Variable variable = variableRepository.findById(stateDto.getVariableId())
                .orElseThrow(() -> new Step2NotFoundException("Variable not found with id: " + stateDto.getVariableId()));

        State state = new State();
        state.setName(stateDto.getName());

        variable.getStates().add(state);
        variableRepository.save(variable);

        return state;
    }

    public void update(long id, StateDto stateDto) throws Step2NotFoundException {
        State state = stateRepository.findById(id)
                .orElseThrow(() -> new Step2NotFoundException("State not found with id: " + id));

        state.setName(stateDto.getName());

        stateRepository.save(state);
    }

    public void delete(long id) throws Step2NotFoundException {
        State state = stateRepository.findById(id)
                .orElseThrow(() -> new Step2NotFoundException("State not found with id: " + id));
        stateRepository.deleteById(state.getId());
    }
}
