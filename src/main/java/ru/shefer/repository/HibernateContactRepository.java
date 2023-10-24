package ru.shefer.repository;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ru.shefer.dao.ContactDao;


import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class HibernateContactRepository implements ContactRepository {
    private final SessionFactory sessionFactory;

    public HibernateContactRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Optional<ContactDao> addContact(String firstName, String lastName, String phoneNUmber, String email) {
        ContactDao contact;
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            contact = new ContactDao(firstName, lastName, phoneNUmber, email);
            long id = (long) session.save(contact);
            transaction.commit();

            contact.setId(id);
        }
        return Optional.of(contact);
    }

    @Override
    public List<ContactDao> addAllContacts(Collection<ContactDao> contacts) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            int i = 0;
            for (ContactDao contact : contacts) {
                session.persist(contact);
                if (i % 50 == 0 && i > 0) {
                    session.flush();
                    session.clear();
                }
                i++;
            }
            transaction.commit();
        }
        return getAllContacts();
    }

    @Override
    public Optional<ContactDao> getContact(long id) {
        try (Session session = sessionFactory.openSession()) {
            return Optional.ofNullable(session.get(ContactDao.class, id));
        }
    }

    @Override
    public List<ContactDao> getAllContacts() {
        try (Session session = sessionFactory.openSession()) {
            return session.createQuery("SELECT c FROM ContactDao c", ContactDao.class).getResultList();
        }
    }

    @Override
    public Optional<ContactDao> setFirstName(long id, String firstName) {
        ContactDao contactToUpdate;
        try (Session session = sessionFactory.openSession()) {
            contactToUpdate = session.get(ContactDao.class, id);
            contactToUpdate.setFirstName(firstName);
            Transaction transaction = session.beginTransaction();
            session.persist(contactToUpdate);
            transaction.commit();
        }
        return Optional.of(contactToUpdate);
    }

    @Override
    public Optional<ContactDao> setLastName(long id, String lastName) {
        ContactDao contactToUpdate;
        try (Session session = sessionFactory.openSession()) {
            contactToUpdate = session.get(ContactDao.class, id);
            contactToUpdate.setLastName(lastName);
            Transaction transaction = session.beginTransaction();
            session.persist(contactToUpdate);
            transaction.commit();
        }
        return Optional.of(contactToUpdate);
    }

    @Override
    public Optional<ContactDao> setPhoneNUmber(long id, String phoneNumber) {
        ContactDao contactToUpdate;
        try (Session session = sessionFactory.openSession()) {
            contactToUpdate = session.get(ContactDao.class, id);
            contactToUpdate.setPhoneNumber(phoneNumber);
            Transaction transaction = session.beginTransaction();
            session.persist(contactToUpdate);
            transaction.commit();
        }
        return Optional.of(contactToUpdate);
    }

    @Override
    public Optional<ContactDao> setEmail(long id, String email) {
        ContactDao contactToUpdate;
        try (Session session = sessionFactory.openSession()) {
            contactToUpdate = session.get(ContactDao.class, id);
            contactToUpdate.setEmail(email);
            Transaction transaction = session.beginTransaction();
            session.persist(contactToUpdate);
            transaction.commit();
        }
        return Optional.of(contactToUpdate);
    }

    @Override
    public Optional<ContactDao> deleteContact(long id) {
        try (Session session = sessionFactory.openSession()) {
            ContactDao contactToDelete = session.get(ContactDao.class, id);
            if (contactToDelete != null) {
                Transaction transaction = session.beginTransaction();
                session.delete(contactToDelete);
                transaction.commit();
                return Optional.of(contactToDelete);
            }
        }
        return Optional.empty();
    }
}
