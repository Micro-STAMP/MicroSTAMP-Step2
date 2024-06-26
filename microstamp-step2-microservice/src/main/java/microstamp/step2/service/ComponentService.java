package microstamp.step2.service;

import microstamp.step2.data.Connection;
import microstamp.step2.data.Variable;
import microstamp.step2.dto.ComponentDependenciesDto;
import microstamp.step2.exception.Step2NotFoundException;
import microstamp.step2.repository.ComponentRepository;
import microstamp.step2.repository.ConnectionRepository;
import microstamp.step2.repository.VariableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Component
public class ComponentService {

    @Autowired
    private ComponentRepository componentRepository;

    @Autowired
    private ConnectionRepository connectionRepository;

    @Autowired
    private VariableRepository variableRepository;

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

    public void updateType(long id, String type){
        componentRepository.updateType(id, type);
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

    public void save(microstamp.step2.data.Component component){
        componentRepository.save(component);
    }

    public List<microstamp.step2.data.Component> getComponentChildren(long id) throws Step2NotFoundException {
        microstamp.step2.data.Component component = componentRepository.findById(id)
                .orElseThrow(() -> new Step2NotFoundException("Component not found with id: " + id));

        List<microstamp.step2.data.Component> children = new ArrayList<>();
        getChildren(component, children);

        return children;
    }

    public ComponentDependenciesDto getComponentDependencies(long id) throws Step2NotFoundException {
        microstamp.step2.data.Component component = componentRepository.findById(id)
                .orElseThrow(() -> new Step2NotFoundException("Component not found with id: " + id));

        ComponentDependenciesDto dependenciesDto = new ComponentDependenciesDto();
        Set<Connection> seen = new HashSet<>();

        getDependencies(component, dependenciesDto, seen);

        return dependenciesDto;
    }

    private void getChildren(microstamp.step2.data.Component parent, List<microstamp.step2.data.Component> children) {
        List<microstamp.step2.data.Component> directChildren = componentRepository.findChildrenByComponentId(parent.getId());
        for (microstamp.step2.data.Component child : directChildren) {
            children.add(child);
            getChildren(child, children);
        }
    }

    private void getDependencies(microstamp.step2.data.Component component, ComponentDependenciesDto dependenciesDto, Set<Connection> seen) {
        dependenciesDto.getComponents().add(component);
        List<Connection> connectionsSource = connectionRepository.findConnectionsThatTheComponentIsSource(component.getId());
        for (Connection connection : connectionsSource) {
            if (!seen.contains(connection)) {
                dependenciesDto.getConnections().add(connection);
                seen.add(connection);
            }
        }

        List<Connection> connectionsTarget = connectionRepository.findConnectionsThatTheComponentIsTarget(component.getId());
        for (Connection connection : connectionsTarget) {
            if (!seen.contains(connection)) {
                dependenciesDto.getConnections().add(connection);
                seen.add(connection);
            }
        }

        dependenciesDto.getVariables().addAll(variableRepository.findByComponentId(component.getId()));

        List<microstamp.step2.data.Component> children = componentRepository.findChildrenByComponentId(component.getId());
        for (microstamp.step2.data.Component child : children)
            getDependencies(child, dependenciesDto, seen);
    }
}