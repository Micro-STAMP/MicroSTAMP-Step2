package microstamp.step2.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import microstamp.step2.data.Component;
import microstamp.step2.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/components")
@Tag(name = "Component")
public class ComponentController {

    @Autowired
    private ComponentService componentService;

    @GetMapping
    public ResponseEntity<List<Component>> findAll() {
        return new ResponseEntity<>(componentService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Component> findById(@PathVariable long id) {
        return new ResponseEntity<>(componentService.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = {"controlstructure/{id}"})
    public ResponseEntity<List<Component>> findByControlStructureId(@PathVariable long id) {
        return new ResponseEntity<>(componentService.findByControlStructureId(id), HttpStatus.OK);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Void> delete(@PathVariable long id) {
        componentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = {"listComponentsAndConnectionsToBeDeleted/{id}"})
    public ResponseEntity<List> getComponentsAndConnections(@PathVariable long id) {
        List list = componentService.getComponentsAndConnectionsRecursive(id);
        list.remove(componentService.findById(id));
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(path = {"listComponentsChildren/{id}"})
    public ResponseEntity<List> getComponentsChildren(@PathVariable long id) {
        List list = componentService.getComponentsRecursive(id);
        list.remove(componentService.findById(id));

        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
