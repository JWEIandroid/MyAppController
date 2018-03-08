//查询全部用户收支
function LoadAllUserIncomeNPay() {

    $.ajax({
        type: "get",
        // url: "http://123.207.26.152:8080/record/getincomeNpay",
        url: "http://127.0.0.1:8080/record/getincomeNpay",
        dataType: "json",
        success: function (d) {
            console.log("...." + d);
            for (var i = 0; i < d.data.length; i++) {
                var rows = "<tr><td>" + d.data[i].username + "</td>"
                rows += "<td>" + d.data[i].income + "</td>"
                rows += "<td>" + d.data[i].pay + "</td>"
                    "</td></tr>"
                $('#IncomeNpay_tbody').append(rows);
            }
        },
        error: function () {
            alert("查询全部收支----服务器出问题啦");
        }
    });
};




