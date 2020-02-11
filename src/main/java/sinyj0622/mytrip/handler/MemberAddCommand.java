package sinyj0622.mytrip.handler;

import java.sql.Date;
import sinyj0622.mytrip.dao.MemberDao;
import sinyj0622.mytrip.domain.Member;
import sinyj0622.util.Prompt;

public class MemberAddCommand implements Command {


  MemberDao memberDao;
  Prompt prompt;

  public MemberAddCommand(MemberDao memberDao, Prompt prompt) {
    this.memberDao = memberDao;
    this.prompt = prompt;
  }


  @Override
  public void execute() {


    Member member = new Member();
    member.setNo(prompt.inputInt("번호: "));
    member.setName(prompt.inputString("이름: "));
    member.setNickname(prompt.inputString("별명: "));
    member.setPassWord(prompt.inputString("암호: "));
    member.setEmail(prompt.inputString("이메일: "));
    member.setMyphoto(prompt.inputString("사진: "));
    member.setPhonenumber(prompt.inputString("전화: "));
    member.setRegisteredDate(new Date(System.currentTimeMillis()));

    try {
      if (memberDao.insert(member) == 1) {
        System.out.println("저장하였습니다.");
      }

    } catch (Exception e) {
      System.out.println("저장 실패!");
      e.printStackTrace();
    }
  }



}
