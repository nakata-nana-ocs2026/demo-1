function movePage(url){

    // ローディング作成
    const loading =
        document.createElement("div");

    loading.className =
        "loading-screen";

    loading.innerHTML = `

        <div class="loader"></div>

        <div>
            Loading...
        </div>

    `;

    document.body.appendChild(loading);

    // ページ退出アニメ
    document.body.classList.add(
        "page-out"
    );

    setTimeout(() => {

        window.location.href = url;

    }, 500);
}