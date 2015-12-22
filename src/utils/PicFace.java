package utils;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

import com.facepp.error.FaceppParseException;
import com.facepp.http.HttpRequests;
import com.facepp.http.PostParameters;

import pic.entity.FaceEntity;


public class PicFace{
	static HttpRequests httpRequests= new HttpRequests("626d88a706ecc8f5f3a7b6dca2e8c006", "AuhwhMiAX1xUtt49sX-6_lq9dz_4xvM2", true, true);
	private  JSONObject result ;
	private static  List<FaceEntity> faces;

	public PicFace(String url){
		Charset.forName("UTF-8").name();
		try {
			result = httpRequests.detectionDetect(new PostParameters().setUrl(url));
			faces=new ArrayList<FaceEntity>();
			setFaces();
			System.out.println("result==="+result);
		} catch (FaceppParseException e) {
			e.printStackTrace();
		}
	}

	public List<FaceEntity> getFaces() {
		return faces;
	}


	//获得所有图片的脸的信息
	private void setFaces(){
		String id;
		Double x;
		Double y;
		Double width;
		Double hight;
		try {
			for (int i = 0; i < result.getJSONArray("face").length(); ++i){
				FaceEntity face=new FaceEntity();
				//获得中心点的坐标
				x=2*(Double) result.getJSONArray("face").getJSONObject(i).getJSONObject("position").getJSONObject("center").get("x");
				y=2*(Double) result.getJSONArray("face").getJSONObject(i).getJSONObject("position").getJSONObject("center").get("y");
				//获得头像宽度和高度
				width= 2*(Double) result.getJSONArray("face").getJSONObject(i).getJSONObject("position").get("width");
				hight= 2*(Double) result.getJSONArray("face").getJSONObject(i).getJSONObject("position").get("height");
				
				String sno=result.getJSONArray("face").getJSONObject(i).getString("tag");
				face.setcX(x);
				face.setcY(y);
				face.setHight(hight);
				face.setWidth(width);
				face.setlX(x-width/2);
				face.setlY(y-hight/2);
				face.setSno(sno);
				faces.add(face);
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}


	//根据中心点设置脸的名字
	public  void setFaceName(Double x,Double y,String sno) {
		try {
			for (int i = 0; i < faces.size(); ++i){
				Double sx = faces.get(i).getcX();
				Double sy=faces.get(i).getcY();
				if(sx==x&&sy==y){	
					System.out.println("add:"+sno);
					result.getJSONArray("face").getJSONObject(i).put("tag", sno);
					faces.get(i).setSno(sno);
					break;
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	//根据中心点删除脸的名字
	public void deleteFaceName(String sno) {
		try {
			for (int i = 0; i < result.getJSONArray("face").length(); ++i){
				String no=result.getJSONArray("face").getJSONObject(i).getString("tag");
				if(no.equals(sno)){
					System.out.println("delete:"+sno);
					result.getJSONArray("face").getJSONObject(i).put("tag", "");
					faces.get(i).setSno("");
					break;
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

	public void getJsonFile(String fileName, String path){
		fileName=(path+fileName);
		if(!fileName.endsWith(".json")){
			fileName+=".json";
		}
		File jFile=new File(fileName);
		if(!jFile.exists()){
			try {
				jFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			FileOutputStream out=new FileOutputStream(jFile);
			PrintStream p=new PrintStream(out);
			p.println(result);
			p.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

	public String getFaceSNoByCenter(Double x,Double y){
		String sno="";
		try {
			for (int i = 0; i < result.getJSONArray("face").length(); ++i){
				Double sx = (Double) result.getJSONArray("face").getJSONObject(i).getJSONObject("position").getJSONObject("center").get("x");
				Double sy=(Double) result.getJSONArray("face").getJSONObject(i).getJSONObject("position").getJSONObject("center").get("y");
				if((sx.equals(x))&&(sy.equals(y))){	
					sno=result.getJSONArray("face").getJSONObject(i).getString("tag");
					System.out.println("get:"+sno);
					break;
				}
			}
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return sno;
	}
}

