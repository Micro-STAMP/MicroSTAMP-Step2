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
    public List findAll() {
        return labelService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findLabelById(@PathVariable long id) {
        return new ResponseEntity<>(labelService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public Label create(@RequestBody LabelDto labelDto) {
        return labelService.create(labelDto);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody LabelDto labelDto) {
        labelService.update(id, labelDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> delete(@PathVariable long id) {
        labelService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
