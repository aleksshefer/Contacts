package ru.shefer.service;

import ru.shefer.dto.ContactDto;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface ContactService {
    Optional<ContactDto> addContact(String firstName, String lastName, String phoneNUmber, String email);

    List<ContactDto> addAllContacts(Collection<ContactDto> contacts);

    List<ContactDto> addContactsFromCSV(String path);

    Optional<ContactDto> getContact(long id);

    List<ContactDto> getAllContacts();

    Optional<ContactDto> setFirstName(long id, String firstName);

    Optional<ContactDto> setLastName(long id, String lastName);

    Optional<ContactDto> setPhoneNUmber(long id, String phoneNumber);

    Optional<ContactDto> setEmail(long id, String email);

    Optional<ContactDto> deleteContact(long id);
}
