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
	Character(cocos2d::Vec3 pos, cocos2d::Vec3 vec, cocos2d::Sprite* sprite, int hp, cocos2d::Node* node);
	~Character();
	void render();
};
