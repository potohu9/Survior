package gameObject;

import object2D.Object2D;
import line.CollisionLine;
import line.CollisionLine.CollisionMode;
import line.Line;
import function.TextureManager;
import function.Utile;
import function.Vector2D;
import function.Vector2DDomain;

public class OptionType {
	// èWíÜ
	public static OptionFunction Type1(final int optionNum,final float lengthAddVel,final float minLength,final float maxLength,final float rotVel){
		return new AbstractOptionFunction(){
			private int[] angle;
			private float[] rotLength;
			private boolean[] isAddLength;
			
			@Override
			public void initialize() {
				int angleAdditionNum = 360/optionNum;
				int angle = 0;
				this.angle = new int[optionNum];
				rotLength = new float[optionNum];
				isAddLength = new boolean[optionNum];
				for(int i=0;i<optionNum;i++){	
					this.angle[i] = angle;
					angle += angleAdditionNum;
					rotLength[i] = minLength;
					isAddLength[i] = true;
				}
			}
			
			@Override
			public void update(gameObject.Options options,Vector2D aimingPoint) {
				for(int i=0;i<optionNum;i++){
					if(rotLength[i] <= minLength&&!isAddLength[i]){isAddLength[i] = true;}
					if(rotLength[i] >= maxLength&&isAddLength[i]){isAddLength[i] = false;}
					if(isAddLength[i])	{rotLength[i]+=lengthAddVel;}
					else				{rotLength[i]-=lengthAddVel;}
					Vector2D pos = Utile.rotatePoint(angle[i],rotLength[i],options.getPos());
					options.getOptions(i).setGeneratPoint(pos,options.getOptions(i).getGeneratPointMargin());
					options.getOptions(i).update();
					angle[i] += rotVel;
					options.getBulletGenerator(i).setDischargePoint(options.getOptions(i).getGeneratBasePoint());
					options.getBulletGenerator(i).update();
					if(!isAddLength[i]){
						options.getBulletGenerator(i).setAimingPoint(aimingPoint);
						options.getBulletGenerator(i).setIsDischarge(true);	
					}
					else{
						options.getBulletGenerator(i).setIsDischarge(false);
					}
				}
			}		
		};
	}
	
	// ÇŒÇÁÇ‹Ç´
	public static OptionFunction Type2(final Vector2DDomain vector2DDomain,final int optionNum,final float limitX,final float vel,final GameObject holder,final Line... line){
		return new AbstractOptionFunction(){
			GameObject[] objcet;
			CollisionLine collisionLine;

			@Override
			public void initialize() {
				collisionLine = new CollisionLine(CollisionMode.REFLECT,line);
				objcet = new GameObject[optionNum];
				int angleAdditionNum = 360/optionNum;
				int angle = 15;
				for(int i=0;i<optionNum;i++){
					objcet[i] = new GameObject(TextureManager.getTexture("Light"),
							GameObjectFunctionFactory.makeMoveObject(vector2DDomain).compose(
							new AbstractGameObjectFunction(){
								@Override
								public void changeState(Object2D object) {
									collisionLine.collision(object,1.0f);
								}
							}));
					Vector2D pos = Utile.rotatePoint(angle,holder.getRad(),holder.getPos());
					angle += angleAdditionNum;
					objcet[i].setPos(pos);
					Vector2D vec = Vector2D.Scale(vector2DDomain,vel,Vector2D.Normalize(vector2DDomain,
							Vector2D.Sub(vector2DDomain,pos,holder.getPos()))).getClone();
					objcet[i].setVec(vec);
				}
			}
			
			@Override
			public void update(gameObject.Options options,Vector2D aimingPoint) {
				for(int i=0;i<optionNum;i++){
					objcet[i].setRad(options.getOptions(i).getParticleBaseScale()*0.5f);
					objcet[i].update();
					Vector2D pos = objcet[i].getPos();
					options.getOptions(i).setGeneratPoint(pos,options.getOptions(i).getGeneratPointMargin());
					options.getOptions(i).update();
					options.getBulletGenerator(i).setDischargePoint(options.getOptions(i).getGeneratBasePoint());
					options.getBulletGenerator(i).update();
					if(objcet[i].getPos().x > limitX){
						options.getBulletGenerator(i).setAimingPoint(aimingPoint);
						options.getBulletGenerator(i).setIsDischarge(true);
					}
					else{
						options.getBulletGenerator(i).setIsDischarge(false);
					}
				}
			}	
		};
	}
}
