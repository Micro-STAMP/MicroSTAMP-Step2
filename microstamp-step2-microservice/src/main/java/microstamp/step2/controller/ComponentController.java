package microstamp.step2.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import microstamp.step2.data.Component;
import microstamp.step2.dto.ComponentDependenciesDto;
import microstamp.step2.exception.Step2NotFoundException;
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
    public ResponseEntity<Component> findById(@PathVariable long id) throws Step2NotFoundException {
        return new ResponseEntity<>(componentService.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = {"control-structure/{id}"})
    public ResponseEntity<List<Component>> findByControlStructureId(@PathVariable long id) {
        return new ResponseEntity<>(componentService.findByControlStructureId(id), HttpStatus.OK);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Void> delete(@PathVariable long id) {
        componentService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = {"{id}/children"})
    public ResponseEntity<List<Component>> getComponentChildren(@PathVariable long id) throws Step2NotFoundException {
        List<Component> list = componentService.getComponentChildren(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping(path = {"{id}/dependencies"})
    public ResponseEntity<ComponentDependenciesDto> getComponentDependencies(@PathVariable long id) throws Step2NotFoundException {
        ComponentDependenciesDto list = componentService.getComponentDependencies(id);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
