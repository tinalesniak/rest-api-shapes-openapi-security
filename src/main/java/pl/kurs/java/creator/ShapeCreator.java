package pl.kurs.java.creator;

import pl.kurs.java.model.inheritance.Shape;

import java.util.Map;

public interface ShapeCreator {

    String shape();
    Shape create(Map<String, Object> parameters);

    default Double getDoubleParameter(String type, Map<String, Object> parameters) {
        return (Double) parameters.get(type);
    }

}
