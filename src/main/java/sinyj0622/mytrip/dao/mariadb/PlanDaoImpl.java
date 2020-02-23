package sinyj0622.mytrip.dao.mariadb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import sinyj0622.mytrip.dao.BoardDao;
import sinyj0622.mytrip.dao.PlanDao;
import sinyj0622.mytrip.domain.Board;
import sinyj0622.mytrip.domain.Member;
import sinyj0622.mytrip.domain.Plan;

public class PlanDaoImpl implements PlanDao {

	Connection con;

	public PlanDaoImpl(Connection con) {
		this.con = con;
	}

	@Override
	public int insert(Plan plan) throws Exception {
		try (Statement stmt = con.createStatement()){
			int result = stmt.executeUpdate("insert into mytrip_plan(spot, titl, person, sdt, edt, money) values('" + plan.getDestnation()
					+ "','" + plan.getTravelTitle()
					+ "','" + plan.getPerson()
					+ "','" + plan.getStartDate()
					+ "','" + plan.getEndDate()
					+ "','" + plan.getTravelMoney()
					+ "')");

			return result;
		}
	}

	@Override
	public List<Plan> findAll() throws Exception {
		try (Statement stmt = con.createStatement()){
			ResultSet rs = stmt.executeQuery("select * from mytrip_plan");

			ArrayList<Plan> list = new ArrayList<>();

			while(rs.next()) {
				Plan plan = new Plan();
				plan.setNo(rs.getInt("plan_id"));
				plan.setDestnation(rs.getString("spot"));
				plan.setTravelTitle(rs.getString("titl"));
				plan.setPerson(rs.getString("person"));
				plan.setStartDate(rs.getString("sdt"));
				plan.setEndDate(rs.getString("edt"));
				plan.setTravelMoney(rs.getString("money"));
				list.add(plan);
			}
			return list;
		}
	}

	@Override
	public Plan findByNo(int no) throws Exception {
		try (Statement stmt = con.createStatement()){
			ResultSet rs = stmt.executeQuery("select * from mytrip_plan where plan_id=" + no );

			if (rs.next()) {
				Plan plan = new Plan();
				plan.setNo(rs.getInt("plan_id"));
				plan.setTravelTitle(rs.getString("titl"));
				plan.setDestnation(rs.getString("spot"));
				plan.setPerson(rs.getString("person"));
				plan.setStartDate(rs.getString("sdt"));
				plan.setEndDate(rs.getString("edt"));
				plan.setTravelMoney(rs.getString("money"));
				return plan;
			} else {
				return null;
			}
		}
	}

	@Override
	public int update(Plan plan) throws Exception {
		try (Statement stmt = con.createStatement()){

			int result = stmt.executeUpdate("update mytrip_plan set titl='" + plan.getTravelTitle()
					+ "',spot='" + plan.getDestnation()
					+ "',person='" + plan.getPerson()
					+ "',sdt='" + plan.getStartDate()
					+ "',edt='" + plan.getEndDate()
					+ "',money='" + plan.getTravelMoney()
					+ "' where plan_id=" + plan.getNo());

			return result;
		}
	}

	@Override
	public int delete(int no) throws Exception {
		try (Statement stmt = con.createStatement()){
			int result = stmt.executeUpdate("delete from mytrip_plan where plan_id=" + no);
			return result;
		}
	}

	
}
