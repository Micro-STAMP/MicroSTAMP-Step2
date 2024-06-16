package microstamp.step2.service;

import microstamp.step2.data.ControlStructure;
import microstamp.step2.data.ControlledProcess;
import microstamp.step2.dto.ControlledProcessDto;
import microstamp.step2.exception.Step2NotFoundException;
import microstamp.step2.repository.ComponentRepository;
import microstamp.step2.repository.ControlStructureRepository;
import microstamp.step2.repository.ControlledProcessRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public ControlledProcess findById(long id) throws Step2NotFoundException {
        return controlledProcessRepository.findById(id)
                .orElseThrow(() -> new Step2NotFoundException("ControlledProcess not found with id: " + id));
    }

    public List<ControlledProcess> findByControlStructureId(long id) {
        return controlledProcessRepository.findByControlStructureId(id);
    }

    public ControlledProcess insert(ControlledProcessDto controlledProcessDto) throws Step2NotFoundException {
        ControlStructure controlStructure = controlStructureRepository.findById(controlledProcessDto.getControlStructureId())
                .orElseThrow(() -> new Step2NotFoundException("ControlStructure not found with id: " + controlledProcessDto.getControlStructureId()));

        ControlledProcess controlledProcess = new ControlledProcess();
        controlledProcess.setName(controlledProcessDto.getName());
        controlledProcess.setBorder(controlledProcessDto.getBorder());
        controlledProcess.setIsVisible(controlledProcessDto.getIsVisible());

        if (controlledProcessDto.getFatherId() != null) {
            microstamp.step2.data.Component father = componentRepository.findById(controlledProcessDto.getFatherId())
                    .orElseThrow(() -> new Step2NotFoundException("Father component not found with id: " + controlledProcessDto.getFatherId()));
            controlledProcess.setFather(father);
            father.setIsControlStructure(true);
        } else {
            controlledProcess.setFather(null);
        }

        controlStructure.getComponents().add(controlledProcess);
        controlStructureRepository.save(controlStructure);

        return controlledProcess;
    }

    public void update(long id, ControlledProcessDto controlledProcessDto) throws Step2NotFoundException {
        microstamp.step2.data.Component controlledProcess = componentRepository.findById(id)
                .orElseThrow(() -> new Step2NotFoundException("ControlledProcess not found with id: " + id));

        controlledProcess.setName(controlledProcessDto.getName());
        controlledProcess.setBorder(controlledProcessDto.getBorder());
        controlledProcess.setIsVisible(controlledProcessDto.getIsVisible());

        if (controlledProcessDto.getFatherId() != null) {
            microstamp.step2.data.Component father = componentRepository.findById(controlledProcessDto.getFatherId())
                    .orElseThrow(() -> new Step2NotFoundException("Father component not found with id: " + controlledProcessDto.getFatherId()));
            controlledProcess.setFather(father);
        } else {
            controlledProcess.setFather(null);
        }

        if (!controlledProcessDto.getType().equals("ControlledProcess"))
            componentRepository.updateComponentType(id, controlledProcessDto.getType());

        componentRepository.save(controlledProcess);
    }

    public void delete(long id) throws Step2NotFoundException {
        ControlledProcess controlledProcess = controlledProcessRepository.findById(id)
                .orElseThrow(() -> new Step2NotFoundException("ControlledProcess not found with id: " + id));
        controlledProcessRepository.deleteById(controlledProcess.getId());
    }
}
