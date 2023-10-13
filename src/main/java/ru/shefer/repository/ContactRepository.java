package ru.shefer.repository;

import ru.shefer.dao.ContactDao;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ContactRepository {
    Optional<ContactDao> addContact(String firstName, String lastName, String phoneNUmber, String email);

    List<ContactDao> addAllContacts(Collection<ContactDao> contacts);

    Optional<ContactDao> getContact(long id);

    List<ContactDao> getAllContacts();

    Optional<ContactDao> setFirstName(long id, String firstName);

    Optional<ContactDao> setLastName(long id, String lastName);

    Optional<ContactDao> setPhoneNUmber(long id, String phoneNumber);

    Optional<ContactDao> setEmail(long id, String email);

    Optional<ContactDao> deleteContact(long id);
}
