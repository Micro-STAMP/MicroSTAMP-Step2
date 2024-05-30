package microstamp.step2.controller;

import microstamp.step2.dto.ResponsibilityDto;
import microstamp.step2.data.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import microstamp.step2.service.ComponentService;
import microstamp.step2.service.ResponsibilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/responsibilities")
@Tag(name = "Responsibility")
public class ResponsibilityController {

    @Autowired
    private ResponsibilityService responsibilityService;

    @GetMapping
    public List findAll(){
        return responsibilityService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id){
        return new ResponseEntity<>(responsibilityService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public Responsibility create(@RequestBody ResponsibilityDto responsibilityDto){
        return responsibilityService.create(responsibilityDto);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody ResponsibilityDto responsibilityDto) {
        responsibilityService.update(id, responsibilityDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        responsibilityService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }

}
