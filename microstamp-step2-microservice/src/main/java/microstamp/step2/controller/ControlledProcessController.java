package microstamp.step2.controller;


import microstamp.step2.dto.ControlledProcessDto;
import microstamp.step2.data.Component;
import microstamp.step2.data.ControlStructure;
import microstamp.step2.data.ControlledProcess;
import microstamp.step2.data.Controller;
import microstamp.step2.repository.ComponentRepository;
import microstamp.step2.repository.ControlStructureRepository;
import microstamp.step2.repository.ControlledProcessRepository;
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
    public ControlledProcess create(@RequestBody ControlledProcessDto controlledProcessDto){
        ControlledProcess controlledProcess = new ControlledProcess();
        controlledProcess.setName(controlledProcessDto.getName());
        try {
            Optional<Component> father = componentRepository.findById(controlledProcessDto.getFather_id());
            controlledProcess.setFather(father.get());
            father.get().setIsControlStructure(true);
        }catch (Exception ex){
            controlledProcess.setFather(null);
        }
        controlledProcess.setBorder(controlledProcessDto.getBorder());
        controlledProcess.setIsVisible(controlledProcessDto.getIsVisible());
        Optional<ControlStructure> c1 = controlStructureRepository.findById(controlledProcessDto.getControl_structure_id());
        c1.get().getComponents().add(controlledProcess);
        controlStructureRepository.save(c1.get());
        return controlledProcess;
    }

    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody ControlledProcessDto controlledProcessDto) {
        if(controlledProcessDto.getFather_id() != null) {
            if(controlledProcessDto.getType() != "ControlledProcess")
                componentRepository.updateComponentType(id,controlledProcessDto.getType());
            return componentRepository.findById(id)
                    .map(record -> {
                        record.setName(controlledProcessDto.getName());
                        record.setBorder(controlledProcessDto.getBorder());
                        record.setFather(componentRepository.findById(controlledProcessDto.getFather_id()).get());
                        record.setIsVisible(controlledProcessDto.getIsVisible());
                        Component updated = componentRepository.save(record);
                        return ResponseEntity.ok().body(updated);
                    }).orElse(ResponseEntity.notFound().build());
        }else{
            if(controlledProcessDto.getType() != "ControlledProcess")
                componentRepository.updateComponentType(id,controlledProcessDto.getType());
            return componentRepository.findById(id)
                    .map(record -> {
                        record.setName(controlledProcessDto.getName());
                        record.setBorder(controlledProcessDto.getBorder());
                        record.setFather(null);
                        record.setIsVisible(controlledProcessDto.getIsVisible());
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
