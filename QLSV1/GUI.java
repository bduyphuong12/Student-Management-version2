package QLSV1;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import com.toedter.calendar.JDateChooser;

import QLSV.ConnJDBC;
import QLSV.Student;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtMSSV;
	private JTextField txtName;
	private JTextField txtID_Lop;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 617, 428);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Student Managerment");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblNewLabel.setBounds(154, 11, 302, 40);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("MSSV");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1.setBounds(21, 57, 61, 29);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("NS");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_1.setBounds(21, 108, 61, 29);
		contentPane.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Name");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_2.setBounds(217, 62, 61, 29);
		contentPane.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("ID_Lop");
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_3.setBounds(217, 108, 61, 29);
		contentPane.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Gender");
		lblNewLabel_1_4.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblNewLabel_1_4.setBounds(398, 57, 61, 29);
		contentPane.add(lblNewLabel_1_4);
		
		txtMSSV = new JTextField();
		txtMSSV.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtMSSV.setBounds(87, 62, 86, 24);
		contentPane.add(txtMSSV);
		txtMSSV.setColumns(10);
		
		txtName = new JTextField();
		txtName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtName.setColumns(10);
		txtName.setBounds(269, 62, 86, 24);
		contentPane.add(txtName);
		
		txtID_Lop = new JTextField();
		txtID_Lop.setFont(new Font("Tahoma", Font.PLAIN, 13));
		txtID_Lop.setColumns(10);
		txtID_Lop.setBounds(269, 108, 86, 29);
		contentPane.add(txtID_Lop);
		
		JComboBox cbGender = new JComboBox();
		cbGender.setBounds(469, 57, 86, 26);
		cbGender.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female"}));
		contentPane.add(cbGender);
		
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(87, 108, 86, 29);
		contentPane.add(dateChooser);
		
		JButton btSave = new JButton("Save");
		btSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SV st=new SV();
				st.setMSSV(txtMSSV.getText());
				st.setNameSV(txtName.getText());
				st.setGender(cbGender.getSelectedIndex());
				SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
				String getdate = dateformat.format(dateChooser.getDate());
				st.setNS(Date.valueOf(getdate));
				st.setID_Lop(Integer.parseInt(txtID_Lop.getText()));
				DbConnection.insert(st);
				JOptionPane.showMessageDialog(null, "Save Success! ");
				showData(DbConnection.findAll());
			}
		});
		btSave.setFont(new Font("Tahoma", Font.BOLD, 13));
		btSave.setBounds(31, 349, 89, 29);
		contentPane.add(btSave);
		
		JButton btShow = new JButton("Show");
		btShow.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				showData(DbConnection.findAll());
			}
		});
		btShow.setFont(new Font("Tahoma", Font.BOLD, 13));
		btShow.setBounds(466, 349, 89, 29);
		contentPane.add(btShow);
		
		JButton btFind = new JButton("Find");
		btFind.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SV st =new SV();
				st.setMSSV(txtMSSV.getText());
				showData(DbConnection.findByMSSV(st));
			}
		});
		btFind.setFont(new Font("Tahoma", Font.BOLD, 13));
		btFind.setBounds(140, 349, 89, 29);
		contentPane.add(btFind);
		
		JButton btDelete = new JButton("Delete");
		btDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SV st=new SV();
				st.setMSSV(txtMSSV.getText());
				DbConnection.delete(st);
				showData(DbConnection.findAll());
			}
		});
		btDelete.setFont(new Font("Tahoma", Font.BOLD, 13));
		btDelete.setBounds(239, 349, 89, 29);
		contentPane.add(btDelete);
		
		JButton btUpdate = new JButton("Update");
		btUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SV st=new SV();
				st.setMSSV(txtMSSV.getText());
				st.setNameSV(txtName.getText());
				st.setGender(cbGender.getSelectedIndex());
				SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
				String getdate = dateformat.format(dateChooser.getDate());
				st.setNS(Date.valueOf(getdate));
				st.setID_Lop(Integer.parseInt(txtID_Lop.getText()));
				DbConnection.insert(st);
				JOptionPane.showMessageDialog(null, "Save Success! ");
				showData(DbConnection.findAll());
			}
		});
		btUpdate.setFont(new Font("Tahoma", Font.BOLD, 13));
		btUpdate.setBounds(338, 349, 89, 29);
		contentPane.add(btUpdate);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(31, 148, 529, 169);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
				{null, null, null, null, null},
			},
			new String[] {
				"MSSV", "NameSV", "Gender", "NS", "NameLop"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(44);
		table.getColumnModel().getColumn(1).setPreferredWidth(102);
		table.getColumnModel().getColumn(2).setPreferredWidth(52);
		table.getColumnModel().getColumn(3).setPreferredWidth(96);
		table.getColumnModel().getColumn(4).setPreferredWidth(109);
		scrollPane.setViewportView(table);
		final DefaultComboBoxModel option = new DefaultComboBoxModel();
		option.addElement("NameSV");
		option.addElement("NS");
		option.addElement("TenLop");
		JComboBox cbsort = new JComboBox(option);
		cbsort.setBounds(491, 108, 86, 26);
		contentPane.add(cbsort);
		
		JButton btSort = new JButton("Sort");
		btSort.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(cbsort.getSelectedIndex()==0) {
					table.setAutoCreateRowSorter(true);
				}
				if(cbsort.getSelectedIndex()==1) {
					table.setAutoCreateRowSorter(true);
				}
				if(cbsort.getSelectedIndex()==2) {
					table.setAutoCreateRowSorter(true);
				}
			}
		});
		btSort.setFont(new Font("Tahoma", Font.BOLD, 13));
		btSort.setBounds(382, 108, 89, 29);
		contentPane.add(btSort);
		
		
		showData(DbConnection.findAll());
	}
	public void showData(List<SV>studentl) {
		List<SV>listStudent=new ArrayList<>();
		listStudent=studentl;
		DefaultTableModel tableModel;
	    table.getModel();
	    tableModel=(DefaultTableModel)table.getModel();
	    tableModel.setRowCount(0);	    
	    listStudent.forEach((student) -> { 
	    	String gender;
	    	String NameLop;
	    	if(student.getID_Lop()==1)NameLop="19TCLCNhat1"; else {NameLop="19TCLCNhat2";}
	    	if(student.getGender()==0)gender="Male";
	    	else {gender="Female";}
	    	tableModel.addRow(new Object [] {
	    		student.getMSSV(),student.getNameSV(), gender,
	    		student.getNS(),NameLop
	    	});
	    });
	    }
}
