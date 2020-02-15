package sinyj0622.mytrip.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import sinyj0622.mytrip.dao.MemberDao;
import sinyj0622.mytrip.domain.Member;

public class MemberDaoProxy implements MemberDao {
	
	DaoProxyHelper daoProxyHelper;

	public MemberDaoProxy(DaoProxyHelper daoProxyHelper) {
		this.daoProxyHelper = daoProxyHelper;
	}

	@Override
	public int insert(Member member) throws Exception {
		return (int) daoProxyHelper.request((out, in) -> {
				out.writeUTF("/member/add");
				out.writeObject(member);


				String response = in.readUTF();
				if (response.equals("FAIL")) {
					throw new Exception(in.readUTF());
				}
				return 1;
		});
			
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Member> findAll() throws Exception {
		return (List<Member>) daoProxyHelper.request((out, in) -> {
			out.writeUTF("/member/list");

			out.flush();

			String response = in.readUTF();
			if (response.equals("FAIL")) {
				throw new Exception(in.readUTF());
			}

			return (List<Member>) in.readObject();
		});
	}

	@Override
	public Member findByNo(int no) throws Exception {
		return (Member) daoProxyHelper.request((out, in) -> {
			out.writeUTF("/member/detail");
			out.writeInt(no);
			out.flush();

			String response = in.readUTF();
			if (response.equals("FAIL")) {
				throw new Exception(in.readUTF());
			}
			return (Member) in.readObject();
		});
	}

	@Override
	public int update(Member newMember) throws Exception {
		return (int) daoProxyHelper.request((out, in) -> {
			out.writeUTF("/member/update");
			out.writeObject(newMember);
			out.flush();

			String response = in.readUTF();
			if (response.equals("FAIL")) {
				throw new Exception(in.readUTF());
			}
			return 1;
		});
	}

	@Override
	public int delete(int no) throws Exception {
		return (int) daoProxyHelper.request((out, in) -> {
			out.writeUTF("/member/delete");
			out.writeInt(no);
			out.flush();

			String response = in.readUTF();
			if (response.equals("FAIL")) {
				throw new Exception(in.readUTF());
			}
			return 1;
		});
	}

}
