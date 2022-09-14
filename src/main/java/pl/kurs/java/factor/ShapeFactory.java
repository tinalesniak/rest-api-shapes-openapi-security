package pl.kurs.java.factor;

import org.springframework.stereotype.Service;
import pl.kurs.java.creator.ShapeCreator;
import pl.kurs.java.model.command.CreateShapeCommand;
import pl.kurs.java.model.inheritance.Shape;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class ShapeFactory {

    private final Map<String, ShapeCreator> creators;

    public ShapeFactory(Set<ShapeCreator> creators) {
        this.creators = creators.stream()
                .collect(Collectors.toMap(ShapeCreator::shape, Function.identity()));
    }

    public Shape create(CreateShapeCommand command) {
        return creators.get(command.getType()).create(command.getParameters());
    }
}
