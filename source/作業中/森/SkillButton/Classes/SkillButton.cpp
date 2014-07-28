#include "SkillButton.h"
USING_NS_CC;
//�R���X�g���N�^
SkillButton::SkillButton(/*Sprite* sprite,*/std::string image, int collTime, int skillType/*, Player player*/){
	//�N�[���^�C���̃J�E���g
	int collTimeCount = 0;
	//�X�L���̏��
	skillState = true;
	//�摜
	//this->sprite = sprite;
	//���
	this->skillType = skillType;
	//�N�[���^�C��
	this->collTime = collTime;
	//�X�L�����ʂ�^����Ώ�
	//this->player = player;

	 //�{�^��
	 MenuItemImage *pBtnItem = MenuItemImage::create(image, "CloseNormal.png", CC_CALLBACK_1(SkillButton::myButtonCallback, this));
	 pBtnItem->setScale(0.4);
	 //pBtnItem->setScale(0.5);
	 pBtn = Menu::create(pBtnItem, NULL);
	 pBtn->setPosition(ccp(100, 200));
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
Menu* SkillButton::getButton(){
	return pBtn;
}
//�ʒu�����p
void SkillButton::setPosition(Vec2 xy){
	pBtn->setPosition(xy);
}

//�{�^�������ꂽ
void SkillButton::myButtonCallback(Ref* pSender){

	switch(skillState){
	case true:
		collTimeCount=collTime;
		skillState = false;
		break;
	default:
		break;
	}

}


