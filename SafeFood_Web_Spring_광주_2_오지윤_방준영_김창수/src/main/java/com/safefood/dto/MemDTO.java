package com.safefood.dto;

public class MemDTO {
	private String id;
	private String password;
	private String mname;
	private String addr;
	private String tel;
	private String allergy;
	private String question;
	private String answer;
	private String[] allergyArr;
	public String[] getAllergyArr() {
		return allergyArr;
	}

	public void setAllergyArr(String[] allergyArr) {
		this.allergyArr = allergyArr;
	}

	public MemDTO(String id, String password, String mname, String addr, String tel, String allergy, String question,
			String answer, String[] allergyArr) {
		super();
		this.id = id;
		this.password = password;
		this.mname = mname;
		this.addr = addr;
		this.tel = tel;
		this.allergy = allergy;
		this.question = question;
		this.answer = answer;
		this.allergyArr = allergyArr;
	}

	public MemDTO() {
		super();
	}

	public MemDTO(String id, String password, String mname, String addr, String tel, String allergy, String question,
			String answer) {
		super();
		this.id = id;
		this.password = password;
		this.mname = mname;
		this.addr = addr;
		this.tel = tel;
		this.allergy = allergy;
		this.question = question;
		this.answer = answer;
	}

	public MemDTO(String id, String password, String mname, String addr, String tel, String allergy) {
		super();
		this.id = id;
		this.password = password;
		this.mname = mname;
		this.addr = addr;
		this.tel = tel;
		this.allergy = allergy;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAllergy() {
		return allergy;
	}

	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "MemVo [id=" + id + ", password=" + password + ", mname=" + mname + ", addr=" + addr + ", tel=" + tel
				+ ", allergy=" + allergy + ", question=" + question + ", answer=" + answer + "]";
	}

	

	
	
}
