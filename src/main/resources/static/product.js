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
                $('#modalDelPro').modal("toggle");
                // location.reload();
            },
            error: function () {
                alert('error')
            }
        })
    })
    // Khi nhan btn Edit
    $(".edit-product").click(function () {
        var id = $(this).closest('tr').attr("data-id");
        console.log(id);
        $.ajax({
            type: 'GET',
            url: '/api/products/' + id,
            contentType: 'application/json; charset=utf-8',
            success: function (data) {
                $("#updateProduct").find('#btn-update-product').attr("data-id", id);
                $("#updateProduct").find("#codePr").val(data.code);
                $("#updateProduct").find("#namePr").val(data.name);
                $("#updateProduct").find("#pricePr").val(data.price);
                $("#updateProduct").find("#imgInp").val(data.image);
                $('#updateProduct').modal('toggle');
            },
            error: function (error) {
                snackbarError();
            }
        })
    })

    //Khi nhan btn Save
    $('#btn-update-product').click(function () {
        // Lấy giá trị id của hàng cần update
        var id = $("#updateProduct").find('#btn-update-product').attr("data-id");
        // Lấy giá trị của hàng cần update
        var code = $("#updateProduct").find("#codePr").val();
        var name = $("#updateProduct").find("#namePr").val();
        var price = $("#updateProduct").find("#pricePr").val();
        var image = $("#updateProduct").find("#imgInp").val();

        $('#updateProduct').modal('toggle');
        $.ajax({
            type: 'PUT', //Phương thức tương ứng là PUT
            url: '/api/products', //Gửi request tới server theo đường dẫn /api/products
            dataType: 'json', //Data gửi đi dạng Json
            //cover 1 doi tuong Js thanh 1 chuoi Json
            data: JSON.stringify({
                id: id,
                code: code,
                name: name,
                price: price,
                image: image
            }),

            contentType: 'application/json; charset=utf-8',
            success: function (data) { //Sau khi Ajax nhận được Data từ server trả về và product được save


                $("tbody").find("tr[data-id='" + data.id + "']").find(".insert-code").prop(data.code);
                $("tbody").find("tr[data-id='" + data.id + "']").find(".insert-name").prop(data.name);
                $("tbody").find("tr[data-id='" + data.id + "']").find(".insert-price").prop(data.price);
                $("tbody").find("tr[data-id='" + data.id + "']").find(".insert-image").prop(data.image);
                location.reload();
            },
            error: function (error) {
                snackbarError();
            }
        })
    })
})