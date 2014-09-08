#include "EnemyFactory.h"

EnemyFactory::EnemyFactory(cocos2d::Node* node){
	this->node = node;
}

//スキルボタンを作る関数
Enemy EnemyFactory::makeEnemyFactory(int enemyId){
	//引数は　「String image, int collTime, int skillType」
	switch(enemyId){
	case 1:
		return Enemy(cocos2d::Vec3(0,0,0), cocos2d::Vec3(0,0,0), "Bomu.png", 100,/*AI,テンプレート*/node);
		break;
	case 2:
		return Enemy(cocos2d::Vec3(0,0,0), cocos2d::Vec3(0,0,0), "Bomu.png", 100,/*AI,テンプレート*/node);
		break;
	default:
		return Enemy(cocos2d::Vec3(0,0,0), cocos2d::Vec3(0,0,0), "Bomu.png", 100,/*AI,テンプレート*/node);
		break;
	}
	return Enemy(cocos2d::Vec3(0,0,0), cocos2d::Vec3(0,0,0), "Bomu.png", 100,/*AI,テンプレート*/node);
}
