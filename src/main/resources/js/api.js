document.addEventListener("click", async function(e){
    //
    if (!e.target.classList.contains("dtn-delete")) return;

    const id = e.target.dataset.id;
    if (!confirm("削除しますか？")) return;

    try {
        const res = await fetch(`api/gratitude/${id}`, {
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

});