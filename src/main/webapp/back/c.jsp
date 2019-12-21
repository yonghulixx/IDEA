<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contentType}"/>

<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/kindeditor-all.js"></script>
<script charset="utf-8" src="${pageContext.request.contextPath}/kindeditor/lang/zh-CN.js"></script>
<script>
    KindEditor.create('#editor_id', {
        /* width:'900px',
         height:'600px',
         minWidth:"300",
         minHeight:'500',*/
        uploadJson: "${pageContext.request.contextPath}/article/upload",
        filePostName: "photo",
        allowFileManager: true,
        fileManagerJson: "${pageContext.request.contextPath}/article/queryAllPhoto",
        afterBlur: function () {
            this.sync();
        }

    });
</script>
<script>
    $(function () {
        $("#bntable").jqGrid({
            url: "${pageContext.request.contextPath}/article/selectAll",
            styleUI: "Bootstrap",
            datatype: "json",
            autowidth: true,
            cellEdit: true,

            rowNum: 6,
            rowList: [2, 4, 6, 8, 10],
            pager: "#bnpager",
            // sortname: "age",
            // caption: "员工表",
            height: 300,
            editurl: "${pageContext.request.contextPath}/article/edit",
            viewrecords: true,
            colNames: ["Id", "名称", "图片", "内容", "上传时间", "上师Id", /*"option"*/],
            colModel: [
                {name: "id", width: 200},
                {name: "title", editable: true},
                {
                    name: "insert_img", /*width:100,*/align: "center", editable: true, edittype: "file",
                    formatter: function (cellvalue) {
                        return "<img src='${pageContext.request.contextPath}/bootstrap/imgs/" + cellvalue + "' style='width:90px;height:35px' />";
                    },
                },
                {name: "content", editable: true},
                {
                    name: "up_date", /*editable: true,*/ /*edittype: "select", editoptions: {value: "1:展示;2:不展示"},*/
                    /*formatter: function (cellvalue, option, row) {
                        if (cellvalue == 1) {
                            return "<button class='btn btn-success' onclick='change(\""+row.id+"\",\""+cellvalue+"\")'>不展示</button>";
                        }else{
                            return "<button class='btn btn-danger' onclick='change(\""+row.id+"\",\""+cellvalue+"\")'>展示</button>";

                        }
                    },*/
                },
                {
                    name: "guruId",
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
            }
            /* {},
            {
                closeAfterAdd: true,
                afterSubmit: function (data) {
                    // alert(data)
                    console.log(data)
                    $.ajaxFileUpload({
                        url: "/banner/uploadBanner",
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
            {}*/
        );

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
    $("#btn1").click(function () {
        //alert()
        var rowId = $("#bntable").jqGrid("getGridParam", "selrow");
        if (rowId != null) {
            //alert(""行id+rowId);

            var row = $("#bntable").jqGrid("getRowData", rowId);//alert(row);

            $("#myModal").modal("show");

            $("#title").val(row.title);

            KindEditor.html("#editor_id", row.content);

            $("#modalFooter").html("<button type='button' class='btn btn-default' onclick='updateArticle(\"" + rowId + "\")'>提交</button><button type='button' class='btn btn-primary' data-dismiss='modal'>关闭</button>");

        } else {
            alert("请选中一行");
        }
    })
    $("#btn2").click(function () {
        $("#articleFrom")[0].reset();
        KindEditor.html("#editor_id", "");

        $("#myModal").modal("show");
        $("#modalFooter").html("<button type='button' class='btn btn-default' onclick='addArticle()'>提交</button>" +
            "<button type='button' class='btn btn-primary' data-dismiss='modal'>关闭</button>");

    })

    function addArticle() {
        a.ajax({
            url: "${pageContext.request.contextPath}/article/add",
            type: "post",
            //dateType:"json",
            dataType: "json",
            data: $("#articleFrom").serialize(),
            success: function () {
                alert()
                $("#myModal").modal('hide');
                $("#bntable").trigger("reloadGrid");

            }
        })
    }

    $("#btn3").click(function () {
        alert()
    });

    function updateArticle(rowId) {
        alert(rowId)
        a.ajax({
            url: "${pageContext.request.contextPath}/article/update?ArticleId=" + rowId,
            type: "post",
            dataType: "json",
            data: $("#articleFrom").serialize(),
            success: function () {
                $("#myModal").modal('hide');
                $("#bntable").trigger("reloadGrid");

            }
        })

    }
</script>

<div class="panel panel-info">
    <div class="panel panel-heading">
        <h2>文章信息</h2>
    </div>
    <ul class="nav nav-tabs" role="tablist">
        <li class="active"><a href="#"> 文章信息</a></li>
    </ul>
    <button id="btn1" class="btn btn-success">查看文章</button>
    <button id="btn2" class="btn btn-warning">添加文章</button>
    <button id="btn4" class="btn btn-danger">删除文章</button>
    <div id="show" class="alert alert-warning alert-dismissible" style="width: 200px;display: none">
        <button type="button" class="close" data-dismiss="alert"><span>&times;</span></button>
        <%--<div align="right"><h8 >不再提示</h8></div>--%>
        <strong id="showMsg"></strong>
    </div>
    <table id="bntable"/>
    <div id="bnpager"></div>
    <%--<button id="add" class="btn btn-success">添加</button>
    <button id="edit" class="btn btn-warning">修改</button>
    <button id="del" class="btn btn-danger">删除</button>--%>
</div>


<div id="myModal" class="modal fade" <%--tabindex="-1"--%>>
    <div class="modal-dialog" style="width: 730px">
        <div class="modal-content">

            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
                <h4 class="modal-title">Modal title</h4>
            </div>

            <div class="modal-body">
                <form class="form-group" id="articleFrom">
                    <div class="input-group">
                        <span class="input-group-addon">标题</span>
                        <input id="title" name="title" type="text" class="form-control" placeholder="">
                    </div>
                    <div class="input-group">
                        <textarea id="editor_id" name="content" style="width:700px;height:300px;">
                             &lt;strong&gt;HTML内容&lt;/strong&gt;
                        </textarea>
                    </div>

                </form>
            </div>

            <div class="modal-footer" id="modalFooter">

            </div>
        </div>
    </div>
</div>
