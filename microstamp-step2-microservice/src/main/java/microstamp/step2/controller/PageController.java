package microstamp.step2.controller;

import java.util.*;

import microstamp.step2.data.*;
import microstamp.step2.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;


@Controller
public class PageController {

    @Autowired
    private ComponentRepository componentRepository;

    @Autowired
    private ConnectionRepository connectionRepository;

    @Autowired
    private LabelRepository labelRepository;

    @Autowired
    private ControlStructureRepository controlStructureRepository;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private VariableRepository variableRepository;

    @GetMapping("/{controlStructureId:\\d+}")
    public String indexPage(@PathVariable Long controlStructureId, Model model) {

        List<Component> components = componentRepository.findComponentsByControlStructureId(controlStructureId);
        model.addAttribute("components", components);
        model.addAttribute("connections", connectionRepository.findConnectionsByControlStructureId(controlStructureId));
        model.addAttribute("control_structure_id", controlStructureId);
        model.addAttribute("connectionType", ConnectionType.loadConnectionTypes());
        model.addAttribute("process_input",ConnectionType.getProcessInputDisturbance());
        model.addAttribute("process_output",ConnectionType.getProcessOutput());
        model.addAttribute("variables", variableRepository.findVariablesByControlStructureId(controlStructureId));

        model.addAttribute("images",imageRepository.findImagesByControlStructureId(controlStructureId));

        model.addAttribute("style", Style.loadStyles());

        List<Component> componentsWithoutEnvironment = componentRepository.findComponentsByControlStructureId(controlStructureId);
        if(!componentsWithoutEnvironment.isEmpty())
            componentsWithoutEnvironment.remove(0);
        model.addAttribute("componentsWithoutEnvironment",componentsWithoutEnvironment);

        List<Component> controllersControlledProcess = new ArrayList<>();
        for(Component c : components){
            System.out.println(c.getName() + "\n\n");
            System.out.println(c.getType() + "\n\n");
            if (c.getType().equals("Controller")|| c.getType().equals("ControlledProcess")){
                controllersControlledProcess.add(c);
                System.out.println("Add " + c.getName() + "\n\n");
            }
        }
        model.addAttribute("controllersControlledProcess", controllersControlledProcess);

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

    @GetMapping("/403")
    public String error403(){
        return "403";
    }

    @GetMapping("/guests")
    public String guests(Model model){
        model.addAttribute("controlStructures", controlStructureRepository.findControlStructuresForGuests());
        return "guests";
    }

    @GetMapping("/guests/{controlStructureId}")
    public String indexPageGuest(@PathVariable Long controlStructureId, Model model) {

        List<Component> components = componentRepository.findComponentsByControlStructureId(controlStructureId);
        model.addAttribute("components", components);
        model.addAttribute("connections", connectionRepository.findConnectionsByControlStructureId(controlStructureId));
        model.addAttribute("control_structure_id", controlStructureId);
        model.addAttribute("connectionType", ConnectionType.loadConnectionTypes());
        model.addAttribute("process_input",ConnectionType.getProcessInputDisturbance());
        model.addAttribute("process_output",ConnectionType.getProcessOutput());
        model.addAttribute("variables", variableRepository.findVariablesByControlStructureId(controlStructureId));

        model.addAttribute("images",imageRepository.findImagesByControlStructureId(controlStructureId));

        model.addAttribute("style", Style.loadStyles());

        List<Component> componentsWithoutEnvironment = componentRepository.findComponentsByControlStructureId(controlStructureId);
        if(!componentsWithoutEnvironment.isEmpty())
            componentsWithoutEnvironment.remove(0);
        model.addAttribute("componentsWithoutEnvironment",componentsWithoutEnvironment);

        List<Component> controllersControlledProcess = new ArrayList<>();
        for(Component c : components){
            System.out.println(c.getName() + "\n\n");
            System.out.println(c.getType() + "\n\n");
            if (c.getType().equals("Controller")|| c.getType().equals("ControlledProcess")){
                controllersControlledProcess.add(c);
                System.out.println("Add " + c.getName() + "\n\n");
            }
        }
        model.addAttribute("controllersControlledProcess", controllersControlledProcess);

        return "guestsIndex";
    }

}
