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

package es.elearningmedia.listado.repository;

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
import es.elearningmedia.listado.model.Alumno;

@Repository
public class BbRepositoryImpl implements BbRepository {

	@Override
	public List<Alumno> getFotos(int[] userPk1) {
		
		List<Alumno> resultado = new ArrayList<Alumno>();
		
		ConnectionManager cManager = null;
        Connection conn = null;
        
        try {
        	
        	cManager = BbDatabase.getDefaultInstance().getConnectionManager();
        	conn = cManager.getConnection();
        	
        	StringBuffer queryStringBuffer = new StringBuffer("");
            
            queryStringBuffer.append("SELECT cld_avatar_url as foto, email, firstname, middlename, lastname  "
				+ "FROM users "
				+ "WHERE pk1 IN (");
            
            for(int i = 0; i < userPk1.length; i++) {
            	queryStringBuffer.append(userPk1[i] + ",");
            }
            
            String queryString = queryStringBuffer.toString();
            
            if(userPk1.length > 0) {
            	queryString = queryString.substring(0, queryString.length()-1);
            }
            
            queryString += ")";
            
            PreparedStatement selectQuery = conn.prepareStatement(queryString, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            
            ResultSet rSet = selectQuery.executeQuery();
            
            while(rSet.next()){
            	
            	Alumno alumno = new Alumno();
            	
            	if(rSet.getString(1) == null || rSet.getString(1).isEmpty()) {
            		alumno.setFoto("");
            	} else {
            		alumno.setFoto(rSet.getString(1));
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
			
	        if(conn != null){
	            cManager.releaseConnection(conn);
	        }
	        
	    }
		
		return resultado;
		
	}
	
	

}
