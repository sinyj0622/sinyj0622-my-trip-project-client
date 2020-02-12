package sinyj0622.mytrip.handler;

import java.util.List;
import sinyj0622.mytrip.dao.BoardDao;
import sinyj0622.mytrip.domain.Board;

public class BoardListCommand implements Command {

  BoardDao boardDao;

  public BoardListCommand(BoardDao boardDao) {
    this.boardDao = boardDao;
  }

  @SuppressWarnings("unchecked")
  @Override
  public void execute() {

    try {

      List<Board> boards = boardDao.findAll();
      for (Board b : boards) {
        System.out.printf("%d, %s, %s, %d\n", b.getNo(), b.getText(), b.getDate(),
            b.getViewCount());
      }


    } catch (Exception e) {
      System.out.println("조회 실패!");
    }
  }

}
