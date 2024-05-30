package microstamp.step2.service;

import microstamp.step2.data.Connection;
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

    public microstamp.step2.data.Component findById(long id) {
        return componentRepository.findById(id)
                .orElseThrow();
    }

    public List<microstamp.step2.data.Component> findByControlStructureId(long id) {
        return componentRepository.findComponentsByControlStructureId(id);
    }

    public void delete(long id) {
        List<microstamp.step2.data.Component> children = componentRepository.findComponentsChildren(id);
        children.forEach(c -> delete(c.getId()));

        List<Connection> connections_source = connectionRepository.findConnectionsThatTheComponentIsSource(id);
        if (connections_source.size() != 0) {
            connections_source.forEach(c -> connectionRepository.deleteById(c.getId()));
        }

        List<Connection> connections_target = connectionRepository.findConnectionsThatTheComponentIsTarget(id);
        if (connections_target.size() != 0) {
            connections_target.forEach(c -> connectionRepository.deleteById(c.getId()));
        }

        componentRepository.deleteById(id);
    }

    public List getComponentsAndConnectionsRecursive(long id) {
        List<microstamp.step2.data.Component> components = componentRepository.findComponentsChildren(id);
        List items = new ArrayList();
        for (microstamp.step2.data.Component c : components)
            items.addAll(getComponentsAndConnectionsRecursive(c.getId()));

        List<Connection> connections_source = connectionRepository.findConnectionsThatTheComponentIsSource(id);
        if (connections_source.size() != 0) {
            for (Connection c : connections_source) {
                if (!items.contains(c))
                    items.add(c);
            }
        }

        List<Connection> connections_target = connectionRepository.findConnectionsThatTheComponentIsTarget(id);
        if (connections_target.size() != 0) {
            for (Connection c : connections_target) {
                if (!items.contains(c))
                    items.add(c);
            }
        }
        items.add(componentRepository.findById(id));
        return items;
    }

    public List getComponentsRecursive(long id) {
        List<microstamp.step2.data.Component> components = componentRepository.findComponentsChildren(id);
        List items = new ArrayList();
        for (microstamp.step2.data.Component c : components)
            items.addAll(getComponentsAndConnectionsRecursive(c.getId()));

        return items;
    }
}
