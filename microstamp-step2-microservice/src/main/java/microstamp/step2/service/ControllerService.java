package microstamp.step2.service;

import microstamp.step2.data.ControlStructure;
import microstamp.step2.data.Controller;
import microstamp.step2.dto.ControllerDto;
import microstamp.step2.exception.Step2NotFoundException;
import microstamp.step2.exception.Step2OrphanException;
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
    private ComponentService componentService;

    @Autowired
    private ControlStructureService controlStructureService;

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
        ControlStructure controlStructure = controlStructureService.findById(controllerDto.getControlStructureId());

        Controller controller = new Controller();
        controller.setName(controllerDto.getName());
        controller.setBorder(controllerDto.getBorder());
        controller.setIsVisible(controllerDto.getIsVisible());

        if (controllerDto.getFatherId() != null) {
            microstamp.step2.data.Component father = componentService.findById(controllerDto.getFatherId());
            controller.setFather(father);
            father.setIsControlStructure(true);
        } else {
            controller.setFather(null);
        }

        controlStructure.getComponents().add(controller);
        controlStructureService.save(controlStructure);

        return controller;
    }

    public void update(long id, ControllerDto controllerDto) throws Step2NotFoundException {
        microstamp.step2.data.Component controller = componentService.findById(id);

        if (controllerDto.getFatherId() != null) {
            microstamp.step2.data.Component father = componentService.findById(controllerDto.getFatherId());

            List<microstamp.step2.data.Component> children = componentService.getComponentChildren(id);
            if(children.contains(father))
                throw new Step2OrphanException();

            controller.setFather(father);
        } else {
            controller.setFather(null);
        }

        controller.setName(controllerDto.getName());
        controller.setBorder(controllerDto.getBorder());
        controller.setIsVisible(controllerDto.getIsVisible());

        componentService.save(controller);

        if (!controllerDto.getType().equals("Controller"))
            componentService.updateType(id, controllerDto.getType());
    }

    public void delete(long id) throws Step2NotFoundException {
        Controller controller = controllerRepository.findById(id)
                .orElseThrow(() -> new Step2NotFoundException("Controller not found with id: " + id));
        controllerRepository.deleteById(controller.getId());
    }
}
