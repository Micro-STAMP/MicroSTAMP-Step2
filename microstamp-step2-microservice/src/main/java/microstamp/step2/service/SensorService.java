package microstamp.step2.service;

import microstamp.step2.data.ControlStructure;
import microstamp.step2.data.Sensor;
import microstamp.step2.dto.SensorDto;
import microstamp.step2.exception.Step2NotFoundException;
import microstamp.step2.exception.Step2OrphanException;
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
    private ComponentService componentService;

    @Autowired
    private ControlStructureService controlStructureService;

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
        ControlStructure controlStructure = controlStructureService.findById(sensorDto.getControlStructureId());

        Sensor sensor = new Sensor();
        sensor.setName(sensorDto.getName());
        sensor.setBorder(sensorDto.getBorder());
        sensor.setIsVisible(sensorDto.getIsVisible());

        if (sensorDto.getFatherId() != null) {
            microstamp.step2.data.Component father = componentService.findById(sensorDto.getFatherId());
            sensor.setFather(father);
            father.setIsControlStructure(true);
        } else {
            sensor.setFather(null);
        }

        controlStructure.getComponents().add(sensor);
        controlStructureService.save(controlStructure);

        return sensor;
    }

    public void update(long id, SensorDto sensorDto) throws Step2NotFoundException {
        microstamp.step2.data.Component sensor = componentService.findById(id);

        if (sensorDto.getFatherId() != null) {
            microstamp.step2.data.Component father = componentService.findById(sensorDto.getFatherId());

            List<microstamp.step2.data.Component> children = componentService.getComponentChildren(id);
            if(children.contains(father))
                throw new Step2OrphanException();

            sensor.setFather(father);
        } else {
            sensor.setFather(null);
        }

        sensor.setName(sensorDto.getName());
        sensor.setBorder(sensorDto.getBorder());
        sensor.setIsVisible(sensorDto.getIsVisible());

        componentService.save(sensor);

        if (!sensorDto.getType().equals("Sensor"))
            componentService.updateType(id, sensorDto.getType());
    }

    public void delete(long id) throws Step2NotFoundException {
        Sensor sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new Step2NotFoundException("Sensor not found with id: " + id));
        sensorRepository.deleteById(sensor.getId());
    }
}
