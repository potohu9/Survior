/*====================*/
//	作成者：森勇介
//	作成日：2014/07/28
/*====================*/
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
	cocos2d::Sprite* sprite;
	//ボタン
	//cocos2d::Menu* pBtn;
	//クールタイム
	int collTime;
	//スキルの種類
	int skillType;
	//プレイヤに渡す
	//preiya

	cocos2d::Node* node;

public:
	//関数
	//コンストラクタ
	SkillButton();
	SkillButton(std::string image, int collTime, int skillType, cocos2d::Node* node);
	//デストラクタ
	~SkillButton();
	//押されたらプレイヤーのstateに教える
	void teachSkill();
	//クールタイム
	void collTimeUpdete();
	//表示するときとかのボタンを出す
	cocos2d::Sprite* getButton();
	//表示
	void render();
	//位置調整用
	void setPosition(cocos2d::Vec2 xy);
	//ボタン系
	void myButtonCallback();
};
