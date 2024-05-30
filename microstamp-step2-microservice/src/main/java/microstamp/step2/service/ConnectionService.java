package microstamp.step2.service;

import microstamp.step2.data.Connection;
import microstamp.step2.data.ControlStructure;
import microstamp.step2.data.Label;
import microstamp.step2.dto.ConnectionDto;
import microstamp.step2.repository.ComponentRepository;
import microstamp.step2.repository.ConnectionRepository;
import microstamp.step2.repository.ControlStructureRepository;
import microstamp.step2.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ConnectionService {

    @Autowired
    private ConnectionRepository connectionRepository;

    @Autowired
    private ComponentRepository componentRepository;

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private ControlStructureRepository controlStructureRepository;

    public List<Connection> findAll() {
        return connectionRepository.findAll();
    }

    public Connection findById(long id) {
        return connectionRepository.findById(id)
                .orElseThrow();
    }

    public List<Connection> findByControlStructureId(long id) {
        return connectionRepository.findConnectionsByControlStructureId(id);
    }

    public Connection create(ConnectionDto connectionDto) {
        Connection connection = new Connection();
        connection.setConnectionType(connectionDto.getConnectionType());

        Optional<microstamp.step2.data.Component> source = componentRepository.findById(connectionDto.getSourceId());
        connection.setSource(source.get());

        Optional<microstamp.step2.data.Component> target = componentRepository.findById(connectionDto.getTargetId());
        connection.setTarget(target.get());

        connection.setStyle(connectionDto.getStyle());
        Optional<ControlStructure> c1 = controlStructureRepository.findById(connectionDto.getControlStructureId());
        c1.get().getConnections().add(connection);
        controlStructureRepository.save(c1.get());
        return connection;
    }

    public void update(long id, ConnectionDto connectionDto) {
        connectionRepository.findById(id)
                .map(record -> {
                    record.setConnectionType(connectionDto.getConnectionType());
                    record.setStyle(connectionDto.getStyle());
                    record.setTarget(componentRepository.findById(connectionDto.getTargetId()).get());
                    record.setSource(componentRepository.findById(connectionDto.getSourceId()).get());
                    Connection updated = connectionRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElseThrow();
    }

    public void delete(long id) {
        List<Label> labels = labelRepository.findLabelsByConnectionId(id);
        labels.forEach(label -> labelRepository.deleteById(label.getId()));

        connectionRepository.findById(id)
                .map(record -> {
                    connectionRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElseThrow();
    }

    public List<Label> findLabelsByConnectionId(long id) {
        return labelRepository.findLabelsByConnectionId(id);
    }
}
