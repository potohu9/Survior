#include "EnemyFactory.h"

EnemyFactory::EnemyFactory(cocos2d::Node* node){
	this->node = node;
}

//�X�L���{�^�������֐�
Enemy EnemyFactory::makeEnemyFactory(int enemyId){
	//�����́@�uString image, int collTime, int skillType�v
	switch(enemyId){
	case 1:
		return Enemy(cocos2d::Vec3(0,0,0), cocos2d::Vec3(0,0,0), "Bomu.png", 100,/*AI,�e���v���[�g*/node);
		break;
	case 2:
		return Enemy(cocos2d::Vec3(0,0,0), cocos2d::Vec3(0,0,0), "Bomu.png", 100,/*AI,�e���v���[�g*/node);
		break;
	default:
		return Enemy(cocos2d::Vec3(0,0,0), cocos2d::Vec3(0,0,0), "Bomu.png", 100,/*AI,�e���v���[�g*/node);
		break;
	}
	return Enemy(cocos2d::Vec3(0,0,0), cocos2d::Vec3(0,0,0), "Bomu.png", 100,/*AI,�e���v���[�g*/node);
}
