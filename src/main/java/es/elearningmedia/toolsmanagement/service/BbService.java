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

import java.util.List;
import java.util.Map;

import blackboard.data.navigation.ToolSettings;
import blackboard.data.navigation.ToolSettings.Type;
import blackboard.persist.Id;
import blackboard.platform.institutionalhierarchy.service.Node;
import es.elearningmedia.toolsmanagement.model.StringsBundle;

public interface BbService {

	// Get internationalization strings
	public StringsBundle getBundle();

	Type[] getToolTypes();

	List<ToolSettings> getToolsSettings(Type type);

	Node getNodeById(Id nodeId);

	Map<String, Long> getCourseSizes();

}
