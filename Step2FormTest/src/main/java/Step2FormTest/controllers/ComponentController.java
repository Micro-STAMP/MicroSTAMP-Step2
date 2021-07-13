package Step2FormTest.controllers;

import Step2FormTest.repositories.ComponentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/components")
public class ComponentController {

    @Autowired
    private final ComponentRepository componentRepository;

    @Autowired
    public ComponentController(ComponentRepository componentRepository) {
        this.componentRepository = componentRepository;
    }

    @GetMapping
    public List findAll(){
        return componentRepository.findAll();
    }

    //get Component by CS id
    @GetMapping(path = {"/{id}"})
    public List findByControlStructureId(@PathVariable long id){
        return componentRepository.findComponentsByControlStructureId(id);
    }

}
