package sinyj0622.mytrip.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;

import sinyj0622.mytrip.domain.Board;
import sinyj0622.util.Prompt;

public class BoardAddCommand implements Command {

	ObjectOutputStream out;
	ObjectInputStream in;

	Prompt prompt;

	public BoardAddCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
		this.out = out;
		this.in = in;
		this.prompt = prompt;
	}

	@Override
	public void execute() {
		Board board = new Board();
		board.setNo(prompt.inputInt("번호: "));
		board.setText(prompt.inputString("내용: "));
		board.setDate(new Date(System.currentTimeMillis()));
		board.setViewCount(0);

		try {
			out.writeUTF("/board/add");
			out.writeObject(board);
			out.flush();
			
			String response = in.readUTF();
			if (response.equals("FAIL")) {
				System.out.println(in.readUTF());
				return;
			}
			
			System.out.println("게시글을 저장하였습니다.");
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("통신 오류!");
		}
	}

}
