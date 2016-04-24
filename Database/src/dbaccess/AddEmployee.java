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
import JavaClasses.Person;
// import java.sql.PreparedStatement;
// import java.text.DateFormat;

/**
 * @author Shengru Tu
 * 
 * TODO To change the template for this generated type comment go to Window -
 * Preferences - Java - Code Style - Code Templates
 */
public class AddEmployee {
	private Person person;
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
	public AddEmployee(	String host,
						String port, 
						String sID, 
						String username, 
						String passwd) throws SQLException { 
		conn = new DBConnection(host, port, sID).getDBConnection(username, passwd); 
	}

	public AddEmployee(String username, String passwd) throws SQLException {
		this.conn = new DBConnection(host, port, sID).getDBConnection(username, passwd); 
 
	}

	public AddEmployee(Connection conn) throws SQLException {
		this.conn = conn; 
	}

	public void insertEmployee(Person person) throws SQLException{
		this.person = person;
		String str = person.addPerson();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(str);
	}
	
	public void addSkill(String id, String ks_code)throws SQLException{
		String str = "INSERT INTO experience VALUES("+ id + "," + ks_code + ")";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(str);
	}
	
	
	
}