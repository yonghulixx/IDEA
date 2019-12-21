<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contentType}"/>
<script>
    $(function () {
        $("#bntable").jqGrid({
            url: "${pageContext.request.contextPath}/user/selectAll",
            styleUI: "Bootstrap",
            datatype: "json",
            autowidth: true,
            cellEdit: true,

            rowNum: 6,
            rowList: [2, 4, 6, 8, 10],
            pager: "#bnpager",
            // sortname: "age",
            caption: "",
            height: 300,
            editurl: "${pageContext.request.contextPath}/user/edit",
            viewrecords: true,
            colNames: ["Id", "手机号", "密码", "盐", "头像", "法名", "姓名", "性别", "所在地", "签名", "状态", "注册时间", "所属上师"],
            colModel: [
                {name: "id"},
                {name: "phone"/*, editable: true*/},
                {name: "password", /*width:100,*/align: "center"/*, editable: true*/, edittype: "file",},
                {name: "salt"/*, editable: true*/},
                {
                    name: "pic_img",
                    edittype: "file",
                    formatter: function (cellvalue) {
                        return "<img src='${pageContext.request.contextPath}/bootstrap/imgs/" + cellvalue + "' style='width:60px;height:35px' />";
                    }, /*editable: true,*/ /*edittype: "select", editoptions: {value: "1:展示;2:不展示"},*/
                },
                {name: "ahama",},
                {name: "name",},
                {name: "sex",},
                {name: "city",},
                {name: "sign",},
                {
                    name: "status", width: 160,
                    formatter: function (cellvalue, option, row) {
                        if (cellvalue == 1) {
                            return "<button class='btn btn-success' onclick='change(\"" + row.id + "\",\"" + cellvalue + "\")'>不展示</button>";
                        } else {
                            return "<button class='btn btn-danger' onclick='change(\"" + row.id + "\",\"" + cellvalue + "\")'>展示</button>";

                        }
                    },
                },
                {name: "reg_date",},
                {name: "guruId",},

                /*{
                    name: "option",width: 160, formatter: function (cellvalue, options, rowObject) {
                       return "<a class='btn ' onclick=\"del('" + rowObject.id + "')\"><span class='glyphicon glyphicon-trash'></span></a><a class='btn ' onclick=\"update('" + rowObject.id + "')\"><span class='glyphicon glyphicon-wrench'></span></a>";
                       // return "<a class='btn ' onclick=\"update('" + rowObject.id + "')\"><span class='glyphicon glyphicon-wrench'>修改</span></a><a class='btn ' onclick=\"del('" + rowObject.id + "')\"><span class='glyphicon glyphicon-trash'>删除</span></a>";
                       // return "<a class='btn ' onclick=\"update('" + rowObject.id + "')\"><span class='glyphicon glyphicon-wrench'>修改</span></a><a class='btn ' onclick=\"del('" + rowObject.id + "')\"><span class='glyphicon glyphicon-trash'>删除</span></a>";
                       // return "<a class='btn ' onclick=\"update('" + rowObject.id + "')\"><span class='glyphicon glyphicon-wrench'>修改</span></a><a class='btn ' onclick=\"del('" + rowObject.id + "')\"><span class='glyphicon glyphicon-trash'>删除</span></a>";
                    }
                }*/
            ],
            /*onCellSelect:function (rowid,iCol,cellcontent,e) {
                alert(rowid);
                alert(iCol);
                alert(cellcontent);
                alert(e);
            },*/


            /*onSelectRow: function (rowid, status) {
                alert(rowid),
                    alert(status)

            }*/
        })
        /*.jqGrid("navGrid", "#pager", {edit: true});
                $("#add").click(function () {
                    $("#list").jqGrid('editGridRow', "new", {
                        height: 300,
                        reloadAfterSubmit: true
                    })
                });
                $("#edit").click(function () {
                    var gr = $("#list").jqGrid('getGridParam', 'selrow');
                    if (gr != null)
                        $("#list").jqGrid('editGridRow', gr, {
                            height: 300,
                            reloadAfterSubmit: true
                        })
                    else alert("Please Select Row");
                })
                $("#del").click(function () {
                    var gr = $("#list").jqGrid('getGridParam', 'selrow');
                    if (gr != null)
                        $("#list").jqGrid('delGridRow', gr, {
                            height: 300,
                            reloadAfterSubmit: true
                        })
                    else alert("Please Select Row!");
                })*/


        $("#bntable").jqGrid('navGrid', '#bnpager', {
                edit: true,
                add: true,
                del: true,
                addtext: "添加",
                edittext: "编辑",
                deltext: "删除"
            },
            {},
            {
                closeAfterAdd: true,
                afterSubmit: function (data) {
                    // alert(data)
                    console.log(data)
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/banner/uploadBanner",
                        type: "post",
                        dataType: "JSON",
                        fileElementId: "img_path",
                        data: {id: data.responseText},
                        success: function () {
                            $("#bntable").trigger("reloadGrid");
                        }
                    })
                    return "isbug";
                },
                //   closeAfterAdd: true,
            },
            {}
        );

    })

    function change(id, value) {
        // alert(id)
        // alert(value)
        if (value == 1) {
            $.ajax({
                url: "${pageContext.request.contextPath}/user/updateStatus",
                type: "post",
                dataType: "JSON",
                data: {"id": id, "status": "2"},
                success: function (data) {
                    $("#bntable").trigger("reloadGrid");
                    $("#showMsg").html(data.message);
                    $("#show").show();
                }
            })
            /*;*/

        } else {
            $.ajax({
                url: "${pageContext.request.contextPath}/user/updateStatus",
                type: "post",
                dataType: "JSON",
                data: {"id": id, "status": "1"},
                success: function (data) {
                    $("#bntable").trigger("reloadGrid");
                    $("#showMsg").html(data.message);
                    $("#show").show();
                }
            })
        }
    }

    $("#export").click(function () {
        location.href = "${pageContext.request.contextPath}/user/easyPoiExport";
        /*$.ajax({
            url: "{pageContext.request.contextPath}/user/easyPoiExport",
            type: "post",
            dataType: "JSON",
            // data:{"id":id,"status":"1"},
            success: function (data) {
                $("#bntable").trigger("reloadGrid");
                // $("#showMsg").html(data.message);
                // $("#show").show();
            }
        })*/
    })
    /*function del(id) {
        if (id != null)
            $("#list").jqGrid('delGridRow', id, {
                height: 300,
                reloadAfterSubmit: true
            })
        else alert("Please Select Row!");
    }

    function update(id) {
        if (id != null)
            $("#list").jqGrid('editGridRow', id, {
                height: 300,
                reloadAfterSubmit: true
            })
        else alert("Please Select Row");
    }*/
</script>

<div class="panel panel-info">
    <div class="panel panel-heading">
        <h2>用户信息</h2>
    </div>

    <ul class="nav nav-tabs" role="tablist">
        <li class="active"><a href="#"> 用户信息</a></li>
    </ul>
    <button id="export" class="btn btn-success">导出用户信息</button>
    <button id="edit" class="btn btn-warning">导入用户</button>
    <button id="del" class="btn btn-danger">测试按钮</button>
    <div id="show" class="alert alert-warning alert-dismissible" style="width: 200px;display: none">
        <button type="button" class="close" data-dismiss="alert"><span>&times;</span></button>
        <%--<div align="right"><h8 >不再提示</h8></div>--%>
        <strong id="showMsg"></strong><%--Better check yourself, you're not looking too good.--%>
    </div>
    <table id="bntable"/>
    <div id="bnpager"></div>
    <%--<button id="add" class="btn btn-success">添加</button>
    <button id="edit" class="btn btn-warning">修改</button>
    <button id="del" class="btn btn-danger">删除</button>--%>
</div>