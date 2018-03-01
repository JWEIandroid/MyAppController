// 定义全局的用户Id
var NowUserId = "";


$(function () {
    // Junbo.SetCookie();
    $("#UserName").focus();
});

function Loginnew()
{
    var UserName = $("#UserName").val();
    var Psw = $("#Psw").val();
    
    if(UserName.trim()=="")
    {
        alert("请输入用户名.");
        return;
    }
    
    if(Psw.trim()=="")
    {
        alert("请输入用户密码.");
        return;
    }


    $.ajax({
        type: "post",
        data: "tel=" + UserName + "&password=" + Psw,
        url: "http://127.0.0.1:8080/user/login",
        dataType: "json",
        // async: true,
        success: function (d) {
            console.log("服务器响应成功---");
            console.log(d);
            // alert(d.data.id);
            // 在这里补充登录成功或失败后的操作代码
            if(d.error_code == 0){
                alert(d.message);
                window.location.href = "/jsp/normal/index.jsp?loginname="+UserName;
            }
            else {
                alert(d.message);
            }
        },
        error: function (d) {
            alert("Http Status :" + d.status + ",Msg:" + d.statusText);                    
        }
    });

        
    
}

function Login()
{
    var UserName = $("#UserName").val();
    var Psw = $("#Psw").val();
    
    if(UserName.trim()=="")
    {
        alert("请输入用户名.");
        return;
    }
    
    if(Psw.trim()=="")
    {
        alert("请输入用户密码.");
        return;
    }
    
    
    var p={};
    p.Username = UserName;
    p.Psw = Psw;

    Junbo.CallService("UserLogin", p, OnLoginComplete);      
}

function OnLoginComplete(r)
{
    if (!Junbo.getResult(r.S)) {
        return;
    }

    if (r.C) {
        alert("登录成功")
        // $.cookie("jbfu", r.C.Id, { expires: 2, path: '/' });
        // location.href = "/Manage/TaskInfo.aspx";
    }     
}

function InputUserName()
{
    if(event.keyCode==13)  //按下Enter键
    {
        $("#Psw").focus();
    }
}

function InputPsw()
{
    if(event.keyCode==13)
    {
        Login();
    }
}

function ClearUserName()
{
    $("#UserName").val("");
}

function ClearPsw()
{
    $("#Psw").val("");
}
