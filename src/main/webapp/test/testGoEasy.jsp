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
    <script src="http://cdn-hangzhou.goeasy.io/goeasy.js"></script>

    <script>
        var goEasy = new GoEasy({
            appkey: "df0a45499f274b2bae29ae50a6a12dc9"
        });
        goEasy.subscribe({
            channel: "mychannel",
            onMessage: function (message) {
                alert("Channel:" + message.channel + " content:" + message.content);
            }
        });
    </script>
</head>
<body>
<h1>javwa</h1>
</body>
</html>