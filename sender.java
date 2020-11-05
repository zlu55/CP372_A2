import java.io.*;
import java.net.*;
import java.util.Scanner;

public class sender{
	
	
	public static void main(String[] args) throws Exception{
		String rHost, fileName;
		int rPort, sPort, MDS, timeout;//r for receiver, s for sender
		boolean running;
		byte[] buf = new byte[1024];
		
		if(args.length != 6){
			System.out.println("Incorrect number of arguments\n");
			System.exit(0);
		}
		try{
			rHost = args[0];
			rPort = Integer.parseInt(args[1]);
			sPort = Integer.parseInt(args[2]);
			fileName = args[3];
			MDS = Integer.parseInt(args[4]);//MDS = Max data size
			timeout = Integer.parseInt(args[5]);
			
			
			DatagramSocket socket = new DatagramSocket(null);
			socket.bind(new InetSocketAddress(rHost, sPort));
			socket.setSoTimeout(timeout);
			DatagramPacket packet = new DatagramPacket(buf, 1024);

			String fileData = readFile(fileName);

			System.out.println("Sending datagram from " + fileName);
			System.out.println("Address: " + rHost);
			System.out.println("Port: " + rPort); 
			System.out.println("ACKs Recieved at: " + sPort);

			
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

		return lines.toString();//scanFile.toString();
	}
}
