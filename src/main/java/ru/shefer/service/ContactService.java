package ru.shefer.service;

import ru.shefer.dto.ContactDto;

import java.util.List;
import java.util.Optional;

public interface ContactService {
    Optional<ContactDto> addContact(String firstName, String lastName, int phoneNUmber, String email);

    Optional<ContactDto> getContact(long id);

    List<ContactDto> getAllContacts();

    Optional<ContactDto> setFirstName(long id, String firstName);

    Optional<ContactDto> setLastName(long id, String lastName);

    Optional<ContactDto> setPhoneNUmber(long id, int phoneNumber);

    Optional<ContactDto> setEmail(long id, String email);
}
