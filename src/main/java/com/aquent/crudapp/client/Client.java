package com.aquent.crudapp.client;

import com.aquent.crudapp.person.Person;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * The person entity corresponding to the "person" table in the database.
 */
// Injects Getters and Setters for you (via Lombok) https://projectlombok.org/features/Data
@Data
public class Client {

    private Integer clientId;

    @NotNull
    @Size(min = 1, max = 50, message = "Company name is required with maximum length of 50")
    private String clientName;

    @NotNull
    @Size(min = 1, max = 100, message = "Website URI is required with maximum length of 100")
    private String websiteUri;

    @NotNull
    @Size(min = 10, max = 10, message = "Phone number is required with length of 10")
    private String phoneNumber;

    @Size(min = 1, max = 50, message = "Street address is required with maximum length of 50")
    private String streetAddress;

    @NotNull
    @Size(min = 1, max = 50, message = "City is required with maximum length of 50")
    private String city;

    @NotNull
    @Size(min = 2, max = 2, message = "State is required with length 2")
    private String state;

    @NotNull
    @Size(min = 5, max = 5, message = "Zip code is required with length 5")
    private String zipCode;

    private List<Person> contacts;
}
