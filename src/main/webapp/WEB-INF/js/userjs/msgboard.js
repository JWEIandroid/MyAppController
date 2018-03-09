$(function () {

});

// var BASEURL = "127.0.0.1";
var BASEURL = "123.207.26.152";

//加载全部留言
function LoadAllMsg() {

    $.ajax({
        type: "post",
        // url: "http://123.207.26.152:8080/message/list",
        // url: "http://127.0.0.1:8080/message/list",
        url:  "http://"+BASEURL+":8080/message/list",
        dataType: "json",
        success: function (d) {
            console.log("...." + d);
            for (var i = 0; i < d.data.length; i++) {
                var rows = "<tr><td>" + d.data[i].id + "</td>"
                rows += "<td>" + d.data[i].user.name + "</td>"
                rows += "<td>" + d.data[i].receiver.name + "</td>"
                rows += "<td>" + d.data[i].content + "</td>"
                rows += "<td>" + ToLocalString(d.data[i].date) + "</td>"
                rows += "<td>" +
                    "<a href =\"" + "/jsp/User/Msg_edit.jsp?" +
                    "msgid=" + d.data[i].id +
                    "&comment_content=" + d.data[i].content +
                    "\">编辑" +
                    "</a>" +
                    "<a onclick='DelMessage(" + d.data[i].id + ")'>删除" + "</a>" +
                    "</td></tr>"
                $('#Message_tbody').append(rows);
            }
        },
        error: function () {
            alert("查询全部商品----服务器出问题啦");
        }
    });

};

//查询一条留言
function QueryMsg() {

    var content = $('#message_content').val();
    $.ajax({
        type: "post",
        // url: "http://123.207.26.152:8080/message/querybycontent",
        // url: "http://127.0.0.1:8080/message/querybycontent",
        url:  "http://"+BASEURL+":8080/message/querybycontent",
        dataType: "json",
        data: "content=" + content,
        success: function (d) {
            $('#Message_tbody').empty();
            console.log(d);
            for (var i = 0; i < d.data.length; i++) {
                var rows = "<tr><td>" + d.data[i].id + "</td>"
                rows += "<td>" + d.data[i].user.name + "</td>"
                rows += "<td>" + d.data[i].receiver.name + "</td>"
                rows += "<td>" + d.data[i].content + "</td>"
                rows += "<td>" + ToLocalString(d.data[i].date) + "</td>"
                rows += "<td>" +
                    "<a href =\"" + "/jsp/User/Msg_edit.jsp?" +
                    "msgid=" + d.data[i].id +
                    "&comment_content=" + d.data[i].content +
                    "\">编辑" +
                    "</a>" +
                    "<a onclick='DelMessage(" + d.data[i].id + ")'>删除" + "</a>" +
                    "</td></tr>"
                $('#Message_tbody').append(rows);
            }
        },
        error: function () {
            alert("查询一条商品----服务器出问题啦");
        }
    });


};

//删除一条留言
function DelMessage(id) {

    $.ajax({
        type: "post",
        // url: "http://123.207.26.152:8080/message/deletebyid",
        // url: "http://127.0.0.1:8080/message/deletebyid",
        url:  "http://"+BASEURL+":8080/message/deletebyid",
        dataType: "json",
        data: "id=" + id,
        success: function (d) {
            alert(d.message);
        },
        error: function () {
            alert("删除留言----服务器出问题啦");
        }
    });

};

//更新留言
function UpdateMessage(id) {

    var content = $('#message_content').val();

    $.ajax({
        type: "post",
        // url: "http://123.207.26.152:8080/message/update",
        // url: "http://127.0.0.1:8080/message/update",
        url:  "http://"+BASEURL+":8080/message/update",
        dataType: "json",
        data:
        "content=" + content
        + "&id=" + id,
        success: function (d) {
            alert(d.message);
        },
        error: function () {
            alert("更新留言板----服务器出问题啦");
        }
    });


};

//添加一条留言
function AddComment() {

};


function ToLocalString(d) {


    return new Date(parseInt(d)).toLocaleString().replace(/:\d{1,2}$/,' ');

}

