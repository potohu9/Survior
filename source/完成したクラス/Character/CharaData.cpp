#include "CharaData.h"
USING_NS_CC;
CharaData::CharaData(){
}
CharaData::CharaData(Vec3 pos, Vec3 vec, Sprite* sprite, int hp){
	this->pos = pos;	//位置
	this->vec = vec;	//方向べく
	this->sprite = sprite;	//絵
	this->hp = hp;		//体力
}
CharaData::~CharaData(){
}
Sprite* CharaData::getSprite(){
	return sprite;
}
