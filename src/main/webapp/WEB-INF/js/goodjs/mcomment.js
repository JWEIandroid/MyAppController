$(function () {

});

//加载全部评论信息
function LoadAllComment() {

    $.ajax({
        type: "post",
        url: "http://127.0.0.1:8080/goods/getAllComment",
        dataType: "json",
        success: function (d) {
            console.log("...."+d);
            for (var i = 0; i < d.data.length; i++) {
                console.debug(d.data[i].id);
                console.debug(d.data[i].goods.name);
                console.debug(d.data[i].content);
                console.debug(d.data[i].user.name);
                console.debug(d.data[i].date);
                var rows = "<tr><td>" + d.data[i].id + "</td>"
                rows += "<td>" + d.data[i].content + "</td>"
                rows += "<td>" + d.data[i].user.name + "</td>"
                rows += "<td>" + d.data[i].goods.name + "</td>"
                rows += "<td>" + d.data[i].date + "</td>"
                rows += "<td>" +
                    "<a href =\"" + "/jsp/user/Comment_Manager.jsp?" +
                    "commentid=" + d.data[i].id +
                    "&comment_goodname=" + d.data[i].goods.name +
                    "&comment_content=" + d.data[i].content +
                    "&comment_username=" + d.data[i].user.name +
                    "&comment_time=" + d.data[i].date+
                    "\">编辑" +
                    "</a>" +
                    "<a onclick='DelGoods(" +d.data[i].id + ")'>删除" + "</a>" +
                    "</td></tr>"
                $('#Comment_tbody').append(rows);
            }
        },
        error: function () {
            alert("查询全部商品----服务器出问题啦");
        }
    });

};

//查询一条评论信息
function QueryComment(name) {


    $.ajax({
        type: "post",
        url: "http://127.0.0.1:8080/goods/getgoodBynameWithOutPageNum",
        dataType: "json",
        success: function (d) {
            $('#Goods_tbody').empty();
            console.log(d);
            for (var i = 0; i < d.data.length; i++) {
                var rows = "<tr><td>" + d.data[i].id + "</td>"
                rows += "<td>" + d.data[i].name + "</td>"
                rows += "<td>" + d.data[i].status + "</td>"
                rows += "<td>" + d.data[i].price_sale + "</td>"
                rows += "<td>" + d.data[i].create_time + "</td>"
                rows += "<td>" +
                    "<a href =\"" + "/jsp/Goods/Goods_edit.jsp?" +
                    "goodid=" + d.data[i].id +
                    "&goodname=" + d.data[i].name +
                    "&goodprice=" + d.data[i].price_sale +
                    "&good_createtime=" + d.data[i].create_time +
                    "&goodstatus=" + d.data[i].status +
                    "\">编辑" +
                    "</a>" +
                    "<a onclick='DelComment(" +d.data[i].id + ")'>删除" + "</a>" +
                    "</td></tr>"
                $('#Goods_tbody').append(rows);
            }
        },
        error: function () {
            alert("查询一条商品----服务器出问题啦");
        }
    });


};

//删除一条评论信息
function DelComment(id) {

    $.ajax({
        type: "post",
        url: "http://127.0.0.1:8080/comment/deleteById",
        dataType: "json",
        data: "id=" + id,
        success: function (d) {
            alert(d.message);
        },
        error: function () {
            alert("删除评论----服务器出问题啦");
        }
    });

};

//更新评论信息
function UpdateComment(id) {

    var name = $('#good_name').val();
    var status = $('#good_status').val();
    var create_time = $('#good_time').val();
    var price_sale = $('#good_price').val();

    $.ajax({
        type: "post",
        url: "http://127.0.0.1:8080/goods/updategood",
        dataType: "json",
        data:
        "name=" + name
        +"&id="+id
        +"&status="+status
        +"&create_time="+create_time
        +"&price_sale="+price_sale,
        success: function (d) {
            alert(d.message);
        },
        error: function () {
            alert("删除商品----服务器出问题啦");
        }
    });


};

//添加一条信息
function AddComment() {

};

//后退 一页
function custom_close() {
    javascript:history.go(-1);
};

