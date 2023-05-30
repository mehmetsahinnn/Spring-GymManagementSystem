function showUpdateMember() {
    document.getElementById("update-form").style.display = "block";
}

document.getElementById("update-button").addEventListener("click", showUpdateMember);

function showInsertMember() {
    document.getElementById("insert-form").style.display = "block";
}

document.getElementById("insert-button").addEventListener("click", showInsertMember);

function closeInsertMember() {
    document.getElementById("insert-form").style.display = "none";
}

document.getElementById("close-insert-button").addEventListener("click", closeInsertMember);

function closeUpdateMember() {
    document.getElementById("update-form").style.display = "none";
}

document.getElementById("close-update-button").addEventListener("click", closeUpdateMember);



