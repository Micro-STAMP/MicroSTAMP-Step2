package microstamp.step2.controller;

import microstamp.step2.dto.ResponsibilityDto;
import microstamp.step2.data.*;
import microstamp.step2.repository.ComponentRepository;
import microstamp.step2.repository.ResponsibilitiesRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/responsibilities")
@Tag(name = "Responsibility")
public class ResponsibilityController {

    @Autowired
    private final ResponsibilitiesRepository responsibilitiesRepository;

    @Autowired
    private final ComponentRepository componentRepository;

    @Autowired
    public ResponsibilityController(ResponsibilitiesRepository responsibilitiesRepository, ComponentRepository componentRepository) {
        this.responsibilitiesRepository = responsibilitiesRepository;
        this.componentRepository = componentRepository;
    }

    @GetMapping
    public List findAll(){
        return responsibilitiesRepository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findResponsibilitiesById(@PathVariable long id){
        return responsibilitiesRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Responsibility create(@RequestBody ResponsibilityDto responsibilityDto){
        Responsibility responsibility = new Responsibility();
        responsibility.setResponsibility(responsibilityDto.getResponsibility());
        responsibility.setSystemSafetyConstraintAssociated(responsibilityDto.getSystemSafetyConstraintAssociated());

        Optional<Component> c = componentRepository.findById(responsibilityDto.getComponent_id());

        if(c.get().getType().equals("Controller")) {
            Controller controller = (Controller) c.get();
            controller.getResponsibilities().add(responsibility);
        }else if(c.get().getType().equals("ControlledProcess")){
            ControlledProcess controlledProcess = (ControlledProcess) c.get();
            controlledProcess.getResponsibilities().add(responsibility);
        }
        componentRepository.save(c.get());
        return responsibility;
    }

    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody ResponsibilityDto responsibilityDto) {
        return responsibilitiesRepository.findById(id)
                .map(record -> {
                    record.setResponsibility(responsibilityDto.getResponsibility());
                    record.setSystemSafetyConstraintAssociated(responsibilityDto.getSystemSafetyConstraintAssociated());
                    Responsibility updated = responsibilitiesRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        return responsibilitiesRepository.findById(id)
                .map(record -> {
                    responsibilitiesRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
