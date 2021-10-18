package Step2FormTest.controllers;

import Step2FormTest.models.Component;
import Step2FormTest.models.Connection;
import Step2FormTest.repositories.ComponentRepository;
import Step2FormTest.repositories.ConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/components")
public class ComponentController {

    private final ComponentRepository componentRepository;

    private final ConnectionRepository connectionRepository;

    @Autowired
    public ComponentController(ComponentRepository componentRepository, ConnectionRepository connectionRepository) {
        this.componentRepository = componentRepository;
        this.connectionRepository = connectionRepository;
    }

    @GetMapping
    public List findAll(){
        return componentRepository.findAll();
    }

    //get Component by CS id
    @GetMapping(path = {"/{id}"})
    public List findByControlStructureId(@PathVariable long id){
        return componentRepository.findComponentsByControlStructureId(id);
    }

    @GetMapping(path = {"listComponents/{id}"})
    public ResponseEntity findComponentById(@PathVariable long id){
        return componentRepository.findById(id)
                .map(record -> ResponseEntity.ok().body(record))
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping(path ={"/{id}"})
    public ResponseEntity <?> delete(@PathVariable long id) {
        deleteComponentAndChildren(id);
        return ResponseEntity.ok().build();
    }

    public void deleteComponentAndChildren(long id){
        List<Component> children = componentRepository.findComponentsChildren(id);
        children.forEach(c -> deleteComponentAndChildren(c.getId()));

        List<Connection> connections_source = connectionRepository.findConnectionsThatTheComponentIsSource(id);
        if(connections_source.size() != 0) {
            connections_source.forEach(c -> connectionRepository.deleteById(c.getId()));
        }

        List<Connection> connections_target = connectionRepository.findConnectionsThatTheComponentIsTarget(id);
        if(connections_target.size() != 0) {
            connections_target.forEach(c -> connectionRepository.deleteById(c.getId()));
        }

        componentRepository.deleteById(id);
    }

    @GetMapping(path = {"listComponentsAndConnectionsToBeDeleted/{id}"})
    public List getComponentsAndConnections(@PathVariable long id){
        List list = getComponentsAndConnectionsRecursive(id);
        list.remove(componentRepository.findById(id));
        return list;
    }

    public List getComponentsAndConnectionsRecursive(long id){
        List<Component> components = componentRepository.findComponentsChildren(id);
        List items = new ArrayList();
        for(Component c : components)
            items.addAll(getComponentsAndConnectionsRecursive(c.getId()));

        List<Connection> connections_source = connectionRepository.findConnectionsThatTheComponentIsSource(id);
        if(connections_source.size() != 0) {
            for (Connection c : connections_source) {
                if(!items.contains(c))
                    items.add(c);
            }
        }

        List<Connection> connections_target = connectionRepository.findConnectionsThatTheComponentIsTarget(id);
        if(connections_target.size() != 0) {
            for (Connection c : connections_target) {
                if(!items.contains(c))
                    items.add(c);
            }
        }
        items.add(componentRepository.findById(id));
        return items;
    }
}
