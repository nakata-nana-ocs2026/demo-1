const socket = new SockJS('/ws');
const stompClient = Stomp.over(socket);

stompClient.connect({}, function () {

    console.log("WebSocket Connected");

    stompClient.subscribe('/topic/rooms', function (message) {

        console.log("受信:", message.body);

        document.getElementById('result').innerText =
            message.body;
    });

});

function joinRoom() {

    const data = {

        roomName:
            document.getElementById('roomName').value,

        teamName:
            document.getElementById('teamName').value,

        playerName:
            document.getElementById('playerName').value
    };

    console.log("送信:", data);

    stompClient.send(
        '/app/join',
        {},
        JSON.stringify(data)
    );

    setTimeout(() => {
        window.location.href = "battle.html";
    }, 300);
}