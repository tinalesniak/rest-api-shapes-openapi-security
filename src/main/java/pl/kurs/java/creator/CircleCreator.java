package pl.kurs.java.creator;

import org.springframework.stereotype.Service;
import pl.kurs.java.model.inheritance.Circle;
import pl.kurs.java.model.inheritance.Shape;

import java.util.Map;

@Service
public class CircleCreator implements ShapeCreator {

    @Override
    public String shape() {
        return "CIRCLE";
    }

    @Override
    public Shape create(Map<String, Object> parameters) {
        return new Circle(getDoubleParameter("radius", parameters));
    }
}
