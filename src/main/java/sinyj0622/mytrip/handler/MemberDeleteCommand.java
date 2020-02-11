package sinyj0622.mytrip.handler;

import sinyj0622.mytrip.dao.MemberDao;
import sinyj0622.util.Prompt;

public class MemberDeleteCommand implements Command {


  MemberDao memberDao;
  Prompt prompt;

  public MemberDeleteCommand(MemberDao memberDao, Prompt prompt) {
    this.memberDao = memberDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {

    try {
      int no = prompt.inputInt("번호? ");

      if (memberDao.delete(no) == 1) {
        System.out.println("삭제했습니다.");

      }

    } catch (Exception e) {
      System.out.println("삭제 실패!");
    }
  }
}
