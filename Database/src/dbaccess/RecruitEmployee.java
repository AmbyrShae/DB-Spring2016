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
public class RecruitEmployee {
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
	public RecruitEmployee(	String host,
						String port, 
						String sID, 
						String username, 
						String passwd) throws SQLException { 
		conn = new DBConnection(host, port, sID).getDBConnection(username, passwd); 
	}

	public RecruitEmployee(String username, String passwd) throws SQLException {
		this.conn = new DBConnection(host, port, sID).getDBConnection(username, passwd); 
 
	}

	public RecruitEmployee(Connection conn) throws SQLException {
		this.conn = conn; 
	}

	public ResultSet getAllPersons() throws SQLException {
		String str = "SELECT * FROM person";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(str);
		return rs;
	}
	
	public ResultSet getBestFitJobs(String name) throws SQLException {
		String str = "SELECT j.pos_code, j.title\n" +
				"FROM job_profile j\n" +
				"WHERE NOT EXISTS ( (\n" +
				"            SELECT R.ks_code \t  FROM skills R   WHERE R.pos_code=J.pos_code  )           "
				+ "MINUS         (\n" +
				" SELECT ks_code FROM  experience NATURAL JOIN person  WHERE name = '"+ name    +"')     )";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(str);
		return rs;
	}
	public ResultSet getMissingOne(String jobID) throws SQLException{
		String str = "WITH missing_one AS( "
				+ "SELECT per_id FROM experience NATURAL JOIN skills WHERE pos_code= '"+jobID+"' "
				+ "GROUP BY(per_id) "
				+ "HAVING  "
				+ "(SELECT COUNT(*) FROM skills WHERE pos_code='"+jobID+"') "
				+ "- COUNT(per_id) =1)  "
				+ "select ks_code, count(*) as num_person "
				+ "from knowledge_skill,missing_one M "
				+ " WHERE ks_code= "
				+ "(Select ks_code from(  "
				+ "(select ks_code from skills where pos_code='"+jobID+"') "
				+ " minus "
				+ "( select K.ks_code from experience K where K.per_id = M.per_id)) ) "
				+ "GROUP BY (ks_code) order by num_person asc";
		
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(str);
		return rs;
		
	}
	public ResultSet getMissingLeast(String jobID) throws SQLException{
		String str = "WITH number_needs(per_id,needs) AS (  "
				+ "(SELECT per_id,((SELECT COUNT(*) FROM skills WHERE pos_code='"+jobID+"')"
				+ "-count(per_id)) as needs FROM experience natural join skills WHERE pos_code = '"+jobID+"' "
				+ "GROUP BY (per_id) ) ) SELECT per_id,needs FROM  number_needs WHERE needs= "
				+ "(SELECT MIN(needs)  FROM number_needs)";

	
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(str);
		return rs;
		
	}
	public ResultSet getMissingSome(String jobID, String k) throws SQLException{
		String str = "WITH number_needs(per_id,needs) AS "
				+ "(  (SELECT per_id,((SELECT COUNT(*) "
				+ "FROM skills WHERE pos_code='"+jobID+"')-count(per_id)) as needs "
				+ "FROM experience NATURAL JOIN skills "
				+ "WHERE pos_code='"+jobID+"' GROUP BY (per_id) ) ) "
				+ "SELECT * FROM(SELECT per_id,needs "
				+ "FROM number_needs N UNION SELECT per_id, (SELECT count(*) "
				+ "FROM skills WHERE pos_code='"+jobID+"') FROM person P "
				+ "WHERE P.per_id not in (SELECT per_id FROM number_needs) ) "
				+ "WHERE needs<='"+k+"' ORDER BY needs ASC";
	
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(str);
		return rs;
		
	}
	public ResultSet getHowMany(String jobID) throws SQLException{
		String str = "WITH number_needs(per_id,needs) AS (  "
				+ "(SELECT per_id,((SELECT COUNT(*) "
				+ "FROM skills WHERE pos_code='"+jobID+"')-count(per_id)) as needs "
				+ "FROM experience NATURAL JOIN skills WHERE pos_code='"+jobID+"' "
				+ "GROUP BY (per_id) )) "
				+ "SELECT ks_code, count(*) as num_person FROM knowledge_skill, number_needs M "
				+ "WHERE ks_code in (SELECT ks_code FROM (  ("
				+ "SELECT ks_code FROM skills WHERE pos_code='"+jobID+"') "
				+ "minus ( SELECT K.ks_code FROM experience K WHERE K.per_id = M.per_id)) ) "
				+ "GROUP BY (ks_code) ORDER BY num_person DESC";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(str);
		return rs;
		
	}
	/**
	 * return the columns' titles of a table as a Vector
	 */
	public Vector getTitlesAsVector(ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		int col = rsmd.getColumnCount();
		Vector title = new Vector();
		for (int i = 0; i < col; i++) {
			title.add(rsmd.getColumnLabel(i+1));
		}
		return title;
	}
	
	/**
	 * convert a ResultSet object to a two dimensional array of String 
	 */
	public Vector resultSet2Vector(ResultSet rs) throws SQLException {
		ResultSetMetaData rsmd = rs.getMetaData();
		int col = rsmd.getColumnCount();
		Vector vec = new Vector(); 
		Vector row = null; 
		while (rs.next()) { 
			row = new Vector(); 
			for (int i = 0; i < col; i++) {
				row.add(rs.getObject(i+1)); 
			}
			vec.add(row);
		} 
		return vec;
	}
	
	
}
