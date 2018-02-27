$(function () {
    loadUserData();
});

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
                    // con += "<li>id：" + item.id + "</li>";
                    // con += "<li>电话：" + item.tel + "</li>";
                    con = "<tr>"
                        + "<td>"+ item.id +"</td>"
                        + "<td>"+ item.name +"</td>"
                        + "<td>"+ item.tel +"</td>"
                        + "<td>"+ item.update_time +"</td>"
                        + "<td><a href='/jsp/User/user_edit.jsp?id=hehe'>编辑</a></td>"
                        // + "<td><a onclick='jump(item.id)'>编辑</a></td>"
                        + "</tr>";
                    $('#table_user').append(con);
                });
                console.log(con);
            }
        },
        error: function (d) {
            alert("Http Status :" + d.error_code + ",Msg:" + d.message);
        }
    });

    function  jump(ids) {

          window.location.href = '/jsp/User/user_edit.jsp?id='+ids;
    }


}
