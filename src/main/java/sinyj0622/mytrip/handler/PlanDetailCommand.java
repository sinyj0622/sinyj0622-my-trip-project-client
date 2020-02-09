package sinyj0622.mytrip.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import sinyj0622.mytrip.domain.Plan;
import sinyj0622.util.Prompt;

public class PlanDetailCommand implements Command {

	ObjectOutputStream out;
	ObjectInputStream in;

	Prompt prompt;

	public PlanDetailCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
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

			Plan p = (Plan) in.readObject();
			System.out.printf("번호: %d\n", p.getNo());
			System.out.printf("여행 제목: %s\n", p.getTravelTitle());
			System.out.printf("어디로 떠나세요: %s\n", p.getDestnation());
			System.out.printf("여행인원: %s\n", p.getPerson());
			System.out.printf("여행 기간: %s ~ %s\n", p.getStartDate(), p.getEndDate());
			System.out.printf("예상 경비: %s\n", p.getTravelMoney());

			System.out.println();
		} catch (Exception e) {
			System.out.println("통신 오류!");
			e.printStackTrace();
		}
	}
}
