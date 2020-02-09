package sinyj0622.mytrip.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import sinyj0622.mytrip.domain.Plan;
import sinyj0622.util.Prompt;

public class PlanAddCommand implements Command {

	ObjectOutputStream out;
	ObjectInputStream in;

	Prompt prompt;

	public PlanAddCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
		this.out = out;
		this.in = in;
		this.prompt = prompt;
	}

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
			out.writeUTF("/plan/add");
			out.writeObject(travelPlan);
			out.flush();

		 String response = in.readUTF();
		 if (response.equals("FAIL")) {
			 System.out.println(in.readUTF());
		 }
		 
		 
			System.out.println("여행 계획을 저장하였습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("통신 오류!");
		}
	}

}
