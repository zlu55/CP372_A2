import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class receiver{
	private JFrame frame;
	private JPanel left, right, senderPortPanel, receiverPortPanel, IPPanel,
	filePanel, sidePanel, outBoxPanel, receivePanel;
	private JLabel senderPortLabel, receiverPortLabel, IPLabel, fileLabel;
	private JScrollPane scrollPane;
	private JTextField senderPortTxt, receiverPortTxt, IPTxtField, fileTxt;
	private JTextArea outputBox;
	private JButton receiveButton;
	
	
	public static void main(String[] args){
		receiver newReceiver = new receiver();
		newReceiver.setUpGUI();
		
	}
	
	public void setUpGUI(){
		frame = new JFrame("CP372 A1 - Zachary Luloff/Mitchell Mactaggart");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel pane = new JPanel();
		frame.setContentPane(pane);
		pane.setLayout(new BorderLayout());
		
		left = new JPanel();
		right = new JPanel();	
		pane.add(left, BorderLayout.WEST);
		pane.add(right, BorderLayout.EAST);
		left.setLayout(new GridLayout(4,1));
		right.setLayout(new BoxLayout(right, BoxLayout.PAGE_AXIS));
		
		senderPortPanel = new JPanel();
		receiverPortPanel = new JPanel();
		IPPanel = new JPanel();
		filePanel = new JPanel();
		left.add(IPPanel);
		left.add(senderPortPanel);
		left.add(receiverPortPanel);
		left.add(filePanel);
		
		left.setPreferredSize(new Dimension(200, 300));
		left.setMinimumSize(new Dimension(200, 300));
		left.setMaximumSize(new Dimension(200, 300));
		left.setAlignmentX(Component.LEFT_ALIGNMENT);
		right.setPreferredSize(new Dimension(200, 200));
		right.setMinimumSize(new Dimension(200, 200));
		right.setMaximumSize(new Dimension(200, 200));
		right.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		senderPortLabel = new JLabel("Sender Port: ");
		senderPortTxt = new JTextField("3000");
		senderPortTxt.setPreferredSize(new Dimension(60, 24));
		senderPortTxt.setMaximumSize(new Dimension(60, 24));
		senderPortPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		senderPortPanel.add(senderPortLabel);
		senderPortPanel.add(senderPortTxt);
		
		receiverPortLabel = new JLabel("Receiver Port: ");
		receiverPortTxt = new JTextField("3000");
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
		fileTxt = new JTextField();
		fileTxt.setPreferredSize(new Dimension(120, 24));
		fileTxt.setMaximumSize(new Dimension(120, 24));
		filePanel.setAlignmentX(Component.LEFT_ALIGNMENT);
		filePanel.add(fileLabel);
		filePanel.add(fileTxt);
		
		
		receivePanel = new JPanel();
		receiveButton = new JButton("Receive");
		receivePanel.setPreferredSize(new Dimension(90, 40));
		receivePanel.setMinimumSize(new Dimension(90, 40));
		receivePanel.setMaximumSize(new Dimension(90, 40));
		
		receivePanel.add(receiveButton);
		right.add(receivePanel);
		
		outputBox = new JTextArea("");
		outputBox.setEditable(false);
		outBoxPanel = new JPanel();
		scrollPane = new JScrollPane(outputBox);
		scrollPane.setPreferredSize(new Dimension(200, 250));
		scrollPane.setMinimumSize(new Dimension(200, 250));
		scrollPane.setMaximumSize(new Dimension(200, 250));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		outBoxPanel.add(scrollPane);
		right.add(outBoxPanel);
		
		
		frame.setSize(400, 350);
		frame.setVisible(true);
	}
	
	
}
