package pl.kurs.java.service;

import com.querydsl.jpa.JPQLQuery;
import com.querydsl.jpa.impl.JPAQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import pl.kurs.java.factor.ShapeFactory;
import pl.kurs.java.model.command.CreateShapeCommand;
import pl.kurs.java.model.inheritance.QShape;
import pl.kurs.java.model.inheritance.Shape;
import pl.kurs.java.query.FindShapeQuery;
import pl.kurs.java.repository.ShapeRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ShapeService {

    @PersistenceContext
    private EntityManager entityManager;
    private final ShapeRepository shapeRepository;
    private final ShapeFactory factory;

    public Shape saveShape(CreateShapeCommand command) {
        return shapeRepository.saveAndFlush(factory.create(command));
    }

    @Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
    public List<Shape> findShapes(FindShapeQuery findShapeQuery) {

        QShape shape = QShape.shape;
        JPQLQuery query = new JPAQuery(entityManager);

        query.from(shape)
                .fetchAll()
                .where(findShapeQuery.toPredicate());

        return shapeRepository.findAll(query, PageRequest.of(0, Integer.MAX_VALUE)).getContent();
    }
}
