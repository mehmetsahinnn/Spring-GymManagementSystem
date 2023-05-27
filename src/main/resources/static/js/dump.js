function changeMemberSize(size) {
    window.location.href = '/table?page=0&size=' + size;
}

function changeEquipmentSize(size) {
    window.location.href = '/equipment?page=0&size=' + size;
}


$(document).ready(function () {
    $('#loginForm').submit(function (event) {
        event.preventDefault();

        let email = $('#email').val();

        $.post('/login', {email: email})
            .done(function (response) {
                alert(response);
                window.location.href = '/home';
            })
            .fail(function (xhr, status, error) {
                alert('Login failed. Please try again.');
            });
    });
});

