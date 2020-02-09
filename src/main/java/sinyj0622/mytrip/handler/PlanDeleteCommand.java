package sinyj0622.mytrip.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import sinyj0622.mytrip.domain.Plan;
import sinyj0622.util.Prompt;

public class PlanDeleteCommand implements Command {

	ObjectOutputStream out;
	ObjectInputStream in;

	Prompt prompt;

	public PlanDeleteCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
		this.out = out;
		this.in = in;
		this.prompt = prompt;
	}

	public void execute() {
		try{
			int no = prompt.inputInt("번호? ");
		
		out.writeUTF("/plan/delete");
		out.writeInt(no);
		out.flush();
		
		String response = in.readUTF();
		if (response.equals("FAIL")) {
			System.out.println(in.readUTF());
			return;
		}
		
		System.out.println("여행계획을 삭제했습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("통신 오류!");
		}
	}

}
