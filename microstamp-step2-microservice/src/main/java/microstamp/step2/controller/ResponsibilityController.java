package microstamp.step2.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import microstamp.step2.data.Responsibility;
import microstamp.step2.dto.ResponsibilityDto;
import microstamp.step2.service.ResponsibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/responsibilities")
@Tag(name = "Responsibility")
public class ResponsibilityController {

    @Autowired
    private ResponsibilityService responsibilityService;

    @GetMapping
    public ResponseEntity<List<Responsibility>> findAll() {
        return new ResponseEntity<>(responsibilityService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Responsibility> findById(@PathVariable long id) {
        return new ResponseEntity<>(responsibilityService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Responsibility> create(@RequestBody ResponsibilityDto responsibilityDto) {
        return new ResponseEntity<>(responsibilityService.create(responsibilityDto), HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") long id, @RequestBody ResponsibilityDto responsibilityDto) {
        responsibilityService.update(id, responsibilityDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Void> delete(@PathVariable long id) {
        responsibilityService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
