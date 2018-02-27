<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
    <title>后台管理系统</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <link href="/assets/css/dpl-min.css" rel="stylesheet" type="text/css"/>
    <link href="/assets/css/bui-min.css" rel="stylesheet" type="text/css"/>
    <link href="/assets/css/main-min.css" rel="stylesheet" type="text/css"/>
</head>
<body>

<div class="header">

    <div class="dl-title">
        <!--<img src="/chinapost/Public/assets/img/top.png">-->
    </div>

    <div class="dl-log">欢迎您，<span class="dl-log-user">root</span><a href="/chinapost/index.php?m=Public&a=logout"
                                                                    title="退出系统" class="dl-log-quit">[退出]</a>
    </div>
</div>
<div class="content">
    <div class="dl-main-nav">
        <div class="dl-inform">
            <div class="dl-inform-title"><s class="dl-inform-icon dl-up"></s></div>
        </div>
        <ul id="J_Nav" class="nav-list ks-clear">
            <li class="nav-item dl-selected">
                <div class="nav-item-inner nav-home">系统管理</div>
            </li>
            <li class="nav-item dl-selected">
                <div class="nav-item-inner nav-order">业务管理</div>
            </li>
        </ul>
    </div>
    <ul id="J_NavContent" class="dl-tab-conten">

    </ul>
</div>
<script type="text/javascript" src="/assets/js/jquery-1.8.1.min.js"></script>
<script type="text/javascript" src="/assets/js/bui-min.js"></script>
<script type="text/javascript" src="/assets/js/common/main-min.js"></script>
<script type="text/javascript" src="/assets/js/config-min.js"></script>
<script>
    BUI.use('common/main', function () {
        var config = [
            {
                id: '1', menu: [{
                    text: '用户管理',
                    items: [
                        {id: '12', text: '用户管理', href: '/jsp/User/User_manager.jsp'},
                        {id: '3', text: '管理员管理', href: '/jsp/User/User_manager.jsp'},
                        {id: '4', text: '修改密码', href: '/jsp/User/Update_psd.jsp'}]
                }]
            },
            {
                id: '7', homePage: '9', menu: [{
                    text: '业务管理',
                    items: [
                        {id: '9', text: '商品管理', href: '/jsp/Goods/Goods_Manager.jsp'},
                        {id: '10', text: '评论管理', href: '/jsp/User/Comment_Manager.jsp'},
                        {id: '11', text: '留言板管理', href: '/jsp/User/MsgBoard_manager.jsp'},
                        {id: '12', text: '统计业务', href: '/jsp/User/Tongji_manager.jsp'}
                    ]
                }]
            }];

        new PageUtil.MainPage({
            modulesConfig: config
        });
    });
</script>
</body>
</html>
