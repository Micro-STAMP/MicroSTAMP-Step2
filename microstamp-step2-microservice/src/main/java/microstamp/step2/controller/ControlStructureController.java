package microstamp.step2.controller;

import microstamp.step2.dto.ControlStructureDto;
import microstamp.step2.data.*;
import microstamp.step2.repository.ComponentRepository;
import microstamp.step2.repository.ControlStructureRepository;
import microstamp.step2.repository.ImageRepository;
import microstamp.step2.repository.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
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
    private ControlStructureRepository controlStructureRepository;

    @Autowired
    private ComponentRepository componentRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List findAll(){
        return controlStructureRepository.findAll();
    }

    @GetMapping(path = {"user/{id}"})
    public List findByUserId(@PathVariable long id){
        return controlStructureRepository.findControlStructuresByUserId(id);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id){
        return controlStructureRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ControlStructure create(@RequestBody ControlStructureDto controlStructureDto){
        ControlStructure controlStructure = new ControlStructure();
        controlStructure.setName(controlStructureDto.getName());
        controlStructure.getComponents().add(new Environment());

        Optional<User> user = userRepository.findById(controlStructureDto.getUserId());
        user.get().getControlStructures().add(controlStructure);
        userRepository.save(user.get());

        return controlStructure;
    }

    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody ControlStructure controlStructure) {
        return controlStructureRepository.findById(id)
                .map(record -> {
                    record.setName(controlStructure.getName());
                    ControlStructure updated = controlStructureRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        try{
            deleteImages(id);
        }catch (Exception ex){
            System.out.println("\nException when deleting CS images: \n");
            ex.printStackTrace();
        }
        return controlStructureRepository.findById(id)
                .map(record -> {
                    controlStructureRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    public void deleteImages(long id) throws Exception{
        String deleteDir = "MicroSTAMP-Step2/microstamp-step2-microservice/src/main/resources/static/cs-images/" + id + "/";
        Path uploadDeletePath;
        for(Image i : imageRepository.findImagesByControlStructureId(id)) {
            uploadDeletePath = Paths.get(deleteDir + i.getName());
            Files.deleteIfExists(uploadDeletePath);
        }
        uploadDeletePath = Paths.get(deleteDir);
        Files.deleteIfExists(uploadDeletePath);
    }
}
