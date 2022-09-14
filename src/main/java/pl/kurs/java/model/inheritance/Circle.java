package pl.kurs.java.model.inheritance;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Circle extends Shape {

    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }
}
