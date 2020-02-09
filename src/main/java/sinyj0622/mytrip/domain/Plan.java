package sinyj0622.mytrip.domain;

import java.io.Serializable;

public class Plan implements Serializable {

	private static final long serialVersionUID = 20200209L;
	
	private int no;
	private String Destnation;
	private String travelTitle;
	private String person;
	private String startDate;
	private String endDate;
	private String travelMoney;
	
	
	public static Plan valueOf(String csv) {
		String[] data = csv.split(",");

		Plan plan = new Plan();
		plan.setNo(Integer.parseInt(data[0]));
		plan.setDestnation(data[1]);
		plan.setTravelTitle(data[2]);
		plan.setPerson(data[3]);
		plan.setStartDate(data[4]);
		plan.setEndDate(data[5]);
		plan.setTravelMoney(data[6]);
		
		return plan;
	}
	
	public String toCsvString() {
		 return String.format("%d,%s,%s,%s,%s,%s,%s", this.getNo(),this.getDestnation(),
				 this.getTravelTitle(), this.getPerson(), this.getStartDate(), this.getEndDate(),
				 this.getTravelMoney());
	}
	
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public String getDestnation() {
    return Destnation;
  }
  public void setDestnation(String destnation) {
    Destnation = destnation;
  }
  public String getTravelTitle() {
    return travelTitle;
  }
  public void setTravelTitle(String travelTitle) {
    this.travelTitle = travelTitle;
  }
  public String getPerson() {
    return person;
  }
  public void setPerson(String person) {
    this.person = person;
  }
  public String getStartDate() {
    return startDate;
  }
  public void setStartDate(String startDate) {
    this.startDate = startDate;
  }
  public String getEndDate() {
    return endDate;
  }
  public void setEndDate(String endDate) {
    this.endDate = endDate;
  }
  public String getTravelMoney() {
    return travelMoney;
  }
  public void setTravelMoney(String travelMoney) {
    this.travelMoney = travelMoney;
  }

	
	

	
	
}
