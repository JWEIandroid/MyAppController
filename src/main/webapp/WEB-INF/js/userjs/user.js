$(function () {


});


//读取全部用户数据
function loadUserData() {

    $.ajax({
        type: "get",
        url: "http://127.0.0.1:8080/user/userlist",
        dataType: "json",
        // async: true,
        success: function (d) {
            console.log("服务器响应成功---");
            console.log(d);
            // alert(d.data.id);
            // 在这里补充登录成功或失败后的操作代码
            if (d.error_code == "0") {
                var result = d.data;
                con = "";
                $.each(result, function (index, item) {
                    // con += "<li>姓名：" + item.name + "</li>";
                    // con += "<li>电话：" + item.tel + "</li>";
                    con = "<tr>"
                        + "<td>" + item.id + "</td>"
                        + "<td>" + item.name + "</td>"
                        + "<td>" + item.tel + "</td>"
                        + "<td>" + item.update_time + "</td>"
                        // + "<td><a href='/jsp/User/user_edit.jsp?id=<%= item.id>+'>编辑</a></td>"
                        + "<td><a href =\"" +
                        "/jsp/User/user_edit.jsp?" +
                        "id=" + item.id +
                        "&password=" + item.password +
                        "&name=" + item.name +
                        "&tel=" + item.tel +
                        "&adress=" + item.adress +
                        "&sex=" + item.sex +
                        "\">编辑</a>" +
                        "<a onclick='delete_item(0," + item.id + ")'>删除" + "</a>" +
                        "</td>" + "</tr>";
                    $('#User_tbody').append(con);
                });
                console.log(con);
            }
        },
        error: function (d) {
            alert("Http Status :" + d.error_code + ",Msg:" + d.message);
        }
    });
};

//读取全部管理员数据
function loadManagerData() {

    $.ajax({
        type: "post",
        url: "http://127.0.0.1:8080/user/ListManager",
        dataType: "json",
        // async: true,
        success: function (d) {
            console.log("服务器响应成功---");
            console.log(d);
            // alert(d.data.id);
            // 在这里补充登录成功或失败后的操作代码
            if (d.error_code == "0") {
                var result = d.data;
                con = "";
                $.each(result, function (index, item) {
                    con = "<tr>"
                        + "<td>" + item.id + "</td>"
                        + "<td>" + item.account + "</td>"
                        + "<td>" + "</td>"
                        + "<td>" + "</td>"
                        + "<td><a href =\"" +
                        "/jsp/User/Manager_edit.jsp?" +
                        "id_manager=" + item.id +
                        "&password_manager=" + item.password +
                        "&account=" + item.account +
                        "\">编辑</a>" +
                        "<a onclick='delete_item(1," + item.id  + ")'>删除" + "</a>" +
                        "</td>" + "</tr>";
                    $('#User_tbody').append(con);
                });
                console.log(con);
            }
        },
        error: function (d) {
            alert("Http Status :" + d.error_code + ",Msg:" + d.message);
        }
    });
};


//更改用户数据
function updateUserData() {

    var id = $("#id").val();
    var name = encodeURI($("#realname").val());
    var sex = encodeURI($("#sex").val());
    var tel = encodeURI($("#tel").val());
    var password = encodeURI($("#password").val());
    var adress = encodeURI($("#adress").val());


    $.ajax({
        type: "post",
        url: "http://127.0.0.1:8080/user/updateuser",
        data: "tel=" + tel
        + "&id=" + id
        + "&name=" + name
        + "&sex=" + sex
        + "&adress=" + adress
        + "&password=" + password,
        dataType: "json",
        // async: true,
        success: function (result) {
            console.log("服务器响应---");
            console.log(result);
            // alert(d.data.id);
            if (result.error_code == "0") {
                alert("修改成功");
            } else {
                alert("error message" + result.message);
            }
        },
        error: function (result) {
            alert("Http Status :" + result.error_code + ",Msg:" + result.message);
        }
    });
};


//更改管理员数据
updateManagerData = function () {
    alert("");

    var Id_Manager = $("#id").val();
    var Account = encodeURI($("#tel").val());
    var Password_Manager = encodeURI($("#password").val());


    $.ajax({
        type: "post",
        url: "http://127.0.0.1:8080/user/UpdateManager",
        data: "id=" + Id_Manager
        + "&account=" + Account
        + "&password=" + Password_Manager,
        dataType: "json",
        // async: true,
        success: function (result) {
            console.log("服务器响应---");
            console.log(result);
            // alert(d.data.id);
            if (result.error_code == "0") {
                alert("修改成功");
                alert(result.message);
            } else {
                alert("error message" + result.message);
            }
        },
        error: function (result) {
            alert("Http Status :" + result.error_code + ",Msg:" + result.message);
        }
    });
};

//查询用户
function QueryUser(type) {

    //查询Id
    var Id_Query = $("#username").val();

    if (type == 0) {      //查询用户

        $.ajax({
            type: "post",
            url: "http://127.0.0.1:8080/user/QueryUser",
            data: "userid=" + Id_Query,
            dataType: "json",
            async: true,
            success: function (d) {
                console.log("服务器响应---");
                console.log(d);
                // alert(d.data.id);
                if (d.error_code == "0") {

                    var con = "";
                    $('#User_tbody').empty();

                    con = "<tr>"
                        + "<td>" + d.data.id + "</td>"
                        + "<td>" + d.data.name + "</td>"
                        + "<td>" + d.data.tel + "</td>"
                        + "<td>" + d.data.update_time + "</td>"
                        // + "<td><a href='/jsp/User/user_edit.jsp?id=<%= item.id>+'>编辑</a></td>"
                        + "<td><a href =\"" +
                        "/jsp/User/user_edit.jsp?" +
                        "id=" + d.data.id +
                        "&password=" + d.data.password +
                        "&name=" + d.data.name +
                        "&tel=" + d.data.tel +
                        "&adress=" + d.data.adress +
                        "&sex=" + d.data.sex +
                        "\">编辑</a>" +
                        "<a onclick='delete_item(0," + d.data.id + ")'>删除" + "</a>" +
                        "</td>" + "</tr>";
                    $('#User_tbody').append(con);
                } else {
                    alert("error message" + d.message);
                }
            },
            error: function (d) {
                alert("Http Status :" + d.error_code + ",Msg:" + d.message);
            }
        });

    } else if (type == 1) {    //查询管理员


        $.ajax({
            type: "post",
            url: "http://127.0.0.1:8080/user/QueryManager",
            data: "userid=" + Id_Query,
            dataType: "json",
            async: true,
            success: function (d) {
                console.log("服务器响应---");
                console.log(d);

                if (d.error_code == "0") {

                    var id_ = d.data.id;
                    var con = "";
                    $('#User_tbody').empty();

                    con = "<tr>"
                        + "<td>" + d.data.id + "</td>"
                        + "<td>" + d.data.account + "</td>"
                        + "<td>" + "</td>"
                        + "<td>" + "</td>"
                        + "<td>" +
                        "<a href =\"" +
                        "/jsp/User/Manager_edit.jsp?" +
                        "id_manager=" + d.data.id +
                        "&password_manager=" + d.data.password +
                        "&account=" + d.data.account +
                        "\">编辑" +
                        "</a>" +
                        "<a onclick='delete_item(1," + id_ + ")'>删除" + "</a>"
                        + "</td>" + "</tr>";
                    $('#User_tbody').append(con);
                } else {
                    alert(d.message);
                }
            },
            error: function (d) {
                alert("Http Status :" + d.error_code + ",Msg:" + d.message);
            }
        });
    }

};


//后退 一页
function custom_close() {
    javascript:history.go(-1);
};


function delete_item(type, Id) {


    if (type == 0) {  //删除一条用户数据


        var table_user = $('#table table-bordered table-hover definewidth m10');

        $.ajax({
            type: "post",
            url: "http://127.0.0.1:8080/user/deleteuser",
            data: "id=" + Id,
            datatype: "json",
            success: function (d) {
                alert(d.message);
                table_user.parent().parent().remove();
            },
            error: function () {
                alert("error");
            }
        });
    } else if (type == 1) {  //删除一条管理员数据


        var table_user = $('#table table-bordered table-hover definewidth m10');

        $.ajax({
            type: "post",
            url: "http://127.0.0.1:8080/user/delManager",
            data: "id=" + Id,
            datatype: "json",
            success: function (d) {
                alert(d.message);
                table_user.parent().parent().remove();
            },
            error: function () {
                // alert("error");
            }
        });


    }

}





