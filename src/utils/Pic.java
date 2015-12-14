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


public class Pic{
	static HttpRequests httpRequests= new HttpRequests("626d88a706ecc8f5f3a7b6dca2e8c006", "AuhwhMiAX1xUtt49sX-6_lq9dz_4xvM2", true, true);
	private JSONObject result ;

	public void setResult(String url){
		Charset.forName("UTF-8").name();
		try {
			result = httpRequests.detectionDetect(new PostParameters().setUrl(url));
            System.out.println("result==="+result);
		} catch (FaceppParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(result);
	}

	//获得所有图片的中心点的X坐标
	public List getAllX(){
		List<Double> xList=new ArrayList<Double>();
		Double x;
		try {
			for (int i = 0; i < result.getJSONArray("face").length(); ++i){
				x=(Double) result.getJSONArray("face").getJSONObject(i).getJSONObject("position").getJSONObject("center").get("x");
				xList.add(x);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return xList;
	}

	//获得所有图片的中心点的Y坐标
	public List getAllY(){
		List<Double> yList=new ArrayList<Double>();
		Double y;
		try {
			for (int i = 0; i < result.getJSONArray("face").length(); ++i){
				y=(Double) result.getJSONArray("face").getJSONObject(i).getJSONObject("position").getJSONObject("center").get("y");
				yList.add(y);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return yList;
	}

	//根据中心点获得脸的宽和高
	@SuppressWarnings("unchecked")
	public List getWidthHigth(Double x,Double y){
		Double width;
		Double hight;
		List<Double> size=new ArrayList<Double>();
		try {
			for (int i = 0; i < result.getJSONArray("face").length(); ++i){
				Double sx=(Double) result.getJSONArray("face").getJSONObject(i).getJSONObject("position").getJSONObject("center").get("x");
				Double sy=(Double) result.getJSONArray("face").getJSONObject(i).getJSONObject("position").getJSONObject("center").get("y");
				if(sx.equals(x)&&sy.equals(y)){
					width= (Double) result.getJSONArray("face").getJSONObject(i).getJSONObject("position").get("width");
					hight= (Double) result.getJSONArray("face").getJSONObject(i).getJSONObject("position").get("height");
					size.add(width);
					size.add(hight);
					break;
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return size;
	}

	//根据中心点设置脸的名字
	public void setFaceName(Double x,Double y,String name) {
		try {
			for (int i = 0; i < result.getJSONArray("face").length(); ++i){
				Double sx = (Double) result.getJSONArray("face").getJSONObject(i).getJSONObject("position").getJSONObject("center").get("x");
				Double sy=(Double) result.getJSONArray("face").getJSONObject(i).getJSONObject("position").getJSONObject("center").get("y");
				if(sx==x&&sy==y){

					httpRequests.personCreate(new PostParameters().setPersonName(name));

					httpRequests.personAddFace(new PostParameters().setPersonName(name).setFaceId(
							result.getJSONArray("face").getJSONObject(i).getString("face_id")));
					break;
				}
			}
		} catch (FaceppParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//根据中心点删除脸的名字
		public void deleteFaceName(Double x,Double y,String name) {
			try {
				for (int i = 0; i < result.getJSONArray("face").length(); ++i){
					Double sx = (Double) result.getJSONArray("face").getJSONObject(i).getJSONObject("position").getJSONObject("center").get("x");
					Double sy=(Double) result.getJSONArray("face").getJSONObject(i).getJSONObject("position").getJSONObject("center").get("y");
					if(sx==x&&sy==y){
						httpRequests.personRemoveFace(
								new PostParameters().setPersonName(name).setFaceId(
										result.getJSONArray("face").getJSONObject(i).getString("face_id")));
						httpRequests.personDelete(new PostParameters().setPersonName(name));
							break;
					}
				}
			} catch (FaceppParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch (JSONException e) {
				// TODO Auto-generated catch block
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
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			try {
				FileOutputStream out=new FileOutputStream(jFile);
				PrintStream p=new PrintStream(out);
				p.println(result);
				p.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
}

