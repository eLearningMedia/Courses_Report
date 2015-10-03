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

package es.elearningmedia.listado.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import es.elearningmedia.listado.model.Alumno;
import es.elearningmedia.listado.model.StringsBundle;
import es.elearningmedia.listado.service.BbService;

/**
 * Entry point for the application
 * @author albertoruiz
 *
 */
@Controller
public class ListadoController {
	
	@Autowired
	BbService bbService;
	
	@RequestMapping(value = "/listado", method = RequestMethod.GET)
	public String index(@RequestParam("course_id") String courseId,
			Model model) {
		
		//Get internationalization strings
		StringsBundle strBundle = bbService.getBundle();
		
		//Get the context course title
		String sessionCourseCourseTitle = bbService.getCourseTitle();
		
		//Get a sorted list of students with photos
		List<Alumno> alumnos = bbService.getEnroledStudents(courseId);
		
		model.addAttribute("course_id", courseId);
		model.addAttribute("alumnos", alumnos);
		
		model.addAttribute("course", strBundle.getStrCourse() + sessionCourseCourseTitle);
		model.addAttribute("numeroalumnos", strBundle.getStrNumberStudents() + alumnos.size());
		model.addAttribute("titulo", strBundle.getStrTitle());
		model.addAttribute("descripcion", strBundle.getStrDescription());
		model.addAttribute("licencia", strBundle.getStrLicense());
		model.addAttribute("nombre", strBundle.getStrName());
		model.addAttribute("email", strBundle.getStrEmail());
		
		return "listado";
		
	}
	
}
