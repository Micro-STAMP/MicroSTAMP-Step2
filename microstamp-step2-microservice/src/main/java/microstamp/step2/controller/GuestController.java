package microstamp.step2.controller;


import microstamp.step2.repository.ComponentRepository;
import microstamp.step2.repository.ConnectionRepository;
import microstamp.step2.repository.ControlStructureRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/guests-request")
@Tag(name = "Guest")
public class GuestController {

    @Autowired
    private ControlStructureRepository controlStructureRepository;

    @Autowired
    private ComponentRepository componentRepository;

    @Autowired
    private ConnectionRepository connectionRepository;

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
