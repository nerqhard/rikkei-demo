$(document).ready(function () {
    $(".event-delete").click(function () {
        var id = $(this).closest("tr").attr("data-id");
        $('#myModal').modal("toggle");
        $('#myModal').find(".delete-access").attr("data-id", id);
    })

    $(".delete-access").click(function () {

        var id = $('#myModal').find('.delete-access').attr('data-id');
        $.ajax({
            url: "/api/account/delete",
            type: "DELETE",
            data: JSON.stringify({
                id: parseInt(id)
            }),
            contentType: 'application/json; charset=utf-8',
            success: function (value) {
                $('.table').find('.row-' + id).remove();
                location.reload();
            },
            error: function () {
                alert('error')
            }
        })
    })
})