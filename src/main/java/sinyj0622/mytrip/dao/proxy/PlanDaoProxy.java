package sinyj0622.mytrip.dao.proxy;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import sinyj0622.mytrip.dao.PlanDao;
import sinyj0622.mytrip.domain.Plan;

public class PlanDaoProxy implements PlanDao {

	DaoProxyHelper daoProxyHelper;

	public PlanDaoProxy(DaoProxyHelper daoProxyHelper) {
		this.daoProxyHelper = daoProxyHelper;
	}

	@Override
	public int insert(Plan travelPlan) throws Exception {
		return (int) daoProxyHelper.request((out, in) -> {
			out.writeUTF("/plan/add");
			out.writeObject(travelPlan);
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
	public List<Plan> findAll() throws Exception {
		return (List<Plan>) daoProxyHelper.request((out, in) -> {
			out.writeUTF("/plan/list");

			out.flush();

			String response = in.readUTF();
			if (response.equals("FAIL")) {
				throw new Exception(in.readUTF());
			}

			return (List<Plan>) in.readObject();
		});
	}

	@Override
	public Plan findByNo(int no) throws Exception {
		return (Plan) daoProxyHelper.request((out, in) -> {
			out.writeUTF("/plan/detail");
			out.writeInt(no);
			out.flush();


			String response = in.readUTF();
			if (response.equals("FAIL")) {
				throw new Exception(in.readUTF());
			}
			return (Plan) in.readObject();
		});
	}


	@Override
	public int update(Plan newPlan) throws Exception {
		return (int) daoProxyHelper.request((out, in) -> {

			out.writeUTF("/plan/update");
			out.writeObject(newPlan);
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

			out.writeUTF("/plan/delete");
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
