package Step2FormTest.controllers;


import Step2FormTest.repositories.ComponentRepository;
import Step2FormTest.repositories.ConnectionRepository;
import Step2FormTest.repositories.ControlStructureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/guests-request")
public class GuestController {

    @Autowired
    private final ControlStructureRepository controlStructureRepository;

    @Autowired
    private final ComponentRepository componentRepository;

    @Autowired
    private final ConnectionRepository connectionRepository;

    public GuestController(ControlStructureRepository controlStructureRepository, ComponentRepository componentRepository, ConnectionRepository connectionRepository) {
        this.controlStructureRepository = controlStructureRepository;
        this.componentRepository = componentRepository;
        this.connectionRepository = connectionRepository;
    }

    @GetMapping(path = {"controlstructures"})
    public List findControlStructures(){
        return controlStructureRepository.findControlStructuresByUserId(3);
    }

    @GetMapping(path = {"components/{id}"})
    public List findComponentsByControlStructureId(@PathVariable long id){
        return componentRepository.findComponentsByControlStructureId(id);
    }

    @GetMapping(path = {"connections/{id}"})
    public List findConnectionsByControlStructureId(@PathVariable long id){
        return connectionRepository.findConnectionsByControlStructureId(id);
    }
}
