package com.safefood.dto;

public class QnaDTO {
int qnum;
String qtitle;
String qdate;
String qdesc;
String id;
public int getQnum() {
	return qnum;
}
public void setQnum(int qnum) {
	this.qnum = qnum;
}
public String getQtitle() {
	return qtitle;
}
public void setQtitle(String qtitle) {
	this.qtitle = qtitle;
}
public String getQdate() {
	return qdate;
}
public void setQdate(String qdate) {
	this.qdate = qdate;
}
public String getQdesc() {
	return qdesc;
}
public void setQdesc(String qdesc) {
	this.qdesc = qdesc;
}
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
@Override
public String toString() {
	return "QnaDTO [qnum=" + qnum + ", qtitle=" + qtitle + ", qdate=" + qdate + ", qdesc=" + qdesc + ", id=" + id + "]";
}
public QnaDTO(int qnum, String qtitle, String qdate, String qdesc, String id) {
	super();
	this.qnum = qnum;
	this.qtitle = qtitle;
	this.qdate = qdate;
	this.qdesc = qdesc;
	this.id = id;
}
public QnaDTO(int qnum, String qtitle,  String qdesc, String id) {
	super();
	this.qnum = qnum;
	this.qtitle = qtitle;
	
	this.qdesc = qdesc;
	this.id = id;
}



}
