<!-- Copyright 2015 eLearning Solutions S.L.
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as
published by the Free Software Foundation, either version 3 of the
License, or (at your option) any later version.
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.
You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.-->

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	import="blackboard.data.course.Course" pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/bbNG" prefix="bbNG"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/css/students-roster.css" />

<!-- compiled and minified jQuery -->
<script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>

<!-- Bootstrap compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<!-- Bootstrap Optional theme -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
<!-- Bootstrap compiled and minified JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
	
<script src="${pageContext.request.contextPath}/js/tools-management.js"></script>

<bbNG:learningSystemPage title="${title}" ctxId="ctx">

	<bbNG:pageHeader instructions="${description}">
		<bbNG:breadcrumbBar>
			<bbNG:breadcrumb>${title}</bbNG:breadcrumb>
		</bbNG:breadcrumbBar>

		<bbNG:pageTitleBar>
				<h1>Herramientas instaladas</h1>
		</bbNG:pageTitleBar>
	</bbNG:pageHeader>

	<button id='sizes'>Mostrar tamaño de cursos</button>
	<br>
	<c:forEach items="${tool_rows}" var="row">
		<div class='row'>
			<div class="col-xs-3">
				${row.tool_type }
			</div>
			<div class="col-xs-5">
				${row.tool_handler }
			</div>
		</div>
	</c:forEach>

</bbNG:learningSystemPage>