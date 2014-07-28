#include "SkillButton.h"
USING_NS_CC;
//�R���X�g���N�^
SkillButton::SkillButton(){
}
SkillButton::SkillButton(std::string image, int collTime, int skillType){
	//�N�[���^�C���̃J�E���g
	int collTimeCount = 0;
	//�X�L���̏��
	skillState = true;
	//�摜
	//Sprite�N���X��������
	this->sprite = Sprite::create(image);
	sprite->setScale(0.35f);
	//���
	this->skillType = skillType;
	//�N�[���^�C��
	this->collTime = collTime;
	//�X�L�����ʂ�^����Ώ�
	//this->player = player;
}

//�f�X�g���N�^
SkillButton::~SkillButton(){
}

//�N�[���^�C��
void SkillButton::collTimeUpdete(){
	/*if(skillState==false){
		collTimeCount++;
		if(collTime<=collTimeCount){
			skillState = true;
			collTimeCount = 0;
		}
	}*/
	if(collTimeCount>0){
		collTimeCount--;
		if(collTimeCount<=0){
			skillState = true;
			collTimeCount = 0;
		}
	}
}

//�����ꂽ��v���C���[��state�ɋ�����
void SkillButton::teachSkill(){

}

//�\������Ƃ��Ƃ��̃{�^�����o��
/*Menu* SkillButton::getButton(){
	return pBtn;
}*/

Sprite* SkillButton::getButton(){
	return sprite;
}
//�ʒu�����p
void SkillButton::setPosition(Vec2 xy){
	sprite->setPosition(xy);
}

//�{�^�������ꂽ
void SkillButton::myButtonCallback(){

	switch(skillState){
	case true:
		collTimeCount=collTime;
		skillState = false;
		sprite->setRotation(180);
		break;
	default:
		sprite->setRotation(0);
		skillState = true;
		break;
	}

}


