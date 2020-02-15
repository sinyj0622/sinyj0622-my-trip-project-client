package sinyj0622.mytrip.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import sinyj0622.mytrip.dao.PlanDao;
import sinyj0622.mytrip.domain.Plan;

public class PlanDaoProxy implements PlanDao {

	String serverAddr;
	int port;

	public PlanDaoProxy(String serverAddr, int port) {
		this.serverAddr = serverAddr;
		this.port = port;
	}

	@Override
	public int insert(Plan travelPlan) throws Exception {
		try (Socket socket = new Socket(serverAddr, port);
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
			out.writeUTF("/plan/add");
			out.writeObject(travelPlan);
			out.flush();

			String response = in.readUTF();
			if (response.equals("FAIL")) {
				throw new Exception(in.readUTF());
			}
			return 1;
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Plan> findAll() throws Exception {
		try (Socket socket = new Socket(serverAddr, port);
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
			out.writeUTF("/plan/list");

			out.flush();

			String response = in.readUTF();
			if (response.equals("FAIL")) {
				throw new Exception(in.readUTF());
			}

			return (List<Plan>) in.readObject();
		}
	}

	@Override
	public Plan findByNo(int no) throws Exception {
		try (Socket socket = new Socket(serverAddr, port);
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
			out.writeUTF("/plan/detail");
			out.writeInt(no);
			out.flush();


			String response = in.readUTF();
			if (response.equals("FAIL")) {
				throw new Exception(in.readUTF());
			}
			return (Plan) in.readObject();
		}
	}


	@Override
	public int update(Plan newPlan) throws Exception {
		try (Socket socket = new Socket(serverAddr, port);
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

			out.writeUTF("/plan/update");
			out.writeObject(newPlan);
			out.flush();

			String response = in.readUTF();
			if (response.equals("FAIL")) {
				throw new Exception(in.readUTF());
			}
			return 1;
		}
	}

	@Override
	public int delete(int no) throws Exception {
		try (Socket socket = new Socket(serverAddr, port);
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

			out.writeUTF("/plan/delete");
			out.writeInt(no);
			out.flush();

			String response = in.readUTF();
			if (response.equals("FAIL")) {
				throw new Exception(in.readUTF());
			}
			return 1;
		}
	}

}
