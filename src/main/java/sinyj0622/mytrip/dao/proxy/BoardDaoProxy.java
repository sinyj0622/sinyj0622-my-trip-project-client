package sinyj0622.mytrip.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import sinyj0622.mytrip.dao.BoardDao;
import sinyj0622.mytrip.domain.Board;

public class BoardDaoProxy implements BoardDao {

	DaoProxyHelper daoProxyHelper;

	public BoardDaoProxy(DaoProxyHelper daoProxyHelper) {
		this.daoProxyHelper = daoProxyHelper;
	}

	@Override
	public int insert(Board board) throws Exception {

		return (int) daoProxyHelper.request((out, in) -> {
				out.writeUTF("/board/add");
				out.writeObject(board);
				out.flush();

				String response = in.readUTF();
				if (response.equals("FAIL")) {
					throw new Exception(in.readUTF());
				}
				return 1;
			});
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Board> findAll() throws Exception {
		return (List<Board>) daoProxyHelper.request((out, in) -> {
			out.writeUTF("/board/list");
			out.flush();

			String response = in.readUTF();
			if (response.equals("FAIL")) {
				throw new Exception(in.readUTF());
			}

			return (List<Board>) in.readObject();
		});
	}

	@Override
	public Board findByNo(int no) throws Exception {
		return (Board) daoProxyHelper.request((out, in) -> {
			out.writeUTF("/board/detail");
			out.writeInt(no);
			out.flush();

			String response = in.readUTF();
			if (response.equals("FAIL")) {
				throw new Exception(in.readUTF());
			}
			return (Board) in.readObject();
		});
	}

	@Override
	public int update(Board newBoard) throws Exception {
		return (int) daoProxyHelper.request((out, in) -> {
			out.writeUTF("/board/update");
			out.writeObject(newBoard);
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
			out.writeUTF("/board/delete");
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
