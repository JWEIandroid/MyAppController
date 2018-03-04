$(function () {

});

//加载全部评论信息
function LoadAllComment() {

    $.ajax({
        type: "get",
        url: "http://127.0.0.1:8080/comment/getAllComment",
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
                    "<a href =\"" + "/jsp/Goods/comment_edit.jsp?" +
                    "commentid=" + d.data[i].id +
                    // "&comment_goodname=" + d.data[i].goods.name +
                    "&comment_content=" + d.data[i].content +
                    // "&comment_username=" + d.data[i].user.name +
                    "&comment_time=" + d.data[i].date+
                    "\">编辑" +
                    "</a>" +
                    "<a onclick='DelComment(" +d.data[i].id + ")'>删除" + "</a>" +
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
function QueryComment() {

    var content = $('#comment_content').val();
    $.ajax({
        type: "post",
        url: "http://127.0.0.1:8080/comment/Query",
        dataType: "json",
        data:"content="+content,
        success: function (d) {
            $('#Comment_tbody').empty();
            console.log(d);
            for (var i = 0; i < d.data.length; i++) {

                var rows = "<tr><td>" + d.data[i].id + "</td>"
                rows += "<td>" + d.data[i].content + "</td>"
                rows += "<td>" + d.data[i].user.name + "</td>"
                rows += "<td>" + d.data[i].goods.name + "</td>"
                rows += "<td>" + d.data[i].date + "</td>"
                rows += "<td>" +
                    "<a href =\"" + "/jsp/Goods/comment_edit.jsp?" +
                    "commentid=" + d.data[i].id +
                    // "&comment_goodname=" + d.data[i].goods.name +
                    "&comment_content=" + d.data[i].content +
                    // "&comment_username=" + d.data[i].user.name +
                    "&comment_time=" + d.data[i].date+
                    "\">编辑" +
                    "</a>" +
                    "<a onclick='DelComment(" +d.data[i].id + ")'>删除" + "</a>" +
                    "</td></tr>"
                $('#Comment_tbody').append(rows);
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
        url: "http://127.0.0.1:8080/comment/deleteByIds",
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

    var date = $('#comment_time').val();
    var content = $('#comment_content').val();
    console.log("更新评论Id:"+id);

    $.ajax({
        type: "post",
        url: "http://127.0.0.1:8080/comment/UpdateOneComment",
        dataType: "json",
        data:
        "date="+date
        +"&content="+content
        +"&id="+id,
        success: function (d) {
            alert(d.message);
        },
        error: function () {
            alert("更新评论----服务器出问题啦");
        }
    });


};

//添加一条信息
function AddComment() {

};


