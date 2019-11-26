package com.safefood.dto;

public class CommentDTO {

	int cnum;
	int qnum;
	String cdesc;
	String id;
	String cdate;
	public int getCnum() {
		return cnum;
	}
	public void setCnum(int cnum) {
		this.cnum = cnum;
	}
	public int getQnum() {
		return qnum;
	}
	public void setQnum(int qnum) {
		this.qnum = qnum;
	}
	public String getCdesc() {
		return cdesc;
	}
	public void setCdesc(String cdesc) {
		this.cdesc = cdesc;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getCdate() {
		return cdate;
	}
	public void setCdate(String cdate) {
		this.cdate = cdate;
	}
	@Override
	public String toString() {
		return "CommentDTO [cnum=" + cnum + ", qnum=" + qnum + ", cdesc=" + cdesc + ", id=" + id + ", cdate=" + cdate
				+ "]";
	}
	public CommentDTO(int cnum, int qnum, String cdesc, String id, String cdate) {
		super();
		this.cnum = cnum;
		this.qnum = qnum;
		this.cdesc = cdesc;
		this.id = id;
		this.cdate = cdate;
	}
	
	
}
