<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>简历基本信息 - 锐聘网</title>
<base href="<%=basePath%>">
<link href="css/base.css" type="text/css" rel="stylesheet" />
<link href="css/resume.css" type="text/css" rel="stylesheet" />
<meta
	content="大学生求职,大学生就业,大学生招聘,IT人才,IT人才招聘,大学生名企招聘,,大学生找工作,IT名企招聘，IT行业招聘，IT企业快速入职"
	name="keywords">
<meta
	content="锐聘专注于为企业提供高效的人力资源解决方案，同时面向IT类技术人才推出快速一站式免费就业服务。秉承QST青软实训人才服务理念，为数千家企业量身定做个性化、全程化的人才培养体系，同时帮助中高级人才铺设成功之路，为人才和企业架设起沟通之桥。"
	name="description">
<script type="text/javascript">
	function validate() {
		var headShot = document.getElementById("headShot");
		if (headShot.value == "") {
			alert("请选择要上传的头像！");
			headShot.focus();
			return false;
		}
		return true;
	}
</script>
</head>
<body>
<% int goodsId=Integer.valueOf(request.getParameter("goodsId"));

%>
	<!-- 网站公共头部 -->
	<jsp:include page="../top.jsp"></jsp:include>
	<!-- 简历照片上传页面 开始 -->
	<div class="resume_con">
		<!--tab设置-->
		<!--主体部分-->
		<div class="resume_main">
			<!--左边-->
			<div class="resume_left">
				<div class="resume_title">
					<div style="float: left">商品图片</div>
				</div>
				<div class="all_resume">
					<!--------------------- 简历照片修改------------------------->
					<form action="SellGoodsServlet?type=addPic&goodsId=<%=goodsId%>" method="post"
						enctype="multipart/form-data" onsubmit="return validate();">
						<div class="table_style" style="margin-left: 150px;">
							<div class="uploade" style="background-color: white;border: none">
								<div align="center">
									<img src="../images/默认图片.png" width="150" height="150" style="width: 150px;height: 150px">
									<p>&nbsp;</p>
									<input name="gPic" id="gPic" type="file" value="上传照片">
								</div>
							</div>
							<div class="clear"></div>
							<div class="he"></div>
							<div align="center">
								<input name="submit" type="submit" class="save1" value="保存" >
								<input name="reset" type="reset" class="cancel2" value="取消" style="margin-left: 50px">
							</div>
						</div>
					</form>
					<!--------------------- 简历照片修改 结束---------------------->
				</div>
			</div>

		</div>
	</div>
	<!-- 我的简历页面结束 -->
	<!-- 网站公共尾部 -->
	<jsp:include page="../foot.jsp"></jsp:include>
</body>
</html>