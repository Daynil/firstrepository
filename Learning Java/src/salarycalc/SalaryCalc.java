package salarycalc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.text.NumberFormat;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SalaryCalc extends JFrame {
	
	private static JMenuBar menuBar;
	
	private JFormattedTextField rateField;
	private JFormattedTextField hoursField;
	private JFormattedTextField salaryField;
	
	private JLabel rateLabel;
	private JLabel hoursLabel;
	private JLabel salaryLabel;
	
	private double rate = 0.00;
	private double hours = 0;
	private double salary = 0;

	public static final double WKS_PER_YR = 52.0;
	
	public SalaryCalc(String name) {
		super(name);
		setResizable(false);
	}
	
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				createAndShowGUI();
			}
		});
	}
	
	public static void createAndShowGUI() {
		//Create and setup the window
		SalaryCalc frame = new SalaryCalc("Danny's Salary Calculator");
		frame.setSize(305, 152);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Set up the content pane.
		frame.addComponentsToPane(frame.getContentPane());
		frame.setJMenuBar(menuBar);
		//Display the window
		frame.setVisible(true);
	}
	
	public void addComponentsToPane(final Container pane) {
		// Create main panel
		JPanel mainPanel = new JPanel();
		pane.add(mainPanel);
		mainPanel.setBackground(new Color(220, 220, 220));
		
		// Create menu
		menuBar = new JMenuBar();
		JMenu menu = new JMenu("Help");
		menu.setMnemonic(KeyEvent.VK_H);
		menu.getAccessibleContext().setAccessibleDescription("Help");
		menuBar.add(menu);
		JMenuItem menuItem = new JMenuItem("Instructions", KeyEvent.VK_I);
		//menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.ALT_MASK));
		menuItem.addActionListener(new HelpMenuListener());
		//menuItem.getAccessibleContext().setAccessibleDescription("This doesn't really do anything");
		menu.add(menuItem);

		//String directions = "Enter 2 values and leave the third blank to calculate.";
		//JLabel dir = new JLabel(directions);
		//mainPanel.add(dir, BorderLayout.NORTH);
		
		GridLayout gridLay = new GridLayout(0, 2);
		JPanel dataPanel = new JPanel();
		mainPanel.add(dataPanel, BorderLayout.CENTER);
		dataPanel.setLayout(gridLay);
		dataPanel.setBackground(new Color(220, 220, 220));
		
		rateLabel = new JLabel("Hourly Wage: ");
		dataPanel.add(rateLabel);
		
		NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(); 
		rateField = new JFormattedTextField(currencyFormat);
		rateField.setColumns(10);
		rateField.setValue(rate);
		//rateField.setFocusLostBehavior(JFormattedTextField.PERSIST);
		dataPanel.add(rateField);
		
		hoursLabel = new JLabel("Hours Worked Weekly: ");
		dataPanel.add(hoursLabel);
		
		NumberFormat hoursFormat = NumberFormat.getNumberInstance();
		hoursField = new JFormattedTextField(hoursFormat);
		hoursField.setColumns(10);
		hoursField.setValue(hours);
		dataPanel.add(hoursField);
		
		salaryLabel = new JLabel("Yearly Salary: ");
		dataPanel.add(salaryLabel);
		
		salaryField = new JFormattedTextField(currencyFormat);
		salaryField.setColumns(10);
		salaryField.setValue(salary);
		//salaryField.setFocusLostBehavior(JFormattedTextField.PERSIST);
		dataPanel.add(salaryField);
		
		JButton returnButton = new JButton("Calculate");
		mainPanel.add(returnButton, BorderLayout.SOUTH);
		returnButton.addActionListener(new ReturnListener());
		
		JButton clearButton = new JButton("Clear");
		mainPanel.add(clearButton, BorderLayout.SOUTH);
		clearButton.addActionListener(new ClearListener());
	}
	
	class ReturnListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			
			try {
				rateField.commitEdit();
				hoursField.commitEdit();
				salaryField.commitEdit();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			rate = ((Number)rateField.getValue()).doubleValue();
			hours = ((Number)hoursField.getValue()).doubleValue();
			salary = ((Number)salaryField.getValue()).doubleValue();
			
			calculate(rate, hours, salary);		
			
		}
	}
	
	class ClearListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			rate = 0;
			hours = 0;
			salary = 0;
			rateField.setValue(rate);
			hoursField.setValue(hours);
			salaryField.setValue(salary);
		}
	}
	
	class HelpMenuListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			JOptionPane.showMessageDialog(null, "Enter 2 values and leave the third blank to calculate.");
		}
	}
	
	public void calculate(double rate, double hours, double salary) {
		if (rate == 0) {
			rate = salary * (1 / hours) * (1 / WKS_PER_YR);
			rateField.setValue(rate);
		}
		if (hours == 0) {
			hours = (1 / rate) * salary * (1  / WKS_PER_YR);
			hoursField.setValue(hours);
		}
		if (salary == 0) {
			salary = rate * hours * WKS_PER_YR;
			salaryField.setValue(salary);
		}
	}
}
