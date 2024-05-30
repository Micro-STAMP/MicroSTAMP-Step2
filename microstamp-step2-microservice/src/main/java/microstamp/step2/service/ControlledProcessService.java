package microstamp.step2.service;

import microstamp.step2.data.ControlStructure;
import microstamp.step2.data.ControlledProcess;
import microstamp.step2.dto.ControlledProcessDto;
import microstamp.step2.repository.ComponentRepository;
import microstamp.step2.repository.ControlStructureRepository;
import microstamp.step2.repository.ControlledProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ControlledProcessService {

    @Autowired
    private ControlledProcessRepository controlledProcessRepository;

    @Autowired
    private ComponentRepository componentRepository;

    @Autowired
    private ControlStructureRepository controlStructureRepository;

    public List<ControlledProcess> findAll() {
        return controlledProcessRepository.findAll();
    }

    public ControlledProcess findById(long id) {
        return controlledProcessRepository.findById(id)
                .orElseThrow();
    }

    public List<ControlledProcess> findByControlStructureId(long id) {
        return controlledProcessRepository.findControlledProcessesByControlStructureId(id);
    }

    public ControlledProcess create(ControlledProcessDto controlledProcessDto) {
        ControlledProcess controlledProcess = new ControlledProcess();
        controlledProcess.setName(controlledProcessDto.getName());
        try {
            Optional<microstamp.step2.data.Component> father = componentRepository.findById(controlledProcessDto.getFatherId());
            controlledProcess.setFather(father.get());
            father.get().setIsControlStructure(true);
        } catch (Exception ex) {
            controlledProcess.setFather(null);
        }
        controlledProcess.setBorder(controlledProcessDto.getBorder());
        controlledProcess.setIsVisible(controlledProcessDto.getIsVisible());
        Optional<ControlStructure> c1 = controlStructureRepository.findById(controlledProcessDto.getControlStructureId());
        c1.get().getComponents().add(controlledProcess);
        controlStructureRepository.save(c1.get());
        return controlledProcess;
    }

    public void update(long id, ControlledProcessDto controlledProcessDto) {
        if (controlledProcessDto.getFatherId() != null) {
            if (controlledProcessDto.getType() != "ControlledProcess")
                componentRepository.updateComponentType(id, controlledProcessDto.getType());
            componentRepository.findById(id)
                    .map(record -> {
                        record.setName(controlledProcessDto.getName());
                        record.setBorder(controlledProcessDto.getBorder());
                        record.setFather(componentRepository.findById(controlledProcessDto.getFatherId()).get());
                        record.setIsVisible(controlledProcessDto.getIsVisible());
                        microstamp.step2.data.Component updated = componentRepository.save(record);
                        return ResponseEntity.ok().body(updated);
                    }).orElseThrow();
        } else {
            if (controlledProcessDto.getType() != "ControlledProcess")
                componentRepository.updateComponentType(id, controlledProcessDto.getType());
            componentRepository.findById(id)
                    .map(record -> {
                        record.setName(controlledProcessDto.getName());
                        record.setBorder(controlledProcessDto.getBorder());
                        record.setFather(null);
                        record.setIsVisible(controlledProcessDto.getIsVisible());
                        microstamp.step2.data.Component updated = componentRepository.save(record);
                        return ResponseEntity.ok().body(updated);
                    }).orElseThrow();
        }
    }

    public void delete(long id) {
        controlledProcessRepository.findById(id)
                .map(record -> {
                    controlledProcessRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElseThrow();
    }
}
