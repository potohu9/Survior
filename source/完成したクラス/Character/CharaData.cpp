#include "CharaData.h"
USING_NS_CC;
CharaData::CharaData(){
}
CharaData::CharaData(Vec3 pos, Vec3 vec, Sprite* sprite, int hp){
	this->pos = pos;	//ˆÊ’u
	this->vec = vec;	//•ûŒü‚×‚­
	this->sprite = sprite;	//ŠG
	this->hp = hp;		//‘Ì—Í
}
CharaData::~CharaData(){

}
