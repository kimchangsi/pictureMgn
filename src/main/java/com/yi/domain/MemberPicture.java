package com.yi.domain;

public class MemberPicture {
	private String id;
	private String name;
	private String email;
	private String tel;
	private String pass;
	
	
	public MemberPicture() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MemberPicture(String id) {
		super();
		this.id = id;
	}
	
	public MemberPicture(String id, String name, String email, String tel, String pass) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.tel = tel;
		this.pass = pass;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	@Override
	public String toString() {
		return "MemberPicture [id=" + id + ", name=" + name + ", email=" + email + ", tel=" + tel + ", pass=" + pass
				+ "]";
	}
	
	
}
