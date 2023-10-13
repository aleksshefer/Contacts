package ru.shefer.converter;

import org.springframework.stereotype.Component;
import ru.shefer.dao.ContactDao;
import ru.shefer.dto.ContactDto;

@Component
public class ContactDaoToDtoConverter implements Converter<ContactDao, ContactDto> {
    @Override
    public ContactDto convert(ContactDao contactDao) {
        return new ContactDto(
                contactDao.getId(),
                contactDao.getFirstName(),
                contactDao.getLastName(),
                contactDao.getPhoneNumber(),
                contactDao.getEmail());
    }
}
