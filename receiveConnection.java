import javax.swing.*;
import java.io.*;
import java.net.*;

public class receiveConnection{
	private DatagramSocket socket;
	private byte[] buf;
	private int packetCounter;
	
	public void start(String IP, int sPort, int rPort, String outFile, boolean reliability, JTextArea displayACK) throws IOException{
		System.out.println("Receiving...");
		
		socket = new DatagramSocket(null);
		socket.bind(new InetSocketAddress(IP, rPort));
		buf = new byte[1024];
		DatagramPacket packet = new DatagramPacket(buf, 1024);
		
		
		new File(outFile).createNewFile();
		
		
		
		
		while(true){
			try{
				System.out.println("Waiting");
				
				socket.receive(packet);
				packetCounter++;
				
				
				if(reliability || packetCounter % 10 != 0){
					String s = new String(packet.getData(), 0, packet.getLength());
					System.out.println(s);
				}
				int seqNum = packet.getData()[packet.getLength() - 1];
				displayACK.setText(""+seqNum);
				
				//Send ACK
				String ACK = "ACK " + seqNum;
				socket.send(new DatagramPacket(ACK.getBytes(), ACK.getBytes().length, InetAddress.getByName(IP), sPort));
				System.out.println("Receiving ACK " + seqNum);
				
			}catch(IOException e){
				break;
			}
		}
		
		socket.close();
	}
	
	public void stop(){
		socket.close();
	}
	
	
	
	
}