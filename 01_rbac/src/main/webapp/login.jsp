<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
    //http://localhost:8080/01_sxtoa_war_exploded/
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <base href="<%=basePath%>">
    <title>Title</title>
    <%--引入EasyUI的资源--%>
    <link rel="stylesheet" type="text/css" href="static/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="static/themes/icon.css">
    <link rel="stylesheet" type="text/css" href="static/demo.css">
    <script type="text/javascript" src="static/js/jquery.min.js"></script>
    <script type="text/javascript" src="static/js/jquery.easyui.min.js"></script>
    <%--声明js代码域--%>
    <script type="text/javascript">
        /*声明代码:判断登录页面是否在顶层中显示，不是则置顶显示*/
        var p=window.top.location.href;//获取最顶层的url地址
        var c=window.location.href;//获取当前区域的url地址
        if(p!=c){
            window.top.location.href=window.location.href;
        }
        /********声明页面加载事件:给登录按钮添加单击事件，完成登录请求的发送****************************/
        $(function () {
            $("#userLogin").click(function () {
                //提交表单,使用easyUI的表单异步提交
                $("#userFm").form('submit',{
                    url:"userLogin",
                    success:function (data) {//只要有响应结果就会触发，无论后台处理结果施成功还是失败，data接收的就是响应结果
                        if(eval(data)){
                            window.location.href="main.jsp";
                        }else{
                            alert("登录失败:用户名或密码错误")
                        }
                    }
                })
            })
        })
    </script>
</head>
<body>
<%--1.使用panel面板完成页面的基础布局--%>
<div style="width: 430px;height: 300px;margin: auto;margin-top: 100px;">
    <div class="easyui-panel" style="text-align: center" data-options="title:'登录',iconCls:'icon-save',fit:true">
        <%--2.声明表单搜集用户的登录信息--%>
        <form  id="userFm" method="post">
            <%--使用textboxt组件搜集文本信息(身份信息)--%>
            <div style="margin-top: 40px;">
                <input class="easyui-textbox" name="uname" data-options="width:250" prompt="用户名/手机号/邮箱" label="账号:" labelWidth="60">
            </div>
            <%--使用passwordbox组件搜集密码信息--%>
            <div style="margin-top: 40px;">
                <input class="easyui-passwordbox" name="pwd" prompt="请输入密码" data-options="width:250" label="密码:" labelWidth="60">
            </div>
            <%--使用linkbutton组件完成登录按钮--%>
            <div style="margin-top: 40px;">
                <%--将href属性值设置为javascript:void(0)，阻断超链接的资源跳转功能--%>
                <a id="userLogin" href="javascript:void(0)" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">点击登录</a>
                &nbsp;  &nbsp;  &nbsp;  &nbsp;  &nbsp;
                <%--使用checkbox组件，完成记住密码效果--%>
                <input class="easyui-checkbox" label="记住密码" labelPosition="after">
            </div>
        </form>
    </div>
</div>
</body>
</html>
