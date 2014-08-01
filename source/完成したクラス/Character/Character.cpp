#include "Character.h"
USING_NS_CC;
Character::Character(Vec3 pos, Vec3 vec, Sprite* sprite, int hp, cocos2d::Node* node){
	charaDate  = CharaData(pos, vec, sprite, hp);	//�����@�ʒu�@�������F���@�G�@�̗�
	charaState = CharaState();
	this->node = node;
}
Character::~Character(){
}

void Character::render(){
	node->addChild( charaDate.getSprite() );
}
