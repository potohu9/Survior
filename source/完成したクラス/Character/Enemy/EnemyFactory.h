/*====================*/
//	作成者：森勇介
//	作成日：2014/08/17
/*====================*/
#pragma once
#include "cocos2d.h"
#include "Enemy.h"
class EnemyFactory
{
private:
	cocos2d::Node* node;
public:
	EnemyFactory(cocos2d::Node* node);
	Enemy makeEnemyFactory(int enemyId);	//スキルボタンを作る関数
};
