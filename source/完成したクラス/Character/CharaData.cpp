#include "CharaData.h"
USING_NS_CC;
CharaData::CharaData(){
}
CharaData::CharaData(Vec3 pos, Vec3 vec, Sprite* sprite, int hp){
	this->pos = pos;	//�ʒu
	this->vec = vec;	//�����ׂ�
	this->sprite = sprite;	//�G
	this->hp = hp;		//�̗�
}
CharaData::~CharaData(){

}
