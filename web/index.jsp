<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Websocket</title>
    <script>
        var socket;

        function openWebsocket() {
            socket = new WebSocket("ws://localhost:8080/actions");
            console.log("Socket opened");
            socket.onmessage = function (evt) {
                console.log("Message from server: " + evt.data);
            }
        }

        function closeWebsocket() {
            if (socket != null) {
                socket.close();
                console.log("Socket closed");
            }
        }

        function sendMessage() {
            if (socket != null) {
                socket.send("Hello, Server!");
            }
        }
    </script>
</head>
<body>
<button onclick="openWebsocket()">Open websocket</button>
<br>
<button onclick="sendMessage()">Send message</button>
<br>
<button onclick="closeWebsocket()">Close websocket</button>
</body>
</html>
