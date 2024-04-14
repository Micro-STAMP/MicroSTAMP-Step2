package Step2FormTest.controllers;

import Step2FormTest.configuration.MyUserDetails;
import Step2FormTest.domain.ControlStructureDomain;
import Step2FormTest.models.*;
import Step2FormTest.repositories.ComponentRepository;
import Step2FormTest.repositories.ControlStructureRepository;
import Step2FormTest.repositories.ImageRepository;
import Step2FormTest.repositories.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
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
    private final ControlStructureRepository controlStructureRepository;

    @Autowired
    private final ComponentRepository componentRepository;

    @Autowired
    private final ImageRepository imageRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    public ControlStructureController(ControlStructureRepository controlStructureRepository, ComponentRepository componentRepository, ImageRepository imageRepository, UserRepository userRepository) {
        this.controlStructureRepository = controlStructureRepository;
        this.componentRepository = componentRepository;
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
    }

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
    public ControlStructure create(@RequestBody ControlStructureDomain controlStructureDomain){
        ControlStructure controlStructure = new ControlStructure();
        controlStructure.setName(controlStructureDomain.getName());
        controlStructure.getComponents().add(new Environment());

        Optional<User> user = userRepository.findById(controlStructureDomain.getUser_id());
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
        String deleteDir = "Step2FormTest/src/main/resources/static/cs-images/" + id + "/";
        Path uploadDeletePath;
        for(Image i : imageRepository.findImagesByControlStructureId(id)) {
            uploadDeletePath = Paths.get(deleteDir + i.getName());
            Files.deleteIfExists(uploadDeletePath);
        }
        uploadDeletePath = Paths.get(deleteDir);
        Files.deleteIfExists(uploadDeletePath);
    }
}
