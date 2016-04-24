package dbgui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dbaccess.AddEmployee;
import dbaccess.Queries;
import JavaClasses.Person;

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
public class AddEmployeeView extends javax.swing.JFrame {
	private JTextField nameField;
	private JTextField idField;
	private JTextField id2Field;

	private JTextField street_name_field;
	private JTextField cityField;
	private JTextField stateField;
	private JTextField zip_codeField;
	private JTextField genderField;
	private JTextField emailField;
	private JTextField skillField;

	private Person person;
	    
	private JButton submitEmployee;
	private JButton addSkill;

	private JTable table;
	private JTextArea msgArea;
	private JScrollPane msgPane;
	private JScrollPane jScrollPane1;
	private AddEmployee ae;


	/**
	* constructor takes a reference of a db accesser object 
	*/
	public AddEmployeeView(AddEmployee ae) {
		super();
		this.ae = ae;
		this.setTitle("Add Applicant");
		initGUI();
	}
	
	/**
	* drawing the GUI
	*/
	private void initGUI() {
		try {
			{
				nameField = new JTextField("Enter name of applicant");
				getContentPane().add(nameField);
				nameField.setBounds(14, 5, 205, 28);
				nameField.addMouseListener(new MouseAdapter(){
		            @Override
		            public void mouseClicked(MouseEvent e){
		            	nameField.setText("");
		            }
		        });
			}
			{
				idField = new JTextField("ID");
				getContentPane().add(idField);
				idField.setBounds(14, 35, 205, 28);
				idField.addMouseListener(new MouseAdapter(){
		            @Override
		            public void mouseClicked(MouseEvent e){
		            	idField.setText("");
		            }
		        });
			}
//			{
//				street_numberField = new JTextField("Street Number");
//				getContentPane().add(street_numberField);
//				street_numberField.setBounds(14, 65, 205, 28);
//				street_numberField.addMouseListener(new MouseAdapter(){
//		            @Override
//		            public void mouseClicked(MouseEvent e){
//		            	street_numberField.setText("");
//		            }
//		        });
//				
//			}
			{
				street_name_field = new JTextField("Street");
				getContentPane().add(street_name_field);
				street_name_field.setBounds(14, 95, 205, 28);
				street_name_field.addMouseListener(new MouseAdapter(){
		            @Override
		            public void mouseClicked(MouseEvent e){
		            	street_name_field.setText("");
		            }
		        });
			}
			{
				cityField = new JTextField("City");
				getContentPane().add(cityField);
				cityField.setBounds(14, 125, 205, 28);
				cityField.addMouseListener(new MouseAdapter(){
		            @Override
		            public void mouseClicked(MouseEvent e){
		            	cityField.setText("");
		            }
		        });
			}
			{
				stateField = new JTextField("State");
				getContentPane().add(stateField);
				stateField.setBounds(14, 155, 205, 28);
				stateField.addMouseListener(new MouseAdapter(){
		            @Override
		            public void mouseClicked(MouseEvent e){
		            	stateField.setText("");
		            }
		        });
			}
			{
				zip_codeField = new JTextField("Zip Code");
				getContentPane().add(zip_codeField);
				zip_codeField.setBounds(14, 185, 205, 28);
				zip_codeField.addMouseListener(new MouseAdapter(){
		            @Override
		            public void mouseClicked(MouseEvent e){
		            	zip_codeField.setText("");
		            }
		        });
			}
			{
				genderField = new JTextField("Gender");
				getContentPane().add(genderField);
				genderField.setBounds(14, 215, 205, 28);
				genderField.addMouseListener(new MouseAdapter(){
		            @Override
		            public void mouseClicked(MouseEvent e){
		            	genderField.setText("");
		            }
		        });
			}
			{
				emailField = new JTextField("Email");
				getContentPane().add(emailField);
				emailField.setBounds(14, 245, 205, 28);
				emailField.addMouseListener(new MouseAdapter(){
		            @Override
		            public void mouseClicked(MouseEvent e){
		            	emailField.setText("");
		            }
		        });
			}
			
			
			{
				submitEmployee = new JButton();
				getContentPane().add(submitEmployee);
				submitEmployee.setText("Submit Applicant");
				submitEmployee.setBounds(14, 275, 175, 28);
				submitEmployee.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						submitEmployeeButActionPerformed(evt);
					}
				});
			}
			{
				id2Field = new JTextField("ID");
				getContentPane().add(id2Field);
				id2Field.setBounds(225, 125, 205, 28);
				id2Field.addMouseListener(new MouseAdapter(){
		            @Override
		            public void mouseClicked(MouseEvent e){
		            	id2Field.setText("");
		            }
		        });
				
			}
			{
				skillField = new JTextField("Enter Skill ID");
				getContentPane().add(skillField);
				skillField.setBounds(225, 155, 205, 28);
				skillField.addMouseListener(new MouseAdapter(){
		            @Override
		            public void mouseClicked(MouseEvent e){
		            	skillField.setText("");
		            }
		        });
			}
			{
				addSkill = new JButton();
				getContentPane().add(addSkill);
				addSkill.setText("Add Experience");
				addSkill.setBounds(225, 185, 175, 28);
				addSkill.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						addSkillButActionPerformed(evt);
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
	private void submitEmployeeButActionPerformed(ActionEvent evt) {
		person = new Person(Integer.parseInt(idField.getText()), nameField.getText(),
				 stateField.getText(),cityField.getText(), street_name_field.getText(), (Integer.parseInt(zip_codeField.getText())),
				 emailField.getText(), genderField.getText());
		
		
		//create sql statement then request result set from db
		try {
			ae.insertEmployee(person);
		
			msgArea.append("\nEmployee inserted successfully");
		} catch (SQLException sqle) {
			msgArea.append("\n" + sqle.toString());
		}
	}
	private void addSkillButActionPerformed(ActionEvent evt) {

		//create sql statement then request result set from db
		try {
			ae.addSkill(id2Field.getText(), skillField.getText());
		
			msgArea.append("\nSkill added successfully");
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
