package sinyj0622.mytrip.dao;

import java.util.List;
import sinyj0622.mytrip.domain.Plan;

public interface PlanDao {

  public int insert(Plan plan) throws Exception;

  public List<Plan> findAll() throws Exception;

  public Plan findByNo(int no) throws Exception;

  public int update(Plan plan) throws Exception;

  public int delete(int no) throws Exception;

}
