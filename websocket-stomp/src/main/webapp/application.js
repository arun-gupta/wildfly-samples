var url = "ws://localhost:61614";
var client;

var subscription;

var output = document.getElementById("output");

function send_message() {
    client.send(document.getElementById("queue").value, {}, document.getElementById("payload").value);
}

function subscribe() {
    subscription = client.subscribe(document.getElementById("queue").value, function (message) {
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
                // called back after the client is connected and authenticated to the STOMP server
            },
            function (error) {
                // display the error's message header:
                writeToScreen("ERROR: " + error.headers.message);
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
