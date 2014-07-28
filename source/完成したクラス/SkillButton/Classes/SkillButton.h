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
	//�X�L�����ʂ�^����Ώ�
	//Player player;

public:
	//�֐�
	//�R���X�g���N�^
	SkillButton();
	SkillButton(std::string image, int collTime, int skillType);
	//�f�X�g���N�^
	~SkillButton();
	//�����ꂽ��v���C���[��state�ɋ�����
	void teachSkill();
	//�N�[���^�C��
	void collTimeUpdete();
	//�\������Ƃ��Ƃ��̃{�^�����o��
	cocos2d::Sprite* getButton();
	//�ʒu�����p
	void setPosition(cocos2d::Vec2 xy);
	//�{�^���n
	void myButtonCallback();
};
