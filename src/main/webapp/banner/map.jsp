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
    <script src="${pageContext.request.contextPath}/js/china.js"></script>
    <script type="text/javascript">
        $(function () {

            var myChart = echarts.init(document.getElementById('main'));
            $.get("${pageContext.request.contextPath}/user/selectByCity", function (data) {
                //$.get("{pageContext.request.contextPath}/test/china.json", function (data) {
                alert(data)
                var series = [];
                for (var i = 0; i < data.length; i++) {
                    var d = data[i];

                    series.push({
                        name: d.title,
                        type: 'map',
                        mapType: 'china',
                        label: {
                            normal: {
                                show: false
                            },
                            emphasis: {
                                show: true
                            }
                        },
                        data: d.citys
                    })
                }


                // 指定图表的配置项和数据
                option = {
                    title: {
                        text: '销量分布图',
                        subtext: '纯属虚构',
                        left: 'center'
                    },
                    tooltip: {
                        trigger: 'item'
                    },
                    legend: {
                        orient: 'vertical',
                        left: 'left',
                        data: ['小姐姐', '小哥哥']
                    },
                    visualMap: {
                        min: 0,
                        max: 2500,
                        left: 'left',
                        top: 'bottom',
                        text: ['高', '低'],           // 文本，默认为数值文本
                        calculable: true
                    },
                    toolbox: {
                        show: true,
                        orient: 'vertical',
                        left: 'right',
                        top: 'center',
                        feature: {
                            mark: {show: true},
                            dataView: {show: true, readOnly: false},
                            restore: {show: true},
                            saveAsImage: {show: true}
                        }
                    },
                    series: series
                };

                // 使用刚指定的配置项和数据显示图表。
                myChart.setOption(option);
            }, "json")
        });
    </script>
</head>
<body>
<div id="main" style="width: 600px;height:400px;"></div>

</body>
</html>