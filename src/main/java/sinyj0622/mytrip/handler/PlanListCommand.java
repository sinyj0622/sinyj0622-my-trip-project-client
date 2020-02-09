package sinyj0622.mytrip.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import sinyj0622.mytrip.domain.Plan;

public class PlanListCommand implements Command {

	ObjectOutputStream out;
	ObjectInputStream in;

	public PlanListCommand(ObjectOutputStream out, ObjectInputStream in) {
		this.out = out;
		this.in = in;
	}

	public void execute() {
		try {
			out.writeUTF("/plan/list");
			out.flush();

			String response = in.readUTF();
			if (response.equals("FAIL")) {
				System.out.println(in.readUTF());
			}

			List<Plan> plan = (List<Plan>) in.readObject();
			for (Plan p : plan) {
				System.out.printf("%d, %s, %s, %s ~ %s\n", p.getNo(), p.getTravelTitle(), p.getDestnation(),
						p.getStartDate(), p.getEndDate());
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("통신 오류!");
		}
	}

}
