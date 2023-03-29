const popup = document.getElementById("notification-popup");
const popupCloseBtn = document.getElementById("close-popup");
const exitBtn = document.getElementById("exit-btn");

if(popup != null) {
    popupCloseBtn.addEventListener("click", () => {
        popup.parentElement.removeChild(popup);
    });
}

if(exitBtn != null) {
    exitBtn.addEventListener("click", () => {
        window.location.replace("http://localhost:8080/logout");
    });
}