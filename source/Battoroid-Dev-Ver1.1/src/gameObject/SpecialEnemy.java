package gameObject;

import line.CollisionLine;
import bullet.BulletFactory;
import bullet.BulletGenerator;

public class SpecialEnemy extends Enemy {
	private Options options;
	
	public void setOptionFunction(OptionFunction optionFunction){
		options.setOptionFunction(optionFunction);
	}
	
	public SpecialEnemy(int texture,CollisionLine collisionLine,GameObjectFunction function) {
		super(texture,collisionLine,function);
		options = new Options();
		bulletGenerator = composeArray(bulletGenerator,options.getBulletGenerator());
	}
	
	public SpecialEnemy(int optionNum,int texture,CollisionLine collisionLine,GameObjectFunction function) {
		super(texture,collisionLine,function);
		options = new Options(optionNum);
		bulletGenerator = composeArray(bulletGenerator,options.getBulletGenerator());
	}
	
	@Override
	public void update(){
		super.update();
		options.setPos(this.pos);
		options.setAimingPoint(myEnemy.getPos());
		options.update();
	}
	
	@Override
	protected void initBullet(){
		bulletGenerator = new BulletGenerator[bulletTypeNum];
		for(int i=0;i<bulletTypeNum;i++){
			bulletGenerator[i] = BulletFactory.makeEnemyRefrectBullet(vector2DDomain,collisionLine);
		}
	}
	
	// ”z—ñ‡¬
	public static BulletGenerator[] composeArray(BulletGenerator[] array1,BulletGenerator[] array2){
		BulletGenerator[] array = new BulletGenerator[array1.length + array2.length]; 
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
