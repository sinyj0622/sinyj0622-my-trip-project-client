package sinyj0622.mytrip.handler;

import sinyj0622.mytrip.dao.PlanDao;
import sinyj0622.util.Prompt;

public class PlanDeleteCommand implements Command {

  PlanDao planDao;
  Prompt prompt;

  public PlanDeleteCommand(PlanDao planDao, Prompt prompt) {
    this.planDao = planDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");

      if (planDao.delete(no) == 1) {
        System.out.println("삭제했습니다.");

      }

    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("삭제 실패!");
    }
  }

}
