package microstamp.step2.controller;

import microstamp.step2.dto.VariableDto;
import microstamp.step2.data.*;
import microstamp.step2.repository.ComponentRepository;
import microstamp.step2.repository.VariableRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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
    private VariableRepository variableRepository;

    @Autowired
    private ComponentRepository componentRepository;

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
    public Variable create(@RequestBody VariableDto variableDto){
        Variable variable = new Variable();
        variable.setName(variableDto.getName());

        Optional<Component> c = componentRepository.findById(variableDto.getComponentId());
        c.get().getVariables().add(variable);
        componentRepository.save(c.get());
        return variable;
    }

    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody VariableDto variableDto) {
        return variableRepository.findById(id)
                .map(record -> {
                    record.setName(variableDto.getName());
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
