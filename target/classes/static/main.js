$(document).ready(function () {

    var url = 'http://localhost:8080';

    $('#addForm').on('submit', function (event) {
        var data = {
            id: keyInput.val(),
            username: valueInput.val()
        };
        $.post(url + '/add', data, function () {
            keyInput.val('');
            valueInput.val('');
            keyInput.focus();
        });
        event.preventDefault();
    });
    keyInput.focus();
});



