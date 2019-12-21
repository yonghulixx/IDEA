<%@page pageEncoding="UTF-8" contentType="text/html; UTF-8" isELIgnored="false" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contentType}"/>
<script>
    $(function () {
        $("#abtable").jqGrid(
            {
                url: "${pageContext.request.contextPath}/album/queryByPage",
                datatype: "json",
                height: 190,
                rowNum: 6,
                rowList: [2, 4, 6, 8],
                pager: '#abpager',
                sortname: 'id',
                viewrecords: true,
                sortorder: "desc",
                multiselect: false,

                autowidth: true,
                height: "auto",
                styleUI: "Bootstrap",
                caption: "Grid as Subgrid",
                colNames: ['ID', '名字', '作者', '封面', '集数', '发布时间'/*, 'option'*/],
                colModel: [
                    {name: 'id', index: 'id', width: 55},
                    {name: 'title', index: 'invdate', width: 90},
                    {name: 'author', index: 'name', width: 100},
                    {
                        name: 'cover_img', index: 'amount', width: 80, align: "right", /*edittype:"file",*/
                        editoptions: {enctype: "multipart/from-data"}
                    },
                    {name: 'count', index: 'tax', width: 80, align: "right"},
                    {name: 'pub_date', index: 'total', width: 80, align: "right"},
                    //{name: 'note', index: 'note', width: 150, sortable: false}
                ],
                subGrid: true,
                subGridRowExpanded: function (subgridId, rowId) {
                    addSubGrid(subgridId, rowId);


                },
                /*subGridRowColapsed : function(subgrid_id, row_id) {
                    // this function is called before removing the data
                    //var subgrid_table_id;
                    //subgrid_table_id = subgrid_id+"_t";
                    //jQuery("#"+subgrid_table_id).remove();
                }*/
            });
        $("#abtable").jqGrid('navGrid', '#abpager',
            {add: true, edit: true, del: true}
        );
    });

    function addSubGrid(subgridId, rowId) {

        // var subgridTableId = subgrid_id + "table";
        // var pagerId = subgrid_table_id +"pager";
        var subgridTableId = subgridId + "table";
        var pagerId = subgridTableId + "pager";

        // $("#" + subgridId).html("<table id='subgridTableId'/>"+" <div id='pagerId'/>");
        $("#" + subgridId).html("<table id='" + subgridTableId + "'/><div id='" + pagerId + "'/>");

        $("#" + subgridTableId).jqGrid(
            {
                //url: ctx + "/SubGrid?q=2&id=" + row_id,
                url: "${pageContext.request.contextPath}/chapter/queryByPage?albumId=" + rowId,
                editurl: "${pageContext.request.contextPath}/chapter/edit?albumId=" + rowId,
                datatype: "json",
                rowNum: 6,
                rowList: [2, 4, 6, 8],
                pager: "#" + pagerId,
                sortname: 'num',
                sortorder: "asc",

                autowidth: true,
                height: "auto",
                viewrecords: true,
                styleUI: "Bootstrap",
                height: '100%',
                colNames: ['ID', '名字', '音频大小', '音频时长', '上传时间', "操作"],
                colModel: [
                    {name: "id", index: "num", width: 80, key: true},
                    {name: "url", editable: true, edittype: "file", index: "item", width: 130},
                    {name: "size", index: "qty", width: 70, align: "right"},
                    {name: "duration", index: "unit", width: 70, align: "right"},
                    {name: "up_date", index: "total", width: 70, align: "right"/*, sortable: false*/},
                    {
                        name: "url", align: "center",
                        formatter: function (cellVale) {
                            return "<a href=''#' onclick='playerChapter(\"" + cellVale + "\")'><span class='glyphicon glyphicon-play-circle'></span></a>&emsp;&emsp;&emsp;" +
                                "<a href=''#' onclick='downloadChapter(\"" + cellVale + "\")'><span class='glyphicon glyphicon-download'></span></a>";
                        }
                    }
                ]
            });
        $("#" + subgridTableId).jqGrid('navGrid', "#" + pagerId,
            {edit: true, add: true, del: true},
            {},
            {
                closeAfterAdd: true,
                afterSubmit: function (data) {
                    // alert(data)
                    console.log(data)
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/chapter/uploadChapter",
                        type: "post",
                        dataType: "JSON",
                        fileElementId: "url",
                        data: {id: data.responseText},
                        success: function () {
                            $("#bntable").trigger("reloadGrid");

                            $("#bntable").trigger("reloadGrid");
                            $("#showMsg").html(data.message);
                            $("#show").show();

                            /*setTimeout(function () {
                                $("#show").hide();
                            },3000);*/


                        }
                    })
                    return "isbug";
                },
            }
        );
    }

    function playerChapter(fileName) {
        alert(fileName);
        $("#audioModal").modal("show");
        $("#playAudio").attr("src", "${pageContext.request.contextPath}/bootstrap/radio/" + fileName);
    }

    function downloadChapter(fileName) {
        alert(fileName);
        location.href = "${pageContext.request.contextPath}/chapter/downloadChapter?fileNme=" + fileName;

    }
</script>

<div class="panel panel-success" id="">
    <div class="panel panel-heading">
        <h2>专辑信息</h2>
    </div>

    <ul class="nav nav-tabs" role="tablist">
        <li class="active"><a href="#"> 专辑信息</a></li>
    </ul>
    <%--<button id="add" class="btn btn-success">添加</button>
    <button id="edit" class="btn btn-warning">修改</button>
    <button id="del" class="btn btn-danger">删除</button>
--%>
    <div id="show" class="alert alert-warning alert-dismissible" style="width: 200px;display: none">
        <button type="button" class="close" data-dismiss="alert"><span>&times;</span></button>
        <strong id="showMsg">CHENGGONG!</strong><%-- Better check yourself, you're not looking too good.--%>
    </div>
    <table id="abtable"/>
    <div id="abpager"></div>

    <div id="audioModal" class="modal fade" tabindex="-1">
        <div class="modal-dialog">
            <audio id="playAudio" src="" controls/>
        </div>
    </div>

    <button id="add" class="btn btn-success">添加</button>
    <button id="edit" class="btn btn-warning">修改</button>
    <button id="del" class="btn btn-danger">删除</button>
</div>