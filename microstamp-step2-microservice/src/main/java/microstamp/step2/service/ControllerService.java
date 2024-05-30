package microstamp.step2.service;

import microstamp.step2.data.ControlStructure;
import microstamp.step2.data.Controller;
import microstamp.step2.dto.ControllerDto;
import microstamp.step2.repository.ComponentRepository;
import microstamp.step2.repository.ControlStructureRepository;
import microstamp.step2.repository.ControllerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ControllerService {

    @Autowired
    private ControllerRepository controllerRepository;

    @Autowired
    private ComponentRepository componentRepository;

    @Autowired
    private ControlStructureRepository controlStructureRepository;

    public List<Controller> findAll() {
        return controllerRepository.findAll();
    }

    public Controller findById(Long id) {
        return controllerRepository.findById(id)
                .orElseThrow();
    }

    public List<Controller> findByControlStructureId(long id) {
        return controllerRepository.findControllersByControlStructureId(id);
    }

    public Controller create(ControllerDto controllerDto) {
        Controller controller = new Controller();
        controller.setName(controllerDto.getName());
        try {
            Optional<microstamp.step2.data.Component> father = componentRepository.findById(controllerDto.getFatherId());
            controller.setFather(father.get());
            father.get().setIsControlStructure(true);
        } catch (Exception ex) {
            controller.setFather(null);
        }
        controller.setBorder(controllerDto.getBorder());
        controller.setIsVisible(controllerDto.getIsVisible());
        Optional<ControlStructure> c1 = controlStructureRepository.findById(controllerDto.getControlStructureId());
        c1.get().getComponents().add(controller);
        controlStructureRepository.save(c1.get());
        return controller;
    }

    public void update(long id, ControllerDto controllerDto) {
        if (controllerDto.getFatherId() != null) {
            if (controllerDto.getType() != "Controller")
                componentRepository.updateComponentType(id, controllerDto.getType());
            componentRepository.findById(id)
                    .map(record -> {
                        record.setName(controllerDto.getName());
                        record.setBorder(controllerDto.getBorder());
                        record.setFather(componentRepository.findById(controllerDto.getFatherId()).get());
                        record.setIsVisible(controllerDto.getIsVisible());
                        microstamp.step2.data.Component updated = componentRepository.save(record);
                        return ResponseEntity.ok().body(updated);
                    }).orElseThrow();
        } else {
            if (controllerDto.getType() != "Controller")
                componentRepository.updateComponentType(id, controllerDto.getType());
            componentRepository.findById(id)
                    .map(record -> {
                        record.setName(controllerDto.getName());
                        record.setBorder(controllerDto.getBorder());
                        record.setFather(null);
                        record.setIsVisible(controllerDto.getIsVisible());
                        microstamp.step2.data.Component updated = componentRepository.save(record);
                        return ResponseEntity.ok().body(updated);
                    }).orElseThrow();
        }
    }

    public void delete(long id) {
        controllerRepository.findById(id)
                .map(record -> {
                    controllerRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElseThrow();
    }
}
