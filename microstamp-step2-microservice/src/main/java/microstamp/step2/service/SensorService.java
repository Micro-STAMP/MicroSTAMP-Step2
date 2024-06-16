package microstamp.step2.service;

import microstamp.step2.data.ControlStructure;
import microstamp.step2.data.Sensor;
import microstamp.step2.dto.SensorDto;
import microstamp.step2.exception.Step2NotFoundException;
import microstamp.step2.repository.ComponentRepository;
import microstamp.step2.repository.ControlStructureRepository;
import microstamp.step2.repository.SensorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public Sensor findById(long id) throws Step2NotFoundException {
        return sensorRepository.findById(id)
                .orElseThrow(() -> new Step2NotFoundException("Sensor not found with id: " + id));
    }

    public List<Sensor> findByControlStructureId(long id) {
        return sensorRepository.findByControlStructureId(id);
    }

    public Sensor insert(SensorDto sensorDto) throws Step2NotFoundException {
        ControlStructure controlStructure = controlStructureRepository.findById(sensorDto.getControlStructureId())
                .orElseThrow(() -> new Step2NotFoundException("ControlStructure not found with id: " + sensorDto.getControlStructureId()));

        Sensor sensor = new Sensor();
        sensor.setName(sensorDto.getName());
        sensor.setBorder(sensorDto.getBorder());
        sensor.setIsVisible(sensorDto.getIsVisible());

        if (sensorDto.getFatherId() != null) {
            microstamp.step2.data.Component father = componentRepository.findById(sensorDto.getFatherId())
                    .orElseThrow(() -> new Step2NotFoundException("Father component not found with id: " + sensorDto.getFatherId()));
            sensor.setFather(father);
            father.setIsControlStructure(true);
        } else {
            sensor.setFather(null);
        }

        controlStructure.getComponents().add(sensor);
        controlStructureRepository.save(controlStructure);

        return sensor;
    }

    public void update(long id, SensorDto sensorDto) throws Step2NotFoundException {
        microstamp.step2.data.Component sensor = componentRepository.findById(id)
                .orElseThrow(() -> new Step2NotFoundException("Sensor not found with id: " + id));

        sensor.setName(sensorDto.getName());
        sensor.setBorder(sensorDto.getBorder());
        sensor.setIsVisible(sensorDto.getIsVisible());

        if (sensorDto.getFatherId() != null) {
            microstamp.step2.data.Component father = componentRepository.findById(sensorDto.getFatherId())
                    .orElseThrow(() -> new Step2NotFoundException("Father component not found with id: " + sensorDto.getFatherId()));
            sensor.setFather(father);
        } else {
            sensor.setFather(null);
        }

        if (!sensorDto.getType().equals("Sensor"))
            componentRepository.updateComponentType(id, sensorDto.getType());

        componentRepository.save(sensor);
    }

    public void delete(long id) throws Step2NotFoundException {
        Sensor sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new Step2NotFoundException("Sensor not found with id: " + id));
        sensorRepository.deleteById(sensor.getId());
    }
}
