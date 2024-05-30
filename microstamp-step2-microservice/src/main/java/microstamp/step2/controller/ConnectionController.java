package microstamp.step2.controller;

import microstamp.step2.dto.ConnectionDto;
import microstamp.step2.data.Component;
import microstamp.step2.data.Connection;
import microstamp.step2.data.ControlStructure;
import microstamp.step2.data.Label;
import microstamp.step2.repository.ComponentRepository;
import microstamp.step2.repository.ConnectionRepository;
import microstamp.step2.repository.ControlStructureRepository;
import microstamp.step2.repository.LabelRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/connections")
@Tag(name = "Connection")
public class ConnectionController {

    @Autowired
    private ConnectionRepository connectionRepository;

    @Autowired
    private ComponentRepository componentRepository;

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private ControlStructureRepository controlStructureRepository;

    @GetMapping
    public List findAll(){
        return connectionRepository.findAll();
    }

    @GetMapping(path = {"cs/{id}"})
    public List findByControlStructureId(@PathVariable long id){
        return connectionRepository.findConnectionsByControlStructureId(id);
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findConnectionById(@PathVariable long id){
        return connectionRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Connection create(@RequestBody ConnectionDto connectionDto){
        Connection connection = new Connection();
        connection.setConnectionType(connectionDto.getConnectionType());

        Optional<Component> source = componentRepository.findById(connectionDto.getSourceId());
        connection.setSource(source.get());

        Optional<Component> target = componentRepository.findById(connectionDto.getTargetId());
        connection.setTarget(target.get());

        connection.setStyle(connectionDto.getStyle());
        Optional<ControlStructure> c1 = controlStructureRepository.findById(connectionDto.getControlStructureId());
        c1.get().getConnections().add(connection);
        controlStructureRepository.save(c1.get());
        return connection;
    }

    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody ConnectionDto connectionDto) {
        return connectionRepository.findById(id)
                .map(record -> {
                    record.setConnectionType(connectionDto.getConnectionType());
                    record.setStyle(connectionDto.getStyle());
                    record.setTarget(componentRepository.findById(connectionDto.getTargetId()).get());
                    record.setSource(componentRepository.findById(connectionDto.getSourceId()).get());
                    Connection updated = connectionRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {

        List<Label> labels = labelRepository.findLabelsByConnectionId(id);
        labels.forEach(label -> labelRepository.deleteById(label.getId()));

        return connectionRepository.findById(id)
                .map(record -> {
                    connectionRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = {"listLabelsToBeDeleted/{id}"})
    public List getLabelsToBeDeleted(@PathVariable long id){
        return labelRepository.findLabelsByConnectionId(id);
    }

}
