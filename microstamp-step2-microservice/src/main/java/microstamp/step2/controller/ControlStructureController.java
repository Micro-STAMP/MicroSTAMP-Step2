package microstamp.step2.controller;

import microstamp.step2.dto.ControlStructureDto;
import microstamp.step2.data.*;
import microstamp.step2.repository.ComponentRepository;
import microstamp.step2.repository.ControlStructureRepository;
import microstamp.step2.repository.ImageRepository;
import microstamp.step2.repository.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import microstamp.step2.service.ComponentService;
import microstamp.step2.service.ControlStructureService;
import microstamp.step2.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.nio.file.Files;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/controlstructures")
@Tag(name = "ControlStructure")
public class ControlStructureController {

    @Autowired
    private ControlStructureService controlStructureService;

    @GetMapping
    public List findAll(){
        return controlStructureService.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id){
        return new ResponseEntity<>(controlStructureService.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = {"user/{id}"})
    public List findByUserId(@PathVariable long id){
        return controlStructureService.findByUserId(id);
    }

    @PostMapping
    public ControlStructure create(@RequestBody ControlStructureDto controlStructureDto){
        return controlStructureService.create(controlStructureDto);
    }

    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody ControlStructureDto controlStructureDto) {
        controlStructureService.update(id,controlStructureDto);
        return new ResponseEntity(HttpStatus.OK);
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        controlStructureService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
