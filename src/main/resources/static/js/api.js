document.addEventListener("click", async function(e){

    // delete API
    if (e.target.classList.contains("btn-delete")) {
        const id = e.target.dataset.id;
        if (!confirm("削除しますか？")) return;

        try {
            const res = await fetch(`/api/gratitude/${id}`, {
                method: "DELETE",
                headers: { "Accept" : "application/json" }
            });

            const data = await res.json().catch(() => null);

            if (!res.ok) {
                //alert(data?.message ?? `削除失敗 (status = ${res.status}`);
                alert(data?.message ?? "削除対象を見つかりませんでした。");
                return;
            }

            const li = e.target.closest("li");
            if (li) li.remove();
            alert(data?.message ?? "削除されました。");

        } catch (err) {
            alert("ネットワークエラーが発生しました。")
        }
    }

    // keyword API
    if (e.target.id === 'btn-search') {
            const keyword = document.getElementById('searchKeyword').value;
            const sort = document.getElementById('sortOrder').value;
            const itemList = document.getElementById('itemList');

            try {
                const res = await fetch(`/api/gratitude?keyword=${encodeURIComponent(keyword)}&sort=${sort}`);

                const body = await res.json().catch(() => null);

                //HTTP エラーの場合、
                if (!res.ok) {
                    alert(body?.message ?? "サーバーエラーが発生しました。")
                    console.log(`${res.status}`);
                    return;
                }

                renderList(body?.data ?? [], itemList);

            } catch (err) {
                console.error('検索エラー：', err)
                alert('検索中にエラーが発生しました。');
            }
        }
});

//
function renderList(items, container) {
  if (!Array.isArray(items)) items = [];

  if (items.length === 0) {
    container.innerHTML = '<li class="empty">投稿された記事はありません。</li>';
    return;
  }

  container.innerHTML = items.map(it => `
    <li class="card">
      <div class="card-body">
        <strong class="title">${escapeHtml(it.title)}</strong>
        <p class="content">${escapeHtml(it.content ?? '')}</p>
      </div>

      <div class="card-footer">
        <small class="date">${formatDate(it.createdAt)}</small>

        <div class="actions">
          <a href="/gratitude/${it.id}/edit" class="pill link-pill">修正</a>
          <button type="button" class="pill btn-delete" data-id="${it.id}">削除</button>
        </div>
      </div>
    </li>
  `).join('');
}

// 日付生成関数
function formatDate(dateStr) {
  if(!dateStr) return '';
  const d = new Date(dateStr);
  const y = d.getFullYear();
  const m = String(d.getMonth()+1).padStart(2,'0');
  const day = String(d.getDate()).padStart(2,'0');
  return `${y}.${m}.${day}`;
}

// HTML 文字エスケープ
function escapeHtml(str) {
  return String(str ?? '')
    .replaceAll('&', '&amp;')
    .replaceAll('<', '&lt;')
    .replaceAll('>', '&gt;')
    .replaceAll('"', '&quot;')
    .replaceAll("'", '&#39;');
}