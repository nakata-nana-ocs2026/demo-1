let count = 0;
let history = [];

fetch('/game/start')
    .then(res => res.json())
    .then(data => {

        document.getElementById('weather').innerHTML = `
        <div class="weather-grid">

            <div class="weather-item">
                <div class="icon">🌡</div>
                <div class="label">平均気温</div>
                <div class="value">${data.temperature}℃</div>
            </div>

            <div class="weather-item">
                <div class="icon">☔</div>
                <div class="label">降水量</div>
                <div class="value">${data.rainfall}mm</div>
            </div>

            <div class="weather-item">
                <div class="icon">☀</div>
                <div class="label">日照時間</div>
                <div class="value">${data.sunshine}h</div>
            </div>

        </div>

        <div class="hint-box">
            💡 ヒント: ${data.hint}
        </div>
        `;
    });

function submitAnswer() {

    count++;
    document.getElementById('count').innerHTML =
        `挑戦回数: ${count}`;

    const answer = {
        region: document.getElementById('region').value,
        month: parseInt(document.getElementById('month').value),
        weatherType: document.getElementById('type').value
    };

    fetch('/game/answer', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(answer)
    })
    .then(res => res.json())
    .then(data => {

        history.unshift(
            `${count}回目: `
            + `${answer.region} / `
            + `${answer.month}月 / `
            + `${answer.weatherType} → ${data.hit} Hit`
        );

        renderHistory();

        if (data.clear) {
            document.getElementById('result').innerHTML =
                `🎉 CLEAR!! (${count}回)`;

            document.getElementById('nextButton').style.display =
                'block';
        } else {
            document.getElementById('result').innerHTML =
                `✅ ${data.hit} Hit`;
        }
    });

    window.location.href='login.html'
}

function renderHistory() {
    document.getElementById('history').innerHTML =
        history.map(item =>
            `<div class="history-item">${item}</div>`
        ).join('');
}

function nextGame() {
    location.reload();
}

// ---------------chat---------------------

function loadChat() {
    fetch('chat.html')
        .then(response => response.text())
        .then(data => {
            document.getElementById('chat-container').innerHTML = data;
        });
}

loadChat();

const playerName =
    sessionStorage.getItem("playerName");

const playerId =
    sessionStorage.getItem("playerId");

async function sendMessage() {

    
    const input = document.getElementById("message");


    if(input.value.trim() === ""){
        return;
    }

    await fetch("/chat", {

        method:"POST",

        headers:{
            "Content-Type":"application/json"
        },

        body:JSON.stringify({
            message:input.value,
            roomId:document.getElementById("roomId").value,
            teamId:document.getElementById("teamId").value,
            playerId: playerId,
            playerName: playerName
        })
    });

    input.value = "";

    checkInput();

    loadMessages();
}

async function loadMessages(){

    const response = await fetch("/chat");

    const messages = await response.json();

    const chat = document.getElementById("chatMessages");

    chat.innerHTML = "";

    messages.forEach(msg => {

        const div = document.createElement("div");

        div.className = "message";

        // div.textContent = msg.message;
        // div.innerHTML = `
        //     <div>Room : ${msg.roomId}</div>
        //     <div>Team : ${msg.teamId}</div>
        //     <div>Player : ${msg.playerName}</div>
        //     <hr>
        //     <div>${msg.message}</div>
        // `;
        div.innerHTML = `
            <div class="player-name">
                ${msg.playerName}
            </div>

            <div class="message-text">
                ${msg.message}
            </div>
        `;

        chat.appendChild(div);
    });

    chat.scrollTop = chat.scrollHeight;
}

function checkInput(){

    const input = document.getElementById("message");

    const button = document.getElementById("sendButton");

    button.disabled = input.value.trim() === "";
}

function handleEnter(event){

    if(event.key === "Enter"){

        event.preventDefault();

        sendMessage();
    }
}

setInterval(loadMessages,1000);

loadMessages();