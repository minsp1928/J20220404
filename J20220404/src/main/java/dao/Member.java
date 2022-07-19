package dao;

import java.util.Date;

public class Member {
	private String user_id;
	private String user_ps;
	private String uname;
	private Long id_num;
	private String gender;
	private String user_picture;
	private String tel;
	private Date register_date;
	private String status;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_ps() {
		return user_ps;
	}
	public void setUser_ps(String user_ps) {
		this.user_ps = user_ps;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public Long getId_num() {
		return id_num;
	}
	public void setId_num(Long id_num) {
		this.id_num = id_num;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getUser_picture() {
		return user_picture;
	}
	public void setUser_picture(String user_picture) {
		this.user_picture = user_picture;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public Date getRegister_date() {
		return register_date;
	}
	public void setRegister_date(Date register_date) {
		this.register_date = register_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
