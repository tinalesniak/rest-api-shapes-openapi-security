package pl.kurs.java.creator;

import org.springframework.stereotype.Service;
import pl.kurs.java.model.inheritance.Shape;
import pl.kurs.java.model.inheritance.Square;

import java.util.Map;

@Service
public class SquareCreator implements ShapeCreator{

    @Override
    public String shape() {
        return "SQUARE";
    }
    @Override
    public Shape create(Map<String, Object> parameters) {
        return new Square(getDoubleParameter("width", parameters));
    }
}
