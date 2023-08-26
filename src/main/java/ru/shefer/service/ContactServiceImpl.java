package ru.shefer.service;

import org.springframework.stereotype.Service;
import ru.shefer.converter.ContactDaoToDtoConverter;
import ru.shefer.dao.ContactDao;
import ru.shefer.dto.ContactDto;
import ru.shefer.repository.ContactRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ContactServiceImpl implements ContactService {
    private final ContactDaoToDtoConverter converter;
    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactDaoToDtoConverter converter, ContactRepository contactRepository) {
        this.converter = converter;
        this.contactRepository = contactRepository;
    }

    @Override
    public Optional<ContactDto> addContact(String firstName, String lastName, int phoneNUmber, String email) {
        Optional<ContactDao> contactDaoOptional = contactRepository.addContact(firstName, lastName, phoneNUmber, email);

        return contactDaoOptional.map(converter::convert);
    }

    @Override
    public Optional<ContactDto> getContact(long id) {
        Optional<ContactDao> contactDaoOptional = contactRepository.getContact(id);

        return contactDaoOptional.map(converter::convert);
    }

    @Override
    public List<ContactDto> getAllContacts() {
        return contactRepository.getAllContacts().stream().map(converter::convert).toList();
    }

    @Override
    public Optional<ContactDto> setFirstName(long id, String firstName) {
        Optional<ContactDao> contactDaoOptional = contactRepository.setFirstName(id, firstName);

        return contactDaoOptional.map(converter::convert);
    }

    @Override
    public Optional<ContactDto> setLastName(long id, String lastName) {
        Optional<ContactDao> contactDaoOptional = contactRepository.setLastName(id, lastName);

        return contactDaoOptional.map(converter::convert);
    }

    @Override
    public Optional<ContactDto> setPhoneNUmber(long id, int phoneNumber) {
        Optional<ContactDao> contactDaoOptional = contactRepository.setPhoneNUmber(id, phoneNumber);

        return contactDaoOptional.map(converter::convert);
    }

    @Override
    public Optional<ContactDto> setEmail(long id, String email) {
        Optional<ContactDao> contactDaoOptional = contactRepository.setEmail(id, email);

        return contactDaoOptional.map(converter::convert);
    }
}
