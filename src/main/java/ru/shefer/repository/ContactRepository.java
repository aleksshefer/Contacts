package ru.shefer.repository;

import ru.shefer.dao.ContactDao;

import java.util.List;
import java.util.Optional;

public interface ContactRepository {
    Optional<ContactDao> addContact(String firstName, String lastName, int phoneNUmber, String email);

    Optional<ContactDao> getContact(long id);

    List<ContactDao> getAllContacts();

    Optional<ContactDao> setFirstName(long id, String firstName);

    Optional<ContactDao> setLastName(long id, String lastName);

    Optional<ContactDao> setPhoneNUmber(long id, int phoneNumber);

    Optional<ContactDao> setEmail(long id, String email);
}
