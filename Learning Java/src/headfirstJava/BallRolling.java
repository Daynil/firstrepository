package headfirstJava;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class BallRolling {
	
	int x = 70;
	int y = 70;
	
	public static void main(String[] args) {
		BallRolling prog = new BallRolling();
		prog.go();
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void go() {
		JFrame frame = new JFrame();	
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//InnerDrawPanel panel = new InnerDrawPanel();
		MyDrawPanel panel = new MyDrawPanel();
				
		frame.getContentPane().add(panel);
		frame.setSize(300, 300);
		frame.setVisible(true);
		
		for (int i = 0; i < 130; i++) {
			panel.setX(panel.getX()+1);
			panel.setY(panel.getY()+1);
			panel.repaint();
			try {
				Thread.sleep(50);
			} catch(Exception ex) {
				System.out.println("Something's Wrong"); 
				}
		}
	}
	
	class InnerDrawPanel extends JPanel {
		public void paintComponet(Graphics g) {
			System.out.println("paint called");
			g.setColor(Color.white);
			g.fillRect(0, 0, this.getWidth(), this.getHeight());			
			
			g.setColor(Color.green);
			g.fillOval(x, y, 40, 40);
		}
	}
}
