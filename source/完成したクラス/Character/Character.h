/*====================*/
//	ì¬ÒFX—E‰î
//	ì¬“úF2014/07/28
/*====================*/
#pragma once
#include "cocos2d.h"
#include "CharaData.h"
#include "CharaState.h"
class Character{
private:
	CharaData charaDate;
	CharaState charaState;
public:
	Character(cocos2d::Vec3 pos, cocos2d::Vec3 vec, cocos2d::Sprite* sprite, int hp);
	~Character();
};
