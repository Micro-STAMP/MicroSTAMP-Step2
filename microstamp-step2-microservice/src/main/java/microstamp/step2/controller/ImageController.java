package microstamp.step2.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import microstamp.step2.data.Image;
import microstamp.step2.exception.Step2NotFoundException;
import microstamp.step2.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/images")
@Tag(name = "Image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @GetMapping
    public ResponseEntity<List<Image>> findAll() {
        return new ResponseEntity<>(imageService.findAll(), HttpStatus.OK);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity<Image> findById(@PathVariable long id) throws Step2NotFoundException {
        return new ResponseEntity<>(imageService.findById(id), HttpStatus.OK);
    }

    @GetMapping(path = {"control-structure/{id}"})
    public ResponseEntity<List<Image>> findByControlStructureId(@PathVariable long id) {
        return new ResponseEntity<>(imageService.findByControlStructureId(id), HttpStatus.OK);
    }

    @PostMapping(value = "control-structure/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Image> insert(@PathVariable("id") long controlStructureId, @RequestParam("file") MultipartFile file) throws Exception {
        return new ResponseEntity<>(imageService.insert(controlStructureId, file), HttpStatus.CREATED);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Void> delete(@PathVariable long id) throws Exception {
        imageService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
