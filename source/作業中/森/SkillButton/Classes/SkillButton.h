#pragma once
#include "cocos2d.h"

class SkillButton
{
private:
	//変数
	//クールタイム
	int collTimeCount;
	//スキルの状態
	bool skillState;
	//画像データ
	//Sprite* sprite;
	//ボタン
	cocos2d::Menu* pBtn;
	//クールタイム
	int collTime;
	//スキルの種類
	int skillType;
	//スキル効果を与える対象
	//Player player;

	//ボタン系
	void myButtonCallback(cocos2d::Ref* pSender);

public:
	//関数
	//コンストラクタ
	SkillButton(/*Sprite* sprite,*/std::string image, int collTime, int skillType);
	//デストラクタ
	~SkillButton();
	//押されたらプレイヤーのstateに教える
	void teachSkill();
	//クールタイム
	void collTimeUpdete();
	//表示するときとかのボタンを出す
	cocos2d::Menu* getButton();
	//位置調整用
	void setPosition(cocos2d::Vec2 xy);
};
