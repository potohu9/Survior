/*====================*/
//	作成者：森勇介
//	作成日：2014/07/28
/*====================*/
#pragma once
#include "cocos2d.h"

class CharaData{
private:
	cocos2d::Vec3 pos;	//位置
	cocos2d::Vec3 vec;	//方向べく
	cocos2d::Sprite* sprite;	//絵
	int hp;				//体力
public:
	CharaData();
	CharaData(cocos2d::Vec3 pos, cocos2d::Vec3 vec, std::string image, int hp);
	~CharaData();
	cocos2d::Sprite* getSprite();
	void setPos(cocos2d::Vec3 vec3);
};
