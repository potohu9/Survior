package gameObject;

import openGLES20.Common;
import line.CollisionLine;
import line.Line;
import line.CollisionLine.CollisionMode;
import function.TextureManager;
import function.Vector2D;
import function.Vector2DDomain;

public class EnemyList {
	public enum EnemyName{
		ZAKO,
		TANK,
		DAMAGE_DEALER,
		SP_ENEMY1,
		SP_ENEMY2,
		SP_ENEMY3,
	}
	
	public static Enemy getEnemy(Vector2DDomain vector2DDomain,
			Line[] line,
			Enemy[] enemy,
			Player player,
			CollisionLine slideLine,
			CollisionLine refrectLine,
			EnemyName name){
		Enemy enemyData;
		switch(name){
		case DAMAGE_DEALER:
			enemyData = getDamageDealer(vector2DDomain,line,enemy,player,slideLine,refrectLine);
			break;
		case SP_ENEMY1:
			enemyData = getSPEnemy1(vector2DDomain,line,enemy,player,slideLine,refrectLine);
			break;
		case SP_ENEMY2:
			enemyData = getSPEnemy2(vector2DDomain,line,enemy,player,slideLine,refrectLine);
			break;
		case SP_ENEMY3:
			enemyData = getSPEnemy3(vector2DDomain,line,enemy,player,slideLine,refrectLine);
			break;
		case TANK:
			enemyData = getTank(vector2DDomain,line,enemy,player,slideLine,refrectLine);
			break;
		case ZAKO:
			enemyData = getZako(vector2DDomain,line,enemy,player,slideLine,refrectLine);
			break;
		default:
			enemyData = getZako(vector2DDomain,line,enemy,player,slideLine,refrectLine);
			break;
		}
		return enemyData;
	}
	
	private static Enemy getZako(Vector2DDomain vector2DDomain,
			Line[] line,
			Enemy[] enemy,
			Player player,
			CollisionLine slideLine,
			CollisionLine refrectLine){
		Enemy zako;
		zako = new Enemy(TextureManager.getTexture("Enemy"),new CollisionLine(CollisionMode.REFLECT),GameObjectFunctionFactory.makeNotProcessing());
		zako.setRad(40);
		zako.setVec(new Vector2D(3,2));
		zako.setMoveSpeed(Vector2D.Length(vector2DDomain,zako.getVec()));
		zako.getCollisionLine().addLine(line);
		zako.setHitPoint(10);
		zako.setFunction(EnemyAI.AIPattern1(vector2DDomain,
				800f,		// ”ğ‚¯‚é’e‚ğ”­Œ©‚·‚é‹ŠE‚Ì‘å‚«‚³
				6f,			// ’e‚ğ”ğ‚¯‚é§“x
				2.4f,		// ƒvƒŒƒCƒ„[‚É‹ß‚Ã‚­¸“x
				400f,		// ’‡ŠÔ‚ğ”­Œ©‚·‚é‹ŠE‚Ì‘å‚«‚³
				4f,			// ’‡ŠÔ‚Æ—£‚ê‚é¸“x
				zako,
				enemy,
				player,
				slideLine,
				refrectLine));
		for(int i=0;i<zako.getBulletTypeNum();i++){
			zako.getBulletGenerator(i).setDischargeInterval(50);
		}
		return zako;
	}
	
	private static Enemy getTank(Vector2DDomain vector2DDomain,
			Line[] line,
			Enemy[] enemy,
			Player player,
			CollisionLine slideLine,
			CollisionLine refrectLine){
		Enemy tank;
		tank = new Enemy(TextureManager.getTexture("Enemy"),new CollisionLine(CollisionMode.REFLECT),GameObjectFunctionFactory.makeNotProcessing());
		tank.setRad(60);
		tank.setVec(new Vector2D(5,7));
		tank.setMoveSpeed(Vector2D.Length(vector2DDomain,tank.getVec()));
		tank.getCollisionLine().addLine(line);
		tank.setHitPoint(30);
		tank.setFunction(EnemyAI.AIPattern2(vector2DDomain,
				800f,	// ”ğ‚¯‚é’e‚ğ”­Œ©‚·‚é‹ŠE‚Ì‘å‚«‚³
				6f,		// ’e‚ğ”ğ‚¯‚é§“x
				2.4f,	// ƒvƒŒƒCƒ„[‚É‹ß‚Ã‚­¸“x
				400f, 	// ’‡ŠÔ‚ğ”­Œ©‚·‚é‹ŠE‚Ì‘å‚«‚³
				4f,		// ’‡ŠÔ‚Æ—£‚ê‚é¸“x
				tank,
				enemy,
				player,
				slideLine,
				refrectLine));
		for(int i=0;i<tank.getBulletTypeNum();i++){
			tank.getBulletGenerator(i).setDischargeInterval(50);
		}
		return tank;
	}
	
	private static Enemy getDamageDealer(Vector2DDomain vector2DDomain,
			Line[] line,
			Enemy[] enemy,
			Player player,
			CollisionLine slideLine,
			CollisionLine refrectLine){
		Enemy damageDealer;
		damageDealer = new Enemy(TextureManager.getTexture("Enemy"),new CollisionLine(CollisionMode.REFLECT),GameObjectFunctionFactory.makeNotProcessing());
		damageDealer.setRad(40);
		damageDealer.setVec(new Vector2D(7,7));
		damageDealer.setMoveSpeed(Vector2D.Length(vector2DDomain,damageDealer.getVec()));
		damageDealer.getCollisionLine().addLine(line);
		damageDealer.setHitPoint(10);
		damageDealer.setFunction(EnemyAI.AIPattern1(vector2DDomain,
				800f,		// ”ğ‚¯‚é’e‚ğ”­Œ©‚·‚é‹ŠE‚Ì‘å‚«‚³
				6f,			// ’e‚ğ”ğ‚¯‚é§“x
				2.4f,		// ƒvƒŒƒCƒ„[‚É‹ß‚Ã‚­¸“x
				400f,		// ’‡ŠÔ‚ğ”­Œ©‚·‚é‹ŠE‚Ì‘å‚«‚³
				4f,			// ’‡ŠÔ‚Æ—£‚ê‚é¸“x
				damageDealer,
				enemy,
				player,
				slideLine,
				refrectLine));
		for(int i=0;i<damageDealer.getBulletTypeNum();i++){
			damageDealer.getBulletGenerator(i).setDischargeInterval(10);
		}
		return damageDealer;
	}
	
	private static Enemy getSPEnemy1(Vector2DDomain vector2DDomain,
			Line[] line,
			Enemy[] enemy,
			Player player,
			CollisionLine slideLine,
			CollisionLine refrectLine){
		SpecialEnemy specialEnemy = new SpecialEnemy(5,TextureManager.getTexture("Enemy"),new CollisionLine(CollisionMode.REFLECT),GameObjectFunctionFactory.makeNotProcessing());
		specialEnemy.setOptionFunction(OptionType.Type1(1,3,0,200,3));
		specialEnemy.setRad(40);
		specialEnemy.setVec(new Vector2D(3,2));
		specialEnemy.setMoveSpeed(Vector2D.Length(vector2DDomain,specialEnemy.getVec()));
		specialEnemy.setPos(new Vector2D(Common.getScreenProportionW()/8*7,Common.getScreenProportionH()/2-100));
		specialEnemy.getCollisionLine().addLine(line);
		specialEnemy.setHitPoint(20);
		specialEnemy.setFunction(EnemyAI.AIPattern1(vector2DDomain,
				800f,		// ”ğ‚¯‚é’e‚ğ”­Œ©‚·‚é‹ŠE‚Ì‘å‚«‚³
				6f,			// ’e‚ğ”ğ‚¯‚é§“x
				2.4f,		// ƒvƒŒƒCƒ„[‚É‹ß‚Ã‚­¸“x
				400f,		// ’‡ŠÔ‚ğ”­Œ©‚·‚é‹ŠE‚Ì‘å‚«‚³
				4f,			// ’‡ŠÔ‚Æ—£‚ê‚é¸“x
				specialEnemy,
				enemy,
				player,
				slideLine,
				refrectLine));
		for(int i=0;i<specialEnemy.getBulletTypeNum();i++){
			specialEnemy.getBulletGenerator(i).setDischargeInterval(30);
		}
		return specialEnemy;
	}
	
	private static Enemy getSPEnemy2(Vector2DDomain vector2DDomain,
			Line[] line,
			Enemy[] enemy,
			Player player,
			CollisionLine slideLine,
			CollisionLine refrectLine){
		SpecialEnemy specialEnemy = new SpecialEnemy(5,TextureManager.getTexture("Enemy"),new CollisionLine(CollisionMode.REFLECT),GameObjectFunctionFactory.makeNotProcessing());
		specialEnemy.setOptionFunction(OptionType.Type1(5,3,0,200,3));
		specialEnemy.setRad(40);
		specialEnemy.setVec(new Vector2D(3,2));
		specialEnemy.setMoveSpeed(Vector2D.Length(vector2DDomain,specialEnemy.getVec()));
		specialEnemy.setPos(new Vector2D(Common.getScreenProportionW()/8*7,Common.getScreenProportionH()/2-100));
		specialEnemy.getCollisionLine().addLine(line);
		specialEnemy.setHitPoint(25);
		specialEnemy.setFunction(EnemyAI.AIPattern1(vector2DDomain,
				800f,		// ”ğ‚¯‚é’e‚ğ”­Œ©‚·‚é‹ŠE‚Ì‘å‚«‚³
				6f,			// ’e‚ğ”ğ‚¯‚é§“x
				2.4f,		// ƒvƒŒƒCƒ„[‚É‹ß‚Ã‚­¸“x
				400f,		// ’‡ŠÔ‚ğ”­Œ©‚·‚é‹ŠE‚Ì‘å‚«‚³
				4f,			// ’‡ŠÔ‚Æ—£‚ê‚é¸“x
				specialEnemy,
				enemy,
				player,
				slideLine,
				refrectLine));
		for(int i=0;i<specialEnemy.getBulletTypeNum();i++){
			specialEnemy.getBulletGenerator(i).setDischargeInterval(30);
		}
		return specialEnemy;
	}
	
	private static Enemy getSPEnemy3(Vector2DDomain vector2DDomain,
			Line[] line,
			Enemy[] enemy,
			Player player,
			CollisionLine slideLine,
			CollisionLine refrectLine){
		SpecialEnemy specialEnemy = new SpecialEnemy(5,TextureManager.getTexture("Enemy"),new CollisionLine(CollisionMode.REFLECT),GameObjectFunctionFactory.makeNotProcessing());
		specialEnemy.setRad(40);
		specialEnemy.setPos(new Vector2D(Common.getScreenProportionW()/8*7,Common.getScreenProportionH()/2-100));
		specialEnemy.setOptionFunction(OptionType.Type2(vector2DDomain,3,400,15,specialEnemy,line));
		int optionNum = specialEnemy.getBulletGenerator().length;
		for(int i = 0;i<optionNum;i++){
			specialEnemy.getBulletGenerator(i).setDischargeInterval(10);
		}
		specialEnemy.setVec(new Vector2D(3,2));
		specialEnemy.setMoveSpeed(Vector2D.Length(vector2DDomain,specialEnemy.getVec()));
		specialEnemy.getCollisionLine().addLine(line);
		specialEnemy.setHitPoint(25);
		specialEnemy.setFunction(EnemyAI.AIPattern1(vector2DDomain,
				800f,		// ”ğ‚¯‚é’e‚ğ”­Œ©‚·‚é‹ŠE‚Ì‘å‚«‚³
				6f,			// ’e‚ğ”ğ‚¯‚é§“x
				2.4f,		// ƒvƒŒƒCƒ„[‚É‹ß‚Ã‚­¸“x
				400f,		// ’‡ŠÔ‚ğ”­Œ©‚·‚é‹ŠE‚Ì‘å‚«‚³
				4f,			// ’‡ŠÔ‚Æ—£‚ê‚é¸“x
				specialEnemy,
				enemy,
				player,
				slideLine,
				refrectLine));
		for(int i=0;i<specialEnemy.getBulletTypeNum();i++){
			specialEnemy.getBulletGenerator(i).setDischargeInterval(30);
		}
		return specialEnemy;
	}
}
