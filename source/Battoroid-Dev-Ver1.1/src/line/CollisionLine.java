package line;

import collision.Collision;
import object2D.Object2D;
import function.ColorData;
import function.Vector2D;
import function.Vector2DDomain;

public class CollisionLine {
	private Vector2DDomain vector2DDomain;
	public enum CollisionMode{
		REFLECT,
		SLIDE,
	};
	private Line[] collisionLine;
	private VectorOperationFunction reflectFunction;
	private VectorOperationFunction slideFunction;
	private VectorOperationFunction playFunction;
	private CollisionMode collisionMode;
	
	public void setCollisionMode(CollisionMode collisionMode){
		this.collisionMode = collisionMode;
		switch(collisionMode){
		case REFLECT:
			playFunction = reflectFunction;
			break;
		case SLIDE:
			playFunction = slideFunction;
			break;
		}
	}
	
	public void setCollisionFunction(VectorOperationFunction playFunction){
		this.playFunction = playFunction;
	}
	
	public CollisionMode getCollisionMode(){return collisionMode;}
	
	public CollisionLine(CollisionMode collisionMode,Line... collisionLine){
		vector2DDomain = new Vector2DDomain();
		this.collisionLine = collisionLine;
		reflectFunction = new AbstractVectorOperationFunction() {
			@Override
			public Vector2D VectorOperation(Vector2DDomain vector2DDomain,Vector2D spd,Vector2D nor,float coefficient){
				return Vector2D.Reflect(vector2DDomain,
						spd,nor,coefficient);
			}
		};
		slideFunction = new AbstractVectorOperationFunction() {
			@Override
			public Vector2D VectorOperation(Vector2DDomain vector2DDomain,Vector2D spd,Vector2D nor,float coefficient){
				return Vector2D.Slide(vector2DDomain,
						spd,nor,coefficient);
			}
		};
		
		setCollisionMode(collisionMode);
	}
	// ���̒ǉ�
	public void addLine(Line... colisionLine){
		this.collisionLine = composeArray(this.collisionLine,colisionLine);
	}
	
	// �����蔻��
	public boolean collision(Object2D object,float coefficient){
		boolean isHit = false;
		for(int i = 0;i<collisionLine.length;i++){
			if(object.getIsExistence()){
				if(Collision.circleToCircle(object.getPos().x,object.getPos().y,(float)object.getRad(),collisionLine[i].getCenter().x,collisionLine[i].getCenter().y,(float)collisionLine[i].getRadius())){
					if(Collision.segmentToCircle(object,collisionLine[i])){
						changePos(object,collisionLine[i]);
						CollisionPostprocessing.segment(vector2DDomain,
								object,collisionLine[i],coefficient,playFunction);
						isHit = true;
					}
					if(Collision.circleToCircle(object.getPos(),object.getRad(),collisionLine[i].startPos,1)){
						Vector2D v = Vector2D.Sub(vector2DDomain,
								object.getPos(),collisionLine[i].startPos);
						v = Vector2D.Normalize(vector2DDomain,
								v);
						v = Vector2D.Scale(vector2DDomain,
								v,object.getRad());
						Vector2D.Add(object.getPos(),v,collisionLine[i].startPos);
						CollisionPostprocessing.point(vector2DDomain,object,collisionLine[i].startPos,coefficient,playFunction);
					}
					if(Collision.circleToCircle(object.getPos(),object.getRad(),collisionLine[i].endPos,1)){
						Vector2D v = Vector2D.Sub(vector2DDomain,
								object.getPos(),collisionLine[i].endPos);
						v = Vector2D.Normalize(vector2DDomain,
								v);
						v = Vector2D.Scale(vector2DDomain,
								v,object.getRad());
						Vector2D.Add(object.getPos(),v,collisionLine[i].endPos);
						CollisionPostprocessing.point(vector2DDomain,object,collisionLine[i].endPos,coefficient,playFunction);
					}
				}
				
			}
		}
		return isHit;
	}
	
	// ���̕\��
	public void drawLine(float thickness,ColorData color){
		for(int i=0;i<collisionLine.length;i++){
			collisionLine[i].drawLine(thickness,color);
		}
	}
	
	// �ʒu����
	private void changePos(Object2D object,Line line){
		// �@�������߂�
		Vector2D normal =	Vector2D.Normalize(vector2DDomain,
							Vector2D.Perp(vector2DDomain,		
							Vector2D.Sub(vector2DDomain,
									line.endPos,line.startPos)));
		// �@�����I�u�W�F�N�g�̔��a�̒����ɕ␳
		Vector2D radLength = Vector2D.Scale(vector2DDomain,
				object.getRad(),normal);
		// �␳��̖@�����I�u�W�F�N�g����o���A���Ƃ̌�_�����߂�
		Vector2D crossPoint = Vector2D.CrossPoint(vector2DDomain,
				line.startPos,line.endPos,
				object.getPos(),Vector2D.Add(vector2DDomain,
						object.getPos(),radLength));
		// �ʒu�␳���钷�������߂�
		float moveLength =	object.getRad() - 
							Vector2D.Length(vector2DDomain,Vector2D.Sub(vector2DDomain,
									object.getPos(),crossPoint));
		// �␳����ړ��x�N�g�������߂�
		Vector2D moveVector = Vector2D.Scale(vector2DDomain,
				-moveLength,normal);
		// �ʒu�␳
		Vector2D.Add(object.getPos(),object.getPos(),moveVector);
	}
	
	// �z�񍇐�
	public static Line[] composeArray(Line[] array1,Line[] array2){
		Line[] array = new Line[array1.length + array2.length]; 
		for(int i = 0;i < array1.length + array2.length;i++){
			if(i < array1.length){
				array[i] = array1[i];
			}
			else{
				array[i] = array2[i - array1.length];
			}
		}
		return array;
	}
}
