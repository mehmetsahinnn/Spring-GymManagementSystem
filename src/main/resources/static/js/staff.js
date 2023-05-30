function showUpdateForm() {
    document.getElementById("update-form").style.display = "block";
}

document.getElementById("update-button").addEventListener("click", showUpdateForm);

function showInsertForm() {
    document.getElementById("insert-form").style.display = "block";
}

document.getElementById("insert-button").addEventListener("click", showInsertForm);

function closeInsertForm() {
    document.getElementById("insert-form").style.display = "none";
}

document.getElementById("close-insert-button").addEventListener("click", closeInsertForm);

function closeUpdateForm() {
    document.getElementById("update-form").style.display = "none";
}

document.getElementById("close-update-button").addEventListener("click", closeUpdateForm);



