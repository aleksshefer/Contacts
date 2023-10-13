package ru.shefer.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("ru.shefer")
@Import({JdbcConfig.class, PropertiesConfig.class})
public class ContactManagerConfig {
}
