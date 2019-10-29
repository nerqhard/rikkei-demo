$(document).ready(function () {
    $(".add-new").click(function () {
        $('#modalProduct').modal("toggle");
    })

    $(".delete-product").click(function () {
        var id = $(this).closest("tr").attr("data-id");
        $('#modalDelPro').modal("toggle");
        $('#modalDelPro').find(".delete-y-product").attr("data-id", id);
    })

    $(".delete-y-product").click(function () {
        var id = $('#modalDelPro').find('.delete-y-product').attr('data-id');
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
    $(".edit-product").click(function () {
        $('input[name ="code"]').css("border-color", "#ced4da");
        $('input[name ="name"]').css("border-color", "#ced4da");
        $('input[name ="price"]').css("border-color", "#ced4da");
        $('input[name ="image"]').css("border-color", "#ced4da");
        var id = $(this).closest('tr').attr("data-id");
        console.log(id);

        $.ajax({
            type: 'GET',
            url: '/api/products/' + id,
            contentType: 'application/json; charset=utf-8',
            cache: false,
            success: function (data) {
                $("#updateProduct").find('#btn-update-product').attr("data-id", id);
                $('input[name ="code"]').val(data.code);
                $('input[name ="name"]').val(data.name);
                $('input[name ="price"]').val(data.price);
                $('input[name ="image"]').val(data.image);
                $('#updateProduct').modal('toggle');
            },
            error: function (error) {
                snackbarError();
            }
        })
    })
    $('#btn-update-product').click(function () {
        var id = $("#updateProduct").find('#btn-update-product').attr("data-id");
        console.log(id);
        var code = $('input[name ="code"]').val();
        var name = $('input[name ="name"]').val();
        var price = $('input[name ="price"]').val();
        var image = $('input[name ="image"]').val();
        $('#updateProduct').modal('toggle');

        $.ajax({
            type: 'PUT',
            url: '/api/products',
            contentType: 'application/json',
            data: JSON.stringify({
                id: id,
                code: code,
                name: name,
                price: price,
                image: image
            }),
            dataType: 'json',
            responseType: 'application/json',

            success: function (data) {
                $("tbody").find("tr[data-id='" + data.id + "']").find(".insert-code").text(data.code);
                $("tbody").find("tr[data-id='" + data.id + "']").find(".insert-name").text(data.name);
                $("tbody").find("tr[data-id='" + data.id + "']").find(".insert-price").text(data.price);
                $("tbody").find("tr[data-id='" + data.id + "']").find(".insert-image").text(data.image);
            },
            error: function (error) {
                snackbarError();
            }
        })
    })
})