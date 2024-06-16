package microstamp.step2.service;

import microstamp.step2.data.Connection;
import microstamp.step2.exception.Step2NotFoundException;
import microstamp.step2.repository.ComponentRepository;
import microstamp.step2.repository.ConnectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ComponentService {

    @Autowired
    private ComponentRepository componentRepository;

    @Autowired
    private ConnectionRepository connectionRepository;

    public List<microstamp.step2.data.Component> findAll() {
        return componentRepository.findAll();
    }

    public microstamp.step2.data.Component findById(long id) throws Step2NotFoundException {
        return componentRepository.findById(id)
                .orElseThrow(() -> new Step2NotFoundException("Component not found with id: " + id));
    }

    public List<microstamp.step2.data.Component> findByControlStructureId(long id) {
        return componentRepository.findByControlStructureId(id);
    }

    public void delete(long id) {
        List<microstamp.step2.data.Component> children = componentRepository.findChildrenByComponentId(id);
        children.forEach(c -> delete(c.getId()));

        List<Connection> connections_source = connectionRepository.findConnectionsThatTheComponentIsSource(id);
        connections_source.forEach(connection -> connectionRepository.deleteById(connection.getId()));

        List<Connection> connections_target = connectionRepository.findConnectionsThatTheComponentIsTarget(id);
        connections_target.forEach(connection -> connectionRepository.deleteById(connection.getId()));

        componentRepository.deleteById(id);
    }

    public List<Object> getComponentsAndConnectionsRecursive(long id) throws Step2NotFoundException {
        List<Object> items = new ArrayList<>();
        List<microstamp.step2.data.Component> components = componentRepository.findChildrenByComponentId(id);
        microstamp.step2.data.Component currentComponent = componentRepository.findById(id)
                .orElseThrow(() -> new Step2NotFoundException("Component not found with id: " + id));

        for (microstamp.step2.data.Component component : components)
            items.addAll(getComponentsAndConnectionsRecursive(component.getId()));

        List<Connection> connections_source = connectionRepository.findConnectionsThatTheComponentIsSource(id);
        for (Connection connection : connections_source) {
            if (!items.contains(connection))
                items.add(connection);
        }

        List<Connection> connections_target = connectionRepository.findConnectionsThatTheComponentIsTarget(id);
        for (Connection connection : connections_target) {
            if (!items.contains(connection))
                items.add(connection);
        }

        items.add(currentComponent);

        return items;
    }

    public List<Object> getComponentsRecursive(long id) throws Step2NotFoundException {
        List<microstamp.step2.data.Component> components = componentRepository.findChildrenByComponentId(id);
        List<Object> items = new ArrayList<>();
        for (microstamp.step2.data.Component c : components)
            items.addAll(getComponentsAndConnectionsRecursive(c.getId()));

        return items;
    }
}
