package configuration;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

/**
 * Created by Pikachu on 8/8/15.
 */
@Configuration
@EnableJpaRepositories(basePackages = "repository")
public class JpaConfiguration {
}
