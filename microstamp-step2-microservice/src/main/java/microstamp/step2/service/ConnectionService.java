package microstamp.step2.service;

import microstamp.step2.data.Connection;
import microstamp.step2.data.ControlStructure;
import microstamp.step2.data.Label;
import microstamp.step2.dto.ConnectionDto;
import microstamp.step2.exception.Step2NotFoundException;
import microstamp.step2.repository.ComponentRepository;
import microstamp.step2.repository.ConnectionRepository;
import microstamp.step2.repository.ControlStructureRepository;
import microstamp.step2.repository.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public Connection findById(long id) throws Step2NotFoundException {
        return connectionRepository.findById(id)
                .orElseThrow(() -> new Step2NotFoundException("Connection not found with id: " + id));
    }

    public List<Connection> findByControlStructureId(long id) {
        return connectionRepository.findByControlStructureId(id);
    }

    public Connection insert(ConnectionDto connectionDto) throws Step2NotFoundException {
        ControlStructure controlStructure = controlStructureRepository.findById(connectionDto.getControlStructureId())
                .orElseThrow(() -> new Step2NotFoundException("ControlStructure not found with id: " + connectionDto.getControlStructureId()));

        microstamp.step2.data.Component source = componentRepository.findById(connectionDto.getSourceId())
                .orElseThrow(() -> new Step2NotFoundException("Source Component not found with id: " + connectionDto.getSourceId()));

        microstamp.step2.data.Component target = componentRepository.findById(connectionDto.getTargetId())
                .orElseThrow(() -> new Step2NotFoundException("Target component not found with id: " + connectionDto.getTargetId()));

        Connection connection = new Connection();
        connection.setConnectionType(connectionDto.getConnectionType());
        connection.setStyle(connectionDto.getStyle());
        connection.setSource(source);
        connection.setTarget(target);

        controlStructure.getConnections().add(connection);
        controlStructureRepository.save(controlStructure);

        return connection;
    }

    public void update(long id, ConnectionDto connectionDto) throws Step2NotFoundException {
        Connection connection = connectionRepository.findById(id)
                .orElseThrow(() -> new Step2NotFoundException("Connection not found with id: " + id));

        connection.setSource(componentRepository.findById(connectionDto.getSourceId())
                .orElseThrow(() -> new Step2NotFoundException("Source component not found with id: " + connectionDto.getSourceId())));

        connection.setTarget(componentRepository.findById(connectionDto.getTargetId())
                .orElseThrow(() -> new Step2NotFoundException("Target component not found with id: " + connectionDto.getTargetId())));

        connection.setConnectionType(connectionDto.getConnectionType());
        connection.setStyle(connectionDto.getStyle());

        connectionRepository.save(connection);
    }

    public void delete(long id) throws Step2NotFoundException {
        Connection connection = connectionRepository.findById(id)
                .orElseThrow(() -> new Step2NotFoundException("Connection not found with id: " + id));

        List<Label> labels = labelRepository.findByConnectionId(id);
        labels.forEach(label -> labelRepository.deleteById(label.getId()));

        connectionRepository.deleteById(connection.getId());
    }
}
