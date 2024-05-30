package microstamp.step2.controller;

import microstamp.step2.dto.LabelDto;
import microstamp.step2.data.*;
import microstamp.step2.repository.ConnectionRepository;
import microstamp.step2.repository.LabelRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import microstamp.step2.service.ConnectionService;
import microstamp.step2.service.LabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/labels")
@Tag(name = "Label")
public class LabelController {

    @Autowired
    private LabelService labelService;

    @GetMapping
    public List findAll(){
        return labelService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findLabelById(@PathVariable long id){
        return new ResponseEntity<>(labelService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public Label create(@RequestBody LabelDto labelDto){
        return labelService.create(labelDto);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody LabelDto labelDto) {
        labelService.update(id, labelDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        labelService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
