package microstamp.step2.controller;

import microstamp.step2.dto.SensorDto;
import microstamp.step2.data.Component;
import microstamp.step2.data.ControlStructure;
import microstamp.step2.data.Sensor;
import microstamp.step2.repository.ComponentRepository;
import microstamp.step2.repository.ControlStructureRepository;
import microstamp.step2.repository.SensorRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/sensors")
@Tag(name = "Sensor")
public class SensorController {

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private ComponentRepository componentRepository;

    @Autowired
    private ControlStructureRepository controlStructureRepository;

    @GetMapping
    public List findAll(){
        return sensorRepository.findAll();
    }

    @GetMapping(path = {"/{id}"})
    public ResponseEntity findById(@PathVariable long id){
        return sensorRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping(path = {"cs/{id}"})
    public List findByControlStructureId(@PathVariable long id){
        return sensorRepository.findSensorsByControlStructureId(id);
    }

    @PostMapping
    public Sensor create(@RequestBody SensorDto sensorDto){
        Sensor sensor = new Sensor();
        sensor.setName(sensorDto.getName());
        try{
            Optional<Component> father = componentRepository.findById(sensorDto.getFatherId());
            sensor.setFather(father.get());
            father.get().setIsControlStructure(true);
        }catch (Exception ex){
            sensor.setFather(null);
        }
        sensor.setBorder(sensorDto.getBorder());
        sensor.setIsVisible(sensorDto.getIsVisible());
        Optional<ControlStructure> c1 = controlStructureRepository.findById(sensorDto.getControlStructureId());
        c1.get().getComponents().add(sensor);
        controlStructureRepository.save(c1.get());
        return sensor;
    }

    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody SensorDto sensorDto) {
        if(sensorDto.getFatherId() != null) {
            if(sensorDto.getType() != "Sensor")
                componentRepository.updateComponentType(id,sensorDto.getType());
            return componentRepository.findById(id)
                    .map(record -> {
                        record.setName(sensorDto.getName());
                        record.setBorder(sensorDto.getBorder());
                        record.setFather(componentRepository.findById(sensorDto.getFatherId()).get());
                        record.setIsVisible(sensorDto.getIsVisible());
                        Component updated = componentRepository.save(record);
                        return ResponseEntity.ok().body(updated);
                    }).orElse(ResponseEntity.notFound().build());
        }else{
            if(sensorDto.getType() != "Sensor")
                componentRepository.updateComponentType(id,sensorDto.getType());
            return componentRepository.findById(id)
                    .map(record -> {
                        record.setName(sensorDto.getName());
                        record.setBorder(sensorDto.getBorder());
                        record.setFather(null);
                        record.setIsVisible(sensorDto.getIsVisible());
                        Component updated = componentRepository.save(record);
                        return ResponseEntity.ok().body(updated);
                    }).orElse(ResponseEntity.notFound().build());
        }
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        return sensorRepository.findById(id)
                .map(record -> {
                    sensorRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
