package sinyj0622.mytrip.handler;

import java.util.List;
import sinyj0622.mytrip.dao.MemberDao;
import sinyj0622.mytrip.domain.Member;

public class MemberListCommand implements Command {


  MemberDao memberDao;

  public MemberListCommand(MemberDao memberDao) {
    this.memberDao = memberDao;
  }


  @Override
  @SuppressWarnings("unchecked")
  public void execute() {
    try {

      List<Member> member = memberDao.findAll();
      for (Member m : member) {
        System.out.printf("%d, %s, %s, %s, %s\n", m.getNo(), m.getName(), m.getEmail(),
            m.getPhonenumber(), m.getRegisteredDate());
      }
    } catch (Exception e) {
      System.out.println("조회 실패!");
    }
  }


}
