/*====================*/
//	�쐬�ҁF�X�E��
//	�쐬���F2014/07/28
/*====================*/
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
	//0�ȏ�@���@�X�L��������ăN�[���^�C������������
	if(collTimeCount>0){
		collTimeCount--;	//�N�[���^�C�����炷
		//�N�[���^�C���I��������
		if(collTimeCount<=0){
			skillState = true;	//�ĂуX�L���{�^����������悤�ɂ���
			collTimeCount = 0;	//�N�[���^�C����0�ɂ���
		}
	}
}

//�����ꂽ��v���C���[��state�ɋ�����
void SkillButton::teachSkill(){

}

//�X�v���C�g���o��
Sprite* SkillButton::getButton(){
	return sprite;
}

//�ʒu�����p
void SkillButton::setPosition(Vec2 xy){
	sprite->setPosition(xy);
}

//�{�^�������ꂽ
void SkillButton::myButtonCallback(){

	/*switch(skillState){
	case true:
		collTimeCount=collTime;
		skillState = false;
		sprite->setRotation(180);
		break;
	default:
		sprite->setRotation(0);
		skillState = true;
		break;
	}*/
	//�X�L���������\���ǂ���
	if(skillState){
		collTimeCount=collTime;	//�N�[���^�C�����Z�b�g
		skillState = false;		//�X�L�������s�ɃZ�b�g
		teachSkill();			//�X�L������`����

		sprite->setRotation(180);	//�e�X�g�p�@�㉺���]
	} else {
		//�e�X�g�p�@�X�L�������s��
		sprite->setRotation(0);	//�㉺�߂�
		skillState = true;		//�X�L�������\�ɃZ�b�g
	}
}


