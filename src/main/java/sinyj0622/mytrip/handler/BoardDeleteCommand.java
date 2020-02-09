package sinyj0622.mytrip.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import sinyj0622.util.Prompt;

public class BoardDeleteCommand implements Command {

	ObjectOutputStream out;
	ObjectInputStream in;

	Prompt prompt;

	public BoardDeleteCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
		this.out = out;
		this.in = in;
		this.prompt = prompt;
	}

	@Override
	public void execute() {

		try {
			int no = prompt.inputInt("번호? ");
			
			out.writeUTF("/board/delete");
			out.writeInt(no);
			out.flush();

			String response = in.readUTF();
			if (response.equals("FAIL")) {
				System.out.println(in.readUTF());
				return;
			}

			System.out.println("게시글을 삭제했습니다.");
		} catch (Exception e) {
			System.out.println("통신 오류!");
		}
	}
}
