package function;

public class Curve{
	public static double sinCurve(int count,double period,double shaped_width){
		return Math.sin(Math.PI * 2 / period * count) * shaped_width;
	}

	public static double cosCurve(int count,double period,double shaped_width){
		return Math.cos(Math.PI * 2 / period * count) * shaped_width;
	}

	public static double tanCurve(int count,double period,double shaped_width){
		return Math.tan(Math.PI * 2 / period * count) * shaped_width;
	}
}
