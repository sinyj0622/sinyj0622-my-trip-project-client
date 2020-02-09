package sinyj0622.mytrip.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import sinyj0622.util.Prompt;

public class MemberDeleteCommand implements Command {

	ObjectOutputStream out;
	ObjectInputStream in;

	Prompt prompt;

	public MemberDeleteCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
		this.out = out;
		this.in = in;
		this.prompt = prompt;
	}

	@Override
	public void execute() {

		try {
			int no = prompt.inputInt("번호? ");
			
			out.writeUTF("/member/delete");
			out.writeInt(no);
			out.flush();

			String response = in.readUTF();
			if (response.equals("FAIL")) {
				System.out.println(in.readUTF());
				return;
			}

			System.out.println("회원 정보를 삭제했습니다.");
		} catch (Exception e) {
			System.out.println("통신 오류!");
		}
	}
}
