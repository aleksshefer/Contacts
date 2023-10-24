package ru.shefer.configuration;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.shefer.dao.ContactDao;


@Configuration
public class HibernateConfig {
    @Bean
    public SessionFactory sessionFactory() {
        return new org.hibernate.cfg.Configuration()
                .addAnnotatedClass(ContactDao.class)
                .configure()
                .buildSessionFactory();
    }
}


