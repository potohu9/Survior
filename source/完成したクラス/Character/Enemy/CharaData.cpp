#include "CharaData.h"
USING_NS_CC;
CharaData::CharaData(){
}
CharaData::CharaData(Vec3 pos, Vec3 vec, std::string image, int hp){
	this->pos = pos;	//ˆÊ’u
	this->vec = vec;	//•ûŒü‚×‚­
	this->sprite = Sprite::create(image);	//ŠG
	this->hp = hp;		//‘Ì—Í
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
