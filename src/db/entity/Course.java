package db.entity;

// Generated 2015-12-11 11:02:07 by Hibernate Tools 4.0.0

/**
 * Course表的字段有cno课程编号，
 * cname课程名字，cweek课程上课时间（0表示1-16周，1表示1-8周，2表示9-16周），
 * cday为每周的上课时间（1代表周一上课，17代表周一和周日上课），
 * ctime代表每天的具体上课时间段（1代表早上第一节，45代表4/5节连上，
 * 若每周上两次，那么中间用#隔开不同两天的上课时间）
 * 
 * @author 周灿桢
 *
 */
public class Course implements java.io.Serializable {

	private String cno;
	private String cname;

	public Course() {
	}

	public Course(String cno, String cname) {
		this.cno = cno;
		this.cname = cname;
	}

	public String getCno() {
		return this.cno;
	}

	public void setCno(String cno) {
		this.cno = cno;
	}

	public String getCname() {
		return this.cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

}
