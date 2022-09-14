package pl.kurs.java.creator;

import org.springframework.stereotype.Service;
import pl.kurs.java.model.inheritance.Rectangle;
import pl.kurs.java.model.inheritance.Shape;

import java.util.Map;

@Service
public class RectangleCreator implements ShapeCreator {

    @Override
    public String shape() {
        return "RECTANGLE";
    }
    @Override
    public Shape create(Map<String, Object> parameters) {
        return new Rectangle(getDoubleParameter("width", parameters),
                getDoubleParameter("height", parameters));
    }
}
