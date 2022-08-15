package Step2FormTest.controllers;

import Step2FormTest.domain.ResponsibilityDomain;
import Step2FormTest.models.*;
import Step2FormTest.repositories.ComponentRepository;
import Step2FormTest.repositories.ResponsibilitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/responsibilities")
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
    public Responsibility create(@RequestBody ResponsibilityDomain responsibilityDomain){
        Responsibility responsibility = new Responsibility();
        responsibility.setResponsibility(responsibilityDomain.getResponsibility());
        responsibility.setSystemSafetyConstraintAssociated(responsibilityDomain.getSystemSafetyConstraintAssociated());

        Optional<Component> c = componentRepository.findById(responsibilityDomain.getComponent_id());

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
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody ResponsibilityDomain responsibilityDomain) {
        return responsibilitiesRepository.findById(id)
                .map(record -> {
                    record.setResponsibility(responsibilityDomain.getResponsibility());
                    record.setSystemSafetyConstraintAssociated(responsibilityDomain.getSystemSafetyConstraintAssociated());
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
