package Step2FormTest.controllers;

import Step2FormTest.domain.VariableDomain;
import Step2FormTest.models.*;
import Step2FormTest.repositories.ComponentRepository;
import Step2FormTest.repositories.VariableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/variables")
public class VariableController {

    @Autowired
    private final VariableRepository variableRepository;

    @Autowired
    private final ComponentRepository componentRepository;

    @Autowired
    public VariableController(VariableRepository variableRepository, ComponentRepository componentRepository) {
        this.variableRepository = variableRepository;
        this.componentRepository = componentRepository;
    }

    @GetMapping
    public List findAll(){
        return variableRepository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findVariableById(@PathVariable long id){
        return variableRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = {"cs/{id}"})
    public List findByControlStructureId(@PathVariable long id){
        List<Component> components = componentRepository.findComponentsByControlStructureId(id);
        List<Variable> variables = new ArrayList<>();
        for(Component c : components){
            if(!c.getVariables().isEmpty()){
                variables.addAll(variableRepository.findVariablesByComponentId(c.getId()));
            }
        }
        return variables;
    }

    @PostMapping
    public Variable create(@RequestBody VariableDomain variableDomain){
        Variable variable = new Variable();
        variable.setName(variableDomain.getName());

        Optional<Component> c = componentRepository.findById(variableDomain.getComponent_id());
        c.get().getVariables().add(variable);
        componentRepository.save(c.get());
        return variable;
    }

    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody VariableDomain variableDomain) {
        return variableRepository.findById(id)
                .map(record -> {
                    record.setName(variableDomain.getName());
                    Variable updated = variableRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        return variableRepository.findById(id)
                .map(record -> {
                    variableRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
