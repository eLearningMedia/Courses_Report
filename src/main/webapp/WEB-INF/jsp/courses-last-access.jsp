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
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="/bbNG" prefix="bbNG"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.11/css/jquery.dataTables.min.css">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/buttons/1.1.2/css/buttons.dataTables.min.css">

<script src="https://code.jquery.com/jquery-1.12.0.min.js"></script>

<script src="https://cdn.datatables.net/1.10.11/js/jquery.dataTables.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.1.2/js/dataTables.buttons.min.js"></script>
<script src="https://cdn.datatables.net/buttons/1.1.2/js/buttons.html5.min.js"></script>

<script src="${pageContext.request.contextPath}/js/courses-report.js"></script>

<bbNG:learningSystemPage title="${str_bundle.strTitle}" ctxId="ctx">

	<bbNG:pageHeader instructions="${str_bundle.strDescription}">
		<bbNG:breadcrumbBar>
			<bbNG:breadcrumb>${str_bundle.strTitle}</bbNG:breadcrumb>
		</bbNG:breadcrumbBar>

		<bbNG:pageTitleBar>
				${str_bundle.strTitle}
		</bbNG:pageTitleBar>
		<a href="https://github.com/eLearningMedia/Courses_Report/releases/tag/V1.1" target="_blank">${str_bundle.strLicense}</a>
	</bbNG:pageHeader>
	<table id="courses-report">
		<thead>
			<tr>
				<th>${str_bundle.strCourse}</th>
				<th id="order-date">hidden</th>
				<th>${str_bundle.strLastAccess}</th>
				<th>${str_bundle.strSize}</th>
				<th>${str_bundle.strStudents}</th>
				<th>${str_bundle.strInstructors}</th>
				<th>${str_bundle.strCreated}</th>
				<th>${str_bundle.strStatus}</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${course_rows}" var="row">
			<tr>
				<td>${row.course_id }</td>
				<td>${row.date_sort }</td>
				<td>${row.last_access }</td>
				<td>${row.size }</td>
				<td>${row.students }</td>
				<td>${row.instructors }</td>
				<td>${row.created }</td>
				<td>${row.status }</td>
		</c:forEach>
		</tbody>
	</table>

</bbNG:learningSystemPage>