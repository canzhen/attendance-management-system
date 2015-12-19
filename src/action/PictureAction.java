package action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import pic.Face;
import pic.Pic;
import pic.PicFace;

@SuppressWarnings("serial")
public class PictureAction extends MyActionSupport{
	private Map session = getSession();//获取session
	//图片链接
	private String url="http://uploadimgsaetest-pics.stor.sinaapp.com/20bc08e8aa5eceb82822b101ec9e662d.jpg";

    int webX=800;
    int webY=370;
	//错误信息
	String msg="";

	//提供脸的左上角的信息
	public String giveFaces(){
		double widthInPic=0;
		double hightInPic=0;
		//获得图片原来的信息,拉伸后的信息为800*370
		Pic pic=new Pic(url);
		pic.getPicInfo();
		double width=pic.getWidth();
		double hight=pic.getHeight();
		//获得图片上脸的坐标信息
		PicFace face=new PicFace(url);
		List<Face> faceInfo=face.getFaces();
		for(int i=0;i<faceInfo.size();i++){
			double lx=faceInfo.get(i).getlX();
			widthInPic=lx*webX/width;
			double ly=faceInfo.get(i).getlY();
			hightInPic=ly*webY/hight;
			faceInfo.get(i).setlXInPic(widthInPic);
			faceInfo.get(i).setlYInPic(hightInPic);
			//设置宽度和高度为网页上的宽度和高度
			double oriW=faceInfo.get(i).getWidth();
			double oriH=faceInfo.get(i).getHight();
			faceInfo.get(i).setWidth(oriH*webX/webY);
			faceInfo.get(i).setlYInPic(oriW*webY/webX);
		}

		//返回所有左上角坐标信息，数据类型为List<List<Double>>
		session.put("faces",faceInfo);
		session.put("picFace",face);
		return SUCCESS;
	}


	public String chooseOneFace(){
		//左上角坐标点的信息
		Face face=(Face) session.get("face");
		if(face.getName()==null||face.getName()==""){
			@SuppressWarnings("unchecked")
			List<Face> faceInfo=(List<Face>) session.get("faces");
			String name=(String) session.get("id");
			for(int i=0;i<faceInfo.size();i++){
				if(faceInfo.get(i).equals(face)){
					face.setName(name);
					PicFace picFace=(PicFace) session.get("picFace");
					picFace.setFaceName(face.getcX(), face.getcY(), name);
				}
			}
			msg="设置成功^_^";
			session.put("faces",faceInfo);
			return SUCCESS;
		}
		msg="该脸已被占用=.=";
		return SUCCESS;
	}

}
