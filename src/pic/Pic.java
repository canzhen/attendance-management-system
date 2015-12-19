package pic;
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

	public Pic(String url){
		Charset.forName("UTF-8").name();
		try {
			result = httpRequests.detectionDetect(new PostParameters().setUrl(url));
            System.out.println("result==="+result);
		} catch (FaceppParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//获得所有图片的中心点的X坐标
	public List getAllCenter(){
		List<List<Double>> list=new ArrayList<List<Double>>();
		Double x;
		Double y;
		try {
			for (int i = 0; i < result.getJSONArray("face").length(); ++i){
				List<Double> sList=new ArrayList<Double>();
				x=(Double) result.getJSONArray("face").getJSONObject(i).getJSONObject("position").getJSONObject("center").get("x");
				sList.add(x);
				y=(Double) result.getJSONArray("face").getJSONObject(i).getJSONObject("position").getJSONObject("center").get("y");
				sList.add(y);
				list.add(sList);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
	
	//根据中心点返回左上角坐标
	public List getAllLeft(double x,double y){
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
					size.add(x-width/2);
					size.add(y-hight/2);
					break;
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return size;
	}


	//根据中心点获得脸的宽和高
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
					System.out.println("add:"+name);
					result.getJSONArray("face").getJSONObject(i).put("tag", name);
					break;
				}
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//根据中心点删除脸的名字
		public void deleteFaceName(String name) {
			try {
				for (int i = 0; i < result.getJSONArray("face").length(); ++i){
					String sname=result.getJSONArray("face").getJSONObject(i).getString("tag");
					if(sname.equals(name)){
						System.out.println("delete:"+name);
						result.getJSONArray("face").getJSONObject(i).put("tag", "");
						break;
					}
				}
			} catch (JSONException e) {
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
		
		public String getFaceNameByCenter(Double x,Double y){
			String sname="";
			try {
				for (int i = 0; i < result.getJSONArray("face").length(); ++i){
					Double sx = (Double) result.getJSONArray("face").getJSONObject(i).getJSONObject("position").getJSONObject("center").get("x");
					Double sy=(Double) result.getJSONArray("face").getJSONObject(i).getJSONObject("position").getJSONObject("center").get("y");
					if((sx.equals(x))&&(sy.equals(y))){	
						sname=result.getJSONArray("face").getJSONObject(i).getString("tag");
						System.out.println("get:"+sname);
						break;
					}
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return sname;
		}
}

