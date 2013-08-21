package networking;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;

import javax.swing.*;

public class TesterClient extends JFrame {
	
	private JEditorPane display;	
	private String displayTxt = "";
	private String helpText = "> Client Application: \n> Type 'connect' to attempt connection to server";
	
	private JTextField outgoing;
	
	private PrintWriter writer;
	private BufferedReader reader;
	private Socket sock;
	private boolean isConnected = false;
	
	private static TesterClient frame;
	
	
	public TesterClient(String name) {
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
		frame = new TesterClient("Danny's Test Client");
		frame.setSize(500, 270);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Set up the content pane.
		frame.addComponentsToPane(frame.getContentPane());
		//Display the window
		frame.setVisible(true);
	}
	
	public void addComponentsToPane(final Container pane) {
		
		JPanel mainPanel = new JPanel();
		pane.add(mainPanel);
		mainPanel.setBackground(new Color(220, 220, 220));
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
		
		display = new JEditorPane();
		display.setEditable(false);
		//display.setBackground(Color.WHITE);
		
		JScrollPane displayScroll = new JScrollPane(display);
		displayScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		displayScroll.setPreferredSize(new Dimension(400, 200));
		displayScroll.setMinimumSize(new Dimension(10, 10));
		mainPanel.add(displayScroll);
		
		displayTxt += helpText;
		display.setText(displayTxt);
		
		//TODO create text entry field, button to send, listener of send button/or enter key, commands that the server can respond to
		JPanel userPanel = new JPanel();
		mainPanel.add(userPanel);
		
		OutgoingListener sendListener = new OutgoingListener();
		outgoing = new JTextField(31);
		outgoing.addActionListener(sendListener);
		userPanel.add(outgoing);
		
		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(sendListener);
		userPanel.add(sendButton);
		
	}
	
	public void connectServer() {
		try {
			sock = new Socket("127.0.0.1", 5000);
			writer = new PrintWriter(sock.getOutputStream());
			InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
			reader = new BufferedReader(streamReader);
			Thread readerThread = new Thread(new IncomingReader()); 
			readerThread.start(); //start listener thread
			displayTxt += "\n> " + "Connection Established!";
			display.setText(displayTxt);			
		} catch(IOException ex) {
			displayTxt += "\n" + "> " + "Couldn't Connect!";
			display.setText(displayTxt);
			//ex.printStackTrace();
		}
	}
	
	class OutgoingListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			displayTxt += "\n> " + outgoing.getText();
			display.setText(displayTxt);
			if (outgoing.getText().toLowerCase().equals("connect") && !isConnected) {
				frame.connectServer(); 
				isConnected = true;
			}
			else if (!outgoing.getText().toLowerCase().equals("connect")) {
				try {
					writer.println(outgoing.getText());
					writer.flush();
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			outgoing.setText("");
			outgoing.requestFocus();
		}
	}
	
	class IncomingReader implements Runnable {
		public void run() {
			String incomingMsg;
			try {
				while ((incomingMsg = reader.readLine()) != null) {
					displayTxt += "/n> ";
					displayTxt += incomingMsg;
					display.setText(displayTxt);
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}
