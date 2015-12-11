package dbentity;

// Generated 2015-12-11 11:02:07 by Hibernate Tools 4.0.0

/**
 * Course表的字段有cno课程编号，
 * cname课程名字，cweek课程上课时间（0表示1-8周，1表示9-16周），
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
	private int cweek;
	private int cday;
	private int ctime;

	public Course() {
	}

	public Course(String cno, String cname, int cweek, int cday, int ctime) {
		this.cno = cno;
		this.cname = cname;
		this.cweek = cweek;
		this.cday = cday;
		this.ctime = ctime;
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

	public int getCweek() {
		return this.cweek;
	}

	public void setCweek(int cweek) {
		this.cweek = cweek;
	}

	public int getCday() {
		return this.cday;
	}

	public void setCday(int cday) {
		this.cday = cday;
	}

	public int getCtime() {
		return this.ctime;
	}

	public void setCtime(int ctime) {
		this.ctime = ctime;
	}

}
