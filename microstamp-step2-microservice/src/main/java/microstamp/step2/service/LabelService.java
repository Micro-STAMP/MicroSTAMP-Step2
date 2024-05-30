package microstamp.step2.service;

import microstamp.step2.data.Connection;
import microstamp.step2.data.Label;
import microstamp.step2.dto.LabelDto;
import microstamp.step2.repository.ConnectionRepository;
import microstamp.step2.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class LabelService {

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private ConnectionRepository connectionRepository;

    public List<Label> findAll() {
        return labelRepository.findAll();
    }

    public Label findById(long id) {
        return labelRepository.findById(id)
                .orElseThrow();
    }

    public Label create(LabelDto labelDto) {
        Label label = new Label();
        label.setLabel(labelDto.getLabel());

        Optional<Connection> c = connectionRepository.findById(labelDto.getConnectionId());
        c.get().getLabels().add(label);
        connectionRepository.save(c.get());
        return label;
    }

    public void update(long id, LabelDto labelDto) {
        labelRepository.findById(id)
                .map(record -> {
                    record.setLabel(labelDto.getLabel());
                    Label updated = labelRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElseThrow();
    }

    public void delete(long id) {
        labelRepository.findById(id)
                .map(record -> {
                    labelRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElseThrow();
    }
}
