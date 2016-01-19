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

package es.elearningmedia.toolsmanagement.model;

/**
 * Represents the relevant information of each student for the roster
 * 
 * @author albertoruiz
 *
 */
public class Tool {

	private String tool_type;
	private String tool_handler;

	public String getTool_type() {
		return tool_type;
	}

	public void setTool_type(String tool_type) {
		this.tool_type = tool_type;
	}

	public String getTool_handler() {
		return tool_handler;
	}

	public void setTool_handler(String tool_handler) {
		this.tool_handler = tool_handler;
	}

}
