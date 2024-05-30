package microstamp.step2.service;

import microstamp.step2.data.ControlledProcess;
import microstamp.step2.data.Controller;
import microstamp.step2.data.Responsibility;
import microstamp.step2.dto.ResponsibilityDto;
import microstamp.step2.repository.ComponentRepository;
import microstamp.step2.repository.ResponsibilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ResponsibilityService {

    @Autowired
    private ResponsibilityRepository responsibilityRepository;

    @Autowired
    private ComponentRepository componentRepository;

    public List<Responsibility> findAll() {
        return responsibilityRepository.findAll();
    }

    public Responsibility findById(long id) {
        return responsibilityRepository.findById(id)
                .orElseThrow();
    }

    public Responsibility create(ResponsibilityDto responsibilityDto) {
        Responsibility responsibility = new Responsibility();
        responsibility.setResponsibility(responsibilityDto.getResponsibility());
        responsibility.setSystemSafetyConstraintAssociated(responsibilityDto.getSystemSafetyConstraintAssociated());

        Optional<microstamp.step2.data.Component> c = componentRepository.findById(responsibilityDto.getComponentId());

        if (c.get().getType().equals("Controller")) {
            Controller controller = (Controller) c.get();
            controller.getResponsibilities().add(responsibility);
        } else if (c.get().getType().equals("ControlledProcess")) {
            ControlledProcess controlledProcess = (ControlledProcess) c.get();
            controlledProcess.getResponsibilities().add(responsibility);
        }
        componentRepository.save(c.get());
        return responsibility;
    }

    public void update(long id, ResponsibilityDto responsibilityDto) {
        responsibilityRepository.findById(id)
                .map(record -> {
                    record.setResponsibility(responsibilityDto.getResponsibility());
                    record.setSystemSafetyConstraintAssociated(responsibilityDto.getSystemSafetyConstraintAssociated());
                    Responsibility updated = responsibilityRepository.save(record);
                    return ResponseEntity.ok().body(updated);
                }).orElseThrow();
    }

    public void delete(long id) {
        responsibilityRepository.findById(id)
                .map(record -> {
                    responsibilityRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElseThrow();
    }
}
