package dbentity;

// Generated 2015-12-11 11:02:07 by Hibernate Tools 4.0.0

/**
 * Course generated by hbm2java
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
