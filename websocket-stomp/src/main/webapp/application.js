var server = document.getElementById("server");
var output = document.getElementById("output");
var queue = document.getElementById("queue");
var payload = document.getElementById("payload");

var url = server.value;
var client;

var subscription;


function send_message() {
    writeToScreen("Payload: " + payload.value + ", Queue: " + queue.value);
    client.send(queue.value, {}, payload.value);
}

function subscribe() {
    subscription = client.subscribe(queue.value, function (message) {
        // called when the client receives a STOMP message from the server
        if (message.body) {
            writeToScreen("got message with body " + message.body)
        } else {
            writeToScreen("got empty message");
        }
    })
}

function unsubscribe() {
    subscription.unsubscribe();
}

function connect() {
    client = Stomp.client(url);

    client.connect("admin", "admin",
            function () {
                writeToScreen("WebSocket connected");
            },
            function (error) {
                writeToScreen("ERROR: " + error);
            });
}

function disconnect() {
    client.disconnect(function () {
        writeToScreen("Hasta la vista, baby!");
    });
}

function writeToScreen(message) {
    output.innerHTML += message + "<br>";
}
