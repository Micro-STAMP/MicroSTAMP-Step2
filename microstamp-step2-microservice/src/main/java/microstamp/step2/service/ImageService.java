package microstamp.step2.service;

import microstamp.step2.data.ControlStructure;
import microstamp.step2.data.Image;
import microstamp.step2.repository.ControlStructureRepository;
import microstamp.step2.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import java.util.Optional;

@Component
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ControlStructureRepository controlStructureRepository;

    public List<Image> findByControlStructureId(long id) {
        return imageRepository.findByControlStructureId(id);
    }

    public Image create(long controlStructureId, MultipartFile multipartFile) throws IOException {
        Image image = new Image();

        String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());
        image.setName(fileName);

        Optional<ControlStructure> c1 = controlStructureRepository.findById(controlStructureId);
        c1.get().getImages().add(image);

        controlStructureRepository.save(c1.get());

        String uploadDir = "src/main/resources/static/cs-images/" + controlStructureId;

        saveFile(uploadDir, fileName, multipartFile);

        return image;
    }

    public void delete(long id) {
        Image img = imageRepository.findById(id).get();
        List<ControlStructure> list = controlStructureRepository.findAll();
        for (ControlStructure cs : list) {
            if (cs.getImages().contains(img)) {
                String deleteDir = "MicroSTAMP-Step2/microstamp-step2-microservice/src/main/resources/static/cs-images/" + cs.getId() + "/";
                //String deleteDir = "microstamp-step2-microservice/src/main/resources/static/cs-images/" + cs.getId() + "/";
                try {
                    Files.deleteIfExists(Paths.get(deleteDir + img.getName()));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }
        imageRepository.findById(id)
                .map(record -> {
                    imageRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElseThrow();
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
