package pl.kurs.java.model.inheritance;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Square extends Shape {
    private double width;

    public Square(double width) {
        this.width = width;
    }
}
