let data2server = {};

window.onload = () => {
    let hash = window.location.hash;
    if(hash === "") {
        return;
    }
    let params = getParams(hash);
    if(params.hasOwnProperty("error")) {
        window.location.replace("http://localhost:8080/registration");
    }
    data2server.email = params["email"];
    let head = document.getElementsByTagName("head")[0];
    // https://dev.vk.com/api/api-requests
    // https://dev.vk.com/method/users.get
    let apiParams = {
        "access_token": params["access_token"],
        "user_ids": params["user_id"],
        "fields": "city,country,has_photo,photo_400_orig,nickname,domain",
        "v": "5.131",
        "callback": "APICallback"
    };
    let url = buildURL("users.get", apiParams);
    VkontakteAPICall(head, url);
}

function getParams(hash) {
    if(hash.charAt(0) == '#') {
        hash = hash.substring(1);
    }
    let params = hash.split("&");
    let map = {};
    params.forEach((item) => {
        let keyValue = item.split("=");
        map[keyValue[0]] = keyValue[1];
    });
    return map;
}

function buildURLParams(map, encodeComponent) {
    let pairs = [];
    Object.keys(map).forEach((key) => {
        let value = map[key].toString();
        if(encodeComponent) {
            value = encodeURIComponent(value).replace("%20", "+");
        }
        pairs.push(`${key}=${value}`);
    });
    let params = pairs.join("&");
    return params;
}

function buildURL(method, params) {
    let result = `https://api.vk.com/method/${method}?`;
    result += buildURLParams(params);
    return result;
}

function VkontakteAPICall(head, url) {
    // https://ru.wikipedia.org/wiki/JSONP
    let script = document.createElement("script");
    script.src = url;
    let node = head.appendChild(script);
    //TODO удалить скрипт (хотя можно и не делать этого)
}

async function APICallback(result) {
    function getRightSizeUsername(str) {
        if(str.length > 20) {
            return str.slice(0, 20);
        }
        return str;
    }
    result = result.response[0];
    data2server.firstName = result.first_name;
    data2server.lastName = result.last_name;
    data2server.hasAvatar = result.has_photo;
    data2server.avatarURL = (data2server.hasAvatar ? result.photo_400_orig : "");
    data2server.userName = (result.nickname === "" ? getRightSizeUsername(result.domain) : getRightSizeUsername(result.nickname));
    await sendToServer(data2server);
}

async function sendToServer(data) {
    let params = buildURLParams(data, true);
    let response = await fetch(`http://localhost:8080/oauth/vk/eat-data?${params}`, {
        method: "post",
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        }
    });
    let json = await response.json();
    let status = json["status"];
    if(status === "created" || status === "exists") {
        window.location.replace("http://localhost:8080/home");
    } else {
        //TODO
        window.location.replace("http://localhost:8080/login");
    }
}