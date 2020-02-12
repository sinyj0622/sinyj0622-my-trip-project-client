package sinyj0622.mytrip.handler;

import sinyj0622.mytrip.dao.PlanDao;
import sinyj0622.mytrip.domain.Plan;
import sinyj0622.util.Prompt;

public class PlanUpdateCommand implements Command {

  PlanDao planDao;
  Prompt prompt;

  public PlanUpdateCommand(PlanDao planDao, Prompt prompt) {
    this.planDao = planDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");

      Plan oldPlan = planDao.findByNo(no);
      Plan newPlan = new Plan();

      String inputStr = null;
      newPlan.setNo(oldPlan.getNo());

      newPlan.setTravelTitle(prompt.inputString(
          String.format("여행제목(%s): ", oldPlan.getTravelTitle()), oldPlan.getTravelTitle()));

      newPlan.setDestnation(prompt.inputString(
          String.format("어디로 떠나세요(%s): ", oldPlan.getDestnation()), oldPlan.getDestnation()));

      newPlan.setPerson(prompt.inputString(String.format("여행인원(%s): ", oldPlan.getPerson()),
          oldPlan.getPerson()));

      newPlan.setStartDate(prompt.inputString(
          String.format("여행 시작일 (%s): ", oldPlan.getStartDate()), oldPlan.getStartDate()));

      newPlan.setEndDate(prompt.inputString(String.format("여행 종료일 (%s): ", oldPlan.getEndDate()),
          oldPlan.getEndDate()));

      newPlan.setPerson(prompt.inputString(String.format("여행인원(%s): ", oldPlan.getTravelMoney()),
          oldPlan.getTravelMoney()));

      newPlan.setTravelMoney(prompt.inputString(String.format("예상 경비(%s): ", oldPlan.getPerson()),
          oldPlan.getPerson()));

      if (oldPlan.equals(newPlan)) {
        System.out.println("변경을 취소하였습니다.");
        return;
      }

      if (planDao.update(newPlan) == 1) {
        System.out.println("변경하였습니다.");

      }


    } catch (Exception e) {
      System.out.println("변경 실패!");
    }
  }

}
