
const formInputs = [
    {
        name: "firstName",
        rule: /^.{1,50}$/
    },
    {
        name: "lastName",
        rule: /^.{1,50}$/
    },
    {
        name: "emailAddress",
        rule: /^[a-z0-9._%+-]+@[a-z0-9.-]+$/
    },
    {
        name: "streetAddress",
        rule: /^.{1,50}$/
    },
    {
        name: "city",
        rule: /^.{1,50}$/
    },
    {
        name: "state",
        rule: /^.{1,2}$/
    },
    {
        name: "zipCode",
        rule: /^.{1,5}$/
    },
];

formInputs.forEach((formInput) => {
    document.getElementsByName(formInput.name)[0].addEventListener("change", function() {validateChange(formInput)});
});



//test!
document.getElementsByTagName("nav")[0].classList.add("test2");


function validateChange(formInput){
    let inputElement = document.getElementsByName(formInput.name)[0];
    let inputGroup = inputElement.parentElement;

    if (inputElement.value.match(formInput.rule)) {
        inputGroup.classList.remove("has-error");
        if (!document.getElementsByClassName("has-error")[0]) 
            document.querySelector("input[name='Submit']").disabled = false;
        return true;
    } else {
        inputGroup.classList.add("has-error");
        document.querySelector("input[name='Submit']").disabled = true;
        return false;
    }
}