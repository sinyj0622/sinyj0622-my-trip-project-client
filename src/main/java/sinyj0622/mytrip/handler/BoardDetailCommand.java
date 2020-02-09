package sinyj0622.mytrip.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import sinyj0622.mytrip.domain.Board;
import sinyj0622.util.Prompt;

public class BoardDetailCommand implements Command {

	ObjectOutputStream out;
	ObjectInputStream in;

	Prompt prompt;

	public BoardDetailCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
		this.out = out;
		this.in = in;
		this.prompt = prompt;
	}

	@Override
	public void execute() {
		try {
			int no = prompt.inputInt("번호? ");

			out.writeUTF("/board/detail");
			out.writeInt(no);
			out.flush();
			
			 String response = in.readUTF();
		      if (response.equals("FAIL")) {
		        System.out.println(in.readUTF());
		        return;
		      }
			
			Board board = (Board) in.readObject();
			System.out.printf("번호: %d\n", board.getNo());
			System.out.printf("내용: %s\n", board.getText());
			System.out.printf("등록일: %s\n", board.getDate());
			System.out.printf("조회수: %d\n", board.getViewCount());
		} catch (Exception e) {
			System.out.println("통신 오류!");
			e.printStackTrace();
		}
	}

}
