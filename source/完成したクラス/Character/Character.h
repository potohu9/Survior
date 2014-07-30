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
public:
	Character(cocos2d::Vec3 pos, cocos2d::Vec3 vec, cocos2d::Sprite* sprite, int hp);
	~Character();
};
