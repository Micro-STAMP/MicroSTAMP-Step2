package microstamp.step2.service;

import microstamp.step2.data.ControlStructure;
import microstamp.step2.data.Environment;
import microstamp.step2.data.User;
import microstamp.step2.dto.ControlStructureDto;
import microstamp.step2.exception.Step2NotFoundException;
import microstamp.step2.repository.ControlStructureRepository;
import microstamp.step2.repository.ImageRepository;
import microstamp.step2.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public ControlStructure findById(long id) throws Step2NotFoundException {
        return controlStructureRepository.findById(id)
                .orElseThrow(() -> new Step2NotFoundException("ControlStructure not found with id: " + id));
    }

    public List<ControlStructure> findByUserId(long id) {
        return controlStructureRepository.findByUserId(id);
    }

    public List<ControlStructure> findControlStructuresForGuests() {
        return controlStructureRepository.findControlStructuresForGuests();
    }

    public ControlStructure insert(ControlStructureDto controlStructureDto) throws Step2NotFoundException {
        User user = userRepository.findById(controlStructureDto.getUserId())
                .orElseThrow(() -> new Step2NotFoundException("User not found with id: " + controlStructureDto.getUserId()));

        ControlStructure controlStructure = new ControlStructure();
        controlStructure.setName(controlStructureDto.getName());
        controlStructure.getComponents().add(new Environment());

        user.getControlStructures().add(controlStructure);
        userRepository.save(user);

        return controlStructure;
    }

    public void update(long id, ControlStructureDto controlStructureDto) throws Step2NotFoundException {
        ControlStructure controlStructure = controlStructureRepository.findById(id)
                .orElseThrow(() -> new Step2NotFoundException("ControlStructure not found with id: " + id));

        controlStructure.setName(controlStructureDto.getName());

        controlStructureRepository.save(controlStructure);
    }

    public void delete(long id) throws Exception {
        ControlStructure controlStructure = controlStructureRepository.findById(id)
                .orElseThrow(() -> new Step2NotFoundException("ControlStructure not found with id: " + id));
        controlStructureRepository.deleteById(controlStructure.getId());
    }

    public void save(ControlStructure controlStructure){
        controlStructureRepository.save(controlStructure);
    }
}
