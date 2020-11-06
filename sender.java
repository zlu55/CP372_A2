import java.io.*;
import java.net.*;
import java.util.Scanner;

public class sender{
	
	
	public static void main(String[] args) throws Exception{
		String rHost, fileName;
		int rPort, sPort, MDS, timeout;//r for receiver, s for sender
		boolean running;
		//byte[] buf = new byte[1024];
		
		if(args.length != 6){
			System.out.println("Incorrect number of arguments\n");
			System.exit(0);
		}
		try{//Initalization
			rHost = args[0]; //ip address
			rPort = Integer.parseInt(args[1]);//reciever port
			sPort = Integer.parseInt(args[2]);//sender port
			fileName = args[3];
			MDS = Integer.parseInt(args[4]);//MDS = Max data size
			timeout = Integer.parseInt(args[5]);
			
			byte[] buff = new byte[1024];
			
			DatagramSocket socket = new DatagramSocket(null);
			socket.bind(new InetSocketAddress(rHost, sPort));
			socket.setSoTimeout(timeout);

			String fileData = readFile(fileName);
			DatagramPacket packet = new DatagramPacket(buff, 1024);
			byte[] dataBuff;

			System.out.println("Sending datagram from " + fileName);
			System.out.println("Address: " + rHost);
			System.out.println("Port: " + rPort); 
			System.out.println("ACKs Recieved at: " + sPort);

			long timer = System.currentTimeMillis();//for testing transfer time
			int datagramCount = fileData.length() / MDS;//amount of datagrams to be sent
			
			for (int i = 0; i < datagramCount + 2; i++) { // plus 2 index and eot datagram
				if(i < datagramCount + 1) { //checks that it is not last datagram
					int endIndex;
					
					dataBuff = new byte[MDS + 1];
					// determines size of byte array needed
					if(i == (fileData.length() / MDS)){
						endIndex = fileData.length();
					}else{
						endIndex = MDS * (i + 1);
					}
					for(int index = MDS * i; index < endIndex; index++){
						dataBuff[MDS + index - endIndex] = (byte) fileData.charAt(index);
					}
					dataBuff[MDS] = (byte) (i % 2);
				}else{
				// creates EOT datagram defined as a newline character with sequence 5
					dataBuff = new byte[]{(byte) '\r', (byte) 5};
				}

				System.out.println("Sending Datagram...");
				socket.send(new DatagramPacket(dataBuff, dataBuff.length, InetAddress.getByName(rHost), rPort));//sends data in datagram packet
				
				try{//Receivce ack
					System.out.println("Receiving ACK... ");
					socket.receive(packet);
					int ackCount = -1;

					for(byte data : packet.getData()) {
						String c = String.valueOf((char) data);
						if (c.equals("0") || c.equals("1") || c.equals("5")){// sets ackCount to sequence number
                            ackCount = Integer.parseInt(c);
						}
                    }
					if(ackCount != i % 2 && ackCount != 5){ // checks for correct sequence responses
						System.out.println("ACK is Invalid, re-sending datagram");
                        i--;
					} else {
                        System.out.println("ACK Received");
                    }
					
				}catch (SocketTimeoutException exception) { // re-sends packet in the case of a timeout
					System.out.println("ACK timed out, re-sending datagram");
					i--;
				}
			} 
			System.out.println("Transfer time: " + (System.currentTimeMillis() - timer) + "ms");//output of transmission time
			socket.close();

		}catch(NumberFormatException e){
			System.out.println("Incorrect argument types");
			System.exit(0);
		}
		
		
	}
	// Stores data from file in a string
	public static String readFile(String fileName){
		
		StringBuilder lines = new StringBuilder();
		Scanner scanFile;

		try{
			scanFile = new Scanner(new File(fileName));
			
			while (scanFile.hasNextLine()) {
				lines.append(scanFile.nextLine());
				lines.append("\n");
			}
			scanFile.close();

		}catch(FileNotFoundException e) {//checks for correct file name
			System.out.println("File is Invaild, cannot be found.");
			System.exit(0);
		}

		return lines.toString();
	}

}
