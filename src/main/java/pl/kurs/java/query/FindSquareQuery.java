package pl.kurs.java.query;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.NumberExpression;
import pl.kurs.java.model.inheritance.QSquare;

import java.util.Optional;

public class FindSquareQuery extends FindShapeQuery {

    private Double widthFrom;
    private Double widthTo;

    private Double areaFrom;
    private Double areaTo;

    private Double perimiterFrom;
    private Double perimiterTo;

    QSquare qSquare = QSquare.square;

    @Override
    public Predicate toPredicate() {
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        Optional.ofNullable(widthFrom).map(qSquare.width::goe).ifPresent(booleanBuilder::and);
        Optional.ofNullable(widthTo).map(qSquare.width::loe).ifPresent(booleanBuilder::and);
        Optional.ofNullable(areaFrom).map(countArea()::goe).ifPresent(booleanBuilder::and);
        Optional.ofNullable(areaTo).map(countArea()::loe).ifPresent(booleanBuilder::and);
        Optional.ofNullable(perimiterFrom).map(countPerimiter()::goe).ifPresent(booleanBuilder::and);
        Optional.ofNullable(perimiterTo).map(countPerimiter()::loe).ifPresent(booleanBuilder::and);

        return booleanBuilder;
    }

    @Override
    public NumberExpression<Double> countArea() {return qSquare.width.multiply(qSquare.width);}

    @Override
    public NumberExpression<Double> countPerimiter() {return qSquare.width.multiply(4);}
}