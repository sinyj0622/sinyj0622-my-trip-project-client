package sinyj0622.mytrip.dao;

import java.util.List;
import sinyj0622.mytrip.domain.Member;

public interface MemberDao {

  public int insert(Member member) throws Exception;

  public List<Member> findAll() throws Exception;

  public Member findByNo(int no) throws Exception;

  public int update(Member member) throws Exception;

  public int delete(int no) throws Exception;

}
