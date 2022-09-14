package pl.kurs.java.query;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.NumberExpression;
import pl.kurs.java.model.inheritance.QCircle;

import java.util.Optional;

public class FindCircleQuery extends FindShapeQuery {

    private Double radiusFrom;
    private Double radiusTo;

    private Double areaFrom;
    private Double areaTo;

    private Double perimiterFrom;
    private Double perimiterTo;

    QCircle qCircle = QCircle.circle;

    @Override
    public Predicate toPredicate() {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        Optional.ofNullable(radiusFrom).map(qCircle.radius::goe).ifPresent(booleanBuilder::and);
        Optional.ofNullable(radiusTo).map(qCircle.radius::loe).ifPresent(booleanBuilder::and);
        Optional.ofNullable(areaFrom).map(countArea()::goe).ifPresent(booleanBuilder::and);
        Optional.ofNullable(areaTo).map(countArea()::loe).ifPresent(booleanBuilder::and);
        Optional.ofNullable(perimiterFrom).map(countPerimiter()::goe).ifPresent(booleanBuilder::and);
        Optional.ofNullable(perimiterTo).map(countPerimiter()::loe).ifPresent(booleanBuilder::and);

        return booleanBuilder;
    }

    @Override
    public NumberExpression<Double> countArea() {return qCircle.radius.multiply(qCircle.radius).multiply(Math.PI);}

    @Override
    public NumberExpression<Double> countPerimiter() {return qCircle.radius.multiply(2).multiply(Math.PI);}

}