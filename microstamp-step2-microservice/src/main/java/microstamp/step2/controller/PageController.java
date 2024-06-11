package microstamp.step2.controller;

import microstamp.step2.data.Component;
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
        model.addAttribute("connectionType", ConnectionType.loadConnectionTypes());
        model.addAttribute("process_input", ConnectionType.getProcessInputDisturbance());
        model.addAttribute("process_output", ConnectionType.getProcessOutput());
        model.addAttribute("variables", variableService.findByControlStructureId(controlStructureId));

        model.addAttribute("images", imageService.findByControlStructureId(controlStructureId));

        model.addAttribute("style", Style.loadStyles());

        List<Component> componentsWithoutEnvironment = componentService.findByControlStructureId(controlStructureId);
        if (!componentsWithoutEnvironment.isEmpty())
            componentsWithoutEnvironment.removeFirst();
        model.addAttribute("componentsWithoutEnvironment", componentsWithoutEnvironment);

        List<Component> controllersControlledProcess = new ArrayList<>();
        for (Component c : components) {
            if (c.getType().equals("Controller") || c.getType().equals("ControlledProcess")) {
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

    @GetMapping("/403")
    public String error403() {
        return "403";
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
        model.addAttribute("connectionType", ConnectionType.loadConnectionTypes());
        model.addAttribute("process_input", ConnectionType.getProcessInputDisturbance());
        model.addAttribute("process_output", ConnectionType.getProcessOutput());
        model.addAttribute("variables", variableService.findByControlStructureId(controlStructureId));

        model.addAttribute("images", imageService.findByControlStructureId(controlStructureId));

        model.addAttribute("style", Style.loadStyles());

        List<Component> componentsWithoutEnvironment = componentService.findByControlStructureId(controlStructureId);
        if (!componentsWithoutEnvironment.isEmpty())
            componentsWithoutEnvironment.removeFirst();
        model.addAttribute("componentsWithoutEnvironment", componentsWithoutEnvironment);

        List<Component> controllersControlledProcess = new ArrayList<>();
        for (Component c : components) {
            if (c.getType().equals("Controller") || c.getType().equals("ControlledProcess")) {
                controllersControlledProcess.add(c);
            }
        }
        model.addAttribute("controllersControlledProcess", controllersControlledProcess);

        return "guestsIndex";
    }

}
