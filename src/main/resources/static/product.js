$(document).ready(function () {
    $(".add-new").click(function () {
        $('#modalProduct').modal("toggle");
    })

    //Delete
    $(".delete-product").click(function () {
        var id = $(this).closest("tr").attr("data-id");
        $('#modalDelPro').find(".delete-y-product").attr("data-id", id);
        $('#modalDelPro').modal("toggle");
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
                $('#modalDelPro').modal("toggle");
            },
            error: function () {
                alert('error')
            }
        })
    })
    // Khi nhan btn Edit
    $(".edit-product").click(function () {
        var id = $(this).closest('tr').attr("data-id");
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
            error: function () {
                alert('error');
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
                $("tbody").find("tr[data-id='" + data.id + "']").find(".insert-code").text(data.code);
                $("tbody").find("tr[data-id='" + data.id + "']").find(".insert-name").text(data.name);
                $("tbody").find("tr[data-id='" + data.id + "']").find(".insert-price").text(data.price);
                $("tbody").find("tr[data-id='" + data.id + "']").find(".insert-image").text(data.image);
                $('#updateProduct').modal('toggle');
            },
            error: function () {
                alert('error');
            }
        })
    })

    //Mua hang, khi nhan Buy
    $(".buy-pr").click(function () {
        var id = $(this).closest('tr').attr("data-id");
        $.ajax({
            type: 'GET',
            url: '/api/products/buy/' + id,
            contentType: 'application/json; charset=utf-8',
            success: function (data) {
                $("#buyProduct").find('#buy-pr').attr("data-id", id);
                $("#namePrForm").text(data.name);
                $('#buyProduct').modal('toggle');
            },
            error: function () {
                alert('error');
            }
        })

    })

    $(".btn-buy-product").click(function () {
        var id = $("#buyProduct").find('#buy-pr').attr("data-id");
        var num = $("#numberPr").val();
        $('#buyProduct').modal('toggle');
        $.ajax({
            type: 'POST',
            url: '/api/products/buy',
            dataType: 'json',
            data: JSON.stringify({
                id: id,
                code: num,
            }),
        })
    })
})