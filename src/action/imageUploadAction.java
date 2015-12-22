package action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.apache.struts2.ServletActionContext;

public class imageUploadAction extends MyActionSupport{
	private static final long serialVersionUID = 1L;
	private static final int BUFFER_SIZE = 16 * 1024;  
	@SuppressWarnings("rawtypes")
	private Map request = getRequest();
	private File myImage = new File("my_icon.jpg");
	private String imageFileName;
	
	public String execute(){
		if (myImage == null ){
			request.put("result",0);
		}
		/*
		 * 得到图片保存的位置
		 * 根据root来得到图片保存的路径
		 * 在tomcat下的该工程里
		 */
		File imageFile = new File(ServletActionContext.getServletContext()   
                .getRealPath("UploadImages")     
                + "/" + imageFileName);   
        copy(myImage, imageFile);  //把图片写入到上面设置的路径里 
		
		return SUCCESS;
	}
	
	private void copy(File src , File dst){
		try {  
            InputStream in = null;  
            OutputStream out = null;  
            try {  
                in = new BufferedInputStream(new FileInputStream(src),  
                        BUFFER_SIZE);  
                out = new BufferedOutputStream(new FileOutputStream(dst),  
                        BUFFER_SIZE);  
                byte[] buffer = new byte[BUFFER_SIZE];  
                while (in.read(buffer) > 0) {  
                    out.write(buffer);  
                }  
            } finally {  
                if (null != in) {  
                    in.close();  
                }  
                if (null != out) {  
                    out.close();  
                }  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        }		
	}

}
