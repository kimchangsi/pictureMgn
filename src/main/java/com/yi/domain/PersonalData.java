package com.yi.domain;

import java.util.Date;

public class PersonalData {
	private int no;
	private String id;
	private Date regdate;
	private String file;
	private String originFile;
	private String originFileName;

	public PersonalData() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getOriginFile() {
		return originFile;
	}


	public void setOriginFile(String originFile) {
		this.originFile = originFile;
	}
	
	
	public String getOriginFileName() {
		return originFileName;
	}
	public void setOriginFileName(String originFileName) {
		this.originFileName = originFileName;
	}
	public PersonalData(int no) {
		super();
		this.no = no;
	}


	public PersonalData(String id) {
		super();
		this.id = id;
	}


	public PersonalData(String id, Date regdate, String file) {
		super();
		this.id = id;
		this.regdate = regdate;
		this.file = file;
	}


	public int getNo() {
		return no;
	}


	public void setNo(int no) {
		this.no = no;
	}


	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}


	@Override
	public String toString() {
		return "PersonalData [no=" + no + ", id=" + id + ", regdate=" + regdate + ", file=" + file + "]";
	}



	
	
}
