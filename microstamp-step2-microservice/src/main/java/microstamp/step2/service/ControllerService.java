package microstamp.step2.service;

import microstamp.step2.data.ControlStructure;
import microstamp.step2.data.Controller;
import microstamp.step2.dto.ControllerDto;
import microstamp.step2.exception.Step2NotFoundException;
import microstamp.step2.repository.ComponentRepository;
import microstamp.step2.repository.ControlStructureRepository;
import microstamp.step2.repository.ControllerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public Controller findById(Long id) throws Step2NotFoundException {
        return controllerRepository.findById(id)
                .orElseThrow(() -> new Step2NotFoundException("Controller not found with id: " + id));
    }

    public List<Controller> findByControlStructureId(long id) {
        return controllerRepository.findByControlStructureId(id);
    }

    public Controller insert(ControllerDto controllerDto) throws Step2NotFoundException {
        ControlStructure controlStructure = controlStructureRepository.findById(controllerDto.getControlStructureId())
                .orElseThrow(() -> new Step2NotFoundException("ControlStructure not found with id: " + controllerDto.getControlStructureId()));

        Controller controller = new Controller();
        controller.setName(controllerDto.getName());
        controller.setBorder(controllerDto.getBorder());
        controller.setIsVisible(controllerDto.getIsVisible());

        if (controllerDto.getFatherId() != null) {
            microstamp.step2.data.Component father = componentRepository.findById(controllerDto.getFatherId())
                    .orElseThrow(() -> new Step2NotFoundException("Father component not found with id: " + controllerDto.getFatherId()));
            controller.setFather(father);
            father.setIsControlStructure(true);
        } else {
            controller.setFather(null);
        }

        controlStructure.getComponents().add(controller);
        controlStructureRepository.save(controlStructure);

        return controller;
    }

    public void update(long id, ControllerDto controllerDto) throws Step2NotFoundException {
        microstamp.step2.data.Component controller = componentRepository.findById(id)
                .orElseThrow(() -> new Step2NotFoundException("Controller not found with id: " + id));

        controller.setName(controllerDto.getName());
        controller.setBorder(controllerDto.getBorder());
        controller.setIsVisible(controllerDto.getIsVisible());

        if (controllerDto.getFatherId() != null) {
            microstamp.step2.data.Component father = componentRepository.findById(controllerDto.getFatherId())
                    .orElseThrow(() -> new Step2NotFoundException("Father component not found with id: " + controllerDto.getFatherId()));
            controller.setFather(father);
        } else {
            controller.setFather(null);
        }

        if (!controllerDto.getType().equals("Controller"))
            componentRepository.updateComponentType(id, controllerDto.getType());

        componentRepository.save(controller);
    }

    public void delete(long id) throws Step2NotFoundException {
        Controller controller = controllerRepository.findById(id)
                .orElseThrow(() -> new Step2NotFoundException("Controller not found with id: " + id));
        controllerRepository.deleteById(controller.getId());
    }
}
