import java.io.*;
import java.net.*;
import java.util.Scanner;

public class sender{
	
	private String rHost, fileName;
	private int rPort, sPort, MDS, timeout;//r for receiver, s for sender
	private boolean running;
	private byte[] buf = new byte[256];
	
	public static void main(String[] args) throws Exception{
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
			
			byte buf = new byte[1024];
			
			
			DatagramSocket socket = new DatagramSocket(sPort);
			socket.bind(new InetSocketAddress(ip, sPort));
			socket.setSoTimeout(timeout);
			DatagramPacket packet = DatagramPacket(buf, 1024);

			String fileData = readFile(filename);

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
			scanFile = new Scanner(new File(filename));
			
			while (scanner.hasNextLine()) {
				lines.append(scanner.nextLine())
				lines.append("\n")
			}
			scanFile.close();

		}catch(FileNotFoundException e) {
			System.out.println("File is Invaild, cannot be found.");
			System.exit(0);
		}

		return scanFile.toString();
	}
}
