package microstamp.step2.controller;


import microstamp.step2.repository.ComponentRepository;
import microstamp.step2.repository.ConnectionRepository;
import microstamp.step2.repository.ControlStructureRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import microstamp.step2.service.ComponentService;
import microstamp.step2.service.ConnectionService;
import microstamp.step2.service.ControlStructureService;
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
    private ControlStructureService controlStructureService;

    @Autowired
    private ComponentService componentService;

    @Autowired
    private ConnectionService connectionService;

    @GetMapping(path = {"controlstructures"})
    public List findControlStructures(){
        return controlStructureService.findByUserId(3);
    }

    @GetMapping(path = {"components/{id}"})
    public List findComponentsByControlStructureId(@PathVariable long id){
        return componentService.findByControlStructureId(id);
    }

    @GetMapping(path = {"connections/{id}"})
    public List findConnectionsByControlStructureId(@PathVariable long id){
        return connectionService.findByControlStructureId(id);
    }
}
