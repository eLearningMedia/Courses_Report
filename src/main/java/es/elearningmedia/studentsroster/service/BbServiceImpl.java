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

package es.elearningmedia.studentsroster.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blackboard.data.course.Course;
import blackboard.data.course.CourseMembership;
import blackboard.persist.PersistenceException;
import blackboard.persist.course.CourseMembershipDbLoader;
import blackboard.platform.context.Context;
import blackboard.platform.context.ContextManager;
import blackboard.platform.context.ContextManagerFactory;
import blackboard.platform.intl.BbResourceBundle;
import blackboard.platform.intl.BundleManagerFactory;
import blackboard.platform.plugin.PlugIn;
import blackboard.platform.plugin.PlugInManagerFactory;
import es.elearningmedia.studentsroster.model.StringsBundle;
import es.elearningmedia.studentsroster.model.Student;
import es.elearningmedia.studentsroster.repository.BbRepository;

@Service
public class BbServiceImpl implements BbService {

	@Autowired
	BbRepository bbRepository;

	@Override
	public List<Student> getEnroledStudents(String courseId) {

		ContextManager contextManager = ContextManagerFactory.getInstance();
		Context ctx = contextManager.getContext();
		Course sessionCourse = ctx.getCourse();

		List<Student> resultado = new ArrayList<Student>();

		try {

			// Get enroled Students
			List<CourseMembership> alumnos = CourseMembershipDbLoader.Default.getInstance()
					.loadByCourseIdAndRole(sessionCourse.getId(), CourseMembership.Role.STUDENT, null, true);

			// Get database pk1 for each user
			int[] ids = new int[alumnos.size()];

			for (int i = 0; i < alumnos.size(); i++) {
				ids[i] = Integer.parseInt(alumnos.get(i).getUserId().getExternalString().split("_")[1]);
			}

			// extract photos from database
			resultado = bbRepository.getPhotos(ids);

			// Order students by lastname
			Map<String, Student> sorter = new TreeMap<String, Student>();
			for (Student element : resultado) {
				sorter.put(
						element.getLastname() + element.getFirstname() + element.getMiddlename() + element.getEmail(),
						element);
			}

			resultado = new ArrayList<Student>(sorter.values());

		} catch (PersistenceException e) {
			e.printStackTrace();
		}

		return resultado;
	}

	@Override
	public String getCourseTitle() {

		ContextManager contextManager = ContextManagerFactory.getInstance();
		Context ctx = contextManager.getContext();
		Course sessionCourse = ctx.getCourse();

		return sessionCourse.getTitle();
	}

	@Override
	public StringsBundle getBundle() {

		// Get bundle strings for internationalization
		StringsBundle resultado = new StringsBundle();

		PlugIn plugin = PlugInManagerFactory.getInstance().getPlugIn("elea", "listadodealumnos");
		BbResourceBundle resourceBundle = BundleManagerFactory.getInstance().getPluginBundle(plugin.getId());

		resultado.setStrCourse(resourceBundle.getString("b2.strCourse"));
		resultado.setStrDescription(resourceBundle.getString("b2.description"));
		resultado.setStrNumberStudents(resourceBundle.getString("b2.strNumberStudents"));
		resultado.setStrName(resourceBundle.getString("b2.strName"));
		resultado.setStrLicense(resourceBundle.getString("b2.strLicense"));
		resultado.setStrEmail(resourceBundle.getString("b2.strEmail"));

		return resultado;
	}

}
