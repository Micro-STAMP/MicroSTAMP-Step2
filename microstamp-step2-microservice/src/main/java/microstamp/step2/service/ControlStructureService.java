package microstamp.step2.service;

import microstamp.step2.data.ControlStructure;
import microstamp.step2.data.Environment;
import microstamp.step2.data.Image;
import microstamp.step2.data.User;
import microstamp.step2.dto.ControlStructureDto;
import microstamp.step2.repository.ControlStructureRepository;
import microstamp.step2.repository.ImageRepository;
import microstamp.step2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Component
public class ControlStructureService {

    @Autowired
    private ControlStructureRepository controlStructureRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private UserRepository userRepository;

    public List<ControlStructure> findAll() {
        return controlStructureRepository.findAll();
    }

    public ControlStructure findById(long id) {
        return controlStructureRepository.findById(id)
                .orElseThrow();
    }

    public List<ControlStructure> findByUserId(long id) {
        return controlStructureRepository.findControlStructuresByUserId(id);
    }

    public List<ControlStructure> findControlStructuresForGuests() {
        return controlStructureRepository.findControlStructuresForGuests();
    }

    public ControlStructure create(ControlStructureDto controlStructureDto) {
        ControlStructure controlStructure = new ControlStructure();
        controlStructure.setName(controlStructureDto.getName());
        controlStructure.getComponents().add(new Environment());

        Optional<User> user = userRepository.findById(controlStructureDto.getUserId());
        user.get().getControlStructures().add(controlStructure);
        userRepository.save(user.get());

        return controlStructure;
    }

    public void update(long id, ControlStructureDto controlStructureDto) {
        controlStructureRepository.findById(id)
                .map(record -> {
                    record.setName(controlStructureDto.getName());
                    ControlStructure updated = controlStructureRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElseThrow();
    }

    public void delete(long id) {
        try {
            deleteImages(id);
        } catch (Exception ex) {
            System.out.println("\nException when deleting CS images: \n");
            ex.printStackTrace();
        }
        controlStructureRepository.findById(id)
                .map(record -> {
                    controlStructureRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElseThrow();
    }

    private void deleteImages(long id) throws Exception {
        String deleteDir = "MicroSTAMP-Step2/microstamp-step2-microservice/src/main/resources/static/cs-images/" + id + "/";
        Path uploadDeletePath;
        for (Image i : imageRepository.findImagesByControlStructureId(id)) {
            uploadDeletePath = Paths.get(deleteDir + i.getName());
            Files.deleteIfExists(uploadDeletePath);
        }
        uploadDeletePath = Paths.get(deleteDir);
        Files.deleteIfExists(uploadDeletePath);
    }
}
