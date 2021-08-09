package Step2FormTest.controllers;

import Step2FormTest.models.ControlStructure;
import Step2FormTest.models.Image;
import Step2FormTest.repositories.ControlStructureRepository;
import Step2FormTest.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageRepository imageRepository;

    private final ControlStructureRepository controlStructureRepository;

    @Autowired
    public ImageController(ImageRepository imageRepository, ControlStructureRepository controlStructureRepository){
        this.imageRepository = imageRepository;
        this.controlStructureRepository = controlStructureRepository;
    }

    @PostMapping(value="/{id}")
    public Image create(@PathVariable("id") long id, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        Image image = new Image();

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        image.setName(fileName);

        Optional<ControlStructure> c1 = controlStructureRepository.findById(id);
        c1.get().getImages().add(image);

        controlStructureRepository.save(c1.get());

        String uploadDir = "Step2FormTest/src/main/resources/cs-photos/" + id;

        saveFile(uploadDir, fileName, multipartFile);

        return image;
    }

    public static void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }

}
