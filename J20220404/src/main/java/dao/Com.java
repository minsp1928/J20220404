package dao;

import java.util.Date;

public class Com {
	private int num;
	private String user_id;
	private int com_type;
	private String subject;
	private String content;
	private String poto;
	private int count;
	private int ref;
	private int re_step;
	private int re_level;
	private Date reg_date;
	private int reco;
	private String re_content;
	private String re_max_content;
    private String re_user_id;
	private int h_seq;
	private String hsre_content;
	private String com_type_name;
	
    public String getCom_type_name() {
		return com_type_name;
	}
	public void setCom_type_name(String com_type_name) {
		this.com_type_name = com_type_name;
	}
	public String getRe_max_content() {
		return re_max_content;
	}
	public void setRe_max_content(String re_max_content) {
		this.re_max_content = re_max_content;
	}
	public String getRe_user_id() {
		return re_user_id;
	}
	public void setRe_user_id(String re_user_id) {
		this.re_user_id = re_user_id;
	}

	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getCom_type() {
		return com_type;
	}
	public void setCom_type(int com_type) {
		this.com_type = com_type;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPoto() {
		return poto;
	}
	public void setPoto(String poto) {
		this.poto = poto;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getRe_step() {
		return re_step;
	}
	public void setRe_step(int re_step) {
		this.re_step = re_step;
	}
	public int getRe_level() {
		return re_level;
	}
	public void setRe_level(int re_level) {
		this.re_level = re_level;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public int getReco() {
		return reco;
	}
	public void setReco(int reco) {
		this.reco = reco;
	}
	public String getRe_content() {
		return re_content;
	}
	public void setRe_content(String re_content) {
		this.re_content = re_content;
	}
	public int getH_seq() {
		return h_seq;
	}
	public void setH_seq(int h_seq) {
		this.h_seq = h_seq;
	}
	public String getHsre_content() {
		return hsre_content;
	}
	public void setHsre_content(String hsre_content) {
		this.hsre_content = hsre_content;
	}

}
