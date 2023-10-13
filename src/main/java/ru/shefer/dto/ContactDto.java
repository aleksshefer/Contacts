package ru.shefer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ContactDto {
    @JsonProperty("contactId")
    private long id;
    @JsonProperty("firstName")
    private String firstName;
    @JsonProperty("lastName")
    private String lastName;
    @JsonProperty("phoneNumber")
    private String phoneNUmber;
    @JsonProperty("email")
    private String email;

    public ContactDto(long id, String firstName, String lastName, String phoneNUmber, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNUmber = phoneNUmber;
        this.email = email;
    }

    public ContactDto(String firstName, String lastName, String phoneNUmber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNUmber = phoneNUmber;
        this.email = email;
    }


    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNUmber() {
        return phoneNUmber;
    }

    public void setPhoneNUmber(String phoneNUmber) {
        this.phoneNUmber = phoneNUmber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
