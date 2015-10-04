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

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import blackboard.db.BbDatabase;
import blackboard.db.ConnectionManager;
import blackboard.db.ConnectionNotAvailableException;
import es.elearningmedia.studentsroster.model.Student;

@Repository
public class BbRepositoryImpl implements BbRepository {

	@Override
	public List<Student> getPhotos(int[] userPk1) {

		List<Student> resultado = new ArrayList<Student>();

		ConnectionManager cManager = null;
		Connection conn = null;

		try {

			cManager = BbDatabase.getDefaultInstance().getConnectionManager();
			conn = cManager.getConnection();

			StringBuffer queryStringBuffer = new StringBuffer("");

			queryStringBuffer.append("SELECT cld_avatar_url, email, firstname, middlename, lastname  "
					+ "FROM users " + "WHERE pk1 IN (");

			for (int i = 0; i < userPk1.length; i++) {
				queryStringBuffer.append(userPk1[i] + ",");
			}

			String queryString = queryStringBuffer.toString();

			if (userPk1.length > 0) {
				queryString = queryString.substring(0, queryString.length() - 1);
			}

			queryString += ")";

			PreparedStatement selectQuery = conn.prepareStatement(queryString, ResultSet.TYPE_FORWARD_ONLY,
					ResultSet.CONCUR_READ_ONLY);

			ResultSet rSet = selectQuery.executeQuery();

			while (rSet.next()) {

				Student alumno = new Student();

				if (rSet.getString(1) == null || rSet.getString(1).isEmpty()) {
					alumno.setPhoto("");
				} else {
					alumno.setPhoto(rSet.getString(1));
				}

				alumno.setEmail(rSet.getString(2));
				alumno.setFirstname(rSet.getString(3));
				alumno.setMiddlename(rSet.getString(4));
				alumno.setLastname(rSet.getString(5));

				resultado.add(alumno);
			}

			rSet.close();
			selectQuery.close();

		} catch (ConnectionNotAvailableException | SQLException e) {

			e.printStackTrace();

		} finally {

			if (conn != null) {
				cManager.releaseConnection(conn);
			}

		}

		return resultado;

	}

}
