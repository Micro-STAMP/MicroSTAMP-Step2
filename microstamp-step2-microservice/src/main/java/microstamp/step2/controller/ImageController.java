package microstamp.step2.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import microstamp.step2.data.Image;
import microstamp.step2.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/images")
@Tag(name = "Image")
public class ImageController {

    @Autowired
    private ImageService imageService;

    @PostMapping(value = "/{id}")
    public Image create(@PathVariable("id") long controlStructureId, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        return imageService.create(controlStructureId, multipartFile);
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> delete(@PathVariable long id) {
        imageService.delete(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
