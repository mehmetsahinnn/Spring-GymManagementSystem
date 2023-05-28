function addDeleteEvent() {
    const buttons = document.querySelectorAll('.delete-button');
    buttons.forEach((button) => {
        button.addEventListener('click', (event) => {
            const id = event.target.dataset.id;
            fetch(`/equipment/${id}`, {method: 'DELETE'});
        });
    });
}

function showInsertForm() {
    document.getElementById("insert-form").style.display = "block";
}
document.getElementById("insert-button").addEventListener("click", showInsertForm);