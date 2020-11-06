import javax.swing.*;
import java.io.*;
import java.net.*;

public class receiveConnection{
	private DatagramSocket socket;
	private byte[] buf;
	private int packetCounter, orderPacketCounter;
	
	public void start(String IP, int sPort, int rPort, String outFile, boolean reliability, JTextArea displayACK) throws IOException{
		System.out.println("Receiving...");
		//Set up socket and packet
		socket = new DatagramSocket(null);
		socket.bind(new InetSocketAddress(IP, rPort));
		buf = new byte[1024];
		DatagramPacket packet = new DatagramPacket(buf, 1024);
		
		
		new File(outFile).createNewFile();
		StringBuilder endData = new StringBuilder();
		packetCounter = 0;
		orderPacketCounter = 0;
		
		
		while(true){
			try{
				System.out.println("Waiting");
				
				socket.receive(packet);
				packetCounter++;
				//If it is using reliable data transfer, or if its not the 10th packet to be dropped...
				if(reliability || packetCounter % 10 != 0){
					StringBuilder tempData = new StringBuilder();
					//Iterate through the packet data and add it to the tempData string
					for(int i=0; i<packet.getLength(); i++){
						if(packet.getData()[i] >= 9){
							tempData.append((char) packet.getData()[i]);
						}
					}
					//Add the tempData to the end data string
					endData.append(tempData.toString());
					
					//get sequence number from the last char of the packet data
					int seqNum = packet.getData()[packet.getLength() - 1];
					
					//Check if the packet was an EOT datagram
					if(tempData.toString().contains("\r") && seqNum == 5){
						PrintWriter w = new PrintWriter(new FileWriter(outFile));
						w.print(endData);
						w.close();
						displayACK.setText("Packet count: " + orderPacketCounter + "\nFinished");
						orderPacketCounter = 0;
						endData = new StringBuilder();
						System.out.println("Finished");
					}else{
						orderPacketCounter++;
						displayACK.setText("Packet count: " + orderPacketCounter);
					}
					//Send ACK
					String ACK = "ACK " + seqNum;
					socket.send(new DatagramPacket(ACK.getBytes(), ACK.getBytes().length, InetAddress.getByName(IP), sPort));
					System.out.println("Receiving ACK " + seqNum);
				}
			}catch(IOException e){
				break;
			}
		}
	}
	
	public void stop(){
		socket.close();
	}
}