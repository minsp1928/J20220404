package dao;

import java.util.Date;

public class Admin {
	private String admin_id;
	private String admin_ps;
	private Date admin_date;
	private String admin_ip;
	
	public String getAdmin_id() {
		return admin_id;
	}
	public void setAdmin_id(String admin_id) {
		this.admin_id = admin_id;
	}
	public String getAdmin_ps() {
		return admin_ps;
	}
	public void setAdmin_ps(String admin_ps) {
		this.admin_ps = admin_ps;
	}
	public Date getAdmin_date() {
		return admin_date;
	}
	public void setAdmin_date(Date admin_date) {
		this.admin_date = admin_date;
	}
	public String getAdmin_ip() {
		return admin_ip;
	}
	public void setAdmin_ip(String admin_ip) {
		this.admin_ip = admin_ip;
	}
	
}
