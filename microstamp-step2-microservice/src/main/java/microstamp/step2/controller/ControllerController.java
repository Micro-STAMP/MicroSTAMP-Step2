package microstamp.step2.controller;

import microstamp.step2.dto.ControllerDto;
import microstamp.step2.data.*;
import microstamp.step2.repository.ComponentRepository;
import microstamp.step2.repository.ControlStructureRepository;
import microstamp.step2.repository.ControllerRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/controllers")
@Tag(name = "Controller")
public class ControllerController {

    @Autowired
    private ControllerRepository controllerRepository;

    @Autowired
    private ComponentRepository componentRepository;

    @Autowired
    private ControlStructureRepository controlStructureRepository;

    @GetMapping
    public List findAll(){
        return controllerRepository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id){
        return controllerRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = {"cs/{id}"})
    public List findByControlStructureId(@PathVariable long id){
        return controllerRepository.findControllersByControlStructureId(id);
    }

    @PostMapping
    public Controller create(@RequestBody ControllerDto controllerDto){
        Controller controller = new Controller();
        controller.setName(controllerDto.getName());
        try {
            Optional<Component> father = componentRepository.findById(controllerDto.getFatherId());
            controller.setFather(father.get());
            father.get().setIsControlStructure(true);
        }catch (Exception ex){
            controller.setFather(null);
        }
        controller.setBorder(controllerDto.getBorder());
        controller.setIsVisible(controllerDto.getIsVisible());
        Optional<ControlStructure> c1 = controlStructureRepository.findById(controllerDto.getControlStructureId());
        c1.get().getComponents().add(controller);
        controlStructureRepository.save(c1.get());
        return controller;
    }

    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody ControllerDto controllerDto) {
        if(controllerDto.getFatherId() != null) {
            if(controllerDto.getType() != "Controller")
                componentRepository.updateComponentType(id,controllerDto.getType());
            return componentRepository.findById(id)
                    .map(record -> {
                        record.setName(controllerDto.getName());
                        record.setBorder(controllerDto.getBorder());
                        record.setFather(componentRepository.findById(controllerDto.getFatherId()).get());
                        record.setIsVisible(controllerDto.getIsVisible());
                        Component updated = componentRepository.save(record);
                        return ResponseEntity.ok().body(updated);
                    }).orElse(ResponseEntity.notFound().build());
        }else{
            if(controllerDto.getType() != "Controller")
                componentRepository.updateComponentType(id,controllerDto.getType());
            return componentRepository.findById(id)
                    .map(record -> {
                        record.setName(controllerDto.getName());
                        record.setBorder(controllerDto.getBorder());
                        record.setFather(null);
                        record.setIsVisible(controllerDto.getIsVisible());
                        Component updated = componentRepository.save(record);
                        return ResponseEntity.ok().body(updated);
                    }).orElse(ResponseEntity.notFound().build());
        }
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        return controllerRepository.findById(id)
                .map(record -> {
                    controllerRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
