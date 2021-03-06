package networking;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.nio.channels.Selector;

import javax.swing.*;
import javax.swing.text.DefaultCaret;

public class TesterClient extends JFrame {
	
	// GUI components
	private static TesterClient frame;
	private JEditorPane display;	
	private JTextField outgoing;
	
	private JLabel serverStatus; 
	private ImageIcon onImage;
	private ImageIcon offImage;
	
	// Networking components
	private PrintWriter writer;
	private BufferedReader reader;
	private Socket sock;
	private boolean isConnected = false;
	
	// Data Helpers
	private String serverTerminationCode = "789159357";
	private String displayTxt = "";
	private String helpText = "Type 'connect IP Address' to attempt connection to server, 'disconnect' to close connection, 'help' to review this information";
	private String[] commands = {"connect", "disconnect", "help"};
	

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
		
		// Shows server status TODO Get the images to load when making a JAR
		onImage = createImageIcon("/serveronimage.png");
		offImage = createImageIcon("/serveroffimage.png");
		JPanel statusPanel = new JPanel();
		mainPanel.add(statusPanel);
		serverStatus = new JLabel();
		serverStatus.setText("Connection Status ");
		serverStatus.setIcon(offImage);
		serverStatus.setHorizontalTextPosition(JLabel.LEFT);
		statusPanel.add(serverStatus);
		
		display = new JEditorPane();
		display.setEditable(false);
		DefaultCaret caret = (DefaultCaret) display.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE); //always scroll to bottom on text update
		//display.setBackground(Color.WHITE);
		
		JScrollPane displayScroll = new JScrollPane(display);
		displayScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		displayScroll.setPreferredSize(new Dimension(400, 200));
		displayScroll.setMinimumSize(new Dimension(10, 10));
		mainPanel.add(displayScroll);
		
		toDisplay("System", "Welcome to Danny's Chat Program (Client)\n" + helpText);
		
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
	
	public void connectServer(String ip) {
		try {
			// "25.51.10.123" (from java); "173.24.164.36" (from whatsmyip - doesn't work); "127.0.0.1" (for local testing);
			InetAddress address = InetAddress.getByName(ip);
			sock = new Socket(address, 5000);
			writer = new PrintWriter(sock.getOutputStream());
			InputStreamReader streamReader = new InputStreamReader(sock.getInputStream());
			reader = new BufferedReader(streamReader);
			Thread readerThread = new Thread(new IncomingReader()); 
			readerThread.start(); //start listener thread
			toDisplay("System", "Connection established!");
			isConnected = true;
			serverStatus.setIcon(onImage);
		} catch(IOException ex) {
			toDisplay("System", "Couldn't connect!");
			//ex.printStackTrace();
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
			if (id.substring(0, 7).equals("connect") && !isConnected) {
				frame.connectServer(id.substring(8, id.length()));
			}
			else if (id.equals("disconnect") && isConnected) {
				try {
					writer.write(serverTerminationCode);
					writer.flush();
					sock.close();
					serverStatus.setIcon(offImage);
					isConnected = false;
					toDisplay("System", "Connection severed.");
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
	
	class IncomingReader implements Runnable {
		public void run() {
			String incomingMsg;
			try {
				while ((incomingMsg = reader.readLine()) != null) {
					if (incomingMsg.equals(serverTerminationCode)) {
						toDisplay("System", "Connection to server lost");
						isConnected = false;
						serverStatus.setIcon(offImage);
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
