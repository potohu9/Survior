#include "Character.h"
USING_NS_CC;
Character::Character(Vec3 pos, Vec3 vec, Sprite* sprite, int hp){
	charaDate  = CharaData(pos, vec, sprite, hp);	//�����@�ʒu�@�������F���@�G�@�̗�
	charaState = CharaState();
}
Character::~Character(){
}
