package microstamp.step2.service;

import microstamp.step2.data.Connection;
import microstamp.step2.data.Label;
import microstamp.step2.dto.LabelDto;
import microstamp.step2.exception.Step2NotFoundException;
import microstamp.step2.repository.ConnectionRepository;
import microstamp.step2.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class LabelService {

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private ConnectionRepository connectionRepository;

    public List<Label> findAll() {
        return labelRepository.findAll();
    }

    public Label findById(long id) throws Step2NotFoundException {
        return labelRepository.findById(id)
                .orElseThrow(() -> new Step2NotFoundException("Label not found with id: " + id));
    }

    public List<Label> findByConnectionId(long id) {
        return labelRepository.findByConnectionId(id);
    }

    public Label insert(LabelDto labelDto) throws Step2NotFoundException {
        Connection connection = connectionRepository.findById(labelDto.getConnectionId())
                .orElseThrow(() -> new Step2NotFoundException("Connection not found with id: " + labelDto.getConnectionId()));

        Label label = new Label();
        label.setLabel(labelDto.getLabel());

        connection.getLabels().add(label);
        connectionRepository.save(connection);

        return label;
    }

    public void update(long id, LabelDto labelDto) throws Step2NotFoundException {
        Label label = labelRepository.findById(id)
                .orElseThrow(() -> new Step2NotFoundException("Label not found with id: " + id));
        label.setLabel(labelDto.getLabel());

        labelRepository.save(label);
    }

    public void delete(long id) throws Step2NotFoundException {
        Label label = labelRepository.findById(id)
                .orElseThrow(() -> new Step2NotFoundException("Label not found with id: " + id));
        labelRepository.deleteById(label.getId());
    }

}
