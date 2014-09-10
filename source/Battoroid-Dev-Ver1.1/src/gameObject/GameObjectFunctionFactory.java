package gameObject;

import collision.Collision;
import function.Vector2D;
import function.Vector2DDomain;
import object2D.Object2D;
import visionField.VisionFieldFunction;

public class GameObjectFunctionFactory {
	/*戻り値：処理無し関数*/
	/*処理無し関数作成*/
	public static GameObjectFunction makeNotProcessing(){
		return new AbstractGameObjectFunction(){
			@Override
			public void changeState(Object2D object) {
				
			}
		};
	}
	
	/*戻り値：オブジェクト移動関数*/
	/*オブジェクト移動関数作成*/
	public static GameObjectFunction makeMoveObject(final Vector2DDomain vector2DDomain){
		return new AbstractGameObjectFunction(){
			@Override
			public void changeState(Object2D object) {
				object.setPos(Vector2D.Add(vector2DDomain,
						object.getPos(),object.getVec()).getClone());
			}
		};
	}
	
	/*引数：移動可能範囲*/
	/*戻り値：移動可能範囲の指定関数*/
	/*移動可能範囲の指定関数作成*/
	public static GameObjectFunction makeHitIsNotExistence(final GameObject... collisionObject){
		return new AbstractGameObjectFunction(){
			@Override
			public void changeState(Object2D object) {
				for(int i=0;i<collisionObject.length;i++){
					if(collisionObject[i].isExistence){
						if(Collision.circleToCircle(object,collisionObject[i])){
							collisionObject[i].setIsExistence(false);
						}
					}
				}
			}
		};
	}
	
	/*引数：移動可能範囲*/
	/*戻り値：移動可能範囲の指定関数*/
	/*移動可能範囲の指定関数作成*/
	public static GameObjectFunction makeCanMovementDesignationRange(final Vector2D areaStartPoint,final Vector2D areaEndPoint){
		return new AbstractGameObjectFunction(){
			@Override
			public void changeState(Object2D object) {
				if(	object.getVec().x > 0 && object.getPos().x > areaEndPoint.x ||
					object.getVec().x < 0 && object.getPos().x < areaStartPoint.x){
					object.setVec(new Vector2D(0,object.getVec().y));
				}
				if(	object.getVec().y > 0 && object.getPos().y > areaEndPoint.y ||
					object.getVec().y < 0 && object.getPos().y < areaStartPoint.y){
					object.setVec(new Vector2D(object.getVec().x,0));
				}
			}
		};
	}
	
	/*引数：移動可能範囲*/
	/*戻り値：移動可能範囲まで来たら移動方向を反転する関数*/
	/*移動可能範囲まで来たら移動方向を反転する関数作成*/
	public static GameObjectFunction makeInversionDesignationRange(final Vector2D areaStartPoint,final Vector2D areaEndPoint){
		return new AbstractGameObjectFunction(){
			@Override
			public void changeState(Object2D object) {
				if(	object.getVec().x > 0 && object.getPos().x > areaEndPoint.x ||
					object.getVec().x < 0 && object.getPos().x < areaStartPoint.x){
					object.setVec(new Vector2D(object.getVec().x * -1,object.getVec().y));
				}
				if(	object.getVec().y > 0 && object.getPos().y > areaEndPoint.y ||
					object.getVec().y < 0 && object.getPos().y < areaStartPoint.y){
					object.setVec(new Vector2D(object.getVec().x,object.getVec().y * -1));
				}
			}
		};
	}
	
	/*引数：重力のエネルギー*/
	/*戻り値：指定された重力エネルギーを物体にかける関数*/
	/*指定された重力エネルギーを物体にかける関数作成*/
	public static GameObjectFunction makeSetGravity(final Vector2DDomain vector2DDomain,final Vector2D value){
		return new AbstractGameObjectFunction(){
			@Override
			public void changeState(Object2D object) {
				object.setVec(Vector2D.Add(vector2DDomain,
						object.getVec(),value).getClone());
			}
		};
	}
	
	//-----速度固定、避ける------
	
	/*引数：避ける精度、オブジェクトの速度、複数の点*/
	/*戻り値：複数の点から最も近い点から固定速度で指定された精度でよけようとする関数*/
	/*複数の点から最も近い点から固定速度で指定された精度でよけようとする関数作成*/
	public static GameObjectFunction makeSpeedFixationAvoidMultipleNearPoint(final Vector2DDomain vector2DDomain,final float precision,final float moveSpeed,final Object2D... avoidObject){
		return new AbstractGameObjectFunction(){
			@Override
			public void changeState(Object2D object) {
				boolean isExistenceBullet = false;
				Vector2D nearPoint = new Vector2D();
				float nearLength = 10000;
				for(int i = 0;i<avoidObject.length;i++){
					if(object == avoidObject[i]){
						continue;
					}
					if(avoidObject[i].getIsExistence()){
						float length = Vector2D.Length(vector2DDomain,
								Vector2D.Sub(vector2DDomain,
								object.getPos(),avoidObject[i].getPos()));
						if(nearLength > length){
							nearLength = length;
							nearPoint.x = avoidObject[i].getPos().x;
							nearPoint.y = avoidObject[i].getPos().y;
						}
						isExistenceBullet = true;
					}
				}
				if(isExistenceBullet){
					Vector2D addVec = Vector2D.Scale(vector2DDomain,
							precision,Vector2D.Normalize(vector2DDomain,
							Vector2D.Sub(vector2DDomain,
							object.getPos(),nearPoint)));
					object.setVec(Vector2D.Scale(vector2DDomain,
							moveSpeed,Vector2D.Normalize(vector2DDomain,
							Vector2D.Add(vector2DDomain,
							object.getVec(),addVec))).getClone());
				}
			}
		};
	}
	
	/*引数：視界の形、視界の長さ、避ける精度、オブジェクトの速度、複数の点*/
	/*戻り値：視界内の複数の点から最も近い点から固定速度で指定された精度でよけようとする関数*/
	/*視界内の複数の点から最も近い点から固定速度で指定された精度でよけようとする関数作成*/
	public static GameObjectFunction makeSpeedFixationAvoidMultipleNearPoint(final Vector2DDomain vector2DDomain,final VisionFieldFunction visionFieldFunction,final float visionFieldLength,final float precision,final float moveSpeed,final Object2D... avoidObject){
		return new AbstractGameObjectFunction(){
			@Override
			public void changeState(Object2D object) {
				boolean isExistenceBullet = false;
				Vector2D nearPoint = new Vector2D();
				float nearLength = 10000;
				for(int i = 0;i<avoidObject.length;i++){
					if(avoidObject[i].getIsExistence()){
						if(object == avoidObject[i]){
							continue;
						}
						if(visionFieldFunction.isInVisionField(object,avoidObject[i])){
							float length = Vector2D.Length(vector2DDomain,
									Vector2D.Sub(vector2DDomain,
									object.getPos(),avoidObject[i].getPos()));
							if(nearLength > length){
								nearLength = length;
								nearPoint.x = avoidObject[i].getPos().x;
								nearPoint.y = avoidObject[i].getPos().y;
							}
							isExistenceBullet = true;
						}
					}
				}
				if(isExistenceBullet){
					float newPrecision = (1.0f - nearLength / visionFieldLength) * precision;
					Vector2D addVec = Vector2D.Scale(vector2DDomain,		
							newPrecision,Vector2D.Normalize(vector2DDomain,				
							Vector2D.Sub(vector2DDomain,					
							object.getPos(),nearPoint)));
					object.setVec(Vector2D.Scale(vector2DDomain,						
							moveSpeed,Vector2D.Normalize(vector2DDomain,		
							Vector2D.Add(vector2DDomain,				
							object.getVec(),addVec))).getClone());
				}
			}
		};
	}
	
	/*引数：避ける精度、複数の点*/
	/*戻り値：複数の点から最も近い点から固定速度で指定された精度でよけようとする関数*/
	/*複数の点から最も近い点から固定速度で指定された精度でよけようとする関数作成*/
	public static GameObjectFunction makeSpeedFixationAvoidMultipleNearPoint(final Vector2DDomain vector2DDomain,final float precision,final Object2D... avoidObject){
		return new AbstractGameObjectFunction(){
			@Override
			public void changeState(Object2D object) {
				boolean isExistenceBullet = false;
				Vector2D nearPoint = new Vector2D();
				float nearLength = 10000;
				for(int i = 0;i<avoidObject.length;i++){
					if(object == avoidObject[i]){
						continue;
					}
					if(avoidObject[i].getIsExistence()){
						float length = Vector2D.Length(vector2DDomain,
								Vector2D.Sub(vector2DDomain,
								object.getPos(),avoidObject[i].getPos()));
						if(nearLength > length){
							nearLength = length;
							nearPoint.x = avoidObject[i].getPos().x;
							nearPoint.y = avoidObject[i].getPos().y;
						}
						isExistenceBullet = true;
					}
				}
				if(isExistenceBullet){
					float moveSpeed = Vector2D.Length(vector2DDomain,
							object.getVec());
					Vector2D addVec = Vector2D.Scale(vector2DDomain,
							precision,Vector2D.Normalize(vector2DDomain,
							Vector2D.Sub(vector2DDomain,
							object.getPos(),nearPoint)));
					object.setVec(Vector2D.Scale(vector2DDomain,
							moveSpeed,Vector2D.Normalize(vector2DDomain,
							Vector2D.Add(vector2DDomain,
							object.getVec(),addVec))).getClone());
				}
			}
		};
	}

	/*引数：視界の形、視界の長さ、避ける精度、複数の点*/
	/*戻り値：視界内の複数の点から最も近い点から固定速度で指定された精度でよけようとする関数*/
	/*視界内の複数の点から最も近い点から固定速度で指定された精度でよけようとする関数作成*/
	public static GameObjectFunction makeSpeedFixationAvoidMultipleNearPoint(final Vector2DDomain vector2DDomain,final VisionFieldFunction visionFieldFunction,final float visionFieldLength,final float precision,final Object2D... avoidObject){
		return new AbstractGameObjectFunction(){
			@Override
			public void changeState(Object2D object) {
				boolean isExistenceBullet = false;
				Vector2D nearPoint = new Vector2D();
				float nearLength = 10000;
				for(int i = 0;i<avoidObject.length;i++){
					if(object == avoidObject[i]){
						continue;
					}
					if(avoidObject[i].getIsExistence()){
						if(visionFieldFunction.isInVisionField(object,avoidObject[i])){
							float length = Vector2D.Length(vector2DDomain,Vector2D.Sub(vector2DDomain,
									object.getPos(),avoidObject[i].getPos()));
							if(nearLength > length){
								nearLength = length;
								nearPoint.x = avoidObject[i].getPos().x;
								nearPoint.y = avoidObject[i].getPos().y;
							}
							isExistenceBullet = true;
						}
					}
				}
				if(isExistenceBullet){
					float newPrecision = (1.0f - nearLength / visionFieldLength) * precision;
					float moveSpeed = Vector2D.Length(vector2DDomain,object.getVec());
					Vector2D addVec = Vector2D.Scale(vector2DDomain,
							newPrecision,Vector2D.Normalize(vector2DDomain,
							Vector2D.Sub(vector2DDomain,
							object.getPos(),nearPoint)));
					object.setVec(Vector2D.Scale(vector2DDomain,
							moveSpeed,Vector2D.Normalize(vector2DDomain,
							Vector2D.Add(vector2DDomain,
							object.getVec(),addVec))).getClone());
				}
			}
		};
	}
	
	//-----速度固定、近づく------

	/*引数：視界の形、視界の長さ、近づく精度、複数の点*/
	/*戻り値：視界内の複数の点から最も近い点から固定速度で指定された精度で近づこうとする関数*/
	/*視界内の複数の点から最も近い点から固定速度で指定された精度で近づこうとする関数作成*/
	public static GameObjectFunction makeSpeedFixationApproachMultipleNearPoint(final Vector2DDomain vector2DDomain,final VisionFieldFunction visionFieldFunction,final float visionFieldLength,final float precision,final Object2D... approachObject){
		return new AbstractGameObjectFunction(){
			@Override
			public void changeState(Object2D object) {
				boolean isExistenceBullet = false;
				Vector2D nearPoint = new Vector2D();
				float nearLength = 10000;
				for(int i = 0;i<approachObject.length;i++){
					if(object == approachObject[i]){
						continue;
					}
					if(approachObject[i].getIsExistence()){
						if(visionFieldFunction.isInVisionField(object,approachObject[i])){
							float length = Vector2D.Length(vector2DDomain,
									Vector2D.Sub(vector2DDomain,
									object.getPos(),approachObject[i].getPos()));
							if(nearLength > length){
								nearLength = length;
								nearPoint.x = approachObject[i].getPos().x;
								nearPoint.y = approachObject[i].getPos().y;
							}
							isExistenceBullet = true;
						}
					}
				}
				if(isExistenceBullet){
					float newPrecision = (1.0f - nearLength / visionFieldLength) * precision;
					float moveSpeed = Vector2D.Length(vector2DDomain,object.getVec());
					Vector2D addVec = Vector2D.Scale(vector2DDomain,
							newPrecision,Vector2D.Normalize(vector2DDomain,
							Vector2D.Sub(vector2DDomain,
							nearPoint,object.getPos())));
					object.setVec(Vector2D.Scale(vector2DDomain,
							moveSpeed,Vector2D.Normalize(vector2DDomain,
							Vector2D.Add(vector2DDomain,
							object.getVec(),addVec))).getClone());
				}
			}
		};
	}
	
	/*引数：近づく精度、オブジェクトの速度、複数の点*/
	/*戻り値：複数の点から最も近い点から固定速度で指定された精度で近づこうとする関数*/
	/*複数の点から最も近い点から固定速度で指定された精度で近づこうとする関数作成*/
	public static GameObjectFunction makeSpeedFixationApproachMultipleNearPoint(final Vector2DDomain vector2DDomain,final float precision,final float moveSpeed,final Object2D... approachObject){
		return new AbstractGameObjectFunction(){
			@Override
			public void changeState(Object2D object) {
				boolean isExistenceBullet = false;
				Vector2D nearPoint = new Vector2D();
				float nearLength = 10000;
				for(int i = 0;i<approachObject.length;i++){
					if(object == approachObject[i]){
						continue;
					}
					if(approachObject[i].getIsExistence()){
						float length = Vector2D.Length(vector2DDomain,
								Vector2D.Sub(vector2DDomain,
								object.getPos(),approachObject[i].getPos()));
						if(nearLength > length){
							nearLength = length;
							nearPoint.x = approachObject[i].getPos().x;
							nearPoint.y = approachObject[i].getPos().y;
						}
						isExistenceBullet = true;
					}
				}
				if(isExistenceBullet){
					Vector2D addVec = Vector2D.Scale(vector2DDomain,
							precision,Vector2D.Normalize(vector2DDomain,
							Vector2D.Sub(vector2DDomain,
							nearPoint,object.getPos())));
					object.setVec(Vector2D.Scale(vector2DDomain,
							moveSpeed,Vector2D.Normalize(vector2DDomain,
							Vector2D.Add(vector2DDomain,
							object.getVec(),addVec))).getClone());
				}
			}
		};
	}
	
	/*引数：近づく精度、複数の点*/
	/*戻り値：複数の点から最も近い点から固定速度で指定された精度で近づこうとする関数*/
	/*複数の点から最も近い点から固定速度で指定された精度で近づこうとする関数作成*/
	public static GameObjectFunction makeSpeedFixationApproachMultipleNearPoint(final Vector2DDomain vector2DDomain,final float precision,final Object2D... approachObject){
		return new AbstractGameObjectFunction(){
			@Override
			public void changeState(Object2D object) {
				boolean isExistenceBullet = false;
				Vector2D nearPoint = new Vector2D();
				float nearLength = 10000;
				for(int i = 0;i<approachObject.length;i++){
					if(object == approachObject[i]){
						continue;
					}
					if(approachObject[i].getIsExistence()){
						float length = Vector2D.Length(vector2DDomain,
								Vector2D.Sub(vector2DDomain,
								object.getPos(),approachObject[i].getPos()));
						if(nearLength > length){
							nearLength = length;
							nearPoint.x = approachObject[i].getPos().x;
							nearPoint.y = approachObject[i].getPos().y;
						}
						isExistenceBullet = true;
					}
				}
				if(isExistenceBullet){
					float moveSpeed = Vector2D.Length(vector2DDomain,object.getVec());
					Vector2D addVec = Vector2D.Scale(vector2DDomain,
							precision,Vector2D.Normalize(vector2DDomain,
							Vector2D.Sub(vector2DDomain,
							nearPoint,object.getPos())));
					object.setVec(Vector2D.Scale(vector2DDomain,
							moveSpeed,Vector2D.Normalize(vector2DDomain,
									Vector2D.Add(vector2DDomain,
							object.getVec(),addVec))).getClone());
				}
			}
		};
	}
	
	/*引数：視界の形、視界の長さ、近づく精度、複数の点*/
	/*戻り値：視界内の複数の点から最も近い点から固定速度で指定された精度で近づこうとする関数*/
	/*視界内の複数の点から最も近い点から固定速度で指定された精度で近づこうとする関数作成*/
	public static GameObjectFunction makeSpeedFixationApproachMultipleNearPoint(final Vector2DDomain vector2DDomain,final VisionFieldFunction visionFieldFunction,final float visionFieldLength,final float precision,final float moveSpeed,final Object2D... approachObject){
		return new AbstractGameObjectFunction(){
			@Override
			public void changeState(Object2D object) {
				boolean isExistenceBullet = false;
				Vector2D nearPoint = new Vector2D();
				float nearLength = 10000;
				for(int i = 0;i<approachObject.length;i++){
					if(object == approachObject[i]){
						continue;
					}
					if(approachObject[i].getIsExistence()){
						if(visionFieldFunction.isInVisionField(object,approachObject[i])){
							float length = Vector2D.Length(vector2DDomain,
									Vector2D.Sub(vector2DDomain,
									object.getPos(),approachObject[i].getPos()));
							if(nearLength > length){
								nearLength = length;
								nearPoint.x = approachObject[i].getPos().x;
								nearPoint.y = approachObject[i].getPos().y;
							}
							isExistenceBullet = true;
						}
					}
				}
				if(isExistenceBullet){
					float newPrecision = (1.0f - nearLength / visionFieldLength) * precision;
					Vector2D addVec = Vector2D.Scale(vector2DDomain,
							newPrecision,Vector2D.Normalize(vector2DDomain,
							Vector2D.Sub(vector2DDomain,
							nearPoint,object.getPos())));
					object.setVec(Vector2D.Scale(vector2DDomain,
							moveSpeed,Vector2D.Normalize(vector2DDomain,
							Vector2D.Add(vector2DDomain,
							object.getVec(),addVec))).getClone());
				}
			}
		};
	}
	
	//-----速度制限、避ける-----
	
	/*引数：避ける精度、オブジェクトの速度、複数の点*/
	/*戻り値：複数の点から最も近い点から制限された速度までの速度で指定された精度でよけようとする関数*/
	/*複数の点から最も近い点から制限された速度までの速度で指定された精度でよけようとする関数作成*/
	public static GameObjectFunction makeSpeedLimitAvoidMultipleNearPoint(final Vector2DDomain vector2DDomain,final float precision,final float moveSpeed,final Object2D... avoidObject){
		return new AbstractGameObjectFunction(){
			@Override
			public void changeState(Object2D object) {
				boolean isExistenceBullet = false;
				Vector2D nearPoint = new Vector2D();
				float nearLength = 10000;
				for(int i = 0;i<avoidObject.length;i++){
					if(object == avoidObject[i]){
						continue;
					}
					if(avoidObject[i].getIsExistence()){
						float length = Vector2D.Length(vector2DDomain,
								Vector2D.Sub(vector2DDomain,
								object.getPos(),avoidObject[i].getPos()));
						if(nearLength > length){
							nearLength = length;
							nearPoint.x = avoidObject[i].getPos().x;
							nearPoint.y = avoidObject[i].getPos().y;
						}
						isExistenceBullet = true;
					}
				}
				if(isExistenceBullet){
					Vector2D subVec = Vector2D.Sub(vector2DDomain,
							object.getPos(),nearPoint);
					Vector2D addVec = Vector2D.Scale(vector2DDomain,
							precision,Vector2D.Normalize(vector2DDomain,
							subVec));
					addVec = Vector2D.Add(vector2DDomain,
							object.getVec(),addVec);
					Vector2D newVec = addVec;
					if(Vector2D.Length(vector2DDomain,addVec) > moveSpeed){
						newVec = Vector2D.Scale(vector2DDomain,
								moveSpeed,Vector2D.Normalize(vector2DDomain,
								addVec));
					}		
					object.setVec(newVec.getClone());
				}
			}
		};
	}
	
	/*引数：視界の形、視界の長さ、避ける精度、オブジェクトの速度、複数の点*/
	/*戻り値：視界内の複数の点から最も近い点から制限された速度までの速度で指定された精度でよけようとする関数*/
	/*視界内の複数の点から最も近い点から制限された速度までの速度で指定された精度でよけようとする関数作成*/
	public static GameObjectFunction makeSpeedLimitAvoidMultipleNearPoint(final Vector2DDomain vector2DDomain,final VisionFieldFunction visionFieldFunction,final float visionFieldLength,final float precision,final float moveSpeed,final Object2D... avoidObject){
		return new AbstractGameObjectFunction(){
			@Override
			public void changeState(Object2D object) {
				boolean isExistenceBullet = false;
				Vector2D nearPoint = new Vector2D();
				float nearLength = 10000;
				for(int i = 0;i<avoidObject.length;i++){
					if(object == avoidObject[i]){
						continue;
					}
					if(avoidObject[i].getIsExistence()){
						if(visionFieldFunction.isInVisionField(object,avoidObject[i])){
							float length = Vector2D.Length(vector2DDomain,
									Vector2D.Sub(vector2DDomain,
									object.getPos(),avoidObject[i].getPos()));
							if(nearLength > length){
								nearLength = length;
								nearPoint.x = avoidObject[i].getPos().x;
								nearPoint.y = avoidObject[i].getPos().y;
							}
							isExistenceBullet = true;
						}
					}
				}
				if(isExistenceBullet){
					float newPrecision = (1.0f - nearLength / visionFieldLength) * precision;
					
					Vector2D subVec = Vector2D.Sub(vector2DDomain,
							object.getPos(),nearPoint);
					Vector2D addVec = Vector2D.Scale(vector2DDomain,
							newPrecision,Vector2D.Normalize(vector2DDomain,
							subVec));
					addVec = Vector2D.Add(vector2DDomain,
							object.getVec(),addVec);
					Vector2D newVec = addVec;
					if(Vector2D.Length(vector2DDomain,addVec) > moveSpeed){
						newVec = Vector2D.Scale(vector2DDomain,
								moveSpeed,Vector2D.Normalize(vector2DDomain,
								addVec));
					}		
					object.setVec(newVec.getClone());
				}
			}
		};
	}
	
	/*引数：避ける精度、複数の点*/
	/*戻り値：複数の点から最も近い点から制限された速度までの速度で指定された精度でよけようとする関数*/
	/*複数の点から最も近い点から制限された速度までの速度で指定された精度でよけようとする関数作成*/
	public static GameObjectFunction makeSpeedLimitAvoidMultipleNearPoint(final Vector2DDomain vector2DDomain,final float precision,final Object2D... avoidObject){
		return new AbstractGameObjectFunction(){
			@Override
			public void changeState(Object2D object) {
				boolean isExistenceBullet = false;
				Vector2D nearPoint = new Vector2D();
				float nearLength = 10000;
				for(int i = 0;i<avoidObject.length;i++){
					if(object == avoidObject[i]){
						continue;
					}
					if(avoidObject[i].getIsExistence()){
						float length = Vector2D.Length(vector2DDomain,
								Vector2D.Sub(vector2DDomain,
								object.getPos(),avoidObject[i].getPos()));
						if(nearLength > length){
							nearLength = length;
							nearPoint.x = avoidObject[i].getPos().x;
							nearPoint.y = avoidObject[i].getPos().y;
						}
						isExistenceBullet = true;
					}
				}
				if(isExistenceBullet){
					float moveSpeed = Vector2D.Length(vector2DDomain,object.getVec());
					Vector2D subVec = Vector2D.Sub(vector2DDomain,
							object.getPos(),nearPoint);
					Vector2D addVec = Vector2D.Scale(vector2DDomain,
							precision,Vector2D.Normalize(vector2DDomain,
							subVec));
					addVec = Vector2D.Add(vector2DDomain,
							object.getVec(),addVec);
					Vector2D newVec = addVec;
					if(Vector2D.Length(vector2DDomain,addVec) > moveSpeed){
						newVec = Vector2D.Scale(vector2DDomain,
								moveSpeed,Vector2D.Normalize(vector2DDomain,
								addVec));
					}		
					object.setVec(newVec.getClone());
				}
			}
		};
	}
	
	/*引数：視界の形、視界の長さ、避ける精度、複数の点*/
	/*戻り値：視界内の複数の点から最も近い点から制限された速度までの速度で指定された精度でよけようとする関数*/
	/*視界内の複数の点から最も近い点から制限された速度までの速度で指定された精度でよけようとする関数作成*/
	public static GameObjectFunction makeSpeedLimitAvoidMultipleNearPoint(final Vector2DDomain vector2DDomain,final VisionFieldFunction visionFieldFunction,final float visionFieldLength,final float precision,final Object2D... avoidObject){
		return new AbstractGameObjectFunction(){
			@Override
			public void changeState(Object2D object) {
				boolean isExistenceBullet = false;
				Vector2D nearPoint = new Vector2D();
				float nearLength = 10000;
				for(int i = 0;i<avoidObject.length;i++){
					if(object == avoidObject[i]){
						continue;
					}
					if(avoidObject[i].getIsExistence()){
						if(visionFieldFunction.isInVisionField(object,avoidObject[i])){
							float length = Vector2D.Length(vector2DDomain,
									Vector2D.Sub(vector2DDomain,
									object.getPos(),avoidObject[i].getPos()));
							if(nearLength > length){
								nearLength = length;
								nearPoint.x = avoidObject[i].getPos().x;
								nearPoint.y = avoidObject[i].getPos().y;
							}
							isExistenceBullet = true;
						}
					}
				}
				if(isExistenceBullet){
					float newPrecision = (1.0f - nearLength / visionFieldLength) * precision;
					float moveSpeed = Vector2D.Length(vector2DDomain,object.getVec());
					Vector2D subVec = Vector2D.Sub(vector2DDomain,
							object.getPos(),nearPoint);
					Vector2D addVec = Vector2D.Scale(vector2DDomain,
							newPrecision,Vector2D.Normalize(vector2DDomain,
							subVec));
					addVec = Vector2D.Add(vector2DDomain,
							object.getVec(),addVec);
					Vector2D newVec = addVec;
					if(Vector2D.Length(vector2DDomain,addVec) > moveSpeed){
						newVec = Vector2D.Scale(vector2DDomain,
								moveSpeed,Vector2D.Normalize(vector2DDomain,
								addVec));
					}		
					object.setVec(newVec.getClone());
				}
			}
		};
	}
	
	//-----速度制限、近づく-----
	
	/*引数：近づく精度、オブジェクトの速度、複数の点*/
	/*戻り値：複数の点から最も近い点から制限された速度までの速度で指定された精度で近づこうとする関数*/
	/*複数の点から最も近い点から制限された速度までの速度で指定された精度で近づこうとする関数作成*/
	public static GameObjectFunction makeSpeedLimitApproachMultipleNearPoint(final Vector2DDomain vector2DDomain,final float precision,final float moveSpeed,final Object2D... approachObject){
		return new AbstractGameObjectFunction(){
			@Override
			public void changeState(Object2D object) {
				boolean isExistenceBullet = false;
				Vector2D nearPoint = new Vector2D();
				float nearLength = 10000;
				for(int i = 0;i<approachObject.length;i++){
					if(object == approachObject[i]){
						continue;
					}
					if(approachObject[i].getIsExistence()){
						float length = Vector2D.Length(vector2DDomain,
								Vector2D.Sub(vector2DDomain,
								object.getPos(),approachObject[i].getPos()));
						if(nearLength > length){
							nearLength = length;
							nearPoint.x = approachObject[i].getPos().x;
							nearPoint.y = approachObject[i].getPos().y;
						}
						isExistenceBullet = true;
					}
				}
				if(isExistenceBullet){
					Vector2D subVec = Vector2D.Sub(vector2DDomain,
							nearPoint,object.getPos());
					Vector2D addVec = Vector2D.Scale(vector2DDomain,
							precision,Vector2D.Normalize(vector2DDomain,
							subVec));
					addVec = Vector2D.Add(vector2DDomain,
							object.getVec(),addVec);
					Vector2D newVec = addVec;
					if(Vector2D.Length(vector2DDomain,addVec) > moveSpeed){
						newVec = Vector2D.Scale(vector2DDomain,
								moveSpeed,Vector2D.Normalize(vector2DDomain,
								addVec));
					}		
					object.setVec(newVec.getClone());
				}
			}
		};
	}
	
	/*引数：視界の形、視界の長さ、近づく精度、オブジェクトの速度、複数の点*/
	/*戻り値：視界内の複数の点から最も近い点から制限された速度までの速度で指定された精度で近づこうとする関数*/
	/*視界内の複数の点から最も近い点から制限された速度までの速度で指定された精度で近づこうとする関数作成*/
	public static GameObjectFunction makeSpeedLimitApproachMultipleNearPoint(final Vector2DDomain vector2DDomain,final VisionFieldFunction visionFieldFunction,final float visionFieldLength,final float precision,final float moveSpeed,final Object2D... approachObject){
		return new AbstractGameObjectFunction(){
			@Override
			public void changeState(Object2D object) {
				boolean isExistenceBullet = false;
				Vector2D nearPoint = new Vector2D();
				float nearLength = 10000;
				for(int i = 0;i<approachObject.length;i++){
					if(object == approachObject[i]){
						continue;
					}
					if(approachObject[i].getIsExistence()){
						if(visionFieldFunction.isInVisionField(object,approachObject[i])){
							float length = Vector2D.Length(vector2DDomain,
									Vector2D.Sub(vector2DDomain,
									object.getPos(),approachObject[i].getPos()));
							if(nearLength > length){
								nearLength = length;
								nearPoint.x = approachObject[i].getPos().x;
								nearPoint.y = approachObject[i].getPos().y;
							}
							isExistenceBullet = true;
						}
					}
				}
				if(isExistenceBullet){
					float newPrecision = (1.0f - nearLength / visionFieldLength) * precision;
					Vector2D subVec = Vector2D.Sub(vector2DDomain,
							nearPoint,object.getPos());
					Vector2D addVec = Vector2D.Scale(vector2DDomain,
							newPrecision,Vector2D.Normalize(vector2DDomain,
							subVec));
					addVec = Vector2D.Add(vector2DDomain,
							object.getVec(),addVec);
					Vector2D newVec = addVec;
					if(Vector2D.Length(vector2DDomain,addVec) > moveSpeed){
						newVec = Vector2D.Scale(vector2DDomain,
								moveSpeed,Vector2D.Normalize(vector2DDomain,
								addVec));
					}		
					object.setVec(newVec.getClone());
				}
			}
		};
	}
	
	/*引数：近づく精度、複数の点*/
	/*戻り値：複数の点から最も近い点から制限された速度までの速度で指定された精度で近づこうとする関数*/
	/*複数の点から最も近い点から制限された速度までの速度で指定された精度で近づこうとする関数作成*/
	public static GameObjectFunction makeSpeedLimitApproachMultipleNearPoint(final Vector2DDomain vector2DDomain,final float precision,final Object2D... approachObject){
		return new AbstractGameObjectFunction(){
			@Override
			public void changeState(Object2D object) {
				boolean isExistenceBullet = false;
				Vector2D nearPoint = new Vector2D();
				float nearLength = 10000;
				for(int i = 0;i<approachObject.length;i++){
					if(object == approachObject[i]){
						continue;
					}
					if(approachObject[i].getIsExistence()){
						float length = Vector2D.Length(vector2DDomain,
								Vector2D.Sub(vector2DDomain,
								object.getPos(),approachObject[i].getPos()));
						if(nearLength > length){
							nearLength = length;
							nearPoint.x = approachObject[i].getPos().x;
							nearPoint.y = approachObject[i].getPos().y;
						}
						isExistenceBullet = true;
					}
				}
				if(isExistenceBullet){
					float moveSpeed = Vector2D.Length(vector2DDomain,object.getVec());
					Vector2D subVec = Vector2D.Sub(vector2DDomain,
							nearPoint,object.getPos());
					Vector2D addVec = Vector2D.Scale(vector2DDomain,
							precision,Vector2D.Normalize(vector2DDomain,
							subVec));
					addVec = Vector2D.Add(vector2DDomain,
							object.getVec(),addVec);
					Vector2D newVec = addVec;
					if(Vector2D.Length(vector2DDomain,addVec) > moveSpeed){
						newVec = Vector2D.Scale(vector2DDomain,
								moveSpeed,Vector2D.Normalize(vector2DDomain,
								addVec));
					}		
					object.setVec(newVec.getClone());
				}
			}
		};
	}
	
	/*引数：視界の形、視界の長さ、近づく精度、複数の点*/
	/*戻り値：視界内の複数の点から最も近い点から制限された速度までの速度で指定された精度で近づこうとする関数*/
	/*視界内の複数の点から最も近い点から制限された速度までの速度で指定された精度で近づこうとする関数作成*/
	public static GameObjectFunction makeSpeedLimitApproachMultipleNearPoint(final Vector2DDomain vector2DDomain,final VisionFieldFunction visionFieldFunction,final float visionFieldLength,final float precision,final Object2D... approachObject){
		return new AbstractGameObjectFunction(){
			@Override
			public void changeState(Object2D object) {
				boolean isExistenceBullet = false;
				Vector2D nearPoint = new Vector2D();
				float nearLength = 10000;
				for(int i = 0;i<approachObject.length;i++){
					if(object == approachObject[i]){
						continue;
					}
					if(approachObject[i].getIsExistence()){
						if(visionFieldFunction.isInVisionField(object,approachObject[i])){
							float length = Vector2D.Length(vector2DDomain,
									Vector2D.Sub(vector2DDomain,
									object.getPos(),approachObject[i].getPos()));
							if(nearLength > length){
								nearLength = length;
								nearPoint.x = approachObject[i].getPos().x;
								nearPoint.y = approachObject[i].getPos().y;
							}
							isExistenceBullet = true;
						}
					}
				}
				if(isExistenceBullet){
					float newPrecision = (1.0f - nearLength / visionFieldLength) * precision;
					float moveSpeed = Vector2D.Length(vector2DDomain,object.getVec());
					Vector2D subVec = Vector2D.Sub(vector2DDomain,
							nearPoint,object.getPos());
					Vector2D addVec = Vector2D.Scale(vector2DDomain,
							newPrecision,Vector2D.Normalize(vector2DDomain,
							subVec));
					addVec = Vector2D.Add(vector2DDomain,
							object.getVec(),addVec);
					Vector2D newVec = addVec;
					if(Vector2D.Length(vector2DDomain,addVec) > moveSpeed){
						newVec = Vector2D.Scale(vector2DDomain,
								moveSpeed,Vector2D.Normalize(vector2DDomain,
								addVec));
					}		
					object.setVec(newVec.getClone());
				}
			}
		};
	}
}
