package pl.kurs.java.repository.custom;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.jpa.repository.support.QuerydslJpaRepository;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.querydsl.SimpleEntityPathResolver;
import org.springframework.data.support.PageableExecutionUtils;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class ExtendedQueryDslJpaRepositoryImpl <T, ID extends Serializable>
        extends QuerydslJpaRepository<T, ID> implements ExtendedQueryDslJpaRepository<T, ID> {

    private static final EntityPathResolver DEFAULT_ENTITY_PATH_RESOLVER = SimpleEntityPathResolver.INSTANCE;

    private final EntityPath<T> path;
    private final PathBuilder<T> builder;
    private final Querydsl querydsl;

    private EntityManager entityManager;

    public ExtendedQueryDslJpaRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
        this(entityInformation, entityManager, DEFAULT_ENTITY_PATH_RESOLVER);
    }

    public ExtendedQueryDslJpaRepositoryImpl(JpaEntityInformation<T, ID> entityInformation,
                                             EntityManager entityManager, EntityPathResolver resolver) {

        super(entityInformation, entityManager);
        this.path = resolver.createPath(entityInformation.getJavaType());
        this.builder = new PathBuilder(this.path.getType(), this.path.getMetadata());
        this.querydsl = new Querydsl(entityManager, this.builder);
        this.entityManager = entityManager;
    }

    @Override
    public Page<T> findAll(JPQLQuery jpqlQuery, Pageable pageable) {

        // Count query
        final JPQLQuery<?> countQuery = jpqlQuery;

        // Apply pagination
        JPQLQuery<T> query = querydsl.applyPagination(pageable, jpqlQuery);

        // Run query
        return PageableExecutionUtils.getPage(query.fetch(), pageable, countQuery::fetchCount);
    }
}

