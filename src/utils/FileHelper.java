package utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Date;

import db.entity.StudentInfo;

public class FileHelper {
	
	public static void serializeStudentsInfo(ArrayList<StudentInfo> studentsInfo,String tno){
		ObjectOutputStream oos = null;
		File file = null;
		try {
			file = new File(Values.save_studentsInfo_path);
			if ( !file.exists())
				file.mkdir();
			 oos = new ObjectOutputStream(
					new FileOutputStream(Values.save_studentsInfo_path+tno));
			studentsInfoSerializable temp = new studentsInfoSerializable();
			temp.setStudentsInfo(studentsInfo);
			temp.setSize(studentsInfo.size());
			oos.writeObject(temp);
			oos.flush();//缓冲流
			oos.close();//关闭流
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static ArrayList<StudentInfo> deserializeStudentsInfo(String tno){
		ObjectInputStream oin = null;
		ArrayList<StudentInfo> studentsInfo = null;
		File file = null;
		try {
			file = new File(Values.save_studentsInfo_path);
			if ( !file.exists())
				file.mkdir();
			oin = new ObjectInputStream(
					new FileInputStream(Values.save_studentsInfo_path+tno));
			studentsInfoSerializable temp = (studentsInfoSerializable)oin.readObject();
			studentsInfo = temp.getStudentsInfo();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return studentsInfo;
	}
	
	
	
	public static void serializeStartDate(Date startDate,String tno){
		ObjectOutputStream oos = null;
		File file = null;
		try {
			file = new File(Values.save_startDate_path);
			if ( !file.exists())
				file.mkdir();
			 oos = new ObjectOutputStream(
					new FileOutputStream(Values.save_startDate_path+tno));
			oos.writeObject(startDate);
			oos.flush();//缓冲流
			oos.close();//关闭流
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public static Date deserializeStartDate(String tno){
		ObjectInputStream oin = null;
		Date startDate = null;
		File file = null;
		try {
			file = new File(Values.save_startDate_path);
			if ( !file.exists())
				file.mkdir();
			oin = new ObjectInputStream(
					new FileInputStream(Values.save_startDate_path+tno));
			startDate = (Date)oin.readObject();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return startDate;
		
	}
}