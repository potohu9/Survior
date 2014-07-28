/*====================*/
//	作成者：森勇介
//	作成日：2014/07/28
/*====================*/
#pragma once
#include "SkillButton.h"
class SkillButtonFactory
{
private:

public:
	SkillButton makeSkillButtonFactory(int skillId);	//スキルボタンを作る関数
};
