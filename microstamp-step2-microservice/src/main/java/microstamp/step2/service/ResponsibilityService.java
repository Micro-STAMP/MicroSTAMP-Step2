package microstamp.step2.service;

import microstamp.step2.data.ControlledProcess;
import microstamp.step2.data.Controller;
import microstamp.step2.data.Responsibility;
import microstamp.step2.dto.ResponsibilityDto;
import microstamp.step2.exception.Step2NotFoundException;
import microstamp.step2.repository.ComponentRepository;
import microstamp.step2.repository.ResponsibilityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ResponsibilityService {

    @Autowired
    private ResponsibilityRepository responsibilityRepository;

    @Autowired
    private ComponentRepository componentRepository;

    public List<Responsibility> findAll() {
        return responsibilityRepository.findAll();
    }

    public Responsibility findById(long id) throws Step2NotFoundException {
        return responsibilityRepository.findById(id)
                .orElseThrow(() -> new Step2NotFoundException("Responsibility not found with id: " + id));
    }

    public Responsibility insert(ResponsibilityDto responsibilityDto) throws Exception {
        microstamp.step2.data.Component component = componentRepository.findById(responsibilityDto.getComponentId())
                .orElseThrow(() -> new Step2NotFoundException("Component not found with id: " + responsibilityDto.getComponentId()));

        Responsibility responsibility = new Responsibility();
        responsibility.setResponsibility(responsibilityDto.getResponsibility());
        responsibility.setSystemSafetyConstraintAssociated(responsibilityDto.getSystemSafetyConstraintAssociated());

        if (component.getType().equals("Controller")) {
            Controller controller = (Controller) component;
            controller.getResponsibilities().add(responsibility);
        } else if (component.getType().equals("ControlledProcess")) {
            ControlledProcess controlledProcess = (ControlledProcess) component;
            controlledProcess.getResponsibilities().add(responsibility);
        } else {
            throw new Exception("Responsibilities can only be assigned to a Controller or ControlledProcess");
        }

        componentRepository.save(component);

        return responsibility;
    }

    public void update(long id, ResponsibilityDto responsibilityDto) throws Step2NotFoundException {
        Responsibility responsibility = responsibilityRepository.findById(id)
                .orElseThrow(() -> new Step2NotFoundException("Responsibility not found with id: " + id));

        responsibility.setResponsibility(responsibilityDto.getResponsibility());
        responsibility.setSystemSafetyConstraintAssociated(responsibilityDto.getSystemSafetyConstraintAssociated());

        responsibilityRepository.save(responsibility);
    }

    public void delete(long id) throws Step2NotFoundException {
        Responsibility responsibility = responsibilityRepository.findById(id)
                .orElseThrow(() -> new Step2NotFoundException("Responsibility not found with id: " + id));
        responsibilityRepository.deleteById(responsibility.getId());
    }
}
