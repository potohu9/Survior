#include "SkillButtonFactory.h"


SkillButton SkillButtonFactory::makeSkillButtonFactory(int skillId){
	//引数は　「String image, int collTime, int skillType」
	switch(skillId){
	case 1:
		return SkillButton("ken.png", 200, skillId);
		break;
	case 2:
		return SkillButton("tate.png", 200, skillId);
		break;
	/*case 3:
		return SkillButton("HelloWorld.png", 200, skillId);
		break;
	case 4:
		return SkillButton("HelloWorld.png", 200, skillId);
		break;
	case 5:
		return SkillButton("HelloWorld.png", 200, skillId);
		break;*/
	default:
		return SkillButton("HelloWorld.png", 200, skillId);
		break;
	}
	return SkillButton("HelloWorld.png", 200, skillId);
}
