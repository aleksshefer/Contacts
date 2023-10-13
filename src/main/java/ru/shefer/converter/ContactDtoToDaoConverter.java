package ru.shefer.converter;

import org.springframework.stereotype.Component;
import ru.shefer.dao.ContactDao;
import ru.shefer.dto.ContactDto;

@Component
public class ContactDtoToDaoConverter implements Converter<ContactDto, ContactDao> {

    @Override
    public ContactDao convert(ContactDto contactDto) {
        return new ContactDao(
                contactDto.getId(),
                contactDto.getFirstName(),
                contactDto.getLastName(),
                contactDto.getPhoneNUmber(),
                contactDto.getEmail());
    }
}
