package ru.shefer.repository;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import ru.shefer.dao.ContactDao;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
@Primary
public class ContactRepositoryJpaAdapter implements ContactRepository {
    private final ContactRepositoryJPA contactRepositoryJPA;

    public ContactRepositoryJpaAdapter(ContactRepositoryJPA contactRepositoryJPA) {
        this.contactRepositoryJPA = contactRepositoryJPA;
    }

    @Override
    public Optional<ContactDao> addContact(String firstName, String lastName, String phoneNUmber, String email) {
        return Optional.of(contactRepositoryJPA.save(new ContactDao(firstName, lastName, phoneNUmber, email)));
    }

    @Override
    public List<ContactDao> addAllContacts(Collection<ContactDao> contacts) {
        return contactRepositoryJPA.saveAll(contacts);
    }

    @Override
    public Optional<ContactDao> getContact(long id) {
        return contactRepositoryJPA.findById(id);
    }

    @Override
    public List<ContactDao> getAllContacts() {
        return contactRepositoryJPA.findAll();
    }

    @Override
    public Optional<ContactDao> setFirstName(long id, String firstName) {
        Optional<ContactDao> contactToUpdate = getContact(id);

        if (contactToUpdate.isPresent()) {
            ContactDao contactDaoToUpdate = contactToUpdate.get();
            contactDaoToUpdate.setFirstName(firstName);
            return Optional.of(contactRepositoryJPA.save(contactDaoToUpdate));
        }
        return Optional.empty();
    }

    @Override
    public Optional<ContactDao> setLastName(long id, String lastName) {
        Optional<ContactDao> contactToUpdate = getContact(id);

        if (contactToUpdate.isPresent()) {
            ContactDao contactDaoToUpdate = contactToUpdate.get();
            contactDaoToUpdate.setLastName(lastName);
            return Optional.of(contactRepositoryJPA.save(contactDaoToUpdate));
        }
        return Optional.empty();
    }

    @Override
    public Optional<ContactDao> setPhoneNUmber(long id, String phoneNumber) {
        Optional<ContactDao> contactToUpdate = getContact(id);

        if (contactToUpdate.isPresent()) {
            ContactDao contactDaoToUpdate = contactToUpdate.get();
            contactDaoToUpdate.setPhoneNumber(phoneNumber);
            return Optional.of(contactRepositoryJPA.save(contactDaoToUpdate));
        }
        return Optional.empty();
    }

    @Override
    public Optional<ContactDao> setEmail(long id, String email) {
        Optional<ContactDao> contactToUpdate = getContact(id);

        if (contactToUpdate.isPresent()) {
            ContactDao contactDaoToUpdate = contactToUpdate.get();
            contactDaoToUpdate.setEmail(email);
            return Optional.of(contactRepositoryJPA.save(contactDaoToUpdate));
        }
        return Optional.empty();
    }

    @Override
    public Optional<ContactDao> deleteContact(long id) {
        Optional<ContactDao> contactToDelete = getContact(id);

        if (contactToDelete.isPresent()) {
            contactRepositoryJPA.delete(contactToDelete.get());
            return contactToDelete;
        }
        return Optional.empty();
    }
}
