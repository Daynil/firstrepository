package headfirstJava;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class SimpleGui1 {
	JButton colorButton;
	JFrame frame;
	JLabel label;
	int performed = 1;
	
	public static void main (String[] args) {
		SimpleGui1 gui = new SimpleGui1();
		gui.go();
	}
	
	public void go() {
		frame = new JFrame();
		MyDrawPanel panel = new MyDrawPanel();
		
		colorButton = new JButton("Change colors");
		colorButton.addActionListener(new ColorListener());
		
		JButton labelButton = new JButton("Change Label");
		labelButton.addActionListener(new LabelListener());	
		label = new JLabel("I'm a label!");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(colorButton, BorderLayout.SOUTH);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.getContentPane().add(labelButton, BorderLayout.EAST);
		frame.getContentPane().add(label, BorderLayout.WEST);
		
		frame.setSize(300, 300);
		frame.setVisible(true);
	}
	
	class ColorListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			String buttonTxt = String.format("Change colors (Changed: %d times)", performed);
			frame.repaint();
			colorButton.setText(buttonTxt);
			performed++;
		}
	}
	
	class LabelListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			label.setText("Ouch!");
		}
	}
}
