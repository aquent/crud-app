package com.aquent.crudapp.client;

import com.aquent.crudapp.person.Person;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ContactsDto {
    private List<Person> contacts;

    public ContactsDto(List<Person> contacts) {
        this.contacts = contacts;
    }

    public void addContact(Person contact) {
        this.contacts.add(contact);
    }
}
