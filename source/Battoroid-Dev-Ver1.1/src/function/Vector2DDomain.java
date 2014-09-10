package function;

public class Vector2DDomain {
	private Vector2D[] calculationVectorBuffer;
	private int nowUseVectorBuffer;
	private int vector2DCalculationVectorBufferNum;
	
	private float[] calculationFloatBuffer;
	private int nowUseFloatBuffer;
	private int vector2DCalculationFloatBufferNum;
	
	public Vector2DDomain(){
		this.vector2DCalculationVectorBufferNum = 10;
		setCalculationVectorBuffer(new Vector2D[this.vector2DCalculationVectorBufferNum]);
		for(int i=0;i<vector2DCalculationVectorBufferNum;i++){
			calculationVectorBuffer[i] = new Vector2D();
		}
		setNowUseVectorBuffer(0);
		
		this.vector2DCalculationFloatBufferNum = 10;
		setCalculationFloatBuffer(new float[this.vector2DCalculationFloatBufferNum]);
		setNowUseFloatBuffer(0);
	}
	
	public Vector2DDomain(int vector2DCalculationBufferNum){
		this.vector2DCalculationVectorBufferNum = vector2DCalculationBufferNum;
		setCalculationVectorBuffer(new Vector2D[this.vector2DCalculationVectorBufferNum]);
		for(int i=0;i<vector2DCalculationVectorBufferNum;i++){
			calculationVectorBuffer[i] = new Vector2D();
		}
		setNowUseVectorBuffer(0);
		
		this.vector2DCalculationFloatBufferNum = vector2DCalculationBufferNum;
		setCalculationFloatBuffer(new float[this.vector2DCalculationFloatBufferNum]);
		setNowUseFloatBuffer(0);
	}
	
	public void setCalculationVectorBuffer(Vector2D[] calculationVectorBuffer) {
		this.calculationVectorBuffer = calculationVectorBuffer;
	}
	
	public void setNowUseVectorBuffer(int nowUseVectorBuffer) {
		this.nowUseVectorBuffer = nowUseVectorBuffer;
	}
	
	public void setCalculationFloatBuffer(float[] calculationFloatBuffer) {
		this.calculationFloatBuffer = calculationFloatBuffer;
	}
	
	public void setNowUseFloatBuffer(int nowUseFloatBuffer) {
		this.nowUseFloatBuffer = nowUseFloatBuffer;
	}
	
	public Vector2D[] getCalculationVectorBuffer()			{return calculationVectorBuffer;}
	public Vector2D getCalculationVectorBuffer(int index)	{return calculationVectorBuffer[index];}
	public Vector2D getNowUseVectorBuffer()					{return calculationVectorBuffer[nowUseVectorBuffer];}
	
	public float[] getCalculationFloatBuffer()				{return calculationFloatBuffer;}
	public float getCalculationFloatBuffer(int index)		{return calculationFloatBuffer[index];}
	public Float getNowUseFloatBuffer()						{return calculationFloatBuffer[nowUseFloatBuffer];}
	
	public Vector2D getNextVectorBuffer(){
		nowUseVectorBuffer = Utile.UseBuffer(nowUseVectorBuffer,vector2DCalculationVectorBufferNum);
		return getCalculationVectorBuffer(nowUseVectorBuffer);
	}
	
	public float getNextFloatBuffer(){
		nowUseFloatBuffer = Utile.UseBuffer(nowUseFloatBuffer,vector2DCalculationFloatBufferNum);
		return getCalculationFloatBuffer(nowUseFloatBuffer);
	}
}
