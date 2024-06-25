package microstamp.step2.service;

import microstamp.step2.data.Connection;
import microstamp.step2.data.ControlStructure;
import microstamp.step2.exception.Step2NotFoundException;
import microstamp.step2.repository.ComponentRepository;
import microstamp.step2.repository.ConnectionRepository;
import microstamp.step2.repository.ControlStructureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GuestService {

    @Autowired
    private ControlStructureRepository controlStructureRepository;

    @Autowired
    private ComponentRepository componentRepository;

    @Autowired
    private ConnectionRepository connectionRepository;

    public List<ControlStructure> findControlStructures() {
        return controlStructureRepository.findByUserId(3);
    }

    public List<microstamp.step2.data.Component> findComponentsByControlStructureId(Long id) throws Step2NotFoundException {
        List<ControlStructure> guestsControlStructures = controlStructureRepository.findByUserId(3);

        if (guestsControlStructures.stream().noneMatch(controlStructure -> controlStructure.getId() == id))
            throw new Step2NotFoundException("Guest ControlStructure not found with id: " + id);

        return componentRepository.findByControlStructureId(id);
    }

    public List<Connection> findConnectionsByControlStructureId(Long id) throws Step2NotFoundException {
        List<ControlStructure> guestsControlStructures = controlStructureRepository.findByUserId(3);

        if (guestsControlStructures.stream().noneMatch(controlStructure -> controlStructure.getId() == id))
            throw new Step2NotFoundException("Guest ControlStructure not found with id: " + id);

        return connectionRepository.findByControlStructureId(id);
    }
}
