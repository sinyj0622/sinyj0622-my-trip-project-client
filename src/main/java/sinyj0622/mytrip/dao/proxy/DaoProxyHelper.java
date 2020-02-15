package sinyj0622.mytrip.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class DaoProxyHelper {

	String serverAddr;
	int port;
	
	public DaoProxyHelper(String serverAddr, int port) {
		this.serverAddr = serverAddr;
		this.port = port;
	}
	
	public Object request(Worker worker) throws Exception {
		try (Socket socket = new Socket(serverAddr, port);
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
			return worker.execute(out, in);
		}
	}
}
