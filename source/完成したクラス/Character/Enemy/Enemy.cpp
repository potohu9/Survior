#include "Enemy.h"

Enemy::Enemy() : Character(){
}
Enemy::Enemy(cocos2d::Vec3 pos, cocos2d::Vec3 vec, std::string image, int hp,/*AI,�e���v���[�g*/cocos2d::Node* node) : Character(pos, vec, image, hp, node){
	//moveAi
	//atakkuAi
	//�U���T�C��(�\���j/���
	//�T�C��.ini(nod,10);
	//Attack
	//�A�^�b�N�V�X�e��
	//�A�^�b�N�e���v���[�g
}
Enemy::~Enemy(){
}
void Enemy::Update(){
	//AI�̃A�b�v�f�[�g
	//�U���T�C���̃A�b�v�f�[�g
	//�A�^�b�N�V�X�e���̃A�b�v�f�[�g
}
//Attack�����
void teachAttack(){
	//�A�^�b�N�e���v���[�g�����ăA�^�b�N�����
	//������A�^�b�N���A�^�b�N�V�X�e���ɋ�����
}
