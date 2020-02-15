package sinyj0622.mytrip.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import sinyj0622.mytrip.dao.MemberDao;
import sinyj0622.mytrip.domain.Member;

public class MemberDaoProxy implements MemberDao {

	String serverAddr;
	int port;

	public MemberDaoProxy(String serverAddr, int port) {
		this.serverAddr = serverAddr;
		this.port = port;
	}

	@Override
	public int insert(Member member) throws Exception {
		try (Socket socket = new Socket(serverAddr, port);
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
			out.writeUTF("/member/add");
			out.writeObject(member);


			String response = in.readUTF();
			if (response.equals("FAIL")) {
				throw new Exception(in.readUTF());
			}
			return 1;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Member> findAll() throws Exception {
		try (Socket socket = new Socket(serverAddr, port);
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
			out.writeUTF("/member/list");

			out.flush();

			String response = in.readUTF();
			if (response.equals("FAIL")) {
				throw new Exception(in.readUTF());
			}

			return (List<Member>) in.readObject();
		}
	}

	@Override
	public Member findByNo(int no) throws Exception {
		try (Socket socket = new Socket(serverAddr, port);
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
			out.writeUTF("/member/detail");
			out.writeInt(no);
			out.flush();

			String response = in.readUTF();
			if (response.equals("FAIL")) {
				throw new Exception(in.readUTF());
			}
			return (Member) in.readObject();
		}
	}

	@Override
	public int update(Member newMember) throws Exception {
		try (Socket socket = new Socket(serverAddr, port);
				ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
			out.writeUTF("/member/update");
			out.writeObject(newMember);
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
			out.writeUTF("/member/delete");
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
