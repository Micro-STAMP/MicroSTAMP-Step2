package Step2FormTest.controllers;


import Step2FormTest.domain.ControlledProcessDomain;
import Step2FormTest.models.Component;
import Step2FormTest.models.ControlStructure;
import Step2FormTest.models.ControlledProcess;
import Step2FormTest.models.Controller;
import Step2FormTest.repositories.ComponentRepository;
import Step2FormTest.repositories.ControlStructureRepository;
import Step2FormTest.repositories.ControlledProcessRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/controlledProcesses")
@Tag(name = "ControlledProcess")
public class ControlledProcessController {

    @Autowired
    private final ControlledProcessRepository controlledProcessRepository;

    @Autowired
    private final ComponentRepository componentRepository;

    @Autowired
    private final ControlStructureRepository controlStructureRepository;

    @Autowired
    public ControlledProcessController(ControlledProcessRepository controlledProcessRepository, ComponentRepository componentRepository, ControlStructureRepository controlStructureRepository) {
        this.controlledProcessRepository = controlledProcessRepository;
        this.componentRepository = componentRepository;
        this.controlStructureRepository = controlStructureRepository;
    }

    @GetMapping
    public List findAll(){
        return controlledProcessRepository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id){
        return controlledProcessRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = {"cs/{id}"})
    public List findByControlStructureId(@PathVariable long id){
        return controlledProcessRepository.findControlledProcessesByControlStructureId(id);
    }

    @PostMapping
    public ControlledProcess create(@RequestBody ControlledProcessDomain controlledProcessDomain){
        ControlledProcess controlledProcess = new ControlledProcess();
        controlledProcess.setName(controlledProcessDomain.getName());
        try {
            Optional<Component> father = componentRepository.findById(controlledProcessDomain.getFather_id());
            controlledProcess.setFather(father.get());
            father.get().setControlStructure(true);
        }catch (Exception ex){
            controlledProcess.setFather(null);
        }
        controlledProcess.setBorder(controlledProcessDomain.getBorder());
        controlledProcess.setIsVisible(controlledProcessDomain.getIsVisible());
        Optional<ControlStructure> c1 = controlStructureRepository.findById(controlledProcessDomain.getControl_structure_id());
        c1.get().getComponents().add(controlledProcess);
        controlStructureRepository.save(c1.get());
        return controlledProcess;
    }

    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody ControlledProcessDomain controlledProcessDomain) {
        if(controlledProcessDomain.getFather_id() != null) {
            if(controlledProcessDomain.getType() != "ControlledProcess")
                componentRepository.updateComponentType(id,controlledProcessDomain.getType());
            return componentRepository.findById(id)
                    .map(record -> {
                        record.setName(controlledProcessDomain.getName());
                        record.setBorder(controlledProcessDomain.getBorder());
                        record.setFather(componentRepository.findById(controlledProcessDomain.getFather_id()).get());
                        record.setIsVisible(controlledProcessDomain.getIsVisible());
                        Component updated = componentRepository.save(record);
                        return ResponseEntity.ok().body(updated);
                    }).orElse(ResponseEntity.notFound().build());
        }else{
            if(controlledProcessDomain.getType() != "ControlledProcess")
                componentRepository.updateComponentType(id,controlledProcessDomain.getType());
            return componentRepository.findById(id)
                    .map(record -> {
                        record.setName(controlledProcessDomain.getName());
                        record.setBorder(controlledProcessDomain.getBorder());
                        record.setFather(null);
                        record.setIsVisible(controlledProcessDomain.getIsVisible());
                        Component updated = componentRepository.save(record);
                        return ResponseEntity.ok().body(updated);
                    }).orElse(ResponseEntity.notFound().build());
        }
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        return controlledProcessRepository.findById(id)
                .map(record -> {
                    controlledProcessRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
