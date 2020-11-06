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
		try{
			rHost = args[0]; //ip address
			rPort = Integer.parseInt(args[1]);
			sPort = Integer.parseInt(args[2]);
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

			long timer = System.currentTimeMillis();
			int datagramCount = fileData.length() / MDS;
			
			for (int i = 0; i < datagramCount + 2; i++) {
				if(i < datagramCount + 1) {
					int endIndex;
					
					dataBuff = new byte[MDS + 1];
					
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
					dataBuff = new byte[]{(byte) '\r', (byte) 5};
				}

				System.out.println("Sending Datagram...");
				socket.send(new DatagramPacket(dataBuff, dataBuff.length, InetAddress.getByName(rHost), rPort));
				
				try{
					System.out.println("Receiving ACK... ");
					socket.receive(packet);
					int ackCount = -1;

					for(byte data : packet.getData()) {
						String c = String.valueOf((char) data);
						if (c.equals("0") || c.equals("1") || c.equals("5")){
                            ackCount = Integer.parseInt(c);
						}
                    }
					if(ackCount != i % 2 && ackCount != 5){
						System.out.println("ACK is Invalid, re-sending datagram");
                        i--;
					} else {
                        System.out.println("ACK Received");
                    }
					
				}catch (SocketTimeoutException exception) {
					System.out.println("ACK timed out, re-sending datagram");
					i--;
				}
			} 
			System.out.println("Transfer time: " + (System.currentTimeMillis() - timer) + "ms");
			socket.close();

		}catch(NumberFormatException e){
			System.out.println("Incorrect argument types");
			System.exit(0);
		}
		
		
	}

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

		}catch(FileNotFoundException e) {
			System.out.println("File is Invaild, cannot be found.");
			System.exit(0);
		}

		return lines.toString();
	}

}
