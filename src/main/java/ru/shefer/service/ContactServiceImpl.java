package ru.shefer.service;

import org.springframework.stereotype.Service;
import ru.shefer.converter.ContactDaoToDtoConverter;
import ru.shefer.converter.ContactDtoToDaoConverter;
import ru.shefer.dao.ContactDao;
import ru.shefer.dto.ContactDto;
import ru.shefer.repository.ContactRepository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {
    private final ContactDaoToDtoConverter contactDaoToDtoConverter;
    private final ContactDtoToDaoConverter contactDtoToDaoConverter;
    private final ContactRepository contactRepository;

    public ContactServiceImpl(ContactDaoToDtoConverter converter, ContactDtoToDaoConverter contactDtoToDaoConverter, ContactRepository contactRepository) {
        this.contactDaoToDtoConverter = converter;
        this.contactDtoToDaoConverter = contactDtoToDaoConverter;
        this.contactRepository = contactRepository;
    }

    @Override
    public Optional<ContactDto> addContact(String firstName, String lastName, String phoneNUmber, String email) {
        Optional<ContactDao> contactDaoOptional = contactRepository.addContact(firstName, lastName, phoneNUmber, email);

        return contactDaoOptional.map(contactDaoToDtoConverter::convert);
    }

    @Override
    public List<ContactDto> addAllContacts(Collection<ContactDto> contacts) {
        return contactRepository.addAllContacts(contacts
                        .stream()
                        .map(contactDtoToDaoConverter::convert)
                        .collect(Collectors.toList()))
                .stream()
                .map(contactDaoToDtoConverter::convert)
                .toList();
    }

    @Override
    public List<ContactDto> addContactsFromCSV(String path) {
        List<String> contactDtoStringParametersList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            while (bufferedReader.ready()) {
                contactDtoStringParametersList.add(bufferedReader.readLine());
            }
        } catch (IOException e) {
            throw new RuntimeException("Exception while reading the file along the path: " + path, e);
        }
        return addAllContacts(contactDtoStringParametersList
                .stream()
                .map(contactDtoParameter -> {
                    //contactDtoParametersArray = {"firstName", "lastName,phoneNumber,email"}
                    String[] contactDtoParametersArray = contactDtoParameter.split(" ");
                    //contactDtoAnotherParametersArray = {"lastName", "phoneNumber", "email"}
                    String[] contactDtoAnotherParametersArray = contactDtoParametersArray[1].split(",");
                    return new ContactDto(contactDtoParametersArray[0],
                            contactDtoAnotherParametersArray[0],
                            contactDtoAnotherParametersArray[1],
                            contactDtoAnotherParametersArray[2]
                    );
                }).toList());

    }

    @Override
    public Optional<ContactDto> getContact(long id) {
        Optional<ContactDao> contactDaoOptional = contactRepository.getContact(id);

        return contactDaoOptional.map(contactDaoToDtoConverter::convert);
    }

    @Override
    public List<ContactDto> getAllContacts() {
        return contactRepository.getAllContacts().stream().map(contactDaoToDtoConverter::convert).toList();
    }

    @Override
    public Optional<ContactDto> setFirstName(long id, String firstName) {
        Optional<ContactDao> contactDaoOptional = contactRepository.setFirstName(id, firstName);

        return contactDaoOptional.map(contactDaoToDtoConverter::convert);
    }

    @Override
    public Optional<ContactDto> setLastName(long id, String lastName) {
        Optional<ContactDao> contactDaoOptional = contactRepository.setLastName(id, lastName);

        return contactDaoOptional.map(contactDaoToDtoConverter::convert);
    }

    @Override
    public Optional<ContactDto> setPhoneNUmber(long id, String phoneNumber) {
        Optional<ContactDao> contactDaoOptional = contactRepository.setPhoneNUmber(id, phoneNumber);

        return contactDaoOptional.map(contactDaoToDtoConverter::convert);
    }

    @Override
    public Optional<ContactDto> setEmail(long id, String email) {
        Optional<ContactDao> contactDaoOptional = contactRepository.setEmail(id, email);

        return contactDaoOptional.map(contactDaoToDtoConverter::convert);
    }

    @Override
    public Optional<ContactDto> deleteContact(long id) {
        Optional<ContactDao> contactDaoToDelete = contactRepository.deleteContact(id);

        return contactDaoToDelete.map(contactDaoToDtoConverter::convert);
    }
}
