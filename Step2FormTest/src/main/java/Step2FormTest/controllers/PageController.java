package Step2FormTest.controllers;

import java.util.*;

import Step2FormTest.models.*;
import Step2FormTest.repositories.ComponentRepository;
import Step2FormTest.repositories.ConnectionRepository;
import Step2FormTest.repositories.ControlStructureRepository;
import Step2FormTest.repositories.LabelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


@Controller
public class PageController {

    @Autowired
    private final ComponentRepository componentRepository;

    @Autowired
    private final ConnectionRepository connectionRepository;

    @Autowired
    private final LabelRepository labelRepository;

    @Autowired
    private final ControlStructureRepository controlStructureRepository;

    @Autowired
    public PageController(ComponentRepository componentRepository, ConnectionRepository connectionRepository, ControlStructureRepository controlStructureRepository, LabelRepository labelRepository) {
        this.componentRepository = componentRepository;
        this.connectionRepository = connectionRepository;
        this.controlStructureRepository = controlStructureRepository;
        this.labelRepository = labelRepository;
    }

    @GetMapping("/{controlStructureId}")
    public String indexPage(@PathVariable Long controlStructureId, Model model) {
        model.addAttribute("components", componentRepository.findComponentsByControlStructureId(controlStructureId));
        model.addAttribute("connections", connectionRepository.findConnectionsByControlStructureId(controlStructureId));
        model.addAttribute("control_structure_id", controlStructureId);
        model.addAttribute("connectionType", ConnectionType.loadConnectionTypes());
        model.addAttribute("process_input",ConnectionType.getProcessInputDisturbance());
        model.addAttribute("process_output",ConnectionType.getProcessOutput());

        model.addAttribute("style", Style.loadStyles());

        List<Component> componentsWithoutEnvironment = componentRepository.findComponentsByControlStructureId(controlStructureId);
        componentsWithoutEnvironment.remove(0);
        model.addAttribute("componentsWithoutEnvironment",componentsWithoutEnvironment);

        return "index";
    }

    @GetMapping("/home")
    public String controlStructures(Model model){
        model.addAttribute("controlStructures", controlStructureRepository.findAll());
        return "control_structures";
    }

    @GetMapping("/")
    public String redirectHome(Model model){
        return controlStructures(model);
    }
}
