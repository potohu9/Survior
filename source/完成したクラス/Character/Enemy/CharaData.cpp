#include "CharaData.h"
USING_NS_CC;
CharaData::CharaData(){
}
CharaData::CharaData(Vec3 pos, Vec3 vec, std::string image, int hp){
	this->pos = pos;	//�ʒu
	this->vec = vec;	//�����ׂ�
	this->sprite = Sprite::create(image);	//�G
	this->hp = hp;		//�̗�
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
