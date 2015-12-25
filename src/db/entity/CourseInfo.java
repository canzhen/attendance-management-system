package db.entity;

public class CourseInfo {
	/**
	 * 课程编号
	 */
	String cno;
	/**
	 * 课程名字
	 */
	String cname;
	/**
	 * 上课时间
	 */
	String time;
	/**
	 * 给该节课上课的老师的教工号
	 */
	String tno;
	/**
	 * 点名时间
	 */
	int checkTime;
	/**
	 * 最大缺席数，
	 * 若超过该数字则有期末不计分等处罚
	 */
	int maxAbsence;
	/**
	 * 学生已经迟到的次数
	 */
	int absenceNum;
	
	
	public String getCno() {
		return cno;
	}
	public void setCno(String cno) {
		this.cno = cno;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getTno() {
		return tno;
	}
	public void setTno(String tno) {
		this.tno = tno;
	}
	public int getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(int checkTime) {
		this.checkTime = checkTime;
	}
	public int getMaxAbsence() {
		return maxAbsence;
	}
	public void setMaxAbsence(int maxAbsence) {
		this.maxAbsence = maxAbsence;
	}
	public int getAbsenceNum() {
		return absenceNum;
	}
	public void setAbsenceNum(int absenceNum) {
		this.absenceNum = absenceNum;
	}
	
}
