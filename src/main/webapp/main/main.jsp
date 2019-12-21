<%@page pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:set var="path" value="${pageContext.request.contextPath}"/>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>持明法州后台管理系统</title>
    <link rel="icon" href="${path}/bootstrap/img/arrow-up.png" type="image/x-icon">
    <link rel="stylesheet" href="${path}/bootstrap/css/bootstrap.css">

    <%--引入jqgrid中主题css--%>
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/css/css/hot-sneaks/jquery-ui-1.8.16.custom.css">
    <link rel="stylesheet" href="${path}/bootstrap/jqgrid/boot/css/trirand/ui.jqgrid-bootstrap.css">
    <%--引入js文件--%>
    <script src="${path}/bootstrap/js/jquery.min.js"></script>
    <script src="${path}/bootstrap/js/bootstrap.js"></script>
    <script src="${path}/bootstrap/jqgrid/js/i18n/grid.locale-cn.js"></script>
    <script src="${path}/bootstrap/jqgrid/boot/js/trirand/jquery.jqGrid.min.js"></script>
    <script src="${path}/bootstrap/js/ajaxfileupload.js"></script>

</head>
<body>

<%--<h1 align="center">什么都没有了,全靠你们造啦O(∩_∩)O哈哈~</h1>--%>
<!--顶部导航-->
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">持明法州管理系统</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <%--<ul class="nav navbar-nav">
                <li><a href="#">BootStrap2中文文档</a></li>
            </ul>--%>

            <ul class="nav navbar-nav navbar-right">
                <li>欢迎: <span style="color:red">${sessionScope.adminLogin.name}</span></li>
                <li><a href="#">退出登录<span class="glyphicon glyphicon-log-out"></span></a></li>
            </ul>
        </div>
    </div>
</nav>

<!--栅格系统-->

<div class="container-fluid">
    <div class="row">
        <div class="col-md-2" align="center">
            <!--手风琴的样式 面板-->
            <%--<div class="panel-group" id="accordion1">
                <!--默认的面版样式-->
                <div class="panel panel-info">
                    <!--面板头-->
                    <div class="panel-heading">
                        <!--面板标题-->
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseOne">
                                用户管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse&lt;%&ndash; in&ndash;%&gt;">
                        <!--面板的主体内容-->
                        <div class="panel-body">
                            &lt;%&ndash;<a href="#">用户添加</a> <br/>&ndash;%&gt;
                            <ul class="nav nav-pills nav-stacked">
                                <li>
                                    <button type="button" class="btn btn-info">
                                        <a href="javascript:$('#mainId').load('${pageContext.request.contextPath}/login/login.jsp')">用户添加</a>
                                    </button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
            <hr>--%>
            <div class="panel-group" id="accordion2">
                <div class="panel panel-warning">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseTwo">
                                轮播图管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li>
                                    <button type="button" class="btn btn-info">
                                        <a href="javascript:$('#mainId').load('${path}/banner/banner.jsp')">轮播图信息</a>
                                        <%--
                                                                                <a href="javascript:$('#mainId').load('${pageContext.request.contextPath}/login/login.jsp')">轮播图信息</a>
                                        --%>
                                    </button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

            <hr>
            <div class="panel-group" id="accordion3">
                <div class="panel panel-success">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseScree">
                                专辑管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseScree" class="panel-collapse collapse ">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li>
                                    <button type="button" class="btn btn-info">
                                        <a href="javascript:$('#mainId').load('${pageContext.request.contextPath}/banner/album.jsp')">专辑管理</a>
                                    </button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

            <hr>
            <div class="panel-group" id="accordion4">
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseFour">
                                文章管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFour" class="panel-collapse collapse ">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li>
                                    <button type="button" class="btn btn-info">
                                        <a href="javascript:$('#mainId').load('${pageContext.request.contextPath}/banner/c.jsp')">文章管理</a>
                                    </button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

            <hr>
            <div class="panel-group" id="accordion5">
                <div class="panel panel-danger">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseFive">
                                上师管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFive" class="panel-collapse collapse ">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li>
                                    <button type="button" class="btn btn-info">
                                        <a href="javascript:$('#mainId').load('${pageContext.request.contextPath}/banner/guru.jsp')">上师管理</a>
                                    </button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

            <hr>
            <div class="panel-group">
                <div class="panel panel-primary">
                    <div class="panel-heading">
                        <h4 class="panel-title">
                            <a data-toggle="collapse" data-parent="#accordion" href="#collapseSix">
                                用户管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseSix" class="panel-collapse collapse ">
                        <div class="panel-body">
                            <ul class="nav nav-pills nav-stacked">
                                <li>
                                    <button type="button" class="btn btn-info">
                                        <a href="javascript:$('#mainId').load('${pageContext.request.contextPath}/banner/user.jsp')">用户管理</a>
                                    </button>
                                </li>
                                <br>
                                <li>
                                    <button type="button" class="btn btn-info">
                                        <a href="javascript:$('#mainId').load('${pageContext.request.contextPath}/banner/echarts.jsp')">用户统计</a>
                                    </button>
                                </li>
                                <br>
                                <li>
                                    <button type="button" class="btn btn-info">
                                        <a href="javascript:$('#mainId').load('${pageContext.request.contextPath}/banner/map.jsp')">用户统计</a>
                                    </button>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>

        </div>
        <div class="col-md-10" id="mainId">
            <div class="jumbotron">
                <h3>欢迎来到持明法州后台管理系统</h3>
            </div>

            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel" align="center">
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                </ol>

                <div class="carousel-inner">
                    <div class="item active">
                        <img src="${path}/bootstrap/img/1.png">
                        <div class="carousel-caption">
                            <%--这是一个美女--%>
                        </div>
                    </div>
                    <div class="item">
                        <img src="${path}/bootstrap/img/2.png">
                        <div class="carousel-caption">
                            <%--这是一个电脑--%>
                        </div>
                    </div>
                    <div class="item">
                        <img src="${path}/bootstrap/img/3.png">
                        <div class="carousel-caption">
                            <%--这是一个电脑--%>
                        </div>
                    </div>
                </div>

                <a class="left carousel-control" href="#carousel-example-generic" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#carousel-example-generic" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>

        </div>

    </div>
</div>
<!--左边手风琴部分-->


<!--巨幕开始-->
<!--右边轮播图部分-->
<!--页脚-->
<!--栅格系统-->
<div class="panel panel-footer" align="center">
    <div>
        @百知教育 ly@qq.com
    </div>
</div>
</body>
</html>
