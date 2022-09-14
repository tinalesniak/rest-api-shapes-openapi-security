package pl.kurs.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import pl.kurs.java.repository.custom.ExtendedQueryDslJpaRepositoryImpl;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = RestApiShapesOpenapiSecurityApplication.class, repositoryBaseClass = ExtendedQueryDslJpaRepositoryImpl.class)
public class RestApiShapesOpenapiSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApiShapesOpenapiSecurityApplication.class, args);
    }

}
