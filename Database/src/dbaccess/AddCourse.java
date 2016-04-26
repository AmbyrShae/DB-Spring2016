/*
 * Created on Jul 4, 2006
 */
package dbaccess;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;
import JavaClasses.Course;
// import java.sql.PreparedStatement;
// import java.text.DateFormat;

/**
 * @author Shengru Tu
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class AddCourse {
	private Course course;
	private Connection conn;
	final String host = "localhost"; 
	final String port = "1521";
	final String sID = "nbdb";

	/**
	 * @param username
	 *            the user name to access the database.
	 * @param passwd
	 *            This constructor establishes connection and a java.sql.Types
	 *            checker object.
	 */
	public AddCourse(	String host,
						String port, 
						String sID, 
						String username, 
						String passwd) throws SQLException { 
		conn = new DBConnection(host, port, sID).getDBConnection(username, passwd); 
	}

	public AddCourse(String username, String passwd) throws SQLException {
		this.conn = new DBConnection(host, port, sID).getDBConnection(username, passwd); 
 
	}

	public AddCourse(Connection conn) throws SQLException {
		this.conn = conn; 
	}

	public void insertCourse(Course course) throws SQLException{
		this.course = course;
		String str = course.addCourse();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(str);
	}
	
}