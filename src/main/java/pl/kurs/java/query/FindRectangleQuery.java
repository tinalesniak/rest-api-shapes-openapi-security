package pl.kurs.java.query;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.NumberExpression;
import pl.kurs.java.model.inheritance.QRectangle;

import java.util.Optional;

public class FindRectangleQuery extends FindShapeQuery {

    private Double widthFrom;
    private Double widthTo;

    private Double heightFrom;
    private Double heightTo;

    private Double areaFrom;
    private Double areaTo;

    private Double perimiterFrom;
    private Double perimiterTo;

    QRectangle qRectangle = QRectangle.rectangle;

    @Override
    public Predicate toPredicate() {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        Optional.ofNullable(heightFrom).map(qRectangle.height::goe).ifPresent(booleanBuilder::and);
        Optional.ofNullable(heightTo).map(qRectangle.height::loe).ifPresent(booleanBuilder::and);
        Optional.ofNullable(widthFrom).map(qRectangle.width::goe).ifPresent(booleanBuilder::and);
        Optional.ofNullable(widthTo).map(qRectangle.width::loe).ifPresent(booleanBuilder::and);
        Optional.ofNullable(areaFrom).map(countArea()::goe).ifPresent(booleanBuilder::and);
        Optional.ofNullable(areaTo).map(countArea()::loe).ifPresent(booleanBuilder::and);
        Optional.ofNullable(perimiterFrom).map(countPerimiter()::goe).ifPresent(booleanBuilder::and);
        Optional.ofNullable(perimiterTo).map(countPerimiter()::loe).ifPresent(booleanBuilder::and);

        return booleanBuilder;
    }
    @Override
    public NumberExpression<Double> countArea() {return qRectangle.height.multiply(qRectangle.width);}

    @Override
    public NumberExpression<Double> countPerimiter() {return qRectangle.height.multiply(2).add(qRectangle.width.multiply(2));}
}