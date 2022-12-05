package org.vision.homebook;

import java.sql.Date;
import java.sql.Timestamp;
// 가계부 vo클래스 - 데이타베이스 HOMEBOOK 테이블의 한 레코드의 
// 정보를 담는 객체를 만들어 주는 클래스 
public class HomebookVO {
	private long serialno;//일련번호
	private Date day;//일자
	private String section;//수지구분
	private String accounttitle;//계정과목
	private String remark;//적요
	private int revenue;//수입
	private int expense;//지출 
	private String mem_id;//회원 아이디 
	public HomebookVO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public HomebookVO(long serialno, String section, String accounttitle, String remark, int revenue, int expense, String mem_id) {
		super();
		this.serialno = serialno;
		this.section = section;
		this.accounttitle = accounttitle;
		this.remark = remark;
		this.revenue = revenue;
		this.expense = expense;
		this.mem_id = mem_id;
	}	
	
	public HomebookVO(String section, String accounttitle, String remark, int revenue, int expense, String mem_id) {
		super();
		this.section = section;
		this.accounttitle = accounttitle;
		this.remark = remark;
		this.revenue = revenue;
		this.expense = expense;
		this.mem_id = mem_id;
	}

	public String getMem_id() {
		return mem_id;
	}
	public void setMem_id(String mem_id) {
		this.mem_id = mem_id;
	}
	public long getSerialno() {
		return serialno;
	}
	public void setSerialno(long serialno) {
		this.serialno = serialno;
	}
	public Date getDay() {
		return day;
	}
	public void setDay(Date day) {
		this.day = day;
	}
	public String getSection() {
		return section;
	}
	public void setSection(String section) {
		this.section = section;
	}
	public String getAccounttitle() {
		return accounttitle;
	}
	public void setAccounttitle(String accounttitle) {
		this.accounttitle = accounttitle;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public int getRevenue() {
		return revenue;
	}
	public void setRevenue(int revenue) {
		this.revenue = revenue;
	}
	public int getExpense() {
		return expense;
	}
	public void setExpense(int expense) {
		this.expense = expense;
	}
	@Override
	public String toString() {
		return "HomebookVO [serialno=" + serialno + ", day=" + day + ", section=" + section + ", accounttitle="
				+ accounttitle + ", remark=" + remark + ", revenue=" + revenue + ", expense=" + expense + ", mem_id="
				+ mem_id + "]";
	}
	

}
