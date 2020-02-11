package sinyj0622.mytrip.handler;

import java.sql.Date;
import sinyj0622.mytrip.dao.BoardDao;
import sinyj0622.mytrip.domain.Board;
import sinyj0622.util.Prompt;

public class BoardUpdateCommand implements Command {

  BoardDao boardDao;
  Prompt prompt;

  public BoardUpdateCommand(BoardDao boardDao, Prompt prompt) {
    this.boardDao = boardDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");

      Board oldBoard = boardDao.findByNo(no);
      Board newBoard = new Board();

      newBoard.setNo(oldBoard.getNo());
      newBoard.setViewCount(oldBoard.getViewCount());
      newBoard.setDate(new Date(System.currentTimeMillis()));
      newBoard.setText(prompt.inputString(String.format("내용(%s)? ", oldBoard.getText())));

      if (newBoard.equals(oldBoard)) {
        System.out.println("변경을 취소했습니다.");
        return;
      }

      if (boardDao.update(newBoard) == 1) {
        System.out.println("변경했습니다.");
      }

    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("변경 실패!");
    }
  }

}
