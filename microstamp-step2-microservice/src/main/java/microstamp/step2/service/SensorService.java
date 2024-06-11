package microstamp.step2.service;

import microstamp.step2.data.ControlStructure;
import microstamp.step2.data.Sensor;
import microstamp.step2.dto.SensorDto;
import microstamp.step2.repository.ComponentRepository;
import microstamp.step2.repository.ControlStructureRepository;
import microstamp.step2.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class SensorService {

    @Autowired
    private SensorRepository sensorRepository;

    @Autowired
    private ComponentRepository componentRepository;

    @Autowired
    private ControlStructureRepository controlStructureRepository;

    public List<Sensor> findAll() {
        return sensorRepository.findAll();
    }

    public Sensor findById(long id) {
        return sensorRepository.findById(id)
                .orElseThrow();
    }

    public List<Sensor> findByControlStructureId(long id) {
        return sensorRepository.findByControlStructureId(id);
    }

    public Sensor create(SensorDto sensorDto) {
        Sensor sensor = new Sensor();
        sensor.setName(sensorDto.getName());
        try {
            Optional<microstamp.step2.data.Component> father = componentRepository.findById(sensorDto.getFatherId());
            sensor.setFather(father.get());
            father.get().setIsControlStructure(true);
        } catch (Exception ex) {
            sensor.setFather(null);
        }
        sensor.setBorder(sensorDto.getBorder());
        sensor.setIsVisible(sensorDto.getIsVisible());
        Optional<ControlStructure> c1 = controlStructureRepository.findById(sensorDto.getControlStructureId());
        c1.get().getComponents().add(sensor);
        controlStructureRepository.save(c1.get());
        return sensor;
    }

    public void update(long id, SensorDto sensorDto) {
        if (sensorDto.getFatherId() != null) {
            if (sensorDto.getType() != "Sensor")
                componentRepository.updateComponentType(id, sensorDto.getType());
            componentRepository.findById(id)
                    .map(record -> {
                        record.setName(sensorDto.getName());
                        record.setBorder(sensorDto.getBorder());
                        record.setFather(componentRepository.findById(sensorDto.getFatherId()).get());
                        record.setIsVisible(sensorDto.getIsVisible());
                        microstamp.step2.data.Component updated = componentRepository.save(record);
                        return ResponseEntity.ok().body(updated);
                    }).orElseThrow();
        } else {
            if (sensorDto.getType() != "Sensor")
                componentRepository.updateComponentType(id, sensorDto.getType());
            componentRepository.findById(id)
                    .map(record -> {
                        record.setName(sensorDto.getName());
                        record.setBorder(sensorDto.getBorder());
                        record.setFather(null);
                        record.setIsVisible(sensorDto.getIsVisible());
                        microstamp.step2.data.Component updated = componentRepository.save(record);
                        return ResponseEntity.ok().body(updated);
                    }).orElseThrow();
        }
    }

    public void delete(long id) {
        sensorRepository.findById(id)
                .map(record -> {
                    sensorRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElseThrow();
    }
}
