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


var tableBody = document.getElementById("tableBody");

function filterTable() {

    var input = document.getElementById("nameInput").value.toLowerCase();


    var rows = tableBody.getElementsByTagName("tr");


    for (var i = 0; i < rows.length; i++) {
        var name = rows[i].getElementsByTagName("td")[1].innerText.toLowerCase();
        if (name.includes(input)) {
            rows[i].style.display = "";
        } else {
            rows[i].style.display = "none";
        }
    }
}

document.getElementById("nameInput").addEventListener("input", filterTable);

document.getElementById('searchForm')
    .addEventListener('submit', function (event) {
        event.preventDefault();

        var name = document.getElementById('nameInput').value;
        if (name == null || name === '') {
            window.location.href = '/members';
        } else {
            window.location.href = '/searchMember/' + encodeURIComponent(name);
        }
    });