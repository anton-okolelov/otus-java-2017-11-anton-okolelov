
const loginWebSocket = new WebSocket("ws://" + document.location.host + "/ws");

const submitButton = document.getElementById('submit');
loginWebSocket.onopen = () => submitButton.removeAttribute('disabled');
loginWebSocket.onmessage = onMessage;

submitButton.addEventListener('click', onSubmit, false);

function onSubmit(event) {
    const login = document.getElementById('login').value;
    loginWebSocket.send(login);
    submitButton.setAttribute("disabled", "disabled"); //ждем ответа
}

function onMessage(event) {
    const userIdElement = document.getElementById('user_id');
    userIdElement.innerText = event.data;
    submitButton.removeAttribute("disabled"); 
}