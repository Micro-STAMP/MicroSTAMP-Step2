package microstamp.step2.controller;

import microstamp.step2.data.Component;
import microstamp.step2.data.ComponentType;
import microstamp.step2.data.ConnectionType;
import microstamp.step2.data.Style;
import microstamp.step2.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PageController {

    @Autowired
    private ComponentService componentService;

    @Autowired
    private ConnectionService connectionService;

    @Autowired
    private ControlStructureService controlStructureService;

    @Autowired
    private ImageService imageService;

    @Autowired
    private VariableService variableService;

    @GetMapping("/{controlStructureId:\\d+}")
    public String indexPage(@PathVariable Long controlStructureId, Model model) {

        List<Component> components = componentService.findByControlStructureId(controlStructureId);
        model.addAttribute("components", components);
        model.addAttribute("connections", connectionService.findByControlStructureId(controlStructureId));
        model.addAttribute("control_structure_id", controlStructureId);
        model.addAttribute("connectionType", ConnectionType.values());
        model.addAttribute("variables", variableService.findByControlStructureId(controlStructureId));

        model.addAttribute("images", imageService.findByControlStructureId(controlStructureId));

        model.addAttribute("style", Style.values());

        List<Component> componentsWithoutEnvironment = componentService.findByControlStructureId(controlStructureId);
        if (!componentsWithoutEnvironment.isEmpty())
            componentsWithoutEnvironment.removeFirst();
        model.addAttribute("componentsWithoutEnvironment", componentsWithoutEnvironment);

        List<Component> controllersControlledProcess = new ArrayList<>();
        for (Component c : components) {
            if (c.getType().equals(ComponentType.Controller.name()) || c.getType().equals(ComponentType.ControlledProcess.name())) {
                controllersControlledProcess.add(c);
            }
        }
        model.addAttribute("controllersControlledProcess", controllersControlledProcess);

        return "index";
    }

    @GetMapping("/home")
    public String controlStructures(Model model) {
        model.addAttribute("controlStructures", controlStructureService.findAll());
        return "control_structures";
    }

    @GetMapping("/")
    public String redirectHome(Model model) {
        return controlStructures(model);
    }

    @GetMapping("/guests")
    public String guests(Model model) {
        model.addAttribute("controlStructures", controlStructureService.findControlStructuresForGuests());
        return "guests";
    }

    @GetMapping("/guests/{controlStructureId}")
    public String indexPageGuest(@PathVariable Long controlStructureId, Model model) {

        List<Component> components = componentService.findByControlStructureId(controlStructureId);
        model.addAttribute("components", components);
        model.addAttribute("connections", connectionService.findByControlStructureId(controlStructureId));
        model.addAttribute("control_structure_id", controlStructureId);
        model.addAttribute("variables", variableService.findByControlStructureId(controlStructureId));
        model.addAttribute("images", imageService.findByControlStructureId(controlStructureId));

        return "guestsIndex";
    }
}
