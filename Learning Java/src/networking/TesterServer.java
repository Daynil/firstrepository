package networking;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import javax.swing.*;

// TODO make this a server that sends some random text to a client when they request it, and display both the message sent and that the client
// requested it **Make sure to close Socket when program closes and make socket close IMMEDIATELY so I can rewrite server anytime and
// use the same socket

public class TesterServer extends JFrame {
	
	private JTextField outgoing;
	
	private JEditorPane display;	
	private String displayTxt = "";
	private String helpText = "> Server Application: \n> Type 'start' to start up the server";
	
	private PrintWriter writer;
	private BufferedReader reader;
	private Socket clientSock;
	private ServerSocket serverSocket;
	private boolean serverOn = false;
	
	private static TesterServer frame;
	
	public TesterServer(String name) {
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
		frame = new TesterServer("Danny's Test Server");
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
	
	public void startServer() {
		try {
			// Set up port
			serverSocket = new ServerSocket(5000);
			serverSocket.setReuseAddress(true);
			
			serverOn = true;
			displayTxt += "\n> Server On";
			display.setText(displayTxt);
			
			// Create thread to wait for connections
			Thread connectionWait = new Thread(new ConnectionWaiter());
			connectionWait.start();
			
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	class OutgoingListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			displayTxt += "\n> " + outgoing.getText();
			display.setText(displayTxt);
			if (outgoing.getText().toLowerCase().equals("start") && !serverOn) {
				frame.startServer();
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
					displayTxt += "/n> " + incomingMsg;
					display.setText(displayTxt);
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	class ConnectionWaiter implements Runnable {
		public void run() {
			try {
				while(true) {
					clientSock = serverSocket.accept(); // Listen for and accept connections
					writer = new PrintWriter(clientSock.getOutputStream()); //writer SENDS output TO CLIENT
					
					Thread clients = new Thread(new ClientHandler(clientSock));  // Create a separate thread to handle new client connections
					clients.start();
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	class ClientHandler implements Runnable {
		Socket sock;
		
		public ClientHandler(Socket clientSocket) {
			try {
				sock = clientSock;
				InputStreamReader isReader = new InputStreamReader(clientSock.getInputStream());
				reader = new BufferedReader(isReader);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		public void run() {
			String incomingMsg;
			try {
				while((incomingMsg = reader.readLine()) != null) {
					displayTxt += "\n> ";
					displayTxt += incomingMsg;
					display.setText(displayTxt);
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}
