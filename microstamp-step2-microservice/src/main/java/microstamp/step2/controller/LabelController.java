package microstamp.step2.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import microstamp.step2.data.Label;
import microstamp.step2.dto.LabelDto;
import microstamp.step2.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/labels")
@Tag(name = "Label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @GetMapping
    public ResponseEntity<List<Label>> findAll() {
        return new ResponseEntity<>(labelService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Label> findById(@PathVariable long id) {
        return new ResponseEntity<>(labelService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Label> create(@RequestBody LabelDto labelDto) {
        return new ResponseEntity<>(labelService.create(labelDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") long id, @RequestBody LabelDto labelDto) {
        labelService.update(id, labelDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Void> delete(@PathVariable long id) {
        labelService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping(path = {"connection/{id}"})
    public ResponseEntity<List<Label>> findByConnectionId(@PathVariable long id) {
        return new ResponseEntity<>(labelService.findByConnectionId(id), HttpStatus.OK);
    }
}
