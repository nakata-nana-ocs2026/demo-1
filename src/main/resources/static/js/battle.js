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

function loadChat() {
    fetch('chat.html')
        .then(response => response.text())
        .then(data => {
            document.getElementById('chat-container').innerHTML = data;
        });
}

loadChat();