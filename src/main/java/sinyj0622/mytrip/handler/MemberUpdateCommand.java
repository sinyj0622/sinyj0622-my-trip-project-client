package sinyj0622.mytrip.handler;

import sinyj0622.mytrip.dao.MemberDao;
import sinyj0622.mytrip.domain.Member;
import sinyj0622.util.Prompt;

public class MemberUpdateCommand implements Command {


  MemberDao memberDao;
  Prompt prompt;

  public MemberUpdateCommand(MemberDao memberDao, Prompt prompt) {
    this.memberDao = memberDao;
    this.prompt = prompt;
  }

  @Override
  public void execute() {
    try {
      int no = prompt.inputInt("번호? ");


      Member oldMember = memberDao.findByNo(no);
      Member newMember = new Member();

      String inputStr = null;
      newMember.setNo(oldMember.getNo());

      newMember.setName(
          prompt.inputString(String.format("이름(%s)?", oldMember.getName()), oldMember.getName()));

      newMember.setNickname(prompt.inputString(String.format("별명(%s)?", oldMember.getNickname()),
          oldMember.getNickname()));

      newMember.setPassWord(prompt.inputString(String.format("암호(%s)?", oldMember.getPassWord()),
          oldMember.getPassWord()));

      newMember.setEmail(prompt.inputString(String.format("이메일(%s)?", oldMember.getEmail()),
          oldMember.getEmail()));

      newMember.setMyphoto(prompt.inputString(String.format("사진(%s)?", oldMember.getMyphoto()),
          oldMember.getMyphoto()));

      newMember.setPhonenumber(prompt.inputString(
          String.format("전화(%s)?", oldMember.getPhonenumber()), oldMember.getPhonenumber()));

      if (oldMember.equals(newMember)) {
        System.out.println("변경을 취소하였습니다.");
        return;
      }

      if (memberDao.update(newMember) == 1) {
        System.out.println("변경했습니다.");

      }

    } catch (Exception e) {
      System.out.println("변경 실패!");
    }
  }

}
