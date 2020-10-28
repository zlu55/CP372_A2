

public class sender{
	
	private String rHost, fileName;
	private int rPort, sPort, MDS, timeout;//r for receiver, s for sender
	
	public static void main(String[] args) throws Exception{
		rHost = args[0];
		rPort = args[1];
		sPort = args[2];
		fileName = args[3];
		MDS = args[4];//MDS = Max data size
		timeout = args[5];
	}
}
