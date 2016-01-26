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

package es.elearningmedia.courseslastaccess.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import org.springframework.stereotype.Service;

import blackboard.data.course.Course;
import blackboard.data.course.CourseMembership;
import blackboard.data.course.CourseMembership.Role;
import blackboard.data.course.size.CourseSize;
import blackboard.data.course.size.CourseSizeManager;
import blackboard.persist.Id;
import blackboard.persist.KeyNotFoundException;
import blackboard.persist.PersistenceException;
import blackboard.persist.course.CourseDbLoader;
import blackboard.persist.course.CourseMembershipDbLoader;
import blackboard.platform.intl.BbResourceBundle;
import blackboard.platform.intl.BundleManagerFactory;
import blackboard.platform.plugin.PlugIn;
import blackboard.platform.plugin.PlugInManagerFactory;
import es.elearningmedia.courseslastaccess.model.CourseLastAccess;
import es.elearningmedia.courseslastaccess.model.StringsBundle;

@Service
public class BbServiceImpl implements BbService {
	
	@Override
	public StringsBundle getBundle() {

		// Get bundle strings for internationalization
		StringsBundle resultado = new StringsBundle();

		PlugIn plugin = PlugInManagerFactory.getInstance().getPlugIn("elea", "coursesLastAccess");
		BbResourceBundle resourceBundle = BundleManagerFactory.getInstance().getPluginBundle(plugin.getId());

		resultado.setStrCourse(resourceBundle.getString("b2.strCourse"));
		resultado.setStrLastAccess(resourceBundle.getString("b2.strLastAccess"));
		resultado.setStrLicense(resourceBundle.getString("b2.strLicense"));
		resultado.setStrTitle(resourceBundle.getString("b2.name"));
		resultado.setStrDescription(resourceBundle.getString("b2.description"));
		resultado.setStrSize(resourceBundle.getString("b2.strSize"));
		resultado.setStrStudents(resourceBundle.getString("b2.strStudents"));
		resultado.setStrInstructors(resourceBundle.getString("b2.strInstructors"));

		return resultado;
	}

	@Override
	public List<CourseLastAccess> getCoursesLastAccess() {
		
		List<CourseLastAccess> result = new ArrayList<CourseLastAccess>();
		
		try {
			List<Course> cursos = CourseDbLoader.Default.getInstance().loadAllCourses();
			
			for(Course curso : cursos) {
				
				List<CourseMembership> courseMemberships = CourseMembershipDbLoader.Default.getInstance().loadByCourseId(curso.getId());
				
				Calendar lastAccess = null;
				
				int studentsCount = 0;
				int instructorsCount = 0;
				
				for(CourseMembership courseMembership: courseMemberships) {
					if(lastAccess == null) {
						lastAccess = courseMembership.getLastAccessDate();
					}else{
						Calendar access = courseMembership.getLastAccessDate();
						if(access != null) {
							if(access.after(lastAccess)) {
								lastAccess = courseMembership.getLastAccessDate();
							}
						}
					}
					
					Role role = courseMembership.getRole();
					if(role.equals(CourseMembership.Role.STUDENT)) {
						studentsCount++;
					}else {
						instructorsCount++;
					}
				}
				
				CourseLastAccess courseLastAccess = new CourseLastAccess();
				courseLastAccess.setCourse_id(curso.getCourseId());
				SimpleDateFormat formatedDate = new SimpleDateFormat("dd-MM-yyyy");
				if(lastAccess == null) {
					courseLastAccess.setLast_access("Never");
					courseLastAccess.setDate_sort("0");
				}else {
					courseLastAccess.setLast_access(formatedDate.format(lastAccess.getTime()));
					courseLastAccess.setDate_sort(Long.toString(lastAccess.getTimeInMillis()));
				}
				
				courseLastAccess.setInstructors(Integer.toString(instructorsCount));
				courseLastAccess.setStudents(Integer.toString(studentsCount));
				
				courseLastAccess.setSize(Long.toString(getCourseSizes(curso)));
				
				result.add(courseLastAccess);
			}
			
			
		} catch (KeyNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (PersistenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Collections.sort(result);
		
		return result;
	}
	
	public Long getCourseSizes(Course course) {
		
		Id courseId = course.getId();
		CourseSize csm = new CourseSize();
		try {
			csm = CourseSizeManager.getInstance().loadByCourseId(courseId);
		} catch (KeyNotFoundException e) {
			e.printStackTrace();
		}
		return csm.getSizeTotal();
		
	}

}
