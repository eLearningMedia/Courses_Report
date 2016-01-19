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

package es.elearningmedia.toolsmanagement.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import blackboard.data.course.Course;
import blackboard.data.course.Course.ServiceLevel;
import blackboard.data.course.size.CourseSize;
import blackboard.data.course.size.CourseSizeManager;
import blackboard.data.navigation.ToolSettings;
import blackboard.data.navigation.ToolSettings.Type;
import blackboard.persist.Id;
import blackboard.persist.PersistenceException;
import blackboard.persist.course.CourseDbLoader;
import blackboard.persist.navigation.ToolSettingsManagerFactory;
import blackboard.platform.institutionalhierarchy.service.Node;
import blackboard.platform.institutionalhierarchy.service.NodeManagerFactory;
import blackboard.platform.institutionalhierarchy.service.ObjectType;
import blackboard.platform.intl.BbResourceBundle;
import blackboard.platform.intl.BundleManagerFactory;
import blackboard.platform.plugin.PlugIn;
import blackboard.platform.plugin.PlugInManagerFactory;
import es.elearningmedia.toolsmanagement.model.StringsBundle;
import es.elearningmedia.toolsmanagement.repository.BbRepository;

@Service
public class BbServiceImpl implements BbService {

	@Autowired
	BbRepository bbRepository;
	
	@Override
	public Type[] getToolTypes() {
		
		return ToolSettings.Type.values();
		
	}
	
	@Override
	public List<ToolSettings> getToolsSettings(Type type) {
		
		try {
			return ToolSettingsManagerFactory.getInstance().loadAllToolSettings(type, false);
		} catch (PersistenceException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public Node getNodeById(Id nodeId) {
		
		
		
		try {
			Node node = NodeManagerFactory.getHierarchyManager().loadRootNode();
			
			while(NodeManagerFactory.getHierarchyManager().hasChildren(node.getNodeId())) {
				
				List<Node> associated = NodeManagerFactory.getAssociationManager().loadAssociatedNodes(node.getNodeId(), ObjectType.Tab);
				
			}
			
			
			
			return NodeManagerFactory.getHierarchyManager().loadNodeById(nodeId);
		} catch (PersistenceException e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public StringsBundle getBundle() {

		// Get bundle strings for internationalization
		StringsBundle resultado = new StringsBundle();

		PlugIn plugin = PlugInManagerFactory.getInstance().getPlugIn("elea", "toolsManagement");
		BbResourceBundle resourceBundle = BundleManagerFactory.getInstance().getPluginBundle(plugin.getId());

		resultado.setStrCourse(resourceBundle.getString("b2.strCourse"));
		resultado.setStrDescription(resourceBundle.getString("b2.description"));
		resultado.setStrNumberStudents(resourceBundle.getString("b2.strNumberStudents"));
		resultado.setStrName(resourceBundle.getString("b2.strName"));
		resultado.setStrLicense(resourceBundle.getString("b2.strLicense"));
		resultado.setStrEmail(resourceBundle.getString("b2.strEmail"));

		return resultado;
	}
	
	@Override
	public Map<String, Long> getCourseSizes() {
		
		Map<String, Long> result = new HashMap<String, Long>();
		
		try {
			List<Course> courses = CourseDbLoader.Default.getInstance().loadAllByServiceLevel(ServiceLevel.FULL);
			
			for(Course course: courses) {
				
				Id courseId = course.getId();
				CourseSize csm = CourseSizeManager.getInstance().loadByCourseId(courseId);
				result.put(course.getBatchUid(), csm.getSizeTotal());
			}
		} catch (PersistenceException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return result;
		
	}

}
