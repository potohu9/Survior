package scene;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import object2D.Object2D;
import openGLES20.Common;
import particle.ParticleFunctionFactory;
import particle.ParticleManager;
import particle.ParticleSystem;
import selectCursor.Cursor;
import selectCursor.SelectCursor;
import utile.OriginalArrayList;

import collision.Collision;

import line.Line;
import function.ColorData;
import function.TextureManager;
import function.TouchManager;
import function.Vector2D;
import function.Vector2DDomain;
import gameObject.GameObject;
import gameObject.GameObjectFunctionFactory;
import gesture.ProportionMotionEvent;
import android.os.Environment;
import android.view.MotionEvent;

public class EditScene implements Scene{
	Vector2DDomain vector2DDomain;
	OriginalArrayList<Line> writedLine;
	OriginalArrayList<Line> frameLine;
	Line writeLine;
	Vector2D touchStartPos;
	private GameObject[] select;
	private SelectCursor selectCursor;
	
	private static final int editTypeNum = 3;
	public EditScene() {
		
	}

	@Override
	public void intialize() {
		vector2DDomain = new Vector2DDomain();
		writedLine = new OriginalArrayList<Line>();
		frameLine = new OriginalArrayList<Line>();
		writeLine = null;
		touchStartPos = null;
		Vector2D topLeft = new Vector2D(0,0);
		Vector2D topRight = new Vector2D(Common.getScreenProportionW(),0);
		Vector2D underLeft = new Vector2D(0,Common.getScreenProportionH()-100);
		Vector2D underRight = new Vector2D(Common.getScreenProportionW(),Common.getScreenProportionH()-100);
		
		frameLine.add(new Line(underLeft,underRight));
		frameLine.add(new Line(topLeft,underLeft));
		frameLine.add(new Line(topRight,topLeft));
		frameLine.add(new Line(underRight,topRight));
		
		initCursor();
	}

	@Override
	public void update() {
		
	}

	@Override
	public void render() {
		int writedSize = writedLine.size();
		for(int i=0;i<writedSize;i++){
			writedLine.get(i).drawLine(5,ColorData.makeARGB(1,0,0,0));
		}
		int flameSize = frameLine.size();
		for(int i=0;i<flameSize;i++){
			frameLine.get(i).drawLine(5,ColorData.makeARGB(1,1,1,1));
		}
		if(writeLine != null){
			writeLine.drawLine(5,ColorData.makeARGB(1,1,0,0));
		}
		selectCursor.render();
		ParticleManager.render();
	}

	@Override
	public void finalize() {
		selectCursor.finalize();
	}
	
	private void buttonEreaTouch(MotionEvent event){
		int selectNum = selectCursor.getSelectObject().length;
		for(int i=0;i<selectNum;i++){
			if(Collision.circleToCircle(new Vector2D(ProportionMotionEvent.getX(),ProportionMotionEvent.getY()),2,
					selectCursor.getSelectObject(i).getPos(),selectCursor.getSelectObject(i).getRad())){
				selectCursor.setNowCursor(i);
				return;
			}
		}
	}
	
	private void gameEreaTouch(MotionEvent event){
		switch(selectCursor.getNowCursor()){
		case 0:
			modeWrite(event);
			break;
		case 1:
			modeDeleat(event);
			break;
		default:
			modeEnd(event);
		}
	}
	
	private void modeWrite(MotionEvent event){
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			writeLine = new Line(new Vector2D(ProportionMotionEvent.getX(),ProportionMotionEvent.getY()),new Vector2D(ProportionMotionEvent.getX(),ProportionMotionEvent.getY()));
			touchStartPos = new Vector2D(ProportionMotionEvent.getX(),ProportionMotionEvent.getY());
			break;
		case MotionEvent.ACTION_MOVE:
			if(touchStartPos == null){
				touchStartPos = new Vector2D(ProportionMotionEvent.getX(),ProportionMotionEvent.getY());
			}
			writeLine = new Line(touchStartPos,new Vector2D(ProportionMotionEvent.getX(),ProportionMotionEvent.getY()));
			break;
		case MotionEvent.ACTION_UP:
			Vector2D nearStartPoint = SnapCheck(writeLine.startPos);
			Vector2D nearEndPoint = SnapCheck(writeLine.endPos);
			if(Collision.circleToCircle(writeLine.startPos,15,nearStartPoint,15)){
				writeLine.startPos = nearStartPoint;
			}
			if(Collision.circleToCircle(writeLine.endPos,15,nearEndPoint,15)){
				writeLine.endPos = nearEndPoint;
			}
			writedLine.add(writeLine);
			writeLine = null;
			touchStartPos = null;
			TouchManager.actionReset();
			break;
		}
	}
	
	private void modeDeleat(MotionEvent event){
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
		case MotionEvent.ACTION_MOVE:
			for(int i=0;i<writedLine.size();i++){
				if(Collision.circleToCircle(ProportionMotionEvent.getX(),ProportionMotionEvent.getY(),30,writedLine.get(i).getCenter().x,writedLine.get(i).getCenter().y,(float)writedLine.get(i).getRadius())){
					Object2D object = new Object2D();
					object.setPos(new Vector2D(ProportionMotionEvent.getX(),ProportionMotionEvent.getY()));
					object.setRad(30);
					if(Collision.segmentToCircle(object,writedLine.get(i))){
						writedLine.remove(i);
					}
				}
			}
			break;
		case MotionEvent.ACTION_UP:
			break;
		}
	}
	
	private void modeEnd(MotionEvent event){
		switch(event.getAction()){
		case MotionEvent.ACTION_DOWN:
			// 改行コード
			final String BR = System.getProperty("line.separator");
	        int size = writedLine.size();
	        StringBuffer sb = new StringBuffer();
	    	for(int i=0;i<size;i++){
	    		sb.append(BR);
	    		sb.append("Line :	");
	    		sb.append(String.valueOf(i));
	            sb.append(BR);
	            sb.append("StartPos :	");
	            sb.append("(");
	    		sb.append(String.valueOf(writedLine.get(i).startPos.x));
	    		sb.append(",");
	    		sb.append(String.valueOf(writedLine.get(i).startPos.y));
	    		sb.append(")");
	            sb.append(BR);
	            sb.append("EndPos :	");
	            sb.append("(");
	    		sb.append(String.valueOf(writedLine.get(i).endPos.x));
	    		sb.append(",");
	    		sb.append(String.valueOf(writedLine.get(i).endPos.y));
	    		sb.append(")");
	            sb.append(BR);
	    	}
	    	String filePath = Environment.getExternalStorageDirectory() + "/battroid" + "/line.txt";
	        File file = new File(filePath);
	        file.getParentFile().mkdir();
	        FileOutputStream fos;
	        try {
	            fos = new FileOutputStream(file,false);
	            OutputStreamWriter osw = new OutputStreamWriter(fos,"UTF-8");
	            BufferedWriter bw = new BufferedWriter(osw);
	            bw.write(sb.toString());
	            bw.flush();
	            bw.close();
	        }
	        catch (Exception e) {
	        	
	        }
	        SceneManager.changeScene(SceneList.LOGO);
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		case MotionEvent.ACTION_UP:
			break;
		}
	}

	@Override
	public void touchEvent(MotionEvent event) {
		if(	Double.isNaN(ProportionMotionEvent.getX()) ||
			Double.isNaN(ProportionMotionEvent.getY())){
			return;
		}
		if(ProportionMotionEvent.getY() > Common.getScreenProportionH()-100){
			buttonEreaTouch(event);
			return;
		}
		else{
			gameEreaTouch(event);
		}
	}
	
	private Vector2D SnapCheck(Vector2D Pos){
		Vector2D nearPoint = new Vector2D();
		float nearLength = 10000;
		for(int i = 0;i<writedLine.size();i++){
			{
				float length = Vector2D.Length(vector2DDomain,
						Vector2D.Sub(vector2DDomain,
						Pos,writedLine.get(i).startPos));
				if(nearLength > length){
					nearLength = length;
					nearPoint.x = writedLine.get(i).startPos.x;
					nearPoint.y = writedLine.get(i).startPos.y;
				}
			}
			{
				float length = Vector2D.Length(vector2DDomain,
						Vector2D.Sub(vector2DDomain,
						Pos,writedLine.get(i).endPos));
				if(nearLength > length){
					nearLength = length;
					nearPoint.x = writedLine.get(i).endPos.x;
					nearPoint.y = writedLine.get(i).endPos.y;
				}
			}
		}
		return nearPoint;
	}
	
	private void initCursor(){
		select = new GameObject[editTypeNum];
		String[] keys = {"Write","Deleat","End"};
		for(int i=0;i<editTypeNum;i++){
			select[i] = new GameObject(TextureManager.getTexture(keys[i]),
					GameObjectFunctionFactory.makeNotProcessing());
			select[i].setRad(35);
			select[i].setPos(new Vector2D(Common.getScreenProportionW()-(select[i].getRad()*(editTypeNum-i)*3),Common.getScreenProportionH()-50));
		}
		
		int paticleNum = 3;
		
		ParticleSystem[] particleSystem = new ParticleSystem[paticleNum];
		for(int i=0;i<paticleNum;i++){
			particleSystem[i] = new ParticleSystem();
			particleSystem[i].initialize("Light",
				ParticleFunctionFactory.makeVectorAddition(vector2DDomain,new Vector2D(0,0)).compose(
				ParticleFunctionFactory.makeAlphaMultiplication(0.8f).compose(
				ParticleFunctionFactory.makeDesignationAlphaIsNotExistence(0.2f))));
		
			particleSystem[i].setDefault();
			particleSystem[i].setGeneratNum(1,0);
			particleSystem[i].setAngleMargin(30);
			particleSystem[i].setGeneratAngle(0,360);
			particleSystem[i].setInitialVecocity(3,1);
			particleSystem[i].setDisappearanceDistance(150,10);
			particleSystem[i].setParticleScale(40,10);
			particleSystem[i].setA(1.0f,0.0f);
			particleSystem[i].setR(0.8f,0.3f);
			particleSystem[i].setG(0.4f,0.2f);
			particleSystem[i].setB(0.2f,0.2f);
		}
		Cursor cursor = new Cursor(paticleNum,particleSystem);
		cursor.setRotVel(4);
		cursor.setRad(40);
		
		selectCursor = new SelectCursor(Math.round(editTypeNum/2),10,cursor,select);
	}
}
