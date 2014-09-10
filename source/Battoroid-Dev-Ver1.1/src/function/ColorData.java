package function;

public class ColorData {
	protected float r;	//赤
	protected float g;	//緑
	protected float b;	//青
	protected float a;	//透明度(0に近いほど透明)
	
	private final static ColorData WHITE;
	private final static ColorData BLACK;
	private final static ColorData RED;
	private final static ColorData BLUE;
	private final static ColorData GREEN;
	
	public static enum ColorList{
		WHITE,
		BLACK,
		RED,
		BLUE,
		GREEN,
	}
	
	static{
		WHITE = ColorData.makeARGB(1,1,1,1);
		BLACK = ColorData.makeARGB(1,0,0,0);
		RED = ColorData.makeARGB(1,1,0,0);
		BLUE = ColorData.makeARGB(1,0,0,1);
		GREEN = ColorData.makeARGB(1,0,1,0);
	}

	public ColorData(){
		r = 1.0f;
		g = 1.0f;
		b = 1.0f;
		a = 1.0f;
	}
	
	//各ゲッター
	public float getR()			{return r;}
	public float getG()			{return g;}
	public float getB()			{return b;}
	public float getA()			{return a;}
	//各セッター
	public void setR(float r)	{this.r = r;}
	public void setG(float g)	{this.g = g;}
	public void setB(float b)	{this.b = b;}
	public void setA(float a)	{this.a = a;}
	
	//色の設定
	public void setRGBA(float r,float g,float b,float a){
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	//色の設定
	public void setARGB(float a,float r,float g,float b){
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;
	}
	
	//色の設定
	public void setRGB(float r,float g,float b){
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = 1;
	}
	
	//色データの取得
	public float[] getData(){
		return new float[]{r,g,b,a};
	}
	
	public ColorData getClone(){
		return ColorData.makeARGB(this.a,this.r,this.g,this.b);
	}
	
	public static ColorData makeRGBA(float r,float g,float b,float a){
		ColorData colorData = new ColorData();
		colorData.setRGBA(r,g,b,a);
		return colorData;
	}
	
	public static ColorData makeARGB(float a,float r,float g,float b){
		ColorData colorData = new ColorData();
		colorData.setARGB(a,r,g,b);
		return colorData;
	}
	
	public static ColorData makeRGB(float r,float g,float b){
		ColorData colorData = new ColorData();
		colorData.setRGB(r,g,b);
		return colorData;
	}
	
	public static ColorData getColor(ColorList color){
		ColorData result;
		
		switch(color){
		case BLACK:
			result = BLACK.getClone();
			break;
		case BLUE:
			result = BLUE.getClone();
			break;
		case GREEN:
			result = GREEN.getClone();
			break;
		case RED:
			result = RED.getClone();
			break;
		case WHITE:
			result = WHITE.getClone();
			break;
		default:
			result = ColorData.makeARGB(0,0,0,0);
			break;
		}
		
		return result;
	}
}
