/*====================*/
//	ì¬ÒFX—E‰î
//	ì¬“úF2014/07/28
/*====================*/
#pragma once
#include "cocos2d.h"

class CharaData{
private:
	cocos2d::Vec3 pos;
	cocos2d::Vec3 vec;
	cocos2d::Sprite* sprite;
	int hp;
public:
	CharaData();
	CharaData(cocos2d::Vec3 pos, cocos2d::Vec3 vec, cocos2d::Sprite* sprite, int hp);
	~CharaData();
};
