import { ToastModule } from "./notifications.js";

let toastTime = 8000;

const form = document.getElementById("submit-form");

const firstnameInput = document.getElementById("firstnameInput");
const lastnameInput = document.getElementById("lastnameInput");
const surnameInput = document.getElementById("surnameInput");

const emailInput = document.getElementById("emailInput");
const usernameInput = document.getElementById("usernameInput");

const passwordInput = document.getElementById("pwdInput");
const passwordConfirmationInput = document.getElementById("pwdconfirmInput");

const cityInput = document.getElementById("city");
const streetInput = document.getElementById("street");
const buildingInput = document.getElementById("building");
const houseNumberInput = document.getElementById("home");
const apartmentInput = document.getElementById("apartment");
const indexInput = document.getElementById("index");

const usernameGenerationButton = document.getElementById("rnd-btn");
const avatarInput = document.getElementById("imageprofileInput");
const avatarPreview = document.getElementById("uploadPreview");
const clearAvatarInputButton = document.getElementById("clear");
const notifyBox = document.getElementById("notify-box");
const previewBox = document.getElementById("preview-box");

window.addEventListener("load", () => {
    const body = document.getElementsByTagName("body")[0];
    const head = document.getElementsByTagName("head")[0];
    ToastModule.buyToaster(head, body);
});

usernameGenerationButton.addEventListener("click", async () => {
    let result = await callOutApi("getRandomNickname");
    usernameInput.value = result;
});

async function callOutApi(apiFunction) {
    let response = await fetch("http://localhost:8080/api/v1/" + apiFunction);
    let json = await response.json();
    return json;
}

const FIVE_MEGABYTES = 1024 * 1024 * 5;

avatarInput.addEventListener("change", () => {
    let file = avatarInput.files[0];
    if(file.size > FIVE_MEGABYTES) {
        ToastModule.makeToast("Размер файла не должен превышать 5МБ.");
        return;
    }
    previewBox.style.display = "grid";
    let reader = new FileReader();
    reader.onload = (e) => {
        avatarPreview.src = e.target.result;
    }
    reader.readAsDataURL(file);
});

clearAvatarInputButton.addEventListener("click", (e) => {
    avatarPreview.src = "";
    previewBox.style.display = "none";
});

function showError(message) {
    ToastModule.makeToast(message, toastTime, null, true);
}

function defaultRequiredFieldCheck(obj, min, max) {
    let error = false;
    if((obj.value == null) || (obj.value === "")) {
        showError(`Поле \'${obj.fieldName}\' не должно быть пустым.`);
        error = true;
    }
    if(obj.value.length > max) {
        showError(`Поле \'${obj.fieldName}\' не должно содержать более ${max} символов.`);
        error = true;
    }
    if(min != 0) {
        if(obj.value.length < min) {
            showError(`Поле \'${obj.fieldName}\' не должно содержать менее ${min} символов.`);
            error = true;
        }
    }
    // if(error) {
    //     obj.input.classList.add("input-error");
    // }
    return error;
}

form.addEventListener("submit", (e) => {
    let hasErrors = false;
    const firstname = {
        "fieldName": "Имя",
        "value": firstnameInput.value.trim(),
        "input": firstnameInput
    };
    const lastname = {
        "fieldName": "Фамилия",
        "value": lastnameInput.value.trim(),
        "input": lastnameInput
    };
    const surname = {
        "fieldName": "Отчество",
        "value": surnameInput.value.trim(),
        "input": surnameInput
    };
    const email = {
        "fieldName": "Почта",
        "value": emailInput.value.trim(),
        "input": emailInput
    };
    const username = {
        "fieldName": "Имя пользователя",
        "value": usernameInput.value.trim(),
        "input": usernameInput
    };
    const password = {
        "fieldName": "Пароль",
        "value": passwordInput.value.trim(),
        "input": passwordInput
    };
    const passwordConfrim = {
        "fieldName": "Подтверждение пароля",
        "value": passwordConfirmationInput.value.trim(),
        "input": passwordConfirmationInput
    };
    const city = {
        "fieldName": "Город",
        "value": cityInput.value.trim(),
        "input": cityInput
    };
    const street = {
        "fieldName": "Улица",
        "value": streetInput.value.trim(),
        "input": streetInput
    };
    const building = {
        "fieldName": "Строение",
        "value":  buildingInput.value.trim(),
        "input": buildingInput
    };
    const houseNumber = {
        "fieldName": "Дом",
        "value":  houseNumberInput.value.trim(),
        "input": houseNumberInput
    };
    const apartment = {
        "fieldName": "Квартира",
        "value":  apartmentInput.value.trim(),
        "input": apartmentInput
    };
    const index = {
        "fieldName": "Индекс",
        "value":  indexInput.value.trim(),
        "input": indexInput
    };

    let checkResult = defaultRequiredFieldCheck(firstname, 0, 25);
    hasErrors ||= checkResult;

    checkResult = defaultRequiredFieldCheck(lastname, 0, 50);
    hasErrors ||= checkResult;

    checkResult = defaultRequiredFieldCheck(email, 0, 256);
    hasErrors ||= checkResult;

    checkResult = defaultRequiredFieldCheck(username, 0, 20);
    hasErrors ||= checkResult;

    checkResult = defaultRequiredFieldCheck(password, 8, 32);
    hasErrors ||= checkResult;

    checkResult = defaultRequiredFieldCheck(passwordConfrim, 8, 32);
    hasErrors ||= checkResult;

    if(password.value !== passwordConfrim.value) {
        showError(`Поля \'${password.fieldName}\' и \'${passwordConfrim.fieldName}\' должны совпадать.`);
        hasErrors = true;
    }

    if(surname.value !== "") {
        checkResult = defaultRequiredFieldCheck(passwordConfrim, 0, 25);
    }

    let addressFields = 0;

    if(city.value !== "") {
        checkResult = defaultRequiredFieldCheck(city, 0, 15);
        if(!checkResult) {
            addressFields++;
        }
    }
    if(street.value !== "") {
        checkResult = defaultRequiredFieldCheck(street, 0, 25);
        if(!checkResult) {
            addressFields++;
        }
    }
    if(building.value !== "") {
        checkResult = defaultRequiredFieldCheck(building, 0, 10);
        if(!checkResult) {
            addressFields++;
        }
    }
    if(houseNumber.value !== "") {
        checkResult = defaultRequiredFieldCheck(houseNumber, 0, 5);
        if(!checkResult) {
            addressFields++;
        }
    }
    if(apartment.value !== "") {
        checkResult = defaultRequiredFieldCheck(apartment, 0, 3);
        if(!checkResult) {
            addressFields++;
        }
    }
    if(index.value !== "") {
        checkResult = defaultRequiredFieldCheck(index, 0, 6);
        if(!checkResult) {
            addressFields++;
        }
    }

    if(addressFields !== 0 && addressFields !== 6) {
        showError("Все поля адреса должны быть заполнены.");
        e.preventDefault();
        return;
    }

    if(hasErrors) {
        e.preventDefault();
    }
});