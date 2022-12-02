// This file should just receive the messages from the server and output a batch of messages to the client
let params = new URLSearchParams(location.search);
let username = params.get('username');
let socket = new WebSocket("ws://localhost:8080/CSCI201_Final_Project/chatroomServerEndpoint/" + username);

// Listening for the web socket to open
socket.addEventListener('open', (e) => {
    console.log(e);
    console.log(location.href);
    console.log(location.search);

    // let params = new URLSearchParams(location.search);
    // console.log(params.get('username'));

    // let username = params.get('username');
});

// Listening for a message to be sent from the client
socket.addEventListener('message', (message) => {
    let jsonData = JSON.parse(message.data);
    let userMessage = jsonData.message;
    let users = jsonData.users;

    // Output the JSON to the console to see the structure that it has
    if (userMessage != null) {
        // Parse the message data to get the user and the message
        messageTextBox.value += userMessage + '\n';
        // Output each appropriately depending on the HTML structure
    }
    if (users != null) {
        usersTextArea.value = "";

        let i = 0;
        while (i < users.length) {
            usersTextArea.value += users[i++] + '\n';
        }
    }
});

function sendMessage() {
    socket.send(messageText.value);
    messageText.value = "";
}

window.addEventListener('beforeunload', (event) => {
    // Define closing function to be empty so that we can just close the socket for the individual person
    socket.onclose = function () { };
    socket.close();
})