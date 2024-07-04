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

import java.util.List;

@Component
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ControlStructureRepository controlStructureRepository;

    public List<Image> findAll() {
        return imageRepository.findAll();
    }

    public Image findById(long id) throws Step2NotFoundException {
        return imageRepository.findById(id)
                .orElseThrow(() -> new Step2NotFoundException("Image not found with id: " + id));
    }

    public List<Image> findByControlStructureId(long id) {
        return imageRepository.findByControlStructureId(id);
    }

    public Image insert(long controlStructureId, MultipartFile file) throws Exception {
        if (file.isEmpty())
            throw new Exception("MultipartFile is empty");

        String originalFilename = file.getOriginalFilename();

        if (originalFilename == null)
            throw new Exception("Original filename is null");

        ControlStructure controlStructure = controlStructureRepository.findById(controlStructureId)
                .orElseThrow(() -> new Step2NotFoundException("ControlStructure not found with id: " + controlStructureId));

        Image image = new Image();
        image.setName(StringUtils.cleanPath(originalFilename));
        image.setData(file.getBytes());

        controlStructure.getImages().add(image);
        controlStructureRepository.save(controlStructure);

        return image;
    }

    public void delete(long id) throws Exception {
        Image image = imageRepository.findById(id)
                .orElseThrow(() -> new Step2NotFoundException("Image not found with id: " + id));
        imageRepository.deleteById(image.getId());
    }
}
