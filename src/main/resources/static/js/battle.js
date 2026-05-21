let count = 0;
let history = [];


// =====================================================
// ゲーム開始
// =====================================================

fetch('/match/start')

    .then(res => res.json())

    .then(data => {

        console.log(data);

        document.getElementById('weather').innerHTML = `

        <div class="weather-grid">

            <div class="weather-item">
                <div class="icon">🌡</div>
                <div class="label">平均気温</div>

                <div class="value">
                    ${data.temperature}℃
                </div>
            </div>

            <div class="weather-item">
                <div class="icon">☔</div>
                <div class="label">降水量</div>

                <div class="value">
                    ${data.rainfall}mm
                </div>
            </div>

            <div class="weather-item">
                <div class="icon">☀</div>
                <div class="label">日照時間</div>

                <div class="value">
                    ${data.sunshine}h
                </div>
            </div>

        </div>

        <div class="hint-box">
            🗾 ヒント1:
            ${data.hint1}
        </div>

        <div class="hint-box">
            🌦 ヒント2:
            ${data.hint2}
        </div>

        `;
    });


// =====================================================
// 回答
// =====================================================

function submitAnswer() {

    count++;

    document.getElementById('count').innerHTML =
        `挑戦回数: ${count}`;

    const answer = {

        region:
            document.getElementById('region').value,

        month:
            parseInt(
                document.getElementById('month').value
            ),

        weatherType:
            document.getElementById('type').value
    };

    fetch('/match/check', {

        method: 'POST',

        headers: {
            'Content-Type': 'application/json'
        },

        body: JSON.stringify(answer)

    })

    .then(res => res.json())

    .then(data => {

        history.unshift(
            `${count}回目 :
            ${answer.region} /
            ${answer.month}月 /
            ${answer.weatherType}
            → ${data.hit} Hit`
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
}


// =====================================================
// 履歴表示
// =====================================================

function renderHistory() {

    document.getElementById('history').innerHTML =

        history.map(item =>

            `<div class="history-item">
                ${item}
            </div>`

        ).join('');
}


// =====================================================
// 次の問題
// =====================================================

function nextGame() {

    location.reload();
}


// =====================================================
// chat
// =====================================================

const playerName =
    sessionStorage.getItem("playerName")
    || "guest";

const playerId =
    sessionStorage.getItem("playerId")
    || "guest";


// =====================================================
// メッセージ送信
// =====================================================

async function sendMessage() {

    const input =
        document.getElementById("message");

    if (!input || input.value.trim() === "") {
        return;
    }

    const roomId =
        document.getElementById("roomId")?.value
        || "1";

    const teamId =
        document.getElementById("teamId")?.value
        || "1";

    await fetch("/chat", {

        method: "POST",

        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify({

            message: input.value,

            roomId: roomId,

            teamId: teamId,

            playerId: playerId,

            playerName: playerName
        })
    });

    input.value = "";

    checkInput();

    loadMessages();
}


// =====================================================
// メッセージ取得
// =====================================================

async function loadMessages() {

    const chat =
        document.getElementById("chatMessages");

    if (!chat) return;

    const response =
        await fetch("/chat");

    const messages =
        await response.json();

    chat.innerHTML = "";

    messages.forEach(msg => {

        const div =
            document.createElement("div");

        div.className = "message";

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

    chat.scrollTop =
        chat.scrollHeight;
}


// =====================================================
// 入力チェック
// =====================================================

function checkInput() {

    const input =
        document.getElementById("message");

    const button =
        document.getElementById("sendButton");

    if (!input || !button) return;

    button.disabled =
        input.value.trim() === "";
}


// =====================================================
// Enter送信
// =====================================================

function handleEnter(event) {

    if (event.key === "Enter") {

        event.preventDefault();

        sendMessage();
    }
}


// =====================================================
// 自動更新
// =====================================================

setInterval(() => {

    loadMessages();

}, 1000);


// 初回読み込み
loadMessages();