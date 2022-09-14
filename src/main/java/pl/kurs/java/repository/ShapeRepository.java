package pl.kurs.java.repository;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import pl.kurs.java.model.inheritance.Shape;
import pl.kurs.java.repository.custom.ExtendedQueryDslJpaRepository;

public interface ShapeRepository extends ExtendedQueryDslJpaRepository<Shape, Integer>, QuerydslPredicateExecutor<Shape> {
}
