package microstamp.step2.service;

import microstamp.step2.data.Variable;
import microstamp.step2.dto.VariableDto;
import microstamp.step2.exception.Step2NotFoundException;
import microstamp.step2.repository.ComponentRepository;
import microstamp.step2.repository.VariableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class VariableService {

    @Autowired
    private VariableRepository variableRepository;

    @Autowired
    private ComponentRepository componentRepository;

    public List<Variable> findAll() {
        return variableRepository.findAll();
    }

    public Variable findById(long id) throws Step2NotFoundException {
        return variableRepository.findById(id)
                .orElseThrow(() -> new Step2NotFoundException("Variable not found with id: " + id));
    }

    public List<Variable> findByControlStructureId(long id) {
        List<microstamp.step2.data.Component> components = componentRepository.findByControlStructureId(id);

        return components.stream()
                .filter(c -> !c.getVariables().isEmpty())
                .flatMap(c -> variableRepository.findByComponentId(c.getId()).stream())
                .collect(Collectors.toList());
    }

    public Variable insert(VariableDto variableDto) throws Step2NotFoundException {
        microstamp.step2.data.Component component = componentRepository.findById(variableDto.getComponentId())
                .orElseThrow(() -> new Step2NotFoundException("Component not found with id: " + variableDto.getComponentId()));

        Variable variable = new Variable();
        variable.setName(variableDto.getName());

        component.getVariables().add(variable);
        componentRepository.save(component);

        return variable;
    }

    public void update(long id, VariableDto variableDto) throws Step2NotFoundException {
        Variable variable = variableRepository.findById(id)
                .orElseThrow(() -> new Step2NotFoundException("Variable not found with id: " + id));

        variable.setName(variableDto.getName());

        variableRepository.save(variable);
    }

    public void delete(long id) throws Step2NotFoundException {
        Variable variable = variableRepository.findById(id)
                .orElseThrow(() -> new Step2NotFoundException("Variable not found with id: " + id));
        variableRepository.deleteById(variable.getId());
    }
}
