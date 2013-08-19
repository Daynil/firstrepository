package calculatorproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame {
	
	//The top visible field containing all entries until = pressed
	private JLabel equationField;
	//The bottom visible field containing current return (either from previous operation or after = pressed
	private JLabel returnField;
	//Current input holds whatever the user is currently typing until they hit an operator
	private String currentInput = "0";
	//Holds the result of an operation between two numbers
	private String theReturn = "";
	//Holds all data entered until cleared
	private String equation = "0";
	//Temporarily holds previous number for the calc script to use
	private String holder = "";
	private int tracker = 0;
	private boolean newInput = false;
	private boolean equalsRepeat = false;
	private String lastValue = "0";
	
	//Keeps track of requested operation and whether there is currently an operation being requested
	private String operation = "";
	private boolean operating = false;
	
	//private boolean negative = false;
	
	public Calculator(String name) {
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
		Calculator frame = new Calculator("Danny's Basic Calculator");
		frame.setSize(275, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Set up the content pane.
		frame.addComponentsToPane(frame.getContentPane());
		//Display the window
		frame.setVisible(true);
	}
	
	public void addComponentsToPane(final Container pane) {	
		//Main panel that holds the other panels (to accommodate results display field)
		JPanel mainPanel = new JPanel();
		pane.add(mainPanel);
		mainPanel.setBackground(Color.WHITE);
		mainPanel.setLayout(new BorderLayout(5, 5));
		
		//Add 2 visual return fields to the top, full equation view above and current input/return only on bottom
		JPanel dataPanel = new JPanel();
		dataPanel.setLayout(new GridLayout(0, 1));
		mainPanel.add(dataPanel, BorderLayout.NORTH);
		
		equationField = new JLabel(equation);
		equationField.setHorizontalAlignment(SwingConstants.RIGHT);
		dataPanel.add(equationField);
		
		returnField = new JLabel(currentInput);
		returnField.setHorizontalAlignment(SwingConstants.RIGHT);
		dataPanel.add(returnField);
		
		//Set up appropriate layout for the button panel (Grid looks best for calculators)
		GridLayout gridLay = new GridLayout(0, 4);
		
		//Create button panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(gridLay);
		mainPanel.add(buttonPanel, BorderLayout.CENTER);
		
		//Add all the buttons	
		JButton back = new JButton("<");
		back.addActionListener(new BackListener());
		buttonPanel.add(back);
		
		JButton clearEntry = new JButton("CE");
		clearEntry.addActionListener(new ClearEntryListener());
		buttonPanel.add(clearEntry);
		
		JButton clear = new JButton("C");
		clear.addActionListener(new ClearListener());
		buttonPanel.add(clear);
		
		JButton negate = new JButton("+/-");
		negate.addActionListener(new NegateListener());
		buttonPanel.add(negate);
		
		JButton seven = new JButton("7");
		seven.addActionListener(new SevenListener());
		buttonPanel.add(seven);
		
		JButton eight = new JButton("8");
		eight.addActionListener(new EightListener());
		buttonPanel.add(eight);
		
		JButton nine = new JButton("9");
		nine.addActionListener(new NineListener());
		buttonPanel.add(nine);
		
		JButton divide = new JButton("/");
		divide.addActionListener(new DivideListener());
		buttonPanel.add(divide);
		
		JButton four = new JButton("4");
		four.addActionListener(new FourListener());
		buttonPanel.add(four);
		
		JButton five = new JButton("5");
		five.addActionListener(new FiveListener());
		buttonPanel.add(five);
		
		JButton six = new JButton("6");
		six.addActionListener(new SixListener());
		buttonPanel.add(six);
		
		JButton multiply = new JButton("*");
		multiply.addActionListener(new MultiplyListener());
		buttonPanel.add(multiply);
		
		JButton one = new JButton("1");
		one.addActionListener(new OneListener());
		buttonPanel.add(one);
		
		JButton two = new JButton("2");
		two.addActionListener(new TwoListener());
		buttonPanel.add(two);
		
		JButton three = new JButton("3");
		three.addActionListener(new ThreeListener());
		buttonPanel.add(three);
		
		JButton subtract = new JButton("-");
		subtract.addActionListener(new SubtractListener());
		buttonPanel.add(subtract);
		
		JButton equals = new JButton("=");
		equals.addActionListener(new EqualsListener());
		Font bigFont = new Font("serif", Font.BOLD, 28);
		equals.setFont(bigFont);
		buttonPanel.add(equals);
		
		JButton zero = new JButton("0");
		zero.addActionListener(new ZeroListener());
		buttonPanel.add(zero);
		
		JButton point = new JButton(".");
		point.addActionListener(new PointListener());
		buttonPanel.add(point);
		
		JButton add = new JButton("+");
		add.addActionListener(new AddListener());
		buttonPanel.add(add);	
	}
	
	/**
	 * Listeners for each numerical button return their respective integer.
	 * Listeners for each operation change the operation variable.
	 */
	class NumberButton implements ActionListener {
		protected int number;
			
		public void actionPerformed(ActionEvent event) {
			setField();
		}
		public void setField() {
			if (currentInput.equals("0") && !operating) {
				currentInput = "";
				currentInput += number;
				returnField.setText(currentInput);
			}
			else if (!currentInput.equals("0") && !operating) {
				currentInput += number;
				returnField.setText(currentInput);
			}
			else if (operating && tracker == 0) {
				currentInput = "" + number;
				returnField.setText(currentInput);
				tracker++;
				newInput = true;
			}
			else if (operating && tracker > 0) {
				currentInput += number;
				returnField.setText(currentInput);
			}
		}
	}
	
	class SevenListener extends NumberButton {
		public SevenListener() {
			number = 7;
		}
	}
	
	class EightListener extends NumberButton {
		public EightListener() {
			number = 8;
		}
	}
	
	class NineListener extends NumberButton {
		public NineListener() {
			number = 9;
		}
	}
	
	class FourListener extends NumberButton {
		public FourListener() {
			number = 4;
		}
	}
	
	class FiveListener extends NumberButton {
		public FiveListener() {
			number = 5;
		}
	}
	
	class SixListener extends NumberButton {
		public SixListener() {
			number = 6;
		}
	}
	
	class OneListener extends NumberButton {
		public OneListener() {
			number = 1;
		}
	}
	
	class TwoListener extends NumberButton {
		public TwoListener() {
			number = 2;
		}
	}
	
	class ThreeListener extends NumberButton {
		public ThreeListener() {
			number = 3;
		}
	}
	
	class ZeroListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			setField();
		}
		public void setField() {
			if (currentInput.equals("0")) {
				currentInput = "";
				currentInput += "0";
				returnField.setText(currentInput);
			}
			else {
				currentInput += "0";
				returnField.setText(currentInput);
			}
		}
	}
	
	class PointListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			setField();
		}
		public void setField() {
			currentInput += ".";
			returnField.setText(currentInput);
		}
	}
	class OperationButton implements ActionListener {
		protected String myOperation;
		protected String mySymbol;
		
		public void actionPerformed(ActionEvent event) {
			if (!currentInput.equals("0")) {
				if (equation.equals("0") && !operating) {
					tracker = 0;
					operating = true;
					operation = myOperation;
					equation = "" + currentInput + mySymbol;
					equationField.setText(equation);
					holder = currentInput;
				}
				else if (operating && !newInput && !(equation.substring(equation.length()-3, equation.length())).equals(mySymbol)) {
					equation = equation.substring(0, equation.length() - 3) + mySymbol;
					equationField.setText(equation);
					operation = myOperation;
				}
				else if (!equation.equals("0") && !operating) {
					tracker = 0;
					operating = true;
					equation += currentInput + mySymbol;
					equationField.setText(equation);
				}
				else if (!equation.equals("0") && operating && newInput) {
					tracker = 0;
					newInput = false;
					equalsRepeat = false;
					theReturn = "" + CalcScript.calculate(holder, currentInput, operation);
					holder = theReturn;
					equation += currentInput + mySymbol;
					currentInput = theReturn;
					returnField.setText(currentInput);
					operation = myOperation;
				}
			equationField.setText(equation);
			}
		}
	}
	
	class DivideListener extends OperationButton {
		public DivideListener() {
			myOperation = "divide";
			mySymbol = " / ";
		}
	}
	
	class MultiplyListener extends OperationButton {
		public MultiplyListener() {
			myOperation = "multiply";
			mySymbol = " * ";
		}
	}
	
	class SubtractListener extends OperationButton {
		public SubtractListener() {
			myOperation = "subtract";
			mySymbol = " - ";
		}
	}
	
	class AddListener extends OperationButton {
		public AddListener() {
			myOperation = "add";
			mySymbol = " + ";
		}
	}
	
	class BackListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			setField();
		}
		public void setField() {
			if (currentInput.length() > 1) {
				currentInput = currentInput.substring(0, currentInput.length()-1);
				returnField.setText(currentInput);
			}
		}
	}
	
	class ClearEntryListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			setField();
		}
		public void setField() {
			currentInput = "0";
			returnField.setText(currentInput);
		}
	}
	
	class ClearListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			setField();
		}
		public void setField() {
			currentInput = "0";
			equation = "0";
			returnField.setText(currentInput);
			equationField.setText(equation);
			operating = false;
			equalsRepeat = false;
			operation = "";
			holder = "0";
			lastValue = "0";
			
			//negative = false;
		}
	}
	class NegateListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (!currentInput.equals("0") && !currentInput.contains("-")) {
				currentInput = "-" + currentInput;
				returnField.setText(currentInput);
			}
			else if (currentInput.contains("-")) {
				currentInput = currentInput.substring(1, currentInput.length());
				returnField.setText(currentInput);
			}
		}
	}
	//TODO fix functionality of multiply presses of equals and fix pressing equals after an operator without a value change
	class EqualsListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (!equation.equals("0") && !equalsRepeat) {
				theReturn = "" +  CalcScript.calculate(holder, currentInput, operation);
				holder = theReturn;
				lastValue = currentInput;
				currentInput = theReturn;
				returnField.setText(currentInput);
				equalsRepeat = true;
			}
			if (!equation.equals("0") && equalsRepeat) {
				theReturn = "" + CalcScript.calculate(lastValue, currentInput, operation);
				holder = theReturn;
				currentInput = theReturn;
				returnField.setText(currentInput);
			}
		}
	}
}
