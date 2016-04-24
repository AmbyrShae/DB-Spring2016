package dbgui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import dbaccess.RecruitEmployee;
import dbaccess.Queries;

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
public class RecruitEmployeeView extends javax.swing.JFrame {
	private JTextField nameField;
	private JTextField jobIDField;
	private JTextField kField;


	private JButton allPersons;
	private JButton bestFitJobs;
	private JButton missingOneBut;
	private JButton missingSomeBut;
	private JButton missingLeastBut;

	private JButton howManyBut;

	private JTable table;
	private JTextArea msgArea;
	private JScrollPane msgPane;
	private JScrollPane jScrollPane1;
	private RecruitEmployee re;

	/**
	* constructor takes a reference of a db accesser object 
	*/
	public RecruitEmployeeView(RecruitEmployee re) {
		super();
		this.re = re;
		this.setTitle("Recruit Employees");
		initGUI();
	}
	
	/**
	* drawing the GUI
	*/
	private void initGUI() {
		try {
			
			{
				allPersons = new JButton();
				getContentPane().add(allPersons);
				allPersons.setText("All Potential applicants");
				allPersons.setBounds(14, 5, 175, 28);
				allPersons.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						allPersonsButActionPerformed(evt);
					}
				});
			}
			{
				nameField = new JTextField("Enter person of interest");
				getContentPane().add(nameField);
				nameField.setBounds(205, 5, 175, 28);
				nameField.addMouseListener(new MouseAdapter(){
		            @Override
		            public void mouseClicked(MouseEvent e){
		            	nameField.setText("");
		            }
		        });
			}
			{
				bestFitJobs = new JButton();
				getContentPane().add(bestFitJobs);
				bestFitJobs.setText("Potential Jobs");
				bestFitJobs.setBounds(205, 40, 175, 28);
				bestFitJobs.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						bestFitJobsButActionPerformed(evt);
					}
				});
			}
			{
				jobIDField = new JTextField("Enter job ID for search");
				getContentPane().add(jobIDField);
				jobIDField.setBounds(405, 5, 240, 28);
				jobIDField.addMouseListener(new MouseAdapter(){
		            @Override
		            public void mouseClicked(MouseEvent e){
		            	jobIDField.setText("");
		            }
		        });
			}
			{
				missingOneBut = new JButton();
				getContentPane().add(missingOneBut);
				missingOneBut.setText("List Those Missing One Skill");
				missingOneBut.setBounds(405, 40, 240, 28);
				missingOneBut.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						missingOneButActionPerformed(evt);
					}
				});
			}
			{
				kField = new JTextField("Enter max # missing skills");
				getContentPane().add(kField);
				kField.setBounds(650, 75, 220, 28);
				kField.addMouseListener(new MouseAdapter(){
		            @Override
		            public void mouseClicked(MouseEvent e){
		                kField.setText("");
		            }
		        });
			}
			{
				missingSomeBut = new JButton();
				getContentPane().add(missingSomeBut);
				missingSomeBut.setText("List Those Missing 'X' of skills");
				missingSomeBut.setBounds(405, 75, 240, 28);
				missingSomeBut.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						missingSomeButActionPerformed(evt);
					}
				});
			}
			{
				howManyBut = new JButton();
				getContentPane().add(howManyBut);
				howManyBut.setText("List missing skills and amount of ppl");
				howManyBut.setBounds(405, 110, 240, 28);
				howManyBut.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						howManyButActionPerformed(evt);
					}
				});
			}
			{
				missingLeastBut = new JButton();
				getContentPane().add(missingLeastBut);
				missingLeastBut.setText("List Those Missing The Least Skills");
				missingLeastBut.setBounds(405, 135, 240, 28);
				missingLeastBut.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent evt) {
						missingLeastButActionPerformed(evt);
					}
				});
			}
			
		
			{
				TableModel tableModel = new DefaultTableModel( 
											new String[][] {{" ", " "}}, 
											new String[] {"Column 1", "Column 2" });
				table = new JTable();
				table.setModel(tableModel);
				table.setBounds(21, 255, 820, 357);
			}
			{
				jScrollPane1 = new JScrollPane(table);
				getContentPane().add(jScrollPane1);
				jScrollPane1.setBounds(7, 255, 861, 300);
			}
			{
				msgPane = new JScrollPane();
				getContentPane().add(msgPane);
				msgPane.setBounds(21, 165, 820, 91);
				{
					msgArea = new JTextArea();
					msgPane.setViewportView(msgArea);
					msgArea.setText("messages from the database system ");
				}
			}
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			getContentPane().setLayout(null);
			pack();
			this.setSize(this.getMaximumSize());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	* User's action
	*/
	private void allPersonsButActionPerformed(ActionEvent evt) {

		//create sql statement then request result set from db
		try {
			ResultSet rs = re.getAllPersons();
			Vector res = re.resultSet2Vector(rs);
			TableModel tableModel = new DefaultTableModel(res, re.getTitlesAsVector(rs));
			table.setModel(tableModel);
			msgArea.append("\nNumber of records in result is " + res.size());
		} catch (SQLException sqle) {
			msgArea.append("\n" + sqle.toString());
		}
	}

	private void bestFitJobsButActionPerformed(ActionEvent evt) {
		try {
			ResultSet rs = re.getBestFitJobs(nameField.getText());
			Vector res = re.resultSet2Vector(rs);
			TableModel tableModel = new DefaultTableModel(res, re.getTitlesAsVector(rs));
			table.setModel(tableModel);
			msgArea.append("\nNumber of records in result is " + res.size());
		} catch (SQLException sqle) {
			msgArea.append("\n" + sqle.toString());
		}
	}
	
	private void missingOneButActionPerformed(ActionEvent evt) {

		//create sql statement then request result set from db
		try {
			ResultSet rs = re.getMissingOne(jobIDField.getText());
			Vector res = re.resultSet2Vector(rs);
			TableModel tableModel = new DefaultTableModel(res, re.getTitlesAsVector(rs));
			table.setModel(tableModel);
			msgArea.append("\nNumber of records in result is " + res.size());
		} catch (SQLException sqle) {
			msgArea.append("\n" + sqle.toString());
		}
	}
	private void missingSomeButActionPerformed(ActionEvent evt) {

		//create sql statement then request result set from db
		try {
			ResultSet rs = re.getMissingSome(jobIDField.getText(), kField.getText());
			Vector res = re.resultSet2Vector(rs);
			TableModel tableModel = new DefaultTableModel(res, re.getTitlesAsVector(rs));
			table.setModel(tableModel);
			msgArea.append("\nNumber of records in result is " + res.size());
		} catch (SQLException sqle) {
			msgArea.append("\n" + sqle.toString());
		}
	}
	private void missingLeastButActionPerformed(ActionEvent evt) {

		//create sql statement then request result set from db
		try {
			ResultSet rs = re.getMissingLeast(jobIDField.getText());
			Vector res = re.resultSet2Vector(rs);
			TableModel tableModel = new DefaultTableModel(res, re.getTitlesAsVector(rs));
			table.setModel(tableModel);
			msgArea.append("\nNumber of records in result is " + res.size());
		} catch (SQLException sqle) {
			msgArea.append("\n" + sqle.toString());
		}
	}
	private void howManyButActionPerformed(ActionEvent evt) {

		//create sql statement then request result set from db
		try {
			ResultSet rs = re.getHowMany(jobIDField.getText());
			Vector res = re.resultSet2Vector(rs);
			TableModel tableModel = new DefaultTableModel(res, re.getTitlesAsVector(rs));
			table.setModel(tableModel);
			msgArea.append("\nNumber of records in result is " + res.size());
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
