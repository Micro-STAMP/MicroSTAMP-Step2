package Step2FormTest.controllers;

import Step2FormTest.domain.ControlStructureDomain;
import Step2FormTest.models.Component;
import Step2FormTest.models.ControlStructure;
import Step2FormTest.models.Environment;
import Step2FormTest.models.Image;
import Step2FormTest.repositories.ComponentRepository;
import Step2FormTest.repositories.ControlStructureRepository;
import Step2FormTest.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.nio.file.Files;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/controlstructures")
public class ControlStructureController {

    @Autowired
    private final ControlStructureRepository controlStructureRepository;

    @Autowired
    private final ComponentRepository componentRepository;

    @Autowired
    private final ImageRepository imageRepository;

    @Autowired
    public ControlStructureController(ControlStructureRepository controlStructureRepository, ComponentRepository componentRepository, ImageRepository imageRepository) {
        this.controlStructureRepository = controlStructureRepository;
        this.componentRepository = componentRepository;
        this.imageRepository = imageRepository;
    }

    @GetMapping
    public List findAll(){
        return controlStructureRepository.findAll();
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
        controlStructureRepository.save(controlStructure);
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
