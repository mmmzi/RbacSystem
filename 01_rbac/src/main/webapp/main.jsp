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
        /***
         * 一、问题:
         *    一级菜单点击后会新增一个标签页显示，但是一级菜单是不需要的，怎么办?
         * 解决:
         *   我们需要在菜单节点的单击事件中，新增逻辑，判断当前点击的菜单是否为
         *   一级菜单，如果是则不新增标签页，如果不是则继续其他逻辑。
         * 注意:
         *     菜单节点的单击事件中获取获取一个为node的实参，node为当前点击的菜单节点的
         *     信息的json对象。node中的数据其实是Tree组件的异步加载的响应数据。那么我们
         *     是不是可以在node中添加一个标记，标记该菜单节点是否为一级菜单，而在node中
         *     新增标记数据，说白了，就让Tree组件的响应数据中的json中新增标记数据。而Tree组件的
         *     响应数据是后台给的，一个菜单节点的响应数据为一个TreeResult对象的json。
         *     也就是说我们需要在TreeResult中新增新的属性存储新的数据即可。但是TreeResult中的
         *     属性是不能随便声明的，因为需要和Tree组件配套使用，就需要再次查看Tree组件的API文档
         *     查看TreeResult中如何声明除id,text,state属性以外的属性。通过API发现，每个Tree组件的
         *     节点可以定义6个属性:
         *          id：表示菜单节点的ID
         *          text:菜单节点的名称
         *          state:菜单节点的状态
         *          attributes:节点的自定义属性，放到TreeResult实体类中，就是一个map集合
         *          children:子节点集合
         *          checked:是否显示多选框效果
         *   实现：
         *        ① 在TreeResult实体类中新增属性 attributes,属性类型为map集合
         *        ② 修改MenuServiceImpl的业务代码，在将Menu转换为TreeResult对象时，新增给attributes属性赋值
         *          使用attributes属性存储菜单的自定义的数据，比如:节点的类型，节点的url地址
         *        ③ 在Tree的单击事件中，新增判断逻辑，判断当前点击的节点的node中的attributes中的isparent的属性值，
         *          如果为1，则表示为父节点，则不需要新增标签页。
         * 二、问题:
         *      点击菜单已经实现，新增一个标签页了，但是在标签页面并没有实现显示当前菜单的url地址资源，怎么办?
         *    解决:
         *      新增的标签页中显示当前点击的节点的url地址资源，而标签页本质其实一个DIV标签。
         *      而Div标签本身是不具备URL地址访问的功能的。那么我们就需要在DIv中开辟一个可以
         *      根据URL地址显示资源的空间，考虑使用iframe标签。也就是当我们点击菜单节点，新增一个
         *      标签页的时候，在新增的标签页中添加一个iframe标签，并将点击的节点的url地址给
         *      iframe，让其去加载对应的资源显示给用户。
         *    实现:
         *        ① 在新增的标签页面，初始化时使用content属性，在新增的标签页中添加一个iframe标签，用来
         *          加载节点的url资源
         *        ② 将单击的节点的url地址赋值给新增的标签页中的iframe标签。
         *
         * /
        /***************实现菜单节点和tabs组件的联动效果******************************/
        var p = window.top.location.href;
        var c = window.location.href;
        if(p!=c){
            window.top.location.href=window.location.href;
        }

        $(function () {
            $('#menuTree').tree({
                onClick: function (node) {//node为存储了当前点击的节点的信息的Json对象
                    console.log(node)
                    if(node.attributes.isparent==1){return;}
                    //判断标签页是否存在
                    var has=$("#tabs").tabs('exists',node.text);
                    if(has){
                        //选择已经存在的标签页
                        $("#tabs").tabs('select',node.text);
                    }else{
                        //新增标签页
                        $("#tabs").tabs('add',{
                            title:node.text,
                            content:"<iframe src='"+node.attributes.url+"' style='width: 100%;height: 100%;border: none' />",
                            closable:true
                        })
                    }
                }
            });
        })
        /***********************给数组件添加onLoadError监听**********************************/
        $(function () {
            $("#menuTree").tree({
                onLoadError:function (arguments) {
                    //跳转登录页面
                    window.location.href="login.jsp";
                }
            })
        })
    </script>
</head>
<body class="easyui-layout">
<div data-options="region:'north',split:false" style="height:70px;"></div>
<div data-options="region:'south',split:false" style="height:50px;"></div>
<div data-options="region:'west',title:'系统菜单',split:false" style="width:200px;">
    <%--使用Tree组件完成菜单信息的加载--%>
    <ul class="easyui-tree" id="menuTree" data-options="url:'menuInfo'"></ul>
</div>
<%--布局：中心--%>
<div data-options="region:'center'" style="padding:5px;background:#eee;">
    <%--使用tabs组件完成标签页显示效果--%>
    <div id="tabs" class="easyui-tabs" data-options="fit:true">
        <div title="Tab1" style="padding:20px;display:none;">首页</div>
    </div>
</div>
</body>
</html>
