/*====================*/
//	作成者：森勇介
//	作成日：2014/07/28
/*====================*/
#include "SkillButtonFactory.h"

//スキルボタンを作る関数
SkillButton SkillButtonFactory::makeSkillButtonFactory(int skillId){
	//引数は　「String image, int collTime, int skillType」
	switch(skillId){
	case 1:
		return SkillButton("Ken.png", 200, skillId);
		break;
	case 2:
		return SkillButton("Tate.png", 200, skillId);
		break;
	case 3:
		return SkillButton("SpeedUp.png", 200, skillId);
		break;
	case 4:
		return SkillButton("Yari.png", 200, skillId);
		break;
	case 5:
		return SkillButton("Hane.png", 200, skillId);
		break;
	case 6:
		return SkillButton("Bomu.png", 200, skillId);
		break;
	default:
		return SkillButton("Bomu.png", 200, skillId);
		break;
	}
	return SkillButton("Bomu.png", 200, skillId);
}
