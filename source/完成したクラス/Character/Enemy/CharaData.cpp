#include "CharaData.h"
USING_NS_CC;
CharaData::CharaData(){
}
CharaData::CharaData(Vec3 pos, Vec3 vec, std::string image, int hp){
	this->pos = pos;	//位置
	this->vec = vec;	//方向べく
	this->sprite = Sprite::create(image);	//絵
	this->hp = hp;		//体力
}
CharaData::~CharaData(){
}
Sprite* CharaData::getSprite(){
	return sprite;
}
void CharaData::setPos(Vec3 vec3){
	pos = vec3;
	sprite->setPosition(Vec2(pos.x,pos.y));
}
