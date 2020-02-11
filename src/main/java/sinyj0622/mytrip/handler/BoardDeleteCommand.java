package sinyj0622.mytrip.handler;

import sinyj0622.mytrip.dao.BoardDao;
import sinyj0622.util.Prompt;

public class BoardDeleteCommand implements Command {

  BoardDao boardDao;
  Prompt prompt;

  public BoardDeleteCommand(BoardDao boardDao, Prompt prompt) {
    this.boardDao = boardDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {

    try {
      int no = prompt.inputInt("번호? ");

      if (boardDao.delete(no) == 1) {
        System.out.println("삭제했습니다.");
      }

    } catch (Exception e) {
      System.out.println("삭제 실패!");
    }
  }
}
