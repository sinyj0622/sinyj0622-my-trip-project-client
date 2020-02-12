package sinyj0622.mytrip.handler;

import java.util.List;
import sinyj0622.mytrip.dao.PlanDao;
import sinyj0622.mytrip.domain.Plan;

public class PlanListCommand implements Command {

  PlanDao planDao;

  public PlanListCommand(PlanDao planDao) {
    this.planDao = planDao;
  }

  @Override
  public void execute() {
    try {

      List<Plan> plan = planDao.findAll();
      for (Plan p : plan) {
        System.out.printf("%d, %s, %s, %s ~ %s\n", p.getNo(), p.getTravelTitle(), p.getDestnation(),
            p.getStartDate(), p.getEndDate());
      }
    } catch (Exception e) {
      System.out.println("조회 실패!");
    }
  }

}
