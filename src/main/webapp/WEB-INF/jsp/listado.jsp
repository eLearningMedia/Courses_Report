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
	import="blackboard.data.course.Course"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/bbNG" prefix="bbNG"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

	<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/listado.css"/>
	
	<!-- compiled and minified jQuery -->
	<script src="https://code.jquery.com/jquery-1.11.1.min.js"></script>
	
    <!-- Bootstrap compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
    <!-- Bootstrap Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
    <!-- Bootstrap compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>

<bbNG:learningSystemPage title="Listado" ctxId="ctx">

	<bbNG:pageHeader instructions="${descripcion}">
		<bbNG:breadcrumbBar>
			<bbNG:breadcrumb>${titulo}</bbNG:breadcrumb>
		</bbNG:breadcrumbBar>
		
		<bbNG:pageTitleBar >
				${course}<br>
				${numeroalumnos}<br>
		</bbNG:pageTitleBar>
		<div class="row">
			<div id="link-licencia-listado" class="col-xs-3 col-xs-offset-9">
				<a href="https://github.com/eLearningMedia/b2b-listado-alumnos" target="_blank">${licencia}</a>
			</div>
		</div>
	</bbNG:pageHeader>
	
	<c:forEach items="${alumnos}" var="alumno" varStatus="loopStatus">
		<c:if test="${loopStatus.index % 2 == 0}">
			<div class='row datos-listado'>
		</c:if>
				<div class='col-xs-2'>
					<c:choose>
					    <c:when test="${alumno.foto.length() == 0}">
					       <img src="${pageContext.request.contextPath}/images/desconocido.jpg" class="foto">
					    </c:when>
					    <c:otherwise>
					        <img src="${alumno.foto}" class="foto">
					    </c:otherwise>
					</c:choose>
				</div>
				<div class='col-xs-4'>
					<div class='nombre-listado row'>
					<c:choose>
					    <c:when test="${alumno.middlename.length() == 0}">
					       ${nombre} ${alumno.firstname} &nbsp;${alumno.lastname}
					    </c:when>
					    <c:otherwise>
					        ${nombre} ${alumno.firstname} &nbsp;${alumno.middlename} &nbsp;${alumno.lastname}
					    </c:otherwise>
					</c:choose>
					</div>
					<div class='email-listado row'>
						${email}<a href="mailto:${alumno.email }">${alumno.email }</a>
					</div>
				</div>
		<c:if test="${loopStatus.index % 2 != 0}">
			</div>
		</c:if>	
	</c:forEach>
	
</bbNG:learningSystemPage>