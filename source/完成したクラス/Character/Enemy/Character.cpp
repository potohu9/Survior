#include "Character.h"
USING_NS_CC;

Character::Character(){
}
Character::Character(Vec3 pos, Vec3 vec, std::string image, int hp, cocos2d::Node* node){
	charaDate  = CharaData(pos, vec, image, hp);	//引数　位置　方向ヴェく　絵　体力
	charaState = CharaState();
	this->node = node;
}
Character::~Character(){
}

void Character::render(){
	node->addChild( charaDate.getSprite() );
}
void Character::setPos(Vec3 vec3){
	charaDate.setPos(vec3);
}
