$(function () {

});

//加载全部商品信息
function LoadAllGoods() {

    $.ajax({
        type: "post",
        // url: "http://123.207.26.152:8080/goods/getAllGoodsWithOutPageNum",
        url: "http://127.0.0.1:8080/goods/getAllGoodsWithOutPageNum",
        dataType: "json",
        success: function (d) {
            console.log(d);
            for (var i = 0; i < d.data.length; i++) {
                var rows = "<tr><td>" + d.data[i].id + "</td>"
                rows += "<td>" + d.data[i].name + "</td>"
                rows += "<td>" + d.data[i].status + "</td>"
                rows += "<td>" + d.data[i].price_sale + "</td>"
                rows += "<td>" + d.data[i].user.name + "</td>"
                rows += "<td>" + ToLocalString(d.data[i].create_time) + "</td>"
                rows += "<td>" +
                    "<a href =\"" + "/jsp/Goods/Goods_edit.jsp?" +
                    "goodid=" + d.data[i].id +
                    "&goodname=" + d.data[i].name +
                    "&goodprice=" + d.data[i].price_sale +
                    "&good_createtime=" + ToLocalString(d.data[i].create_time) +
                    "&goodstatus=" + d.data[i].status +
                    "\">编辑" +
                    "</a>" +
                    "<a onclick='DelGoods(" + d.data[i].id + ")'>删除" + "</a>" +
                    "</td></tr>"
                $('#Goods_tbody').append(rows);
            }
        },
        error: function () {
            alert("查询全部商品----服务器出问题啦");
        }
    });

};

//查询一条商品信息
function QueryGoods(name) {

    console.log(name);

    $.ajax({
        type: "post",
        // url: "http://123.207.26.152:8080/goods/getgoodBynameWithOutPageNum",
        url: "http://127.0.0.1:8080/goods/getgoodBynameWithOutPageNum",
        dataType: "json",
        data:
        "name=" + name,
        success: function (d) {
            $('#Goods_tbody').empty();
            console.log(d);
            for (var i = 0; i < d.data.length; i++) {
                var rows = "<tr><td>" + d.data[i].id + "</td>"
                rows += "<td>" + d.data[i].name + "</td>"
                rows += "<td>" + d.data[i].status + "</td>"
                rows += "<td>" + d.data[i].price_sale + "</td>"
                rows += "<td>" + d.data[i].user.name + "</td>"
                rows += "<td>" + ToLocalString(d.data[i].create_time) + "</td>"
                rows += "<td>" +
                    "<a href =\"" + "/jsp/Goods/Goods_edit.jsp?" +
                    "goodid=" + d.data[i].id +
                    "&goodname=" + d.data[i].name +
                    "&goodprice=" + d.data[i].price_sale +
                    "&good_createtime=" + ToLocalString(d.data[i].create_time) +
                    "&goodstatus=" + d.data[i].status +
                    "\">编辑" +
                    "</a>" +
                    "<a onclick='DelGoods(" + d.data[i].id + ")'>删除" + "</a>" +
                    "</td></tr>"
                $('#Goods_tbody').append(rows);
            }
        },
        error: function () {
            alert("查询一条商品----服务器出问题啦");
        }
    });


};

//删除商品信息
function DelGoods(id) {

    $.ajax({
        type: "post",
        // url: "http://123.207.26.152:8080:8080/goods/delwithid",
        url: "http://127.0.0.1:8080/goods/delwithid",
        dataType: "json",
        data: "id=" + id,
        success: function (d) {
            alert(d.message);
        },
        error: function () {
            alert("删除商品----服务器出问题啦");
        }
    });

};

//更新商品信息
function UpdateGoods(id) {

    var name = $('#good_name').val();
    var status = $('#good_status').val();
    var create_time = $('#good_time').val();
    var price_sale = $('#good_price').val();

    $.ajax({
        type: "post",
        // url: "http://123.207.26.152:8080/goods/updategood",
        url: "http://127.0.0.1:8080/goods/updategood",
        dataType: "json",
        data:
        "name=" + name
        + "&id=" + id
        + "&status=" + status
        + "&create_time=" + create_time
        + "&price_sale=" + price_sale,
        success: function (d) {
            alert(d.message);

        },
        error: function () {
            alert("删除商品----服务器出问题啦");
        }
    });


};

//添加一条商品信息
function AddGoods() {

};

//后退 一页
function custom_close() {
    javascript:history.go(-1);
};


function ToLocalString(d) {


    return new Date(parseInt(d)).toLocaleString().replace(/:\d{1,2}$/,' ');

}
