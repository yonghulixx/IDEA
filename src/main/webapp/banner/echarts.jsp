<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contentType}"/>

<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
    <script src="${pageContext.request.contextPath}/js/echarts.js"></script>
    <%--<script src="${pageContext.request.contextPath}/bootstrap/js/jquery.min.js"></script>--%>
    <script type="text/javascript">
        $(function () {

            var myChart = echarts.init(document.getElementById('main'));
            $.get("${pageContext.request.contextPath}/user/selectByMonth", function (data) {

                //$.get("{pageContext.request.contextPath}/test/user.json",function (data) {

                alert(data)
                var option = {
                    title: {
                        text: '用户注册量展示',
                        //link:"{pageContext.request.contextPath}/main/main.jsp",
                        target: "self",
                        subtext: "用户信息"

                    },
                    tooltip: {},
                    legend: {
                        //show:false,
                        data: ['小男孩', '小女孩']
                    },
                    xAxis: {
                        data: data.month
                    },
                    yAxis: {},
                    series: [{
                        name: '小男孩',
                        type: 'bar',
                        data: data.boys
                    }, {
                        name: '小女孩',
                        type: 'bar',
                        data: data.girls
                    }]
                };

                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            }, "json")
        })

    </script>
</head>
<body>
<div id="main" style="width: 600px;height:400px;"></div>

</body>
</html>