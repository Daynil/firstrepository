package networking;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

//TODO test the server with someone, figure out how to detect server-side connection closures to update client connection status

public class TesterServer extends JFrame {
	
	// GUI Components
	private static TesterServer frame;
	private JTextField outgoing;
	private JEditorPane display;	
	
	private JLabel serverStatus; 
	private ImageIcon onImage;
	private ImageIcon offImage;
	
	// Networking Components
	private PrintWriter writer;
	private BufferedReader reader;
	private Socket clientSock;
	private ServerSocket serverSocket;
	private boolean serverOn = false;
	
	// Data Helpers
	private String serverTerminationCode = "789159357";
	private String displayTxt = "";
	private String helpText = "System> Type 'start' to start up the server, 'stop' to shut it down, 'help' to display this information again";
	private String[] commands = {"start", "stop", "help"};
	
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
		
		// Shows server status TODO Get the images to load when making a JAR
		onImage = createImageIcon("/serveronimage.png");
		offImage = createImageIcon("/serveroffimage.png");
		JPanel statusPanel = new JPanel();
		mainPanel.add(statusPanel);
		serverStatus = new JLabel();
		serverStatus.setText("Server Status ");
		serverStatus.setIcon(offImage);
		serverStatus.setHorizontalTextPosition(JLabel.LEFT);
		statusPanel.add(serverStatus);
		
		display = new JEditorPane();
		display.setEditable(false);
		DefaultCaret caret = (DefaultCaret) display.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);  //always scroll to bottom on text update
		//display.setBackground(Color.WHITE);
		
		JScrollPane displayScroll = new JScrollPane(display);
		displayScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		displayScroll.setPreferredSize(new Dimension(400, 200));
		displayScroll.setMinimumSize(new Dimension(10, 10));
		mainPanel.add(displayScroll);
		
		toDisplay("System", "Welcome to Danny's Chat Program (Server)\n" + helpText);
			
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
	
	public void toDisplay(String who, String what) {
		displayTxt += "\n" + who + "> ";
		displayTxt += what;
		display.setText(displayTxt);
	}
	
	/**
	 * Finds images and loads them to an icon
	 * @param path of the image
	 * @param description of the image
	 * @return an ImageIcon
	 */
	private ImageIcon createImageIcon(String path) {
		URL imgURL = frame.getClass().getResource(path);
		if (imgURL != null) {
			return new ImageIcon(imgURL);
		} else {
			System.err.println("Couldn't find file: " + path);
			return null;
		}
	}

	public void startServer() {
		try {
			// Set up port
			serverSocket = new ServerSocket(5000);
			serverSocket.setReuseAddress(true);
			
			serverOn = true;
			toDisplay("System", "Server initialized");
			serverStatus.setIcon(onImage);
			
			// Create thread to wait for connections
			Thread connectionWait = new Thread(new ConnectionWaiter());
			connectionWait.start();
			
		} catch(IOException ex) {
			ex.printStackTrace();
		}
	}
	
	public boolean isCommand(String id) {
		for (String each : commands) {
			if (id.equals(each)) {
				return true;
			}
		}
		return false;
	}
	
	class OutgoingListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			toDisplay("User", outgoing.getText());
			String id = outgoing.getText().toLowerCase();
			if (id.equals("start") && !serverOn) {
				frame.startServer();
			}
			else if (id.equals("stop") && serverOn) {
				try {
					writer.println(serverTerminationCode);
					writer.flush();
					serverSocket.close();
					serverStatus.setIcon(offImage);
					serverOn = false;
					toDisplay("System", "Server terminated");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else if (id.equals("help")) {
				toDisplay("System", helpText);
			}
			else if (!isCommand(id)) {
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
				toDisplay("System", "Someone Connected");
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		public void run() {
			String incomingMsg;
			try {
				while((incomingMsg = reader.readLine()) != null) {
					if (incomingMsg.equals(serverTerminationCode)) {
						toDisplay("System", "Someone Disconnected");
					}
					else {
						toDisplay("Someone", incomingMsg);
					}
				}
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}
