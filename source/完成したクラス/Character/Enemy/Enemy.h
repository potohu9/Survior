/*====================*/
//	�쐬�ҁF�X�E��
//	�쐬���F2014/07/31
/*====================*/
#pragma once
#include "Character.h"
class Enemy : public Character{
private:
	//moveAi
	//atakkuAi
	//�U���T�C��(�\���j/���
	//Attack
	//�A�^�b�N�V�X�e��
	//�A�^�b�N�e���v���[�g
public:
	Enemy(cocos2d::Vec3 pos, cocos2d::Vec3 vec, cocos2d::Sprite* sprite, int hp,/*AI,�e���v���[�g*/cocos2d::Node* node);
	~Enemy();
	void Update();
	void teachAttack();
};
