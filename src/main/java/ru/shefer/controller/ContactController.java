package ru.shefer.controller;

import org.springframework.web.bind.annotation.*;
import ru.shefer.dto.ContactDto;
import ru.shefer.service.ContactService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/contacts")
public class ContactController {
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping
    public Optional<ContactDto> addContact(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam int phoneNumber,
            @RequestParam String email
    ) {
        return contactService.addContact(firstName, lastName, phoneNumber, email);
    }

    @GetMapping("/{contactId}")
    public Optional<ContactDto> getContact(
            @PathVariable long contactId
    ) {
        System.out.println(contactId);
        return contactService.getContact(contactId);
    }

    @GetMapping("/allContacts")
    public List<ContactDto> getAllContacts() {
        return contactService.getAllContacts();
    }

    @PutMapping("/setFirstName/{contactId}/{firstName}")
    public Optional<ContactDto> setFirstName(
            @PathVariable long contactId,
            @PathVariable String firstName
    ) {

        return contactService.setFirstName(contactId, firstName);
    }

    @PutMapping("/setLastName/{contactId}/{lastName}")
    public Optional<ContactDto> setLastName(
            @PathVariable long contactId,
            @PathVariable String lastName
    ) {

        return contactService.setLastName(contactId, lastName);
    }

    @PutMapping("/setPhoneNumber/{contactId}/{phoneNumber}")
    public Optional<ContactDto> setLastName(
            @PathVariable long contactId,
            @PathVariable int phoneNumber
    ) {

        return contactService.setPhoneNUmber(contactId, phoneNumber);
    }

    @PutMapping("/setEmail/{contactId}/{email}")
    public Optional<ContactDto> setEmail(
            @PathVariable long contactId,
            @PathVariable String email
    ) {

        return contactService.setEmail(contactId, email);
    }
}
