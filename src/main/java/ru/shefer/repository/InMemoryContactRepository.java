package ru.shefer.repository;

import org.springframework.stereotype.Service;
import ru.shefer.dao.ContactDao;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class InMemoryContactRepository implements ContactRepository {
    private final HashMap<Long, ContactDao> contacts;

    public InMemoryContactRepository() {
        this.contacts = new HashMap<>();
    }

    @Override
    public Optional<ContactDao> addContact(String firstName, String lastName, String phoneNUmber, String email) {
        long id = contacts.size() + 1;
        ContactDao contact = new ContactDao(id, firstName, lastName, phoneNUmber, email);
        contacts.put(id, contact);

        return Optional.of(contact);
    }

    @Override
    public List<ContactDao> addAllContacts(Collection<ContactDao> contacts) {
        contacts.forEach(contactDao -> addContact(
                contactDao.getFirstName(),
                contactDao.getLastName(),
                contactDao.getPhoneNUmber(),
                contactDao.getEmail())
        );
        return getAllContacts();
    }

    @Override
    public Optional<ContactDao> getContact(long id) {
        ContactDao contact = contacts.get(id);

        return Optional.ofNullable(contact);
    }

    @Override
    public List<ContactDao> getAllContacts() {
        return contacts.values().stream().toList();
    }

    @Override
    public Optional<ContactDao> setFirstName(long id, String firstName) {
        ContactDao contact = contacts.get(id);
        contact.setFirstName(firstName);

        return Optional.of(contact);
    }

    @Override
    public Optional<ContactDao> setLastName(long id, String lastName) {
        ContactDao contact = contacts.get(id);
        contact.setLastName(lastName);

        return Optional.of(contact);
    }

    @Override
    public Optional<ContactDao> setPhoneNUmber(long id, String phoneNumber) {
        ContactDao contact = contacts.get(id);
        contact.setPhoneNUmber(phoneNumber);

        return Optional.of(contact);
    }

    @Override
    public Optional<ContactDao> setEmail(long id, String email) {
        ContactDao contact = contacts.get(id);
        contact.setEmail(email);

        return Optional.of(contact);
    }

    @Override
    public Optional<ContactDao> deleteContact(long id) {
        Optional<ContactDao> contactToDelete = getContact(id);

        contacts.remove(id);
        return contactToDelete;
    }
}
