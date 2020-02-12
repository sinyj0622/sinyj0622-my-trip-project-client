package sinyj0622.mytrip.handler;

import sinyj0622.mytrip.dao.PlanDao;
import sinyj0622.mytrip.domain.Plan;
import sinyj0622.util.Prompt;

public class PlanDetailCommand implements Command {

  PlanDao planDao;
  Prompt prompt;

  public PlanDetailCommand(PlanDao planDao, Prompt prompt) {
    this.planDao = planDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");

      Plan p = planDao.findByNo(no);
      System.out.printf("번호: %d\n", p.getNo());
      System.out.printf("여행 제목: %s\n", p.getTravelTitle());
      System.out.printf("어디로 떠나세요: %s\n", p.getDestnation());
      System.out.printf("여행인원: %s\n", p.getPerson());
      System.out.printf("여행 기간: %s ~ %s\n", p.getStartDate(), p.getEndDate());
      System.out.printf("예상 경비: %s\n", p.getTravelMoney());

      System.out.println();
    } catch (Exception e) {
      System.out.println("조회 실패!");
    }
  }
}
