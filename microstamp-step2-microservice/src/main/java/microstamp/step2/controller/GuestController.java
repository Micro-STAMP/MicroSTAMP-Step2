package microstamp.step2.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import microstamp.step2.data.Component;
import microstamp.step2.data.Connection;
import microstamp.step2.data.ControlStructure;
import microstamp.step2.service.ComponentService;
import microstamp.step2.service.ConnectionService;
import microstamp.step2.service.ControlStructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/guests")
@Tag(name = "Guest")
public class GuestController {

    @Autowired
    private ControlStructureService controlStructureService;

    @Autowired
    private ComponentService componentService;

    @Autowired
    private ConnectionService connectionService;

    @GetMapping(path = {"controlstructures"})
    public ResponseEntity<List<ControlStructure>> findControlStructures() {
        return new ResponseEntity<>(controlStructureService.findByUserId(3), HttpStatus.OK);
    }

    @GetMapping(path = {"components/controlstructure/{id}"})
    public ResponseEntity<List<Component>> findComponentsByControlStructureId(@PathVariable long id) {
        return new ResponseEntity<>(componentService.findByControlStructureId(id), HttpStatus.OK);
    }

    @GetMapping(path = {"connections/controlstructure/{id}"})
    public ResponseEntity<List<Connection>> findConnectionsByControlStructureId(@PathVariable long id) {
        return new ResponseEntity<>(connectionService.findByControlStructureId(id), HttpStatus.OK);
    }
}
