package pic.entity;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

public class PicEntity {
	private String url;
	private double height;
	private double width;
	
	public PicEntity(String url){
		this.url=url;
	}
	
	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWidth() {
		return width;
	}

	public void setWidth(double width) {
		this.width = width;
	}



	public void getPicInfo(){
		BufferedImage image=getBufferedImage(url);
		if (image!=null)
		{
			setHeight(image.getHeight());
			setWidth(image.getWidth());
		}
		else
		{
			setHeight(0);
			setWidth(0);
		}
		
	}

	
	/**
	 * 
	 * @param imgUrl 图片地址
	 * @return 
	 */
	private BufferedImage getBufferedImage(String imgUrl) {
		URL url = null;
		InputStream is = null;
		BufferedImage img = null;
		try {
			url = new URL(imgUrl);
			is = url.openStream();
			img = ImageIO.read(is);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return img;
	}

}
