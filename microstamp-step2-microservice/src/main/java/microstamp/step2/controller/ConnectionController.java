package microstamp.step2.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import microstamp.step2.data.Connection;
import microstamp.step2.dto.ConnectionDto;
import microstamp.step2.exception.Step2NotFoundException;
import microstamp.step2.service.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/connections")
@Tag(name = "Connection")
public class ConnectionController {

    @Autowired
    private ConnectionService connectionService;

    @GetMapping
    public ResponseEntity<List<Connection>> findAll() {
        return new ResponseEntity<>(connectionService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Connection> findById(@PathVariable long id) throws Step2NotFoundException {
        return new ResponseEntity<>(connectionService.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = {"controlstructure/{id}"})
    public ResponseEntity<List<Connection>> findByControlStructureId(@PathVariable long id) {
        return new ResponseEntity<>(connectionService.findByControlStructureId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Connection> insert(@RequestBody ConnectionDto connectionDto) throws Step2NotFoundException {
        return new ResponseEntity<>(connectionService.insert(connectionDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") long id, @RequestBody ConnectionDto connectionDto) throws Step2NotFoundException {
        connectionService.update(id, connectionDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Void> delete(@PathVariable long id) throws Step2NotFoundException {
        connectionService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
