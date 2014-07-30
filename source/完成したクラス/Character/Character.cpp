#include "Character.h"
USING_NS_CC;
Character::Character(Vec3 pos, Vec3 vec, Sprite* sprite, int hp){
	charaDate  = CharaData(pos, vec, sprite, hp);	//引数　位置　方向ヴェく　絵　体力
	charaState = CharaState();
}
Character::~Character(){
}
