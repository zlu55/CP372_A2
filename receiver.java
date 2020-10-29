import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class receiver{
	private JFrame frame;
	private JPanel left, right, senderPortPanel, receiverPortPanel, IPPanel,
	filePanel, sidePanel, outBoxPanel, receivePanel, reliablePanel;
	private JLabel senderPortLabel, receiverPortLabel, IPLabel, fileLabel, fileEndLabel;
	private JScrollPane scrollPane;
	private JTextField senderPortTxt, receiverPortTxt, IPTxtField, fileTxt;
	private JTextArea outputBox;
	private JButton receiveButton;
	private JToggleButton relButton, unrelButton;
	public boolean reliability = true;//True for reliable, False for unreliable
	private connection newConnect;
	
	public static void main(String[] args){
		receiver newReceiver = new receiver();
		newReceiver.setUpGUI();
		
	}
	
	public void setUpGUI(){
		newConnect = new connection();
		frame = new JFrame("CP372 A1 - Zachary Luloff/Mitchell Mactaggart");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pane = new JPanel();
		frame.setContentPane(pane);
		pane.setLayout(new BorderLayout());
		
		left = new JPanel();
		right = new JPanel();	
		pane.add(left, BorderLayout.WEST);
		pane.add(right, BorderLayout.EAST);
		left.setLayout(new GridLayout(5,1));
		right.setLayout(new BoxLayout(right, BoxLayout.PAGE_AXIS));
		
		senderPortPanel = new JPanel();
		receiverPortPanel = new JPanel();
		IPPanel = new JPanel();
		filePanel = new JPanel();
		reliablePanel = new JPanel();
		left.add(IPPanel);
		left.add(senderPortPanel);
		left.add(receiverPortPanel);
		left.add(filePanel);
		left.add(reliablePanel);
		
		left.setPreferredSize(new Dimension(215, 350));
		left.setMinimumSize(new Dimension(215, 350));
		left.setMaximumSize(new Dimension(215, 350));
		left.setAlignmentX(Component.LEFT_ALIGNMENT);
		right.setPreferredSize(new Dimension(275, 200));
		right.setMinimumSize(new Dimension(275, 200));
		right.setMaximumSize(new Dimension(275, 200));
		right.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		senderPortLabel = new JLabel("Sender Port: ");
		senderPortTxt = new JTextField("3321");
		senderPortTxt.setPreferredSize(new Dimension(60, 24));
		senderPortTxt.setMaximumSize(new Dimension(60, 24));
		senderPortPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		senderPortPanel.add(senderPortLabel);
		senderPortPanel.add(senderPortTxt);
		
		receiverPortLabel = new JLabel("Receiver Port: ");
		receiverPortTxt = new JTextField("4455");
		receiverPortTxt.setPreferredSize(new Dimension(60, 24));
		receiverPortTxt.setMaximumSize(new Dimension(60, 24));
		receiverPortPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		receiverPortPanel.add(receiverPortLabel);
		receiverPortPanel.add(receiverPortTxt);
		
		IPLabel = new JLabel("Sender IP: ");
		IPTxtField = new JTextField("127.0.0.1");
		IPTxtField.setPreferredSize(new Dimension(90, 24));
		IPTxtField.setMaximumSize(new Dimension(90, 24));
		IPPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		IPPanel.add(IPLabel);
		IPPanel.add(IPTxtField);
		
		fileLabel = new JLabel("File name: ");
		fileTxt = new JTextField("");
		fileTxt.setPreferredSize(new Dimension(120, 24));
		fileTxt.setMaximumSize(new Dimension(120, 24));
		fileEndLabel = new JLabel(".txt");
		filePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		filePanel.add(fileLabel);
		filePanel.add(fileTxt);
		filePanel.add(fileEndLabel);
		
		relButton = new JToggleButton("Reliable");
		relButton.setSelected(true);
		relButton.addActionListener(new reliableListener());
		unrelButton = new JToggleButton("Unreliable");
		unrelButton.setSelected(false);
		unrelButton.addActionListener(new reliableListener());
		reliablePanel.add(relButton);
		reliablePanel.add(unrelButton);
		
		
		receivePanel = new JPanel();
		receiveButton = new JButton("Receive");
		receiveButton.addActionListener(this::receiveInfo);
		receivePanel.setPreferredSize(new Dimension(90, 40));
		receivePanel.setMinimumSize(new Dimension(90, 40));
		receivePanel.setMaximumSize(new Dimension(90, 40));
		
		receivePanel.add(receiveButton);
		right.add(receivePanel);
		
		outputBox = new JTextArea("");
		outputBox.setEditable(false);
		outBoxPanel = new JPanel();
		scrollPane = new JScrollPane(outputBox);
		scrollPane.setPreferredSize(new Dimension(250, 250));
		scrollPane.setMinimumSize(new Dimension(250, 250));
		scrollPane.setMaximumSize(new Dimension(250, 250));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		outBoxPanel.add(scrollPane);
		right.add(outBoxPanel);
		
		
		frame.setSize(500, 350);
		frame.setVisible(true);
	}
	
	private class reliableListener implements ActionListener{
		public void actionPerformed(ActionEvent e){
			if(e.getSource() == relButton){
				unrelButton.setSelected(false);
				relButton.setSelected(true);
				reliability = true;
			}else if(e.getSource() == unrelButton){
				relButton.setSelected(false);
				unrelButton.setSelected(true);
				reliability = false;
			}
		}
	}
	
	
	private void receiveInfo(ActionEvent event){
		int sPort = 0;
		if(senderPortTxt.getText().length() > 0){
			try{
				sPort = Integer.parseInt(senderPortTxt.getText());
			}catch(NumberFormatException e){
				outputBox.setText("Please enter a correct port number for sender");
			}
		}
		int rPort = 0;
		if(receiverPortTxt.getText().length() > 0){
			try{
				rPort = Integer.parseInt(receiverPortTxt.getText());
			}catch(NumberFormatException e){
				outputBox.setText("Please enter a correct port number for receiver");
			}
		}
		
		String IP = IPTxtField.getText();
		String fileName = fileTxt.getText();

		if(sPort == 0){
			outputBox.setText("Please enter sender port number");
		}else if(rPort == 0){
			outputBox.setText("Please enter receiver port number");
		}else if(IP.equals("")){
			outputBox.setText("Please enter IP address");
		}else if(fileName.equals("")){
			outputBox.setText("Please enter file name");
		}else{
			outputBox.setText("");
			
			
		}
	}
	
}
