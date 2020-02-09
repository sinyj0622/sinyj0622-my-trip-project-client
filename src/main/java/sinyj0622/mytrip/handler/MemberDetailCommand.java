package sinyj0622.mytrip.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import sinyj0622.mytrip.domain.Member;
import sinyj0622.util.Prompt;

public class MemberDetailCommand implements Command {

	ObjectOutputStream out;
	ObjectInputStream in;

	Prompt prompt;

	public MemberDetailCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
		this.out = out;
		this.in = in;
		this.prompt = prompt;
	}

	public void execute() {

		try {
			int no = prompt.inputInt("번호? ");

			out.writeUTF("/member/detail");
			out.writeInt(no);
			out.flush();
			
			 String response = in.readUTF();
		      if (response.equals("FAIL")) {
		        System.out.println(in.readUTF());
		        return;
		      }
			Member member = (Member) in.readObject();
			System.out.printf("번호: %d\n", member.getNo());
			System.out.printf("이름: %s\n", member.getName());
			System.out.printf("별명: %s\n", member.getNickname());
			System.out.printf("암호: %s\n", member.getPassWord());
			System.out.printf("이메일: %s\n", member.getEmail());
			System.out.printf("사진: %s\n", member.getMyphoto());
			System.out.printf("전화: %s\n", member.getPhonenumber());
		} catch (Exception e) {
			System.out.println("통신 오류");
			e.printStackTrace();
		}
	}

}
