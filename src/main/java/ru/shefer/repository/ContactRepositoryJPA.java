package ru.shefer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.shefer.dao.ContactDao;

public interface ContactRepositoryJPA extends JpaRepository<ContactDao, Long> {
}
