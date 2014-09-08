/*====================*/
//	ì¬ÒFX—E‰î
//	ì¬“úF2014/07/28
/*====================*/
#pragma once
#include "cocos2d.h"

class CharaData{
private:
	cocos2d::Vec3 pos;	//ˆÊ’u
	cocos2d::Vec3 vec;	//•ûŒü‚×‚­
	cocos2d::Sprite* sprite;	//ŠG
	int hp;				//‘Ì—Í
public:
	CharaData();
	CharaData(cocos2d::Vec3 pos, cocos2d::Vec3 vec, std::string image, int hp);
	~CharaData();
	cocos2d::Sprite* getSprite();
	void setPos(cocos2d::Vec3 vec3);
};
