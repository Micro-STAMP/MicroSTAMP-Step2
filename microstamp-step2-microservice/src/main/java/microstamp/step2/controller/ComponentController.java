package microstamp.step2.controller;

import microstamp.step2.data.Component;
import microstamp.step2.data.Connection;
import io.swagger.v3.oas.annotations.tags.Tag;
import microstamp.step2.service.ComponentService;
import microstamp.step2.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/components")
@Tag(name = "Component")
public class ComponentController {

    @Autowired
    private ComponentService componentService;

    @GetMapping
    public List findAll(){
        return componentService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id){
        return new ResponseEntity(componentService.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = {"cs/{id}"})
    public List findByControlStructureId(@PathVariable long id){
        return componentService.findByControlStructureId(id);
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        componentService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(path = {"listComponentsAndConnectionsToBeDeleted/{id}"})
    public List getComponentsAndConnections(@PathVariable long id){
        List list = componentService.getComponentsAndConnectionsRecursive(id);
        list.remove(componentService.findById(id));
        return list;
    }

    @GetMapping(path = {"listComponentsChildren/{id}"})
    public List getComponentsChildren(@PathVariable long id){
        List list = componentService.getComponentsRecursive(id);
        list.remove(componentService.findById(id));

        System.out.println(list);
        return list;
    }
}
