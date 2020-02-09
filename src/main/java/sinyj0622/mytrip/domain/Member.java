package sinyj0622.mytrip.domain;

import java.io.Serializable;
import java.sql.Date;

public class Member implements Serializable {
	
	private static final long serialVersionUID = 20200209L;
	
	private int no;
	private String name;
	private String nickname;
	private String email;
	private String passWord;
	private String myphoto;
	private String phonenumber;
	private Date registeredDate;    
	
	public static Member valueOf(String csv) {
		String[] data = csv.split(",");

		Member member = new Member();
		member.setNo(Integer.parseInt(data[0]));
		member.setName(data[1]);
		member.setNickname(data[2]);
		member.setEmail(data[3]);
		member.setPassWord(data[4]);
		member.setMyphoto(data[5]);
		member.setPhonenumber(data[6]);
		member.setRegisteredDate(Date.valueOf(data[7]));
		
		return member;
	}
	
	public String toCsvString() {
		return String.format("%d,%s,%s,%s,%s,%s,%s,%s", this.getNo(), this.getName(),
				this.getNickname(), this.getEmail(), this.getPassWord(), this.getMyphoto(),
				this.getPhonenumber(), this.getRegisteredDate());
	}
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public String getMyphoto() {
		return myphoto;
	}
	public void setMyphoto(String myphoto) {
		this.myphoto = myphoto;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public Date getRegisteredDate() {
		return registeredDate;
	}
	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}
}
