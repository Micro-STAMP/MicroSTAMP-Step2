package microstamp.step2.controller;

import microstamp.step2.dto.ConnectionDto;
import microstamp.step2.data.Component;
import microstamp.step2.data.Connection;
import microstamp.step2.data.ControlStructure;
import microstamp.step2.data.Label;
import io.swagger.v3.oas.annotations.tags.Tag;
import microstamp.step2.service.ComponentService;
import microstamp.step2.service.ConnectionService;
import microstamp.step2.service.ControlStructureService;
import microstamp.step2.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/connections")
@Tag(name = "Connection")
public class ConnectionController {

    @Autowired
    private ConnectionService connectionService;

    @GetMapping
    public List findAll(){
        return connectionService.findAll();
    }


    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id){
        return new ResponseEntity<>(connectionService.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = {"cs/{id}"})
    public List findByControlStructureId(@PathVariable long id){
        return connectionService.findByControlStructureId(id);
    }

    @PostMapping
    public Connection create(@RequestBody ConnectionDto connectionDto){
        return connectionService.create(connectionDto);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody ConnectionDto connectionDto) {
        connectionService.update(id,connectionDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        connectionService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(path = {"listLabelsToBeDeleted/{id}"})
    public List getLabelsToBeDeleted(@PathVariable long id){
        return connectionService.findLabelsByConnectionId(id);
    }

}
