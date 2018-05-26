<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" isELIgnored="false" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <script src="js/jquery/2.0.0/jquery.min.js"></script>
    <link href="css/bootstrap/3.3.6/bootstrap.min.css" rel="stylesheet">
    <script src="js/bootstrap/3.3.6/bootstrap.min.js"></script>

    <style type="text/css">
        .firstTD{
            text-align:right;
            width:50px;
        }
        .secondTD{
            text-align:left;
            width:200px;
        }
        div.loginErrorMessageDiv{
            width:300px;
            margin-top:10px;
            margin-bottom: 10px;
            display:none;
        }
        div.loginErrorMessageDiv div.alert{
            padding:5px !important;
        }
    </style>

    <script type="text/javascript">
        $(function () {
            $("button.edit").click(function () {
                var url = "/testssm/editStudent.do";
                $("#addForm").attr("action",url);
                $("#title").text("修改学生信息");
                $("#IDTR input").prop("disabled",false);
                $("#IDTR").show();
                var id = $(this).parent().siblings(".id").attr("sid");
                var name = $(this).parent().siblings(".name").attr("sname");
                var sex = $(this).parent().siblings(".sex").attr("sex");
                var age = $(this).parent().siblings(".age").attr("age");
                var gid = $(this).parent().siblings(".gid").attr("gid");
                var birthday = $(this).parent().siblings(".birthday").text().trim();
                $("td.secondTD input[name='id']").attr("value",id);
                $("td.secondTD input[name='name']").val(name);
                if ("male"==sex) {
                    $("td.secondTD input[value='male']").prop("checked","checked");
                } else {
                    $("td.secondTD input[value='female']").prop("checked","checked");
                }
                $("td.secondTD input[name='age']").val(age);
                $("td.secondTD option[value="+gid+"]").prop("selected","selected");
                $("td.secondTD input[name='birthday']").val(birthday);
                $("#addModal").modal("show");
            });

            $("button.add").click(function () {
                var url = "addStudent.do";
                $("#addForm").attr("action",url);
                $("#title").text("增加学生");
                $("#IDTR").hide();
                $("#IDTR input").prop("disabled",true);
                $("td.secondTD input[name='id']").attr("value","");
                $("td.secondTD input[name='name']").val("");
                $("td.secondTD input[value='male']").prop("checked","checked");
                $("td.secondTD input[name='age']").val("");
                $("td.secondTD option[value='class']").prop("selected","selected");
                $("td.secondTD input[name='birthday']").val("");
                $("#addModal").modal("show");
            });

            $("button.delete").click(function () {
                var url = "deleteStudent.do";
                var id = $(this).parent().siblings(".id").attr("sid");
                $.get(
                    url,
                    {"id":id},
                    function (result) {
                        if ("success"==result) {
                            window.location.href="listAllStudent.do";
                        }
                    }
                );
            });

            $("#submit").click(function () {
                var name = $("td.secondTD input[name='name']").val();
                if (""==name.trim()||null==name) {
                    $("span.errorMessage").text("姓名不能为空");
                    $("div.loginErrorMessageDiv").show();
                    return false;
                }
                var cla = $("td.secondTD option:selected").val();
                if (isNaN(Number(cla.trim()))) {
                    $("span.errorMessage").text("请选择班级");
                    $("div.loginErrorMessageDiv").show();
                    return false;
                }
                $("#addForm").submit();
            });

        });
    </script>

    <title>所有学生信息</title>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-12">
            <h1>listStudent</h1>
        </div>
    </div>

    <div classs="row">
        <div class="col-md-4 col-md-offset-8">
            <button class="btn btn-primary add">新增</button>
            <button class="btn btn-danger">删除</button>
        </div>
    </div>

    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover">
                <thead>
                <tr>
                    <th>#</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>年龄</th>
                    <th>班级</th>
                    <th>生日</th>
                    <th>操作</th>
                </tr>
                </thead>

                <tbody>
                <c:forEach items="${stus}" var="stu">
                    <tr>
                        <td class="id" sid="${stu.id}">${stu.id}</td>
                        <td class="name" sname="${stu.name}">${stu.name}</td>
                        <td class="sex" sex="${stu.sex}">${stu.sex=="male"?"男":"女"}</td>
                        <td class="age" age="${stu.age}">${stu.age}</td>
                        <td class="gid" gid="${stu.grade.id}">${stu.grade.name}</td>
                        <td class="birthday">
                            <fmt:formatDate value="${stu.birthday}" pattern="yyyy-MM-dd"/>
                        </td>
                        <td>
                            <button class="btn btn-primary edit">
                                <span class="glyphicon glyphicon-pencil" aria-hidden="true"></span>
                                编辑
                            </button>
                            <button class="btn btn-danger delete">
                                <span class="glyphicon glyphicon-trash" aria-hidden="true"></span>
                                删除
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

    <div class="row">
        <div class="col-md-6">
            当前第 ${page.pageNum} 页.总共 ${page.totalPage} 页.一共 ${page.total} 条记录
        </div>

        <div class="col-md-6">
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <li><a href="listAllStudent.do?pn=1">首页</a></li>

                    <li>
                        <c:if test="${page.hasPrevious}">
                            <a href="listAllStudent.do?pn=${page.pageNum-1}" aria-label="Previous">
                                <span aria-hidden="true">«</span>
                            </a>
                        </c:if>
                    </li>

                    <c:forEach items="${page.navPages}" var="navPage">
                        <c:if test="${navPage==page.pageNum}">
                            <li class="active"><a href="#nowhere">${navPage}</a></li>
                        </c:if>
                        <c:if test="${navPage!=page.pageNum}">
                            <li><a href="listAllStudent.do?pn=${navPage}">${navPage}</a></li>
                        </c:if>
                    </c:forEach>

                    <li>
                        <c:if test="${page.hasNext}">
                            <a href="listAllStudent.do?pn=${page.pageNum+1}" aria-label="Next">
                                <span aria-hidden="true">»</span>
                            </a>
                        </c:if>
                    </li>

                    <li><a href="listAllStudent.do?pn=${page.totalPage}">尾页</a></li>
                </ul>
            </nav>
        </div>
    </div>
</div>

<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button data-dismiss="modal" class="close" type="button">
                    <span aria-hidden="true">×</span><span class="sr-only">Close</span>
                </button>
                <h4 id="title" class="modal-title"></h4>
                <div class="loginErrorMessageDiv">
                    <div class="alert alert-danger">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"></button>
                        <span class="errorMessage"></span>
                    </div>
                </div>
            </div>

            <div class="modal-body">
                <form id="addForm" action="#nowhere" method="post">
                <table class="center-block" style="text-align:center;">
                    <tr id="IDTR">
                        <td class="firstTD">#:</td>
                        <td class="secondTD">
                            <input type="text" readonly="readonly" name="id" class="form-control"/>
                        </td>
                    </tr>
                    <tr>
                        <td class="firstTD">姓名:</td>
                        <td class="secondTD"><input type="text" name="name" class="form-control"/></td>
                    </tr>
                    <tr>
                        <td class="firstTD">性别:</td>
                        <td class="secondTD">
                            <span class="input-group-addon">
                                男<input type="radio" name="sex" value="male" checked="checked" />
                            </span>
                            <span class="input-group-addon">
                                女<input type="radio" name="sex" value="female" />
                            </span>
                        </td>
                    </tr>
                    <tr>
                        <td class="firstTD">年龄:</td>
                        <td class="secondTD"><input type="text" name="age" class="form-control"/></td>
                    </tr>
                    <tr>
                        <td class="firstTD">班级:</td>
                        <td class="secondTD">
                            <select name="gid" id="grade" class="form-control">
                                <option value="class" selected="selected">请选择班级</option>
                                <c:forEach items="${grades}" var="grade">
                                    <option value="${grade.id}">${grade.name}</option>
                                </c:forEach>
                            </select>
                        </td>
                    </tr>
                    <tr>
                        <td class="firstTD">生日:</td>
                        <td class="secondTD">
                            <input type="text" name="birthday" class="form-control"
                                   placeholder="输入格式为yyyy-MM-dd"/>
                        </td>
                    </tr>
                </table>
                </form>
            </div>
            <div class="modal-footer">
                <button data-dismiss="modal" class="btn btn-default" type="button">关闭</button>
                <button class="btn btn-primary" id="submit" type="button">提交</button>
            </div>

        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div>

<div style="height:200px"></div>
</body>
</html>