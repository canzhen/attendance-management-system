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


public class PicFace{
	static HttpRequests httpRequests= new HttpRequests("626d88a706ecc8f5f3a7b6dca2e8c006", "AuhwhMiAX1xUtt49sX-6_lq9dz_4xvM2", true, true);
	private  JSONObject result ;
	private  List<Face> faces;

	public PicFace(String url){
		Charset.forName("UTF-8").name();
		try {
			result = httpRequests.detectionDetect(new PostParameters().setUrl(url));
			faces=new ArrayList<Face>();
			setFaces();
			System.out.println("result==="+result);
		} catch (FaceppParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Face> getFaces() {
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
				Face face=new Face();
				id= result.getJSONArray("face").getJSONObject(i).getString("id");
				//获得中心点的坐标
				x=(Double) result.getJSONArray("face").getJSONObject(i).getJSONObject("position").getJSONObject("center").get("x");
				y=(Double) result.getJSONArray("face").getJSONObject(i).getJSONObject("position").getJSONObject("center").get("y");
				//获得头像宽度和高度
				width= (Double) result.getJSONArray("face").getJSONObject(i).getJSONObject("position").get("width");
				hight= (Double) result.getJSONArray("face").getJSONObject(i).getJSONObject("position").get("height");
				
				String sname=result.getJSONArray("face").getJSONObject(i).getString("tag");
				face.setId(id);
				face.setcX(x);
				face.setcY(y);
				face.setHight(hight);
				face.setWidth(width);
				face.setlX(x-width/2);
				face.setlY(y-hight/2);
				face.setName(sname);
				faces.add(face);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	//根据中心点设置脸的名字
	public  void setFaceName(Double x,Double y,String name) {
		try {
			for (int i = 0; i < faces.size(); ++i){
				Double sx = faces.get(i).getcX();
				Double sy=faces.get(i).getcY();
				if(sx==x&&sy==y){	
					System.out.println("add:"+name);
					result.getJSONArray("face").getJSONObject(i).put("tag", name);
					faces.get(i).setName(name);
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
					faces.get(i).setName("");
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

