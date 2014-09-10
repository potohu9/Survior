package line;

import function.ColorData;
import function.Graphics;
import function.Graphics.BlendMode;
import function.TextureManager;
import function.Utile;
import function.Vector2D;
import function.Vector2DDomain;

public class Line {
	private Vector2DDomain vector2DDomain;
	public Vector2D startPos;		// �n�_
	public Vector2D endPos;			// �I�_

	/*����:�n�_�A�I�_*/
	public Line(Vector2D startPos,Vector2D endPos){
		vector2DDomain = new Vector2DDomain();
		this.startPos = startPos;
		this.endPos = endPos;
	}
	
	/*�߂�l:�n�_�ƏI�_�̒��S�_���W*/
	/*���S�_*/
	public Vector2D getCenter(){
		return new Vector2D((startPos.x+endPos.x) * 0.5f,(startPos.y+endPos.y) * 0.5f);
	}
	
	/*�߂�l�F�n�_����I�_�܂ł̒����̔���*/
	/*�����̔���*/
	public double getRadius(){
		return Vector2D.Length(vector2DDomain,
				Vector2D.Sub(vector2DDomain,
				endPos,startPos)) * 0.5;
	}
	
	public void drawLine(float thickness,ColorData color){
		Vector2D revision = Vector2D.Scale(vector2DDomain,
				Vector2D.Normalize(vector2DDomain,
				Vector2D.Perp(vector2DDomain,
				Vector2D.Sub(vector2DDomain,
				endPos,startPos))),thickness);
		
		Vector2D topLeft = Vector2D.Sub(vector2DDomain,
				startPos,revision);
		Vector2D topRight = Vector2D.Sub(vector2DDomain,
				endPos,revision);
		Vector2D underLeft = Vector2D.Add(vector2DDomain,
				startPos,revision);
		Vector2D underRight = Vector2D.Add(vector2DDomain,
				endPos,revision);
		
		topLeft = Utile.translatScreenPoint(topLeft);
		topRight = Utile.translatScreenPoint(topRight);
		underLeft = Utile.translatScreenPoint(underLeft);
		underRight = Utile.translatScreenPoint(underRight);
		
		float[] vertex = {
			topLeft.x,topLeft.y,0.0f,
			topRight.x,topRight.y,0.0f,
			underLeft.x,underLeft.y,0.0f,
			underRight.x,underRight.y,0.0f,
		};
		
		float[] uv = {
			0.0f,	0.0f,
			0.0f,	1.0f,
			1.0f,	0.0f,
			1.0f,	1.0f,
		};
		
		byte[] index = {0,1,2, 1,2,3};
		
		Graphics.drawTexture(vertex,uv,index,color,TextureManager.getTexture("Line"),BlendMode.ALPHA);
	}
}
