package sinyj0622.mytrip.handler;

import java.sql.Date;
import sinyj0622.mytrip.dao.BoardDao;
import sinyj0622.mytrip.domain.Board;
import sinyj0622.util.Prompt;

public class BoardAddCommand implements Command {

  BoardDao boardDao;
  Prompt prompt;

  public BoardAddCommand(BoardDao boardDao, Prompt prompt) {
    this.boardDao = boardDao;
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
      if (boardDao.insert(board) == 1) {
        System.out.println("저장하였습니다.");
      }

    } catch (Exception e) {
      System.out.println("저장 실패: ");
      e.printStackTrace();
    }



  }

}
