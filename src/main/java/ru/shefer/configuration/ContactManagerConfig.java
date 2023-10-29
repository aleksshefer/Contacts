package ru.shefer.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan("ru.shefer")
@EnableJpaRepositories(basePackages = "ru.shefer", entityManagerFactoryRef = "sessionFactory")
@Import({JdbcConfig.class, PropertiesConfig.class})
public class ContactManagerConfig {
}
