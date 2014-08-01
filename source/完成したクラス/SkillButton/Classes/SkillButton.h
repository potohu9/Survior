/*====================*/
//	�쐬�ҁF�X�E��
//	�쐬���F2014/07/28
/*====================*/
#pragma once
#include "cocos2d.h"

class SkillButton
{
private:
	//�ϐ�
	//�N�[���^�C��
	int collTimeCount;
	//�X�L���̏��
	bool skillState;
	//�摜�f�[�^
	cocos2d::Sprite* sprite;
	//�{�^��
	//cocos2d::Menu* pBtn;
	//�N�[���^�C��
	int collTime;
	//�X�L���̎��
	int skillType;
	//�v���C���ɓn��
	//preiya

	cocos2d::Node* node;

public:
	//�֐�
	//�R���X�g���N�^
	SkillButton();
	SkillButton(std::string image, int collTime, int skillType, cocos2d::Node* node);
	//�f�X�g���N�^
	~SkillButton();
	//�����ꂽ��v���C���[��state�ɋ�����
	void teachSkill();
	//�N�[���^�C��
	void collTimeUpdete();
	//�\������Ƃ��Ƃ��̃{�^�����o��
	cocos2d::Sprite* getButton();
	//�\��
	void render();
	//�ʒu�����p
	void setPosition(cocos2d::Vec2 xy);
	//�{�^���n
	void myButtonCallback();
};
