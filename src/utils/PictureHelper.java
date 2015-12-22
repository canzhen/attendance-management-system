package utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pic.entity.FaceEntity;
import pic.entity.PicEntity;

public class PictureHelper {

	//提供脸的左上角的信息
	public void giveFaces( String url,List<FaceEntity> faceInfo){
		double widthInPic=0;
		double hightInPic=0;
		//获得图片原来的信息,拉伸后的信息为800*370
		PicEntity pic=new PicEntity(url);
		pic.getPicInfo();
		double width=pic.getWidth();
		double hight=pic.getHeight();
		//获得图片上脸的坐标信息
		for(int i=0;i<faceInfo.size();i++){
			double lx=faceInfo.get(i).getlX();
			widthInPic=lx*Values.webX/width;
			double ly=faceInfo.get(i).getlY();
			hightInPic=ly*Values.webY/hight;
			faceInfo.get(i).setlXInPic(widthInPic);
			faceInfo.get(i).setlYInPic(hightInPic);
			//设置宽度和高度为网页上的宽度和高度6
			double oriW=faceInfo.get(i).getWidth();
			double oriH=faceInfo.get(i).getHight();
			faceInfo.get(i).setWidth(oriH*Values.webX/Values.webY);
			faceInfo.get(i).setlYInPic(oriW*Values.webY/Values.webX);
		}
	}
}
