package microstamp.step2.service;

import microstamp.step2.data.Variable;
import microstamp.step2.dto.VariableDto;
import microstamp.step2.repository.ComponentRepository;
import microstamp.step2.repository.VariableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class VariableService {

    @Autowired
    private VariableRepository variableRepository;

    @Autowired
    private ComponentRepository componentRepository;

    public List<Variable> findAll(){
        return variableRepository.findAll();
    }

    public Variable findById(long id){
        return variableRepository.findById(id)
                .orElseThrow();
    }

    public List<Variable> findByControlStructureId(long id){
        List<microstamp.step2.data.Component> components = componentRepository.findComponentsByControlStructureId(id);
        List<Variable> variables = new ArrayList<>();
        for(microstamp.step2.data.Component c : components){
            if(!c.getVariables().isEmpty()){
                variables.addAll(variableRepository.findVariablesByComponentId(c.getId()));
            }
        }
        return variables;
    }

    public Variable create(VariableDto variableDto) {
        Variable variable = new Variable();
        variable.setName(variableDto.getName());

        Optional<microstamp.step2.data.Component> c = componentRepository.findById(variableDto.getComponentId());
        c.get().getVariables().add(variable);
        componentRepository.save(c.get());
        return variable;
    }

    public void update(long id, VariableDto variableDto){
        variableRepository.findById(id)
                .map(record -> {
                    record.setName(variableDto.getName());
                    Variable updated = variableRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElseThrow();
    }

    public void delete(long id){
        variableRepository.findById(id)
                .map(record -> {
                    variableRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElseThrow();
    }
}
