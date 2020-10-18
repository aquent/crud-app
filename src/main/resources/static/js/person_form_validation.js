const form = document.getElementById("personForm")
const zipcode = document.getElementById("zipCode")
const zipCodeError = document.getElementById("zipCodeError")
const firstName = document.getElementById("firstName")
const firstNameError = document.getElementById("firstNameError")
const lastName = document.getElementById("lastName")
const lastNameError = document.getElementById("lastNameError")
const emailAddress = document.getElementById("emailAddress")
const emailAddressError = document.getElementById("emailAddressError")
const streetAddress = document.getElementById("streetAddress")
const streetAddressError = document.getElementById("streetAddressError")
const city = document.getElementById("city")
const cityError = document.getElementById("cityError")
const state = document.getElementById("state")
const stateError = document.getElementById("stateError")

city.addEventListener("blur", () => {
    if (city.value.length > 20) {
        city.setCustomValidity("City is required with maximum length of 20");
        cityError.innerText = "City is required with maximum length of 20";
        cityError.hidden = false;
    } else {
        city.setCustomValidity("");
        cityError.innerText = "";
        cityError.hidden = true;
    }
})

state.addEventListener("blur", () => {
    if (state.value.length > 2) {
        state.setCustomValidity("State is required with maximum length of 2");
        stateError.innerText = "State is required with maximum length of 2";
        stateError.hidden = false;
    } else {
        state.setCustomValidity("");
        stateError.innerText = "";
        stateError.hidden = true;
    }
})

streetAddress.addEventListener("blur", () => {
    if (streetAddress.value.length > 50) {
        streetAddress.setCustomValidity("Street Address is required with maximum length of 50");
        streetAddressError.innerText = "Street Address is required with maximum length of 50";
        streetAddressError.hidden = false;
    } else {
        streetAddress.setCustomValidity("");
        streetAddressError.innerText = "";
        streetAddressError.hidden = true;
    }
})

emailAddress.addEventListener("blur", () => {
    let emailPattern = /^[a-zA-Z0-9]+@[a-zA-z]+\.[a-z]{2,3}$/;
    if(!emailPattern.test(emailAddress.value)) {
        emailAddress.setCustomValidity("Email is not valid. Must be formatted like such: johndoe@example.com");
        emailAddressError.innerText = "Email is not valid. Must be formatted like such: johndoe@example.com";
        emailAddressError.hidden = false;
    } else if (emailAddress.value.length > 50) {
        emailAddress.setCustomValidity("Email address is required with maximum length of 50");
        emailAddressError.innerText = "Email address is required with maximum length of 50";
        emailAddressError.hidden = false;
    } else {
        emailAddress.setCustomValidity("");
        emailAddressError.innerText = "";
        emailAddressError.hidden = true;
    }
})

firstName.addEventListener("blur", () => {
    if (firstName.value.length > 20) {
        firstName.setCustomValidity("First Name must be a maximum of 20 characters");
        firstNameError.innerText = "First Name must be a maximum of 20 characters";
        firstNameError.hidden = false;
    } else {
        firstName.setCustomValidity("");
        firstNameError.innerText = "";
        firstNameError.hidden = true;
    }
})

lastName.addEventListener("blur", () => {
    if (lastName.value.length > 20) {
        lastName.setCustomValidity("Last Name must be a maximum of 20 characters");
        lastNameError.innerText = "Last Name must be a maximum of 20 characters";
        lastNameError.hidden = false;
    } else {
        lastName.setCustomValidity("")
        lastNameError.innerText = "";
        lastNameError.hidden = true;
    }
})

zipcode.addEventListener("blur", () => {
    if (zipcode.value.length < 5) {
        zipcode.setCustomValidity("Zipcode too short. Must be 5 characters.");
        zipCodeError.innerText = "Zipcode too short. Must be 5 characters."
        zipCodeError.hidden = false;
    } else if (zipcode.value.length > 5) {
        zipcode.setCustomValidity("Zipcode too long. Must be 5 characters.");
        zipCodeError.innerText = "Zipcode too long. Must be 5 characters."
        zipCodeError.hidden = false;
    } else if (!/\d{5}/.test(zipcode.value)) {
        zipcode.setCustomValidity("Zipcode must be only digits.")
        zipCodeError.innerText = "Zipcode too long. Must be 5 characters."
        zipCodeError.hidden = false;
    } else {
        zipcode.setCustomValidity("");
        zipCodeError.innerText = ""
        zipCodeError.hidden = true;
    }
})

form.addEventListener("submit", function(event) {
    let valid = true;
    let objects = [zipcode, lastName, city, state, streetAddress, firstName, emailAddress];
    let errors = [["Zip Code", zipCodeError],
        ["Last Name", lastNameError],
        ["City", cityError],
        ["State", stateError],
        ["Street Address", streetAddressError],
        ["First Name", firstNameError],
        ["Email", emailAddressError]];
    for (let i = 0; i < objects.length; i++) {
        let element = objects[i];
        if (element.value === "") {
            let error = errors[i][1];
            error.innerText = errors[i][0] + " is required. Please fill out the " + errors[i][0];
            error.hidden = false;
            valid = false;
        }
    }
    console.log(valid)
    if (!valid) {
        event.preventDefault();
    }
    return valid;
})