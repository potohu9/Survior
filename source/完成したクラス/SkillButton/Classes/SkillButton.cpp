#include "SkillButton.h"
USING_NS_CC;
//コンストラクタ
SkillButton::SkillButton(){
}
SkillButton::SkillButton(std::string image, int collTime, int skillType, cocos2d::Node* node){
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
	this->node = node;
}

//デストラクタ
SkillButton::~SkillButton(){
}

//クールタイム
void SkillButton::collTimeUpdete(){
	//0以上　＝　スキル押されてクールタイム中だったら
	if(collTimeCount>0){
		collTimeCount--;	//クールタイム減らす
		//クールタイム終了したら
		if(collTimeCount<=0){
			skillState = true;	//再びスキルボタンを押せるようにする
			collTimeCount = 0;	//クールタイムを0にする
		}
	}
}

//押されたらプレイヤーのstateに教える
void SkillButton::teachSkill(){

}
cocos2d::Sprite* SkillButton::getButton(){
	return sprite;
}
//表示
void SkillButton::render(){
	node->addChild( sprite );
}

//位置調整用
void SkillButton::setPosition(Vec2 xy){
	sprite->setPosition(xy);
}

//ボタンおされた
void SkillButton::myButtonCallback(){
	//スキルが発動可能かどうか
	if(skillState){
		collTimeCount=collTime;	//クールタイムをセット
		skillState = false;		//スキル発動不可にセット
		teachSkill();			//スキル情報を伝える

		sprite->setRotation(180);	//テスト用　上下反転
	} else {
		//テスト用　スキル発動不可時
		sprite->setRotation(0);	//上下戻す
		skillState = true;		//スキル発動可能にセット
	}
}


