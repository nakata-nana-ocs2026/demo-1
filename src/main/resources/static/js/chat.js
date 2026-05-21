async function sendMessage() {

    const input = document.getElementById("message");
    if (!input || input.value.trim() === "") return;

    const roomId = document.getElementById("roomId").value || "1";
    const teamId = document.getElementById("teamId").value || "1";

    await fetch("/chat", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify({
            message: input.value,
            roomId: roomId,
            teamId: teamId,
            playerId: '123',
            playerName: 'defaultName'
        })
    });

    input.value = "";
    checkInput();
    loadMessages();
}

async function loadMessages() {

    const chat = document.getElementById("chatMessages");
    if (!chat) return;

    const response = await fetch("/chat", { method: "GET" });
    const messages = await response.json();

    chat.innerHTML = "";

    messages.forEach(msg => {
        console.log(msg.playerName)

        const div = document.createElement("div");
        div.className = "message";

        div.innerHTML = `
            <div class="player-name">${sessionStorage.getItem("playerName") || "guest"}</div>
            <div class="message-text">${msg.message || 'abc'}</div>
        `;

        chat.appendChild(div);
    });

    chat.scrollTop = chat.scrollHeight;
}

function checkInput() {
    const input = document.getElementById("message");
    const button = document.getElementById("sendButton");

    if (!input || !button) return;

    button.disabled = input.value.trim() === "";
}

function handleEnter(event) {
    if (event.key === "Enter") {
        event.preventDefault();
        sendMessage();
    }
}

setInterval(() => {
    loadMessages();
}, 1000);