<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    // 获得请求的绝对地址
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>注册 - </title>
    <base href="<%=basePath%>">
    <link href="css/base.css" type="text/css" rel="stylesheet" />
    <link href="css/register.css" type="text/css" rel="stylesheet" />
    <meta
            content="二手,二手交易,校园交易,二手买卖,旧物买卖,旧物交易,旧物转手，校园二手交易，校园二手买卖，校园买卖，校园交易"
            name="keywords">
    <meta
            content="易易专注于为校园大学生提供二手交易的机会，同时秉承公平交易和优质售后，让还有利用价值的东西不需要进入垃圾桶，让买家买的放心，让卖家卖的开心。"
            name="description">
    <script type="text/javascript">
        var xhr = false;
        function createXHR() {
            try {
                xhr = new XMLHttpRequest();
            } catch (e) {
                try {
                    xhr = new ActiveXObject("Microsoft.XMLHTTP");
                } catch (e1) {
                    xhr = false;
                }
            }
            if (!xhr)
                alert("初始化XMLHttpRequest对象失败！");
        }
        function ajaxValidate(emailObj){
            createXHR();
            var url = "UserRegisterServlet";
            var content = "type=emailAjaxValidate&email=" + emailObj.value;
            xhr.open("POST", url, true);
            xhr.onreadystatechange = function() {
                if (xhr.readyState == 4 && xhr.status == 200) {
                    document.getElementById("emailValidate").innerHTML = xhr.responseText;
                }
            };
            xhr.setRequestHeader("Content-Length",content.length);
            xhr.setRequestHeader("CONTENT-TYPE","application/x-www-form-urlencoded");
            xhr.send(content);
        }

        function validate() {
            var email = document.getElementById("email");
            var password = document.getElementById("password");
            var repass=document.getElementById("repass");
            var agree = document.getElementById("agree");
            var pattern = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/;

            if (email.value == "") {
                alert("邮箱不能为空！");
                email.focus();
                return false;
            } else if (!pattern.test(email.value)) {
                alert("请输入正确的邮箱格式！");
                email.focus();
                return false;
            }
            if (password.value == "") {
                alert("密码不能为空！");
                password.focus();
                return false;
            } else if (password.length<6 || password.length>12) {
                alert("密码长度不符合要求，请输入6-12位密码!");
                password.focus();
                return false;
            }else if(repass.value!=password.value){
                alert("两次密码不一致");
                repass.focus();
                return false;
            }
            if (!agree.checked) {
                alert("请先同意本站服务条款！");
                return false;
            }

            return true;
        }
        //验证码的更换
        function changeValidateCode() {
            document.getElementById("validateCode").src = "ValidateCodeServlet?rand="
                + Math.random();
        }
    </script>
    <style type="text/css">
        .register-area{
            width: 100%;
            margin-top: 20px;
            position: relative;
            padding-top: 10px;
        }
        .register-content{
            margin-left: auto;
            margin-right: auto;
            width: 900px;
        }
        .span1{
            margin-top: 5px;
            margin-bottom: 5px;
        }
         .login_l{
            margin-left: auto;
            margin-right: auto;
            width: 400px;
            padding: 10px 10px;
            margin-bottom: 10px;
            border-radius: 5px;
            font-size: 14px;
        }
         .red-start{
             color: red;
         }
          .font14{
            font-size: 18px;
            font-weight: bold;
            color: #3d566e;
            margin-bottom: 20px;
              text-align: center;
        }
        .content-login-box .login_l>div{
            margin-top: 20px;
        }
        .login_l .tn-textbox{
            width: 100%;
            border-radius: 5px;
            border: solid 1px #ddd;
            padding: 8px;
            font-size: 15px;
            margin-bottom: 10px;
        }
        .login_l .form-button-area{
            height: 30px;
        }
        .tn-form-label{

        }
        .tn-button-text{
            width: 150px;
            height: 30px;
            color: white;
            background-color: #ff9900;
            border: none;
            margin-left: 125px;
            float: left;
            transition: 1s all;
        }
        .tn-button-text:hover{
            background-color: orange;
        }
    </style>
</head>
<body>
<jsp:include page="top2.jsp"></jsp:include>
<!-- 注册部分开始 -->
<div class="register-area">
    <div class="register-content">
        <p class="font14" style="color: gray">会员注册</p>
        <form action="UserRegisterServlet" method="post" onsubmit="return validate();">
            <div class="login_l">
                <div class="span1">
                    <label class="tn-form-label">会员名称：</label>
                    <input class="tn-textbox" type="text" name="name" id="name">
                </div>
                <div class="span1">
                    <label class="tn-form-label"><span class="red-start">*</span>邮箱地址：</label>
                    <input class="tn-textbox" type="text" name="email" id="email" onblur="ajaxValidate(this)">
                    <label style="color: red" id="emailValidate"></label>
                </div>
                <div class="span1">
                    <label class="tn-form-label"><span class="red-start">*</span>密码：</label> <input class="tn-textbox" type="password" name="password" id="password">
                </div>
                <div class="span1">
                    <label class="tn-form-label"><span class="red-start">*</span>重新输入密码：</label> <input class="tn-textbox" type="password" name="repass" id="repass">
                </div>
                <div class="span1">
                    <label class="tn-form-label">性别：</label>
                    <div class="clear"></div>
                    <input  type="radio" name="sex" value="1">男
                    <input  type="radio" name="sex" value="2">女
                </div>
                <div class="span1" style="line-height: 30px;height: 30px">
                    <label class="tn-form-label" ><span class="red-start">*</span>验证码：</label>
                    <input
                         type="text" name="verifyCode">
                    <span >
                    <img src="ValidateCodeServlet"
                         id="validateCode" title="点击换一换" onclick="changeValidateCode()">
                    <a href="javascript:changeValidateCode();">看不清？</a>
                </span>
                </div>

                <div class="span1" style="height:30px ">
                    <input name="submit" type="submit" class="tn-button-text"
                           value="立即注册">
                </div>
                <div clas3s="span1" style="margin-left: 125px">
                    <p class="it-register-text">
                        <input name="agree" id="agree" class="tn-checkbox"
                               checked="checked" type="checkbox"> <label>同意本站服务条款</label>
                    </p>
                </div>
            </div>
        </form>
    </div>
</div>
<!-- 注册部分结束 -->

<!-- 网站公共尾部 -->
<jsp:include page="foot.jsp"></jsp:include>
</html>