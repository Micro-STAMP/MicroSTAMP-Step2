package microstamp.step2.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import microstamp.step2.data.Image;
import microstamp.step2.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/images")
@Tag(name = "Image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping(value = "/{id}")
    public ResponseEntity<Image> insert(@PathVariable("id") long controlStructureId, @RequestParam("image") MultipartFile multipartFile) throws Exception {
        return new ResponseEntity<>(imageService.insert(controlStructureId, multipartFile), HttpStatus.CREATED);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<Void> delete(@PathVariable long id) throws Exception {
        imageService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
