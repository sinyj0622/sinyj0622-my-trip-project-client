package sinyj0622.mytrip.handler;

import sinyj0622.mytrip.dao.PlanDao;
import sinyj0622.mytrip.domain.Plan;
import sinyj0622.util.Prompt;

public class PlanAddCommand implements Command {

  PlanDao planDao;
  Prompt prompt;

  public PlanAddCommand(PlanDao planDao, Prompt prompt) {
    this.planDao = planDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    Plan travelPlan = new Plan();
    travelPlan.setNo(prompt.inputInt("번호: "));
    travelPlan.setTravelTitle(prompt.inputString("여행 제목? "));
    travelPlan.setDestnation(prompt.inputString("어디로 떠나세요?: "));
    travelPlan.setPerson(prompt.inputString("여행 인원? "));
    travelPlan.setStartDate(prompt.inputString("여행 시작일? "));
    travelPlan.setEndDate(prompt.inputString("여행 종료일? "));
    travelPlan.setTravelMoney(prompt.inputString("예상 경비? "));

    try {

      if (planDao.insert(travelPlan) == 1) {
        System.out.println("저장하였습니다.");
      }

    } catch (Exception e) {
      System.out.println("저장 실패!");
    }
  }

}
