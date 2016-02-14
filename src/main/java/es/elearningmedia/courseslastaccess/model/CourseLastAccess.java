/* Copyright 2015 eLearning Solutions S.L.
This program is free software: you can redistribute it and/or modify
it under the terms of the GNU Affero General Public License as
published by the Free Software Foundation, either version 3 of the
License, or (at your option) any later version.
This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Affero General Public License for more details.
You should have received a copy of the GNU Affero General Public License
along with this program.  If not, see <http://www.gnu.org/licenses/>.*/

package es.elearningmedia.courseslastaccess.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Represents each of the courses with the last access by any user
 * 
 * @author albertoruiz
 *
 */
public class CourseLastAccess implements Comparable<CourseLastAccess> {

	private String course_id;
	private String last_access;
	private String date_sort;
	private String size;
	private String students;
	private String instructors;
	private String created;
	private String status;

	public String getCourse_id() {
		return course_id;
	}

	public void setCourse_id(String course_id) {
		this.course_id = course_id;
	}

	public String getLast_access() {
		return last_access;
	}

	public void setLast_access(String last_access) {
		this.last_access = last_access;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getStudents() {
		return students;
	}

	public void setStudents(String students) {
		this.students = students;
	}

	public String getInstructors() {
		return instructors;
	}

	public void setInstructors(String instructors) {
		this.instructors = instructors;
	}

	public String getDate_sort() {
		return date_sort;
	}

	public void setDate_sort(String date_sort) {
		this.date_sort = date_sort;
	}

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	@Override
	public int compareTo(CourseLastAccess o) {
		
		if(o.getLast_access().equalsIgnoreCase("Never") && getLast_access().equalsIgnoreCase("Never")) {
			return 0;
		}else if(getLast_access().equalsIgnoreCase("Never")) {
			return -1;
		}else if(o.getLast_access().equalsIgnoreCase("Never")) {
			return 1;
		}else {
			SimpleDateFormat formatedDate = new SimpleDateFormat("dd-MM-yyyy");
			
			try {
				return formatedDate.parse(getLast_access()).compareTo(formatedDate.parse(o.getLast_access()));
			} catch (ParseException e) {
				return 0;
			}
		}	
	}

}
