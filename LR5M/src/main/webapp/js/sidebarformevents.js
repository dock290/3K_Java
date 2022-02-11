let regExpIsDigitsOnly = /^\d+$/;

let isChanged = false;

function isValidFormNumber() {
    let element = document.getElementById("sidebar-form-input-number");

    if (!regExpIsDigitsOnly.test(element.value)) {
        alert("Необходимо ввести число в поле \"Номер модели\"");

        return false;
    } else {
        return true;
    }
}

function isValidFormName() {
    let element = document.getElementById("sidebar-form-input-name");

    if (!element.value) {
        alert("Необходимо ввести название в поле \"Название телефона\"");

        return false;
    } else {
        return true;
    }
}

function sidebarFormInputNameOnFocus() {
    if (!isChanged) {
        document.getElementById("sidebar-form-input-name").value = "";
    }
}

function sidebarFormInputNameOnChange() {
    isChanged = true;
}

function sidebarFormInputNameOnBlur() {
    let element = document.getElementById("sidebar-form-input-name");
    
    if (!element.value) {
        element.value = "Nokia 6131";
        isChanged = false;
    }
}
