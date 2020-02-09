package sinyj0622.mytrip.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;

import sinyj0622.mytrip.domain.Board;
import sinyj0622.util.Prompt;

public class BoardUpdateCommand implements Command {

	ObjectOutputStream out;
	ObjectInputStream in;

	Prompt prompt;

	public BoardUpdateCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
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

			Board oldBoard = (Board) in.readObject();
			Board newBoard = new Board();

			newBoard.setNo(oldBoard.getNo());
			newBoard.setViewCount(oldBoard.getViewCount());
			newBoard.setDate(new Date(System.currentTimeMillis()));
			newBoard.setText(prompt.inputString(String.format("내용(%s)? ", oldBoard.getText())));

			if (newBoard.equals(oldBoard)) {
				System.out.println("게시글 변경을 취소했습니다.");
				return;
			}

			out.writeUTF("/board/update");
			out.writeObject(newBoard);
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
