package microstamp.step2.controller;

import microstamp.step2.dto.LabelDto;
import microstamp.step2.data.*;
import microstamp.step2.repository.ConnectionRepository;
import microstamp.step2.repository.LabelRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/labels")
@Tag(name = "Label")
public class LabelController {

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private ConnectionRepository connectionRepository;

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
    public Label create(@RequestBody LabelDto labelDto){
        Label label = new Label();
        label.setLabel(labelDto.getLabel());

        Optional<Connection> c = connectionRepository.findById(labelDto.getConnectionId());
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
