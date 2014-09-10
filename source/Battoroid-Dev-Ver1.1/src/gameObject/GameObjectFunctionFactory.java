package gameObject;

import collision.Collision;
import function.Vector2D;
import function.Vector2DDomain;
import object2D.Object2D;
import visionField.VisionFieldFunction;

public class GameObjectFunctionFactory {
	/*�߂�l�F���������֐�*/
	/*���������֐��쐬*/
	public static GameObjectFunction makeNotProcessing(){
		return new AbstractGameObjectFunction(){
			@Override
			public void changeState(Object2D object) {
				
			}
		};
	}
	
	/*�߂�l�F�I�u�W�F�N�g�ړ��֐�*/
	/*�I�u�W�F�N�g�ړ��֐��쐬*/
	public static GameObjectFunction makeMoveObject(final Vector2DDomain vector2DDomain){
		return new AbstractGameObjectFunction(){
			@Override
			public void changeState(Object2D object) {
				object.setPos(Vector2D.Add(vector2DDomain,
						object.getPos(),object.getVec()).getClone());
			}
		};
	}
	
	/*�����F�ړ��\�͈�*/
	/*�߂�l�F�ړ��\�͈͂̎w��֐�*/
	/*�ړ��\�͈͂̎w��֐��쐬*/
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
	
	/*�����F�ړ��\�͈�*/
	/*�߂�l�F�ړ��\�͈͂̎w��֐�*/
	/*�ړ��\�͈͂̎w��֐��쐬*/
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
	
	/*�����F�ړ��\�͈�*/
	/*�߂�l�F�ړ��\�͈͂܂ŗ�����ړ������𔽓]����֐�*/
	/*�ړ��\�͈͂܂ŗ�����ړ������𔽓]����֐��쐬*/
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
	
	/*�����F�d�͂̃G�l���M�[*/
	/*�߂�l�F�w�肳�ꂽ�d�̓G�l���M�[�𕨑̂ɂ�����֐�*/
	/*�w�肳�ꂽ�d�̓G�l���M�[�𕨑̂ɂ�����֐��쐬*/
	public static GameObjectFunction makeSetGravity(final Vector2DDomain vector2DDomain,final Vector2D value){
		return new AbstractGameObjectFunction(){
			@Override
			public void changeState(Object2D object) {
				object.setVec(Vector2D.Add(vector2DDomain,
						object.getVec(),value).getClone());
			}
		};
	}
	
	//-----���x�Œ�A������------
	
	/*�����F�����鐸�x�A�I�u�W�F�N�g�̑��x�A�����̓_*/
	/*�߂�l�F�����̓_����ł��߂��_����Œ葬�x�Ŏw�肳�ꂽ���x�ł悯�悤�Ƃ���֐�*/
	/*�����̓_����ł��߂��_����Œ葬�x�Ŏw�肳�ꂽ���x�ł悯�悤�Ƃ���֐��쐬*/
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
	
	/*�����F���E�̌`�A���E�̒����A�����鐸�x�A�I�u�W�F�N�g�̑��x�A�����̓_*/
	/*�߂�l�F���E���̕����̓_����ł��߂��_����Œ葬�x�Ŏw�肳�ꂽ���x�ł悯�悤�Ƃ���֐�*/
	/*���E���̕����̓_����ł��߂��_����Œ葬�x�Ŏw�肳�ꂽ���x�ł悯�悤�Ƃ���֐��쐬*/
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
	
	/*�����F�����鐸�x�A�����̓_*/
	/*�߂�l�F�����̓_����ł��߂��_����Œ葬�x�Ŏw�肳�ꂽ���x�ł悯�悤�Ƃ���֐�*/
	/*�����̓_����ł��߂��_����Œ葬�x�Ŏw�肳�ꂽ���x�ł悯�悤�Ƃ���֐��쐬*/
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

	/*�����F���E�̌`�A���E�̒����A�����鐸�x�A�����̓_*/
	/*�߂�l�F���E���̕����̓_����ł��߂��_����Œ葬�x�Ŏw�肳�ꂽ���x�ł悯�悤�Ƃ���֐�*/
	/*���E���̕����̓_����ł��߂��_����Œ葬�x�Ŏw�肳�ꂽ���x�ł悯�悤�Ƃ���֐��쐬*/
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
	
	//-----���x�Œ�A�߂Â�------

	/*�����F���E�̌`�A���E�̒����A�߂Â����x�A�����̓_*/
	/*�߂�l�F���E���̕����̓_����ł��߂��_����Œ葬�x�Ŏw�肳�ꂽ���x�ŋ߂Â����Ƃ���֐�*/
	/*���E���̕����̓_����ł��߂��_����Œ葬�x�Ŏw�肳�ꂽ���x�ŋ߂Â����Ƃ���֐��쐬*/
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
	
	/*�����F�߂Â����x�A�I�u�W�F�N�g�̑��x�A�����̓_*/
	/*�߂�l�F�����̓_����ł��߂��_����Œ葬�x�Ŏw�肳�ꂽ���x�ŋ߂Â����Ƃ���֐�*/
	/*�����̓_����ł��߂��_����Œ葬�x�Ŏw�肳�ꂽ���x�ŋ߂Â����Ƃ���֐��쐬*/
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
	
	/*�����F�߂Â����x�A�����̓_*/
	/*�߂�l�F�����̓_����ł��߂��_����Œ葬�x�Ŏw�肳�ꂽ���x�ŋ߂Â����Ƃ���֐�*/
	/*�����̓_����ł��߂��_����Œ葬�x�Ŏw�肳�ꂽ���x�ŋ߂Â����Ƃ���֐��쐬*/
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
	
	/*�����F���E�̌`�A���E�̒����A�߂Â����x�A�����̓_*/
	/*�߂�l�F���E���̕����̓_����ł��߂��_����Œ葬�x�Ŏw�肳�ꂽ���x�ŋ߂Â����Ƃ���֐�*/
	/*���E���̕����̓_����ł��߂��_����Œ葬�x�Ŏw�肳�ꂽ���x�ŋ߂Â����Ƃ���֐��쐬*/
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
	
	//-----���x�����A������-----
	
	/*�����F�����鐸�x�A�I�u�W�F�N�g�̑��x�A�����̓_*/
	/*�߂�l�F�����̓_����ł��߂��_���琧�����ꂽ���x�܂ł̑��x�Ŏw�肳�ꂽ���x�ł悯�悤�Ƃ���֐�*/
	/*�����̓_����ł��߂��_���琧�����ꂽ���x�܂ł̑��x�Ŏw�肳�ꂽ���x�ł悯�悤�Ƃ���֐��쐬*/
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
	
	/*�����F���E�̌`�A���E�̒����A�����鐸�x�A�I�u�W�F�N�g�̑��x�A�����̓_*/
	/*�߂�l�F���E���̕����̓_����ł��߂��_���琧�����ꂽ���x�܂ł̑��x�Ŏw�肳�ꂽ���x�ł悯�悤�Ƃ���֐�*/
	/*���E���̕����̓_����ł��߂��_���琧�����ꂽ���x�܂ł̑��x�Ŏw�肳�ꂽ���x�ł悯�悤�Ƃ���֐��쐬*/
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
	
	/*�����F�����鐸�x�A�����̓_*/
	/*�߂�l�F�����̓_����ł��߂��_���琧�����ꂽ���x�܂ł̑��x�Ŏw�肳�ꂽ���x�ł悯�悤�Ƃ���֐�*/
	/*�����̓_����ł��߂��_���琧�����ꂽ���x�܂ł̑��x�Ŏw�肳�ꂽ���x�ł悯�悤�Ƃ���֐��쐬*/
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
	
	/*�����F���E�̌`�A���E�̒����A�����鐸�x�A�����̓_*/
	/*�߂�l�F���E���̕����̓_����ł��߂��_���琧�����ꂽ���x�܂ł̑��x�Ŏw�肳�ꂽ���x�ł悯�悤�Ƃ���֐�*/
	/*���E���̕����̓_����ł��߂��_���琧�����ꂽ���x�܂ł̑��x�Ŏw�肳�ꂽ���x�ł悯�悤�Ƃ���֐��쐬*/
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
	
	//-----���x�����A�߂Â�-----
	
	/*�����F�߂Â����x�A�I�u�W�F�N�g�̑��x�A�����̓_*/
	/*�߂�l�F�����̓_����ł��߂��_���琧�����ꂽ���x�܂ł̑��x�Ŏw�肳�ꂽ���x�ŋ߂Â����Ƃ���֐�*/
	/*�����̓_����ł��߂��_���琧�����ꂽ���x�܂ł̑��x�Ŏw�肳�ꂽ���x�ŋ߂Â����Ƃ���֐��쐬*/
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
	
	/*�����F���E�̌`�A���E�̒����A�߂Â����x�A�I�u�W�F�N�g�̑��x�A�����̓_*/
	/*�߂�l�F���E���̕����̓_����ł��߂��_���琧�����ꂽ���x�܂ł̑��x�Ŏw�肳�ꂽ���x�ŋ߂Â����Ƃ���֐�*/
	/*���E���̕����̓_����ł��߂��_���琧�����ꂽ���x�܂ł̑��x�Ŏw�肳�ꂽ���x�ŋ߂Â����Ƃ���֐��쐬*/
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
	
	/*�����F�߂Â����x�A�����̓_*/
	/*�߂�l�F�����̓_����ł��߂��_���琧�����ꂽ���x�܂ł̑��x�Ŏw�肳�ꂽ���x�ŋ߂Â����Ƃ���֐�*/
	/*�����̓_����ł��߂��_���琧�����ꂽ���x�܂ł̑��x�Ŏw�肳�ꂽ���x�ŋ߂Â����Ƃ���֐��쐬*/
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
	
	/*�����F���E�̌`�A���E�̒����A�߂Â����x�A�����̓_*/
	/*�߂�l�F���E���̕����̓_����ł��߂��_���琧�����ꂽ���x�܂ł̑��x�Ŏw�肳�ꂽ���x�ŋ߂Â����Ƃ���֐�*/
	/*���E���̕����̓_����ł��߂��_���琧�����ꂽ���x�܂ł̑��x�Ŏw�肳�ꂽ���x�ŋ߂Â����Ƃ���֐��쐬*/
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
