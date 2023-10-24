package ru.shefer.repository;

import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.shefer.dao.ContactDao;

import java.util.*;

@Repository
public class JdbcContactRepository implements ContactRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private static final String GET_ALL_CONTACTS = "SELECT * FROM CONTACT";
    private static final String GET_CONTACT = "SELECT * FROM CONTACT WHERE ID = :id";
    private static final String INSERT_CONTACT = "INSERT INTO CONTACT(FIRST_NAME, LAST_NAME, PHONE_NUMBER, EMAIL) " +
            "VALUES(:firstName, :lastName, :phoneNumber, :email)";
    private static final String UPDATE_FIRST_NAME = "UPDATE CONTACT SET FIRST_NAME = :firstName WHERE ID = :id";
    private static final String UPDATE_LAST_NAME = "UPDATE CONTACT SET LAST_NAME = :lastName WHERE ID = :id";
    private static final String UPDATE_PHONE_NUMBER = "UPDATE CONTACT SET PHONE_NUMBER = :phoneNumber WHERE ID = :id";
    private static final String UPDATE_EMAIL = "UPDATE CONTACT SET EMAIL = :email WHERE ID = :id";
    private static final String DELETE_CONTACT = "DELETE FROM CONTACT WHERE ID = :id";

    private static final ResultSetExtractor<List<ContactDao>> RS_EXTRACTOR = rs -> {
        List<ContactDao> contactList = new ArrayList<>();
        while (rs.next()) {
            ContactDao contactDao = new ContactDao(
                    rs.getLong("ID"),
                    rs.getString("FIRST_NAME"),
                    rs.getString("LAST_NAME"),
                    rs.getString("PHONE_NUMBER"),
                    rs.getString("EMAIL")
            );
            contactList.add(contactDao);
        }
        return contactList;
    };
    private static final RowMapper<ContactDao> CONTACT_ROW_MAPPER = (rs, id) ->
            new ContactDao(
                    rs.getLong("ID"),
                    rs.getString("FIRST_NAME"),
                    rs.getString("LAST_NAME"),
                    rs.getString("PHONE_NUMBER"),
                    rs.getString("EMAIL")
            );

    public JdbcContactRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    public Optional<ContactDao> addContact(String firstName, String lastName, String phoneNumber, String email) {
        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(INSERT_CONTACT, new MapSqlParameterSource()
                        .addValue("firstName", firstName)
                        .addValue("lastName", lastName)
                        .addValue("phoneNumber", phoneNumber)
                        .addValue("email", email),
                keyHolder, new String[]{"id"});
        return Optional.of(new ContactDao(Objects.requireNonNull(keyHolder.getKey()).longValue(),
                firstName, lastName, phoneNumber, email));
    }

    @Override
    public List<ContactDao> addAllContacts(Collection<ContactDao> contacts) {
        MapSqlParameterSource[] mapSqlParameterSources = contacts.stream().map(contactDao ->
                new MapSqlParameterSource()
                        .addValue("firstName", contactDao.getFirstName())
                        .addValue("lastName", contactDao.getLastName())
                        .addValue("phoneNumber", contactDao.getPhoneNumber())
                        .addValue("email", contactDao.getEmail())
        ).toArray(MapSqlParameterSource[]::new);

        namedParameterJdbcTemplate.batchUpdate(INSERT_CONTACT, mapSqlParameterSources);
        return getAllContacts();
    }

    @Override
    public Optional<ContactDao> getContact(long id) {
        return Optional.ofNullable(namedParameterJdbcTemplate.queryForObject(GET_CONTACT,
                new MapSqlParameterSource("id", id),
                CONTACT_ROW_MAPPER));
    }

    @Override
    public List<ContactDao> getAllContacts() {
        return namedParameterJdbcTemplate.query(GET_ALL_CONTACTS, RS_EXTRACTOR);
    }

    @Override
    public Optional<ContactDao> setFirstName(long id, String firstName) {
        namedParameterJdbcTemplate.update(UPDATE_FIRST_NAME, new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("firstName", firstName));
        return getContact(id);
    }

    @Override
    public Optional<ContactDao> setLastName(long id, String lastName) {
        namedParameterJdbcTemplate.update(UPDATE_LAST_NAME, new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("lastName", lastName));
        return getContact(id);
    }

    @Override
    public Optional<ContactDao> setPhoneNUmber(long id, String phoneNumber) {
        namedParameterJdbcTemplate.update(UPDATE_PHONE_NUMBER, new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("phoneNumber", phoneNumber));
        return getContact(id);
    }

    @Override
    public Optional<ContactDao> setEmail(long id, String email) {
        namedParameterJdbcTemplate.update(UPDATE_EMAIL, new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("email", email));
        return getContact(id);
    }

    @Override
    public Optional<ContactDao> deleteContact(long id) {
        Optional<ContactDao> contactToDelete = getContact(id);
        namedParameterJdbcTemplate.update(DELETE_CONTACT, new MapSqlParameterSource("id", id));

        return contactToDelete;
    }
}
