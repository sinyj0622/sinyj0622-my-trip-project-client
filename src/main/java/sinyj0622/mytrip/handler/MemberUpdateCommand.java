package sinyj0622.mytrip.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import sinyj0622.mytrip.domain.Member;
import sinyj0622.util.Prompt;

public class MemberUpdateCommand implements Command {

	ObjectOutputStream out;
	ObjectInputStream in;

	Prompt prompt;

	public MemberUpdateCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
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

			Member oldMember = (Member) in.readObject();
			Member newMember = new Member();

			String inputStr = null;
			newMember.setNo(oldMember.getNo());

			newMember.setName(prompt.inputString(String.format("이름(%s)?", oldMember.getName()), oldMember.getName()));

			newMember.setNickname(
					prompt.inputString(String.format("별명(%s)?", oldMember.getNickname()), oldMember.getNickname()));

			newMember.setPassWord(
					prompt.inputString(String.format("암호(%s)?", oldMember.getPassWord()), oldMember.getPassWord()));

			newMember.setEmail(
					prompt.inputString(String.format("이메일(%s)?", oldMember.getEmail()), oldMember.getEmail()));

			newMember.setMyphoto(
					prompt.inputString(String.format("사진(%s)?", oldMember.getMyphoto()), oldMember.getMyphoto()));

			newMember.setPhonenumber(prompt.inputString(String.format("전화(%s)?", oldMember.getPhonenumber()),
					oldMember.getPhonenumber()));

			if (oldMember.equals(newMember)) {
				System.out.println("회원 변경을 취소하였습니다.");
				return;
			}

			out.writeUTF("/member/update");
			out.writeObject(newMember);
			out.flush();

			response = in.readUTF();
			if (response.equals("FAIL")) {
				System.out.println(in.readUTF());
				return;
			}
			
			System.out.println("게시글을 변경했습니다.");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("통신 오류!");
		}
	}

}
