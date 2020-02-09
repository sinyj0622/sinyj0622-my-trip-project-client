package sinyj0622.mytrip.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import sinyj0622.mytrip.domain.Plan;
import sinyj0622.util.Prompt;

public class PlanUpdateCommand implements Command {

	ObjectOutputStream out;
	ObjectInputStream in;

	Prompt prompt;

	public PlanUpdateCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
		this.out = out;
		this.in = in;
		this.prompt = prompt;
	}

	public void execute() {
		try {
			int no = prompt.inputInt("번호? ");

			out.writeUTF("/plan/detail");
			out.writeInt(no);
			out.flush();

			String response = in.readUTF();
			if (response.equals("FAIL")) {
				System.out.println(in.readUTF());
			}

			Plan oldPlan = (Plan) in.readObject();
			Plan newPlan = new Plan();

			String inputStr = null;
			newPlan.setNo(oldPlan.getNo());

			newPlan.setTravelTitle(prompt.inputString(String.format("여행제목(%s): ", oldPlan.getTravelTitle()),
					oldPlan.getTravelTitle()));

			newPlan.setDestnation(prompt.inputString(String.format("어디로 떠나세요(%s): ", oldPlan.getDestnation()),
					oldPlan.getDestnation()));

			newPlan.setPerson(
					prompt.inputString(String.format("여행인원(%s): ", oldPlan.getPerson()), oldPlan.getPerson()));

			newPlan.setStartDate(
					prompt.inputString(String.format("여행 시작일 (%s): ", oldPlan.getStartDate()), oldPlan.getStartDate()));

			newPlan.setEndDate(
					prompt.inputString(String.format("여행 종료일 (%s): ", oldPlan.getEndDate()), oldPlan.getEndDate()));

			newPlan.setPerson(prompt.inputString(String.format("여행인원(%s): ", oldPlan.getTravelMoney()),
					oldPlan.getTravelMoney()));

			newPlan.setTravelMoney(
					prompt.inputString(String.format("예상 경비(%s): ", oldPlan.getPerson()), oldPlan.getPerson()));

			if (oldPlan.equals(newPlan)) {
				System.out.println("여행계획 변경을 취소하였습니다.");
				return;
			}

			out.writeUTF("/plan/update");
			out.writeObject(newPlan);
			out.flush();

			response = in.readUTF();
			if (response.equals("FAIL")) {
				System.out.println(in.readUTF());
			}

			System.out.println("여행계획을 변경하였습니다.");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("통신 오류!");
		}
	}

}
