package pic;

public class Face {
	private String id;              //脸的编号
	private double cX;           //原图片中心x坐标
	private double cY;            //原图片中心y坐标
	private double lX;            //原图片左上角x坐标
	private double lY;             //原图片左上角y坐标
	private double lXInPic;     //网页上图片左上角x坐标
	private double lYInPic;      //网页上图片左上角y坐标
	private double width;        //原图片的宽度
	private double hight;         //原图片的高度
	private String name;           //脸的名字
	
	
	public Face() {
		super();
		// TODO Auto-generated constructor stub
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
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


	public double getlX() {
		return lX;
	}


	public void setlX(double lX) {
		this.lX = lX;
	}


	public double getlY() {
		return lY;
	}


	public void setlY(double lY) {
		this.lY = lY;
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


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	@Override
	public String toString() {
		return "Face [id=" + id + ", cX=" + cX + ", cY=" + cY + ", lX=" + lX + ", lY=" + lY + ", lXInPic=" + lXInPic
				+ ", lYInPic=" + lYInPic + ", width=" + width + ", hight=" + hight + ", name=" + name + "]";
	}
	
	

}
