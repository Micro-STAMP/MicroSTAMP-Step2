package microstamp.step2.service;

import microstamp.step2.data.ControlStructure;
import microstamp.step2.data.Image;
import microstamp.step2.exception.Step2NotFoundException;
import microstamp.step2.repository.ControlStructureRepository;
import microstamp.step2.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Component
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ControlStructureRepository controlStructureRepository;

    public List<Image> findByControlStructureId(long id) {
        return imageRepository.findByControlStructureId(id);
    }

    public Image insert(long controlStructureId, MultipartFile multipartFile) throws Exception {
        if (multipartFile.isEmpty())
            throw new Exception("MultipartFile is empty");

        String originalFilename = multipartFile.getOriginalFilename();

        if (originalFilename == null)
            throw new Exception("Original filename is null");

        ControlStructure controlStructure = controlStructureRepository.findById(controlStructureId)
                .orElseThrow(() -> new Step2NotFoundException("ControlStructure not found with id: " + controlStructureId));

        String fileName = StringUtils.cleanPath(originalFilename);

        String uploadDir = "src/main/resources/static/cs-images/" + controlStructure.getId();
        saveFile(uploadDir, fileName, multipartFile);

        Image image = new Image();
        image.setName(fileName);

        controlStructure.getImages().add(image);
        controlStructureRepository.save(controlStructure);

        return image;
    }

    public void delete(long id) throws Exception {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new Step2NotFoundException("Image not found with id: " + id));

        long controlStructureId = imageRepository.findControlStructureIdFromImageId(id);
        String deleteDir = "MicroSTAMP-Step2/microstamp-step2-microservice/src/main/resources/static/cs-images/" + controlStructureId + "/";

        Files.deleteIfExists(Paths.get(deleteDir + image.getName()));
        imageRepository.deleteById(image.getId());
    }

    private void saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
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
