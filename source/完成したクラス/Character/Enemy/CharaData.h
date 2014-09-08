/*====================*/
//	�쐬�ҁF�X�E��
//	�쐬���F2014/07/28
/*====================*/
#pragma once
#include "cocos2d.h"

class CharaData{
private:
	cocos2d::Vec3 pos;	//�ʒu
	cocos2d::Vec3 vec;	//�����ׂ�
	cocos2d::Sprite* sprite;	//�G
	int hp;				//�̗�
public:
	CharaData();
	CharaData(cocos2d::Vec3 pos, cocos2d::Vec3 vec, std::string image, int hp);
	~CharaData();
	cocos2d::Sprite* getSprite();
	void setPos(cocos2d::Vec3 vec3);
};
