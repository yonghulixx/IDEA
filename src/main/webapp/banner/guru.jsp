<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contentType}"/>
<script>
    $(function () {
        $("#bntable").jqGrid({
            url: "${pageContext.request.contextPath}/guru/selectAll",
            styleUI: "Bootstrap",
            datatype: "json",
            autowidth: true,
            cellEdit: true,

            rowNum: 6,
            rowList: [2, 4, 6, 8, 10],
            pager: "#bnpager",
            //sortname: "age",
            //caption: "员工表",
            height: 300,
            editurl: "${pageContext.request.contextPath}/guru/edit",
            viewrecords: true,
            colNames: ["Id", "名字", "头像", "状态", "注册时间", /*"option"*/],
            colModel: [
                {name: "id", width: 300},
                {name: "name"/*, editable: true*/},
                {
                    name: "pic_img", /*width:100,*/align: "center"/*, editable: true*/, edittype: "file",
                    formatter: function (cellvalue) {
                        return "<img src='${pageContext.request.contextPath}/bootstrap/imgs/" + cellvalue + "' style='width:90px;height:35px' />";
                    },
                },
                //  {name: "description", editable: true},
                {
                    name: "status", /*editable: true,*/ /*edittype: "select", editoptions: {value: "1:展示;2:不展示"},*/
                    formatter: function (cellvalue, option, row) {
                        if (cellvalue == 1) {
                            return "<button class='btn btn-success' onclick='change(\"" + row.id + "\",\"" + cellvalue + "\")'>不展示</button>";
                        } else {
                            return "<button class='btn btn-danger' onclick='change(\"" + row.id + "\",\"" + cellvalue + "\")'>展示</button>";

                        }
                    },
                },
                {
                    name: "reg_date",
                },
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
                        url: "${pageContext.request.contextPath}/guru/uploadBanner",
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
                url: "${pageContext.request.contextPath}/guru/updateStatus",
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
                url: "${pageContext.request.contextPath}/guru/updateStatus",
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
        <h2>上师信息</h2>
    </div>

    <ul class="nav nav-tabs" role="tablist">
        <li class="active"><a href="#"> 上师信息</a></li>
    </ul>
    <button id="add" class="btn btn-success">添加上师</button>
    <button id="edit" class="btn btn-warning">修上师</button>
    <button id="del" class="btn btn-danger">删除上师</button>
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