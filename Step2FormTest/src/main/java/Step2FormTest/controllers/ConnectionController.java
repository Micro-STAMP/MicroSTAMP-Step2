package Step2FormTest.controllers;

import Step2FormTest.domain.ConnectionDomain;
import Step2FormTest.models.Component;
import Step2FormTest.models.Connection;
import Step2FormTest.models.ControlStructure;
import Step2FormTest.repositories.ComponentRepository;
import Step2FormTest.repositories.ConnectionRepository;
import Step2FormTest.repositories.ControlStructureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/connections")
public class ConnectionController {

    @Autowired
    private final ConnectionRepository connectionRepository;

    @Autowired
    private final ComponentRepository componentRepository;

    @Autowired
    private final ControlStructureRepository controlStructureRepository;

    @Autowired
    public ConnectionController(ConnectionRepository connectionRepository, ComponentRepository componentRepository, ControlStructureRepository controlStructureRepository) {
        this.connectionRepository = connectionRepository;
        this.componentRepository = componentRepository;
        this.controlStructureRepository = controlStructureRepository;
    }

    @GetMapping
    public List findAll(){
        return connectionRepository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id){
        return connectionRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }


    @PostMapping
    public Connection create(@RequestBody ConnectionDomain connectionDomain){
        Connection connection = new Connection();
        connection.setConnectionType(connectionDomain.getConnectionType());
        try {
            Optional<Component> source = componentRepository.findById(connectionDomain.getSource_id());
            connection.setSource(source.get());
        }catch (Exception ex){
            connection.setSource(null);
        }
        try {
            Optional<Component> target = componentRepository.findById(connectionDomain.getTarget_id());
            connection.setTarget(target.get());
        }catch (Exception ex){
            connection.setTarget(null);
        }
        //connection.setLabels();
        connection.setStyle(connectionDomain.getStyle());
        Optional<ControlStructure> c1 = controlStructureRepository.findById(connectionDomain.getControl_structure_id());
        c1.get().getConnections().add(connection);
        controlStructureRepository.save(c1.get());
        return connection;
    }

    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody ConnectionDomain connectionDomain) {
        return connectionRepository.findById(id)
                .map(record -> {
                    record.setConnectionType(connectionDomain.getConnectionType());
                    record.setStyle(connectionDomain.getStyle());
                    record.setTarget(componentRepository.findById(connectionDomain.getTarget_id()).get());
                    record.setSource(componentRepository.findById(connectionDomain.getSource_id()).get());
                    Connection updated = connectionRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        return connectionRepository.findById(id)
                .map(record -> {
                    connectionRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
