package db.entity;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

public class StudentInfo implements Serializable{
	private String sno;
	private String sname;
	private Integer absenceNum;	
	private boolean isChecked = false;//默认学生尚未签到
	
	
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
	public boolean isChecked() {
		return isChecked;
	}
	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}
	
	private void writeObject(ObjectOutputStream s)
            throws IOException {
        s.defaultWriteObject();
        s.writeObject(sno);
        s.writeObject(sname);
        s.writeInt(absenceNum);
        s.writeBoolean(isChecked);
    }
    private void readObject(ObjectInputStream s)
            throws IOException, ClassNotFoundException {
    	s.defaultReadObject();
    	sno = (String) s.readObject();
        sname = (String) s.readObject();
        absenceNum = (int)s.readInt();
        isChecked = (boolean)s.readBoolean();
    }

}
