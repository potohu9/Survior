#include "SkillButton.h"
USING_NS_CC;
//�R���X�g���N�^
SkillButton::SkillButton(){
}
SkillButton::SkillButton(std::string image, int collTime, int skillType, cocos2d::Node* node){
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
	this->node = node;
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
cocos2d::Sprite* SkillButton::getButton(){
	return sprite;
}
//�\��
void SkillButton::render(){
	node->addChild( sprite );
}

//�ʒu�����p
void SkillButton::setPosition(Vec2 xy){
	sprite->setPosition(xy);
}

//�{�^�������ꂽ
void SkillButton::myButtonCallback(){
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


