package pic.entity;

public class FaceEntity {
	private String sno;              //学生的编号
	private double cX;           //原图片中心x坐标
	private double cY;            //原图片中心y坐标
	private double lXInPic;     //网页上图片左上角x坐标
	private double lYInPic;      //网页上图片左上角y坐标
	private double width;        //图片的宽度
	private double hight;         //图片的高度
	
	
	public FaceEntity() {
		super();
	}
	
	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public double getcX() {
		return cX;
	}
	public void setcX(double cX) {
		this.cX = cX;
	}
	public double getcY() {
		return cY;
	}


	public void setcY(double cY) {
		this.cY = cY;
	}
	public double getlXInPic() {
		return lXInPic;
	}


	public void setlXInPic(double lXInPic) {
		this.lXInPic = lXInPic;
	}

	public double getlYInPic() {
		return lYInPic;
	}


	public void setlYInPic(double lYInPic) {
		this.lYInPic = lYInPic;
	}


	public double getWidth() {
		return width;
	}


	public void setWidth(double width) {
		this.width = width;
	}


	public double getHight() {
		return hight;
	}


	public void setHight(double hight) {
		this.hight = hight;
	}


}
