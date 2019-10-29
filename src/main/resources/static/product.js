$(document).ready(function () {
    $(".add-new").click(function () {
        $('#modalProduct').modal("toggle");
    })
    $(".delete-product").click(function () {
        var id = $(this).closest("tr").attr("data-id");
        console.log("AAA" + id);
        $('#modalDelPro').modal("toggle");
        $('#modalDelPro').find(".delete-y-product").attr("data-id", id);
    })
    $(".delete-y-product").click(function () {
        var id = $('#modalDelPro').find('.delete-y-product').attr('data-id');
        console.log("BBB" + id);
        $.ajax({
            url: "/api/products/delete",
            type: "DELETE",
            data: JSON.stringify({
                id: parseInt(id)
            }),
            contentType: 'application/json; charset=utf-8',
            success: function (value) {
                $('.table').find('.rowpr-' + id).remove();
                location.reload();
            },
            error: function () {
                alert('error')
            }
        })
    })
})