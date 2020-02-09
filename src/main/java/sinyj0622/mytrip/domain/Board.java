package sinyj0622.mytrip.domain;

import java.io.Serializable;
import java.sql.Date;

public class Board implements Serializable {

	private static final long serialVersionUID = 20200209L;
	
	private int no;
	private String text;
	private Date date;
	private int viewCount;

	public static Board valueOf(String csv) {
		String[] data = csv.split(",");

		Board board = new Board();
		board.setNo(Integer.parseInt(data[0]));
		board.setText(data[1]);
		board.setDate(Date.valueOf(data[2]));
		board.setViewCount(Integer.parseInt(data[3]));

		return board;
	}
	
	public String toCsvString() {
		 return String.format("%d,%s,%s,%d", this.getNo(), this.getText(), 
				 this.getDate(), this.getViewCount());
	}

	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getViewCount() {
		return viewCount;
	}
	public void setViewCount(int viewCount) {
		this.viewCount = viewCount;
	}
}
