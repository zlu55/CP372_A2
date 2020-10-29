
public class senderThread extends Thread{
	private String rHost, fileName;
	private int rPort, sPort, MDS, timeout;//r for receiver, s for sender
	
	
	public senderThread(String rHost, int rPort, int sPort, String fileName, int MDS, int timeout){
		this.rHost = rHost;
		this.rPort = rPort;
		this.sPort = sPort;
		this.fileName = fileName;
		this.MDS = MDS;
		this.timeout = timeout;
	}
	
	
	public void  run(){
		String received;
		running = true;
		
		while(running){
			DatagramPacket packet = new DatagramPacket(buf, buf.length);
			socket.receive(packet);
			
			received = new String(packet.getData(), 0, packet.getLength());
		}
	}
}
