package view;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import controller.GownController;
import core.model.Model;
import core.view.*;
import model.GownModel;
public class ViewInterface extends View implements ActionListener {
	
	JPanel mainPan,buttonPan,viewPan,insertPan,formPan;

	JLabel idLbl,nameLbl,typeLbl,priceLbl;
	JTextField idFld,nameFld,typeFld,priceFld;
	JButton insertBtn,deleteBtn,updateBtn;
	
	Vector<Object> tableHeader,tableData;
	JTable table;
	DefaultTableModel dtm;
	
	public ViewInterface() {
		this.title = "tes";
		this.width = 500;
		this.height = 500;
		this.buildAll();
	}

	@Override
	public void initialize() {
		setResizable(false);
		setLayout(new BorderLayout());
		
		idLbl = new JLabel("Gown's Id");
		nameLbl = new JLabel("Gown's Name");
		typeLbl = new JLabel("Gown's Type");
		priceLbl = new JLabel("Gown's Price");
		
		idFld = new JTextField();
		idFld.setPreferredSize(new Dimension(150, 25));

		nameFld = new JTextField();
		nameFld.setPreferredSize(new Dimension(150, 25));
		
		typeFld = new JTextField();
		typeFld.setPreferredSize(new Dimension(150, 25));
		
		priceFld = new JTextField();
		priceFld.setPreferredSize(new Dimension(150, 25));
		
		insertBtn = new JButton("Insert");
		insertBtn.setPreferredSize(new Dimension(100, 25));
		
		
		deleteBtn = new JButton("Delete");
		deleteBtn.setPreferredSize(new Dimension(100, 25));
		
		
		updateBtn = new JButton("Update");
		updateBtn.setPreferredSize(new Dimension(100, 25));
		
		
		buttonPan = new JPanel(new FlowLayout());
		
		insertPan = new JPanel(new GridLayout(4,4));
		
		formPan = new JPanel(new BorderLayout());
		
		tableHeader = new Vector<>();
		tableHeader.add(new String("Gown's ID"));
		tableHeader.add(new String("Gown's Name"));
		tableHeader.add(new String("Gown's Type"));
		tableHeader.add(new String("Gown's Price"));
		
		dtm = new DefaultTableModel(tableHeader,0);
		
		table = new JTable(){
			@Override
			public boolean isCellEditable(int arg0, int arg1) {
				return false;
			}
		};
		
		
	}
	
	public Vector<Object> addRow(GownModel g) {
		Vector<Object> data = new Vector<>();
		data.add(g.getGownId());
		data.add(g.getGownName());
		data.add(g.getGownType());
		data.add(g.getGownPrice());
		
		return data;
		
	}
	
	public void getAll() {
		if(dtm.getRowCount() > 0) {
			for(int i = dtm.getRowCount() -1; i > -1;i--) {
				dtm.removeRow(i);
			}
		}
		List<Model> lists = GownController.GetInstance().getAllData();
		for(Model g : lists) {
			dtm.addRow(addRow((GownModel) g));
		}
		table.setModel(dtm);
	}

	@Override
	public void addComponent() {
		buttonPan.add(insertBtn);
		buttonPan.add(deleteBtn);
		buttonPan.add(updateBtn);
		insertPan.add(idLbl);
		insertPan.add(idFld);
		insertPan.add(nameLbl);
		insertPan.add(nameFld);
		insertPan.add(typeLbl);
		insertPan.add(typeFld);
		insertPan.add(priceLbl);
		insertPan.add(priceFld);
		formPan.add(insertPan,BorderLayout.NORTH);
		formPan.add(buttonPan,BorderLayout.CENTER);
		
		/*
		 * To get all values of the data
		 */
		getAll();
		
		JScrollPane pane = new JScrollPane(table);
		pane.setPreferredSize(new Dimension(500,300));
		
		viewPan = new JPanel(new BorderLayout());
		viewPan.add(pane,BorderLayout.NORTH);
		
		mainPan = new JPanel(new FlowLayout());
		
		JPanel blankPan = new JPanel();
		blankPan.setPreferredSize(new Dimension(50, 100));
		
		mainPan.add(viewPan);
		mainPan.add(formPan);
		mainPan.add(blankPan);
		add(mainPan);
		
	}

	@Override
	public void addListenerForm() {
		insertBtn.addActionListener(this);
		deleteBtn.addActionListener(this);
		updateBtn.addActionListener(this);
		
		/*
		 * Adding the mouse listener to add the action that when we clicked on one of the data/ row in the table
		 * we'll get the values and show it on each of the textfield
		 */
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// TODO Auto-generated method stub
				int row = table.getSelectedRow();
				String id = (String)table.getValueAt(row, 0);
				String name = (String)table.getValueAt(row, 1);
				String type = (String)table.getValueAt(row, 2);
				Integer price = (Integer)table.getValueAt(row, 3);
				
				idFld.setText(id);
				nameFld.setText(name);
				typeFld.setText(type);
				priceFld.setText(price + "");
			}
		});
	}
	
	public void insert(){
		String id = idFld.getText();
		String name = nameFld.getText();
		String type = typeFld.getText();
		int price = Integer.parseInt(priceFld.getText());
		
		GownController.GetInstance().insert(id, name, type, price);
		getAll();
	}
	
	public void update() {
		String id = idFld.getText();
		String name = nameFld.getText();
		String type = typeFld.getText();
		int price = Integer.parseInt(priceFld.getText());
		
		GownController.GetInstance().update(id, name, type, price);
		getAll();
	}
	
	public void delete() {
		String id = idFld.getText();
		GownController.GetInstance().delete(id);
		getAll();
	}
	

	@Override
	public void actionPerformed(ActionEvent arg0) {
		if(arg0.getSource() == insertBtn) {
			insert();
		}
		else if(arg0.getSource() == updateBtn) {
			update();
		}
		else if(arg0.getSource() == deleteBtn) {
			delete();
		}
		
	}		
	}

