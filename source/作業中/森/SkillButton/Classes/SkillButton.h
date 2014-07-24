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
	//Sprite* sprite;
	//�{�^��
	cocos2d::Menu* pBtn;
	//�N�[���^�C��
	int collTime;
	//�X�L���̎��
	int skillType;
	//�X�L�����ʂ�^����Ώ�
	//Player player;

	//�{�^���n
	void myButtonCallback(cocos2d::Ref* pSender);

public:
	//�֐�
	//�R���X�g���N�^
	SkillButton(/*Sprite* sprite,*/std::string image, int collTime, int skillType);
	//�f�X�g���N�^
	~SkillButton();
	//�����ꂽ��v���C���[��state�ɋ�����
	void teachSkill();
	//�N�[���^�C��
	void collTimeUpdete();
	//�\������Ƃ��Ƃ��̃{�^�����o��
	cocos2d::Menu* getButton();
	//�ʒu�����p
	void setPosition(cocos2d::Vec2 xy);
};
