<%@ page import="com.jsu.bean.User" %>
<%@ page import="com.jsu.dao.UserDAO" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>我的简历 - 锐聘网</title>
    <base href="<%=basePath%>">
    <link href="../css/base.css" type="text/css" rel="stylesheet"/>
    <link rel="stylesheet" type="text/css" href="../css/resume.css"/>
    <meta
            content="大学生求职,大学生就业,大学生招聘,IT人才,IT人才招聘,大学生名企招聘,,大学生找工作,IT名企招聘，IT行业招聘，IT企业快速入职"
            name="keywords">
    <meta
            content="锐聘专注于为企业提供高效的人力资源解决方案，同时面向IT类技术人才推出快速一站式免费就业服务。秉承QST青软实训人才服务理念，为数千家企业量身定做个性化、全程化的人才培养体系，同时帮助中高级人才铺设成功之路，为人才和企业架设起沟通之桥。"
            name="description">
</head>
<body>
<jsp:include page="../top.jsp"></jsp:include>
<!-- 从request对象中获取一个JavaBean对象 -->
<% User user=(User)request.getSession().getAttribute("SESSION_USER");
int userId=user.getId();
user=new UserDAO().findByUserID(userId);
%>
<!-- 我的简历页面开始 -->
<div class="resume_con">
    <!--tab设置-->
    <div class="user_operate">
        <ul style="float: left">
            <li> <a href="userBaseInfo.jsp">我的信息</a></li>
        </ul>
        <div class="clear"></div>
    </div>
    <!--主体部分-->
    <div class="resume_main">
        <!--左边-->
        <div class="resume_left">
            <div class="resume_title">
                <div style="float: left">基本信息</div>
                    <div class="btn">
                        <a href="/buy/updateUserInfo.jsp" style="color: white">修改</a>
                    </div>
            </div>
            <div class="all_resume">
                <div class="table_style">
                    <table width="300" border="0" cellpadding="3" cellspacing="1"
                           bgcolor="#EEEEEE">
                        <tr>
                            <td width="110" align="right" bgcolor="#F8F8F8"
                                style="color: gray;">姓名：</td>
                            <td bgcolor="#F8F8F8"><%=user.getName()%></td>
                        </tr>
                    </table>
                    <div class="he"></div>
                    <table width="300" border="0" cellpadding="3" cellspacing="1"
                           bgcolor="#EEEEEE">
                        <tr>
                            <td width="110" align="right" bgcolor="#F8F8F8"
                                style="color: gray;">性别：</td>
                            <td bgcolor="#F8F8F8"><%=user.getSex()%></td>
                        </tr>
                    </table>
                    <div class="he"></div>
                    <table width="300" border="0" cellpadding="3" cellspacing="1"
                           bgcolor="#EEEEEE">
                        <tr>
                            <td width="110" align="right" bgcolor="#F8F8F8"
                                style="color: gray;">出生日期：</td>
                            <td bgcolor="#F8F8F8"><%=user.getAge()%></td>
                        </tr>
                    </table>
                    <div class="he"></div>

                    <table width="300" border="0" cellpadding="3" cellspacing="1"
                           bgcolor="#EEEEEE">
                        <tr>
                            <td width="110" align="right" bgcolor="#F8F8F8"
                                style="color: gray;">手机：</td>
                            <td bgcolor="#F8F8F8"><%=user.getPhone()%></td>
                        </tr>
                    </table>
                    <div class="he"></div>
                    <table width="300" border="0" cellpadding="3" cellspacing="1"
                           bgcolor="#EEEEEE">
                        <tr>
                            <td width="110" align="right" bgcolor="#F8F8F8"
                                style="color: gray;">邮箱：</td>
                            <td bgcolor="#F8F8F8"><%=user.getEmail()%></td>
                        </tr>
                    </table>
                    <div class="he"></div>
                </div>
                <div style="float: right" class="uploade">
                    <% if("".equals(user.getHeadPic()) || null==user.getHeadPic()){%>
                    <img src="../images/profile_pic.png">
                    <% }else{ %>
                    <img src="../buy/images/<%=user.getHeadPic()%>">
                    <% } %>
                    <p>&nbsp;</p>
                    <div align="center">
                        <a href="/buy/HeadPicUpload.jsp" class="uploade_btn">更换照片</a>
                    </div>
                </div>
                <div class="clear"></div>
            </div>

        </div>
        <!-- 右侧公共部分：简历完善度 -->
        <div style="clear: both"></div>
    </div>
</div>
<!-- 我的简历页面结束 -->
<!-- 网站公共尾部 -->
<jsp:include page="../foot.jsp"></jsp:include>
</body>
</html>