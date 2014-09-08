/*====================*/
//	作成者：森勇介
//	作成日：2014/07/31
/*====================*/
#pragma once
#include "Character.h"
class Enemy : public Character{
private:
	//moveAi
	//atakkuAi
	//攻撃サイン(予兆）/滝口
	//Attack
	//アタックシステム
	//アタックテンプレート
public:
	Enemy();
	Enemy(cocos2d::Vec3 pos, cocos2d::Vec3 vec, std::string image, int hp,/*AI,テンプレート*/cocos2d::Node* node);
	~Enemy();
	void Update();
	void teachAttack();
};
