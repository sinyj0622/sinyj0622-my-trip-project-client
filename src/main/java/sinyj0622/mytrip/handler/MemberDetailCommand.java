package sinyj0622.mytrip.handler;

import sinyj0622.mytrip.dao.MemberDao;
import sinyj0622.mytrip.domain.Member;
import sinyj0622.util.Prompt;

public class MemberDetailCommand implements Command {


  MemberDao memberDao;
  Prompt prompt;

  public MemberDetailCommand(MemberDao memberDao, Prompt prompt) {
    this.memberDao = memberDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {

    try {
      int no = prompt.inputInt("번호? ");

      Member member = memberDao.findByNo(no);
      System.out.printf("번호: %d\n", member.getNo());
      System.out.printf("이름: %s\n", member.getName());
      System.out.printf("별명: %s\n", member.getNickname());
      System.out.printf("암호: %s\n", member.getPassWord());
      System.out.printf("이메일: %s\n", member.getEmail());
      System.out.printf("사진: %s\n", member.getMyphoto());
      System.out.printf("전화: %s\n", member.getPhonenumber());
      System.out.printf("등록일: %s\n", member.getRegisteredDate());

    } catch (Exception e) {
      System.out.println("조회 실패");
    }
  }

}
