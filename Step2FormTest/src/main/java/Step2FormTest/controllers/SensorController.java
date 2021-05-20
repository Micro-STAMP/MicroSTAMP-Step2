package Step2FormTest.controllers;

import Step2FormTest.domain.SensorDomain;
import Step2FormTest.models.Sensor;
import Step2FormTest.repositories.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/sensors")
public class SensorController {

    @Autowired
    private final SensorRepository sensorRepository;

    @Autowired
    public SensorController(SensorRepository sensorRepository) {

        this.sensorRepository = sensorRepository;
    }

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

    @PostMapping
    public Sensor create(@RequestBody SensorDomain sensorDomain){
        Sensor sensor = new Sensor();
        sensor.setName(sensorDomain.getName());
        //actuator.setBorder(sensorDomain.getBorder());
        //actuator.setFather(sensorDomain.getFather());
        //actuator.setVisible(sensorDomain.isIsVisible());
        sensorRepository.save(sensor);
        return sensor;
    }

    @PutMapping(value="/{id}")
    public ResponseEntity update(@PathVariable("id") long id, @RequestBody Sensor sensorParam) {
        return sensorRepository.findById(id)
                .map(record -> {
                    record.setName(sensorParam.getName());
                    Sensor updated = sensorRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElse(ResponseEntity.notFound().build());
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
