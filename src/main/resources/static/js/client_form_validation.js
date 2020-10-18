const form = document.getElementById("clientForm")
const zipcode = document.getElementById("zipCode")
const zipCodeError = document.getElementById("zipCodeError")
const phoneNumber = document.getElementById("phoneNumber")
const phoneNumberError = document.getElementById("phoneNumberError")
const clientName = document.getElementById("clientName")
const clientNameError = document.getElementById("clientNameError")
const websiteUri = document.getElementById("websiteUri")
const websiteUriError = document.getElementById("websiteUriError")
const streetAddress = document.getElementById("streetAddress")
const streetAddressError = document.getElementById("streetAddressError")
const city = document.getElementById("city")
const cityError = document.getElementById("cityError")
const state = document.getElementById("state")
const stateError = document.getElementById("stateError")

city.addEventListener("blur", () => {
    if (city.value.length > 50) {
        city.setCustomValidity("City is required with maximum length of 50");
        cityError.innerText = "City is required with maximum length of 50";
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

websiteUri.addEventListener("blur", () => {
    if (websiteUri.validity.typeMismatch) {
        websiteUri.setCustomValidity("Website URI is not valid. Must be formatted like such: http://fakesite.com");
        websiteUriError.innerText = "Website URI is not valid. Must be formatted like such: http://fakesite.com";
        websiteUriError.hidden = false;
    } else if (websiteUri.value.length > 100) {
        websiteUri.setCustomValidity("Website URI is required with maximum length of 100");
        websiteUriError.innerText = "Website URI is required with maximum length of 100";
        websiteUriError.hidden = false;
    } else {
        websiteUri.setCustomValidity("");
        websiteUriError.innerText = "";
        websiteUriError.hidden = true;
    }
})

clientName.addEventListener("blur", () => {
    if (clientName.value.length > 50) {
        clientName.setCustomValidity("Client Name must be a maximum of 50 characters");
        clientNameError.innerText = "Client Name must be a maximum of 50 characters";
        clientNameError.hidden = false;
    } else {
        clientName.setCustomValidity("");
        clientNameError.innerText = "";
        clientNameError.hidden = true;
    }
})

phoneNumber.addEventListener("blur", () => {
    let phoneNumberPattern = /^[2-9]\d{2}-\d{3}-\d{4}$/;
    if (!phoneNumberPattern.test(phoneNumber.value)) {
        phoneNumber.setCustomValidity("Phone number must be of the format ###-###-####");
        phoneNumberError.innerText = "Phone number must be of the format ###-###-####";
        phoneNumberError.hidden = false;
    } else {
        phoneNumber.setCustomValidity("")
        phoneNumberError.innerText = "";
        phoneNumberError.hidden = true;
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
    // event.preventDefault()
    let valid = true;
    let objects = [zipcode, phoneNumber, city, state, streetAddress, clientName, websiteUri];
    let errors = [["Zip Code", zipCodeError],
        ["Phone Number", phoneNumberError],
        ["City", cityError],
        ["State", stateError],
        ["Street Address", streetAddressError],
        ["Client Name", clientNameError],
        ["Website", websiteUriError]];
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