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

package es.elearningmedia.studentsroster.repository;

import java.util.List;

import es.elearningmedia.studentsroster.model.Student;

public interface BbRepository {

	// Get Social Profile user photos from users table, (cld_avatar_url field),
	//along with name and email and put them in Student objects
	public List<Student> getPhotos(int[] userPk1);

}
