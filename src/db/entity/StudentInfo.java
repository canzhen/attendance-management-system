package db.entity;

public class StudentInfo {
	private String sno;
	private String sname;
	private Integer absenceNum;	
	
	public String getSno() {
		return sno;
	}
	public void setSno(String sno) {
		this.sno = sno;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public Integer getAbsenceNum() {
		return absenceNum;
	}
	public void setAbsenceNum(Integer absenceNum) {
		this.absenceNum = absenceNum;
	}

}
