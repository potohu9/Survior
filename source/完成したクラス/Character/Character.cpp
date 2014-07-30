#include "Character.h"
USING_NS_CC;
Character::Character(Vec3 pos, Vec3 vec, Sprite* sprite, int hp){
	charaDate  = CharaData(pos, vec, sprite, hp);
	charaState = CharaState();
}
Character::~Character(){
}
