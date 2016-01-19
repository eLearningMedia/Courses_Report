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

package es.elearningmedia.toolsmanagement.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.google.gson.Gson;

import blackboard.data.course.Course;
import blackboard.data.navigation.ToolSettings;
import blackboard.data.navigation.ToolSettings.Type;
import es.elearningmedia.toolsmanagement.model.StringsBundle;
import es.elearningmedia.toolsmanagement.model.Tool;
import es.elearningmedia.toolsmanagement.service.BbService;

/**
 * Entry point for the application
 * 
 * @author albertoruiz
 *
 */
@Controller
public class ToolsManagementController {

	@Autowired
	BbService bbService;

	@RequestMapping(value = "/tools-management", method = RequestMethod.GET)
	public String index(Model model) {

		// Get internationalization strings
		StringsBundle strBundle = bbService.getBundle();

		Type[] toolTypes = bbService.getToolTypes();
		
		List<Tool> toolRows = new ArrayList<Tool>();
		
		for(Type toolType : toolTypes) {
			
			String toolTypeName = toolType.getDisplayType();
			
			List<ToolSettings> toolSettings = bbService.getToolsSettings(toolType);
			
			for(ToolSettings toolSetting: toolSettings) {
				
				Tool tool = new Tool();
				
				tool.setTool_type(toolTypeName);
				tool.setTool_handler(toolSetting.getApplicationLabel());
				
				
				
				toolRows.add(tool);
				
			}
			
		}

		model.addAttribute("tool_rows", toolRows);
		model.addAttribute("str_bundle", strBundle);

		return "tool-list";

	}
	
	@RequestMapping(value = "/getSizes", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseEntity<byte[]> getSizes(Model model) {
		
		Gson gson = new Gson();
		
		Map<String, Long> sizes = bbService.getCourseSizes();
		
		byte[] contents = gson.toJson(sizes).getBytes();

		ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(contents, HttpStatus.OK);

		return response;
	}

}
