package sinyj0622.mytrip.handler;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;

import sinyj0622.mytrip.domain.Member;
import sinyj0622.util.Prompt;

public class MemberAddCommand implements Command{


	ObjectOutputStream out;
	ObjectInputStream in;

	Prompt prompt;

	public MemberAddCommand(ObjectOutputStream out, ObjectInputStream in, Prompt prompt) {
		this.out = out;
		this.in = in;
		this.prompt = prompt;
	}


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
    out.writeUTF("/member/add");
    out.writeObject(member);
    
    
    String response = in.readUTF();
	  if (response.equals("FAIL")) {
		  System.out.println(in.readUTF());
	  }
	  
    System.out.println("회원 정보를 저장하였습니다.");
    } catch (Exception e) {
    	System.out.println("통신 오류!");
    	e.printStackTrace();
    }
  }

 

}
