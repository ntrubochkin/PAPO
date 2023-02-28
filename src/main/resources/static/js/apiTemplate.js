//TODO этот файлик потом удалится совсем / сильно изменится

const baseUrl = "http://localhost:8080/api/v1";

const api = {
    someUrl: baseUrl + "/some_path/"
};

//TODO это шаблон
async function makeRequest(url) {
    let response = await fetch(url, {
        method: "post",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        }
    });
    let json = await response.json();
    //TODO
}