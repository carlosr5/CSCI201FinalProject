// This file should just receive the messages from the server and output a batch of messages to the client

let socket = new WebSocket("ws://localhost:8080/CSCI201_Final_Project/chatroomServerEndpoint");

// Listening for a message to be sent from the client
socket.addEventListener('message', (message) => {
    let JSONData = JSON.parse(message.data);

    // Output the JSON to the console to see the structure that it has
    if(JSONData.message != null) {
        // Parse the message data to get the user and the message

        // Output each appropriately depending on the HTML structure
    }
});

function sendMessage() {
    socket.send(messageText.value);
    messageText.value = "";
}