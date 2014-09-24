<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    <!--  base href="<%=basePath%>"-->
    <!--  title>My JSP 'tagCloud.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page"-->
	
	<link href="<c:url value="/resources/css/jqcloud.css" />" rel="stylesheet"  type="text/css" />
    <script type="text/javascript" src="<c:url value="/resources/jquery/1.6/jquery.js" />"></script>
    <script type="text/javascript" src="<c:url value="/resources/js/jqcloud-1.0.4.js" />"></script>
    <!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
    <script type="text/javascript">
    
    var word_list = [
      {text: "halo", weight: 2, link:"a"},
	  {text: "hey", weight: 3, link:"a"},
	  {text: "cat", weight: 2, link:"a"},
	  {text: "dog", weight: 4, link:"a"},
	 ];
      
      $(function() {
        $("#tagCloud").jQCloud(word_list);
      });
    </script>
  </head>
  <body>
<!-- border: 1px solid #ccc; -->
    <div id="tagCloud" style="width: 550px; height: 200px; "></div>
  </body>
</html>
