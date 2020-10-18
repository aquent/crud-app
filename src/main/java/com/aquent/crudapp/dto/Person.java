package com.aquent.crudapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * The person entity corresponding to the "person" table in the database.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Person {

    private Integer personId;

    @NotNull
    @Size(min = 1, max = 20, message = "First name is required with maximum length of 20")
    private String firstName;

    @NotNull
    @Size(min = 1, max = 20, message = "Last name is required with maximum length of 20")
    private String lastName;

    @NotNull
    @Size(min = 1, max = 50, message = "Email address is required with maximum length of 50")
    private String emailAddress;

    @NotNull
    @Size(min = 1, max = 50, message = "Street address is required with maximum length of 50")
    private String streetAddress;

    @NotNull
    @Size(min = 1, max = 20, message = "City is required with maximum length of 20")
    private String city;

    @NotNull
    @Size(min = 2, max = 2, message = "State is required with length 2")
    private String state;

    @NotNull
    @Size(min = 5, max = 5, message = "Zip code is required with length 5")
    private String zipCode;

    private String clientName;
}
