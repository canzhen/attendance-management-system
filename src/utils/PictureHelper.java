package utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import db.util.DBHelper;

public class PictureHelper {
	
	public static void savePic(File pic, String tno){
		/*获取当前系统时间*/
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");//设置日期格式
		String date = df.format(new Date());//获取当前日期
		String savePath = Values.save_pic_path+tno,
				filePath = Values.save_pic_path+tno+"\\"+date+".jpg";
		
		if ( pic != null ){
			try {
				DataInputStream in = new DataInputStream(new FileInputStream(pic));
				File saveFile = new File(savePath);
				if ( !saveFile.exists())
					saveFile.mkdirs();
				DataOutputStream out = new DataOutputStream(new FileOutputStream(filePath));
				/*将参数savePath，即将截取的图片的存储在本地地址赋值给out输出流所指定的地址*/
	            byte[] buffer = new byte[4096];
	            int count = 0;
	            while ((count = in.read(buffer)) > 0)/*将输入流以字节的形式读取并写入buffer中*/
	                out.write(buffer, 0, count);
	            out.close();/*后面三行为关闭输入输出流以及网络资源的固定格式*/
	            in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 返回该堂课老师上传的图片，
	 * 如果老师尚未上传图片，则返回null
	 * @param tno
	 * @return
	 */
	public static String getPicUrl(String tno){
		File pic = null;
		/*获取当前系统时间*/
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH");//设置日期格式
		String date = df.format(new Date());//获取当前日期
		/*
		 * 测试部分
		 */
		tno = "11111111";
		/*
		 * 测试部分
		 */
		String savePath = "teacher\\"+tno,
				filePath = "teacher\\"+tno+"\\"+date+".jpg";
		return filePath;
		//String test = PictureHelper.class.getResource("/").getPath();
		
		//pic = new File(filePath);
		//if (!pic.exists())
		//	return null;
		//else return filePath;
	} 
}
