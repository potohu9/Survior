#include "SkillButton.h"
USING_NS_CC;
//コンストラクタ
SkillButton::SkillButton(/*Sprite* sprite,*/std::string image, int collTime, int skillType/*, Player player*/){
	//クールタイムのカウント
	int collTimeCount = 0;
	//スキルの状態
	skillState = true;
	//画像
	//this->sprite = sprite;
	//種類
	this->skillType = skillType;
	//クールタイム
	this->collTime = collTime;
	//スキル効果を与える対象
	//this->player = player;

	 //ボタン
	 MenuItemImage *pBtnItem = MenuItemImage::create(image, "CloseNormal.png", CC_CALLBACK_1(SkillButton::myButtonCallback, this));
	 pBtnItem->setScale(0.4);
	 //pBtnItem->setScale(0.5);
	 pBtn = Menu::create(pBtnItem, NULL);
	 pBtn->setPosition(ccp(100, 200));
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
Menu* SkillButton::getButton(){
	return pBtn;
}
//位置調整用
void SkillButton::setPosition(Vec2 xy){
	pBtn->setPosition(xy);
}

//ボタンおされた
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


