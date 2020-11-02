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
			DatagramPacket packet = DatagramPacket(buf, 1024);
			
			
			
		}catch(NumberFormatException e){
			System.out.println("Incorrect argument types");
			System.exit(0);
		}
		
		
	}
}
