package db.entity;
// Generated 2015-12-16 20:34:33 by Hibernate Tools 4.3.1.Final

/**
 * Teacher generated by hbm2java
 */
public class Teacher implements java.io.Serializable {

	private String tno;
	private String tname;
	private String tpwd;

	public Teacher() {
	}

	public Teacher(String tno, String tname, String tpwd) {
		this.tno = tno;
		this.tname = tname;
		this.tpwd = tpwd;
	}

	public String getTno() {
		return this.tno;
	}

	public void setTno(String tno) {
		this.tno = tno;
	}

	public String getTname() {
		return this.tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getTpwd() {
		return this.tpwd;
	}

	public void setTpwd(String tpwd) {
		this.tpwd = tpwd;
	}

}
