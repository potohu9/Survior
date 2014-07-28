#include "SkillButton.h"
USING_NS_CC;
//コンストラクタ
SkillButton::SkillButton(){
}
SkillButton::SkillButton(std::string image, int collTime, int skillType){
	//クールタイムのカウント
	int collTimeCount = 0;
	//スキルの状態
	skillState = true;
	//画像
	//Spriteクラスを初期化
	this->sprite = Sprite::create(image);
	sprite->setScale(0.35f);
	//種類
	this->skillType = skillType;
	//クールタイム
	this->collTime = collTime;
	//スキル効果を与える対象
	//this->player = player;
}

//デストラクタ
SkillButton::~SkillButton(){
}

//クールタイム
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

//押されたらプレイヤーのstateに教える
void SkillButton::teachSkill(){

}

//表示するときとかのボタンを出す
/*Menu* SkillButton::getButton(){
	return pBtn;
}*/

Sprite* SkillButton::getButton(){
	return sprite;
}
//位置調整用
void SkillButton::setPosition(Vec2 xy){
	sprite->setPosition(xy);
}

//ボタンおされた
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


