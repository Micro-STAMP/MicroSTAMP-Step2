package Step2FormTest.controllers;

import Step2FormTest.domain.LabelDomain;
import Step2FormTest.models.*;
import Step2FormTest.repositories.ConnectionRepository;
import Step2FormTest.repositories.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/labels")
public class LabelController {

    @Autowired
    private final LabelRepository labelRepository;

    @Autowired
    private final ConnectionRepository connectionRepository;

    @Autowired
    public LabelController(LabelRepository labelRepository, ConnectionRepository connectionRepository) {
        this.labelRepository = labelRepository;
        this.connectionRepository = connectionRepository;
    }

    @GetMapping
    public List findAll(){
        return labelRepository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findLabelById(@PathVariable long id){
        return labelRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Label create(@RequestBody LabelDomain labelDomain){
        Label label = new Label();
        label.setLabel(labelDomain.getLabel());

        Optional<Connection> c = connectionRepository.findById(labelDomain.getConnection_id());
        c.get().getLabels().add(label);
        connectionRepository.save(c.get());
        return label;
    }

    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody Label label) {
        return labelRepository.findById(id)
                .map(record -> {
                    record.setLabel(label.getLabel());
                    Label updated = labelRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        return labelRepository.findById(id)
                .map(record -> {
                    labelRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }

}
