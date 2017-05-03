package app.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by aleksei on 03.05.17.
 */

@Configuration
@EnableJpaRepositories(basePackages = {"app.repositories"})
public class JpaConfiguration {
}

