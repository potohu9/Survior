/*====================*/
//	作成者：森勇介
//	作成日：2014/07/28
/*====================*/
#pragma once
#include "cocos2d.h"
#include "CharaData.h"
#include "CharaState.h"
class Character{
private:
	CharaData charaDate;	//キャラクターデータ
	CharaState charaState;	//キャラクター状態異常
	cocos2d::Node* node;
public:
	Character();
	Character(cocos2d::Vec3 pos, cocos2d::Vec3 vec, std::string image, int hp, cocos2d::Node* node);
	~Character();
	void render();
	void setPos(cocos2d::Vec3 vec3);
};
