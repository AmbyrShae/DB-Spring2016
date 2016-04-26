package dbgui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dbaccess.ChangeCourseStatus;
import dbaccess.Queries;
import JavaClasses.Course;

import java.sql.ResultSet; 
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class ChangeCourseStatusView extends javax.swing.JFrame {
	private JTextField c_codeField;
	private JTextField statusField;


	private int newC_code;
	private String newStatus;
	    
	private JButton changeStatus;

	private JTable table;
	private JTextArea msgArea;
	private JScrollPane msgPane;
	private JScrollPane jScrollPane1;
	private ChangeCourseStatus ae;


	/**
	* constructor takes a reference of a db accesser object 
	*/
	public ChangeCourseStatusView(ChangeCourseStatus ae) {
		super();
		this.ae = ae;
		this.setTitle("Change Status of a Course");
		initGUI();
	}
	
	/**
	* drawing the GUI
	*/
	private void initGUI() {
		try {
			{
				c_codeField = new JTextField("Enter course code");
				getContentPane().add(c_codeField);
				c_codeField.setBounds(14, 5, 205, 28);
				c_codeField.addMouseListener(new MouseAdapter(){
		            @Override
		            public void mouseClicked(MouseEvent e){
		            	c_codeField.setText("");
		            }
		        });
			}
			{
				statusField = new JTextField("Status");
				getContentPane().add(statusField);
				statusField.setBounds(14, 35, 205, 28);
				statusField.addMouseListener(new MouseAdapter(){
		            @Override
		            public void mouseClicked(MouseEvent e){
		            	statusField.setText("");
		            }
		        });
			}
			{
				changeStatus = new JButton();
				getContentPane().add(changeStatus);
				changeStatus.setText("Change Status");
				changeStatus.setBounds(14, 95, 205, 28);
				changeStatus.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						changeStatusButActionPerformed(evt);
					}
				});
			}
			{
				msgPane = new JScrollPane();
				getContentPane().add(msgPane);
				msgPane.setBounds(245, 0, 623, 91);
				{
					msgArea = new JTextArea();
					msgPane.setViewportView(msgArea);
					msgArea.setText("messages from the database system ");
				}
			}
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			pack();
			this.setSize(883, 485);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	* User's action
	*/
	private void changeStatusButActionPerformed(ActionEvent evt) {
//		course = new Course(Integer.parseInt(c_codeField.getText()), titleField.getText(),
//				 descriptionField.getText(),statusField.getText(), levelField.getText(), (Double.parseDouble(retail_priceField.getText())));
		newC_code =  Integer.parseInt(c_codeField.getText());
		newStatus = statusField.getText();
		
		//create sql statement then request result set from db
		try {
			ae.changeStatus(newC_code, newStatus);
		
			msgArea.append("\nEmployee inserted successfully");
		} catch (SQLException sqle) {
			msgArea.append("\n" + sqle.toString());
		}
	}
	
	/**
	* activater
	*/
	public static void main(String[] args) throws Exception { 
		if (args.length < 2) {
			System.out.println("usage: java TableInfo db-username db-password"); 
			System.exit(1);
		}
		Queries q = new Queries(args[0], args[1]);
//		TableInfo ti = new TableInfo("scott", "tiger");
		QueryView inst = new QueryView(q);
		inst.setVisible(true);
	}
	
}
