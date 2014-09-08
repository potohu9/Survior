/*====================*/
//	�쐬�ҁF�X�E��
//	�쐬���F2014/07/28
/*====================*/
#pragma once
#include "cocos2d.h"
#include "CharaData.h"
#include "CharaState.h"
class Character{
private:
	CharaData charaDate;	//�L�����N�^�[�f�[�^
	CharaState charaState;	//�L�����N�^�[��Ԉُ�
	cocos2d::Node* node;
public:
	Character();
	Character(cocos2d::Vec3 pos, cocos2d::Vec3 vec, std::string image, int hp, cocos2d::Node* node);
	~Character();
	void render();
	void setPos(cocos2d::Vec3 vec3);
};
