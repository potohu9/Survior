#include "SkillButtonFactory.h"


SkillButton SkillButtonFactory::makeSkillButtonFactory(int skillId){
	//à¯êîÇÕÅ@ÅuString image, int collTime, int skillTypeÅv
	switch(skillId){
	case 1:
		return SkillButton("Ken.png", 200, skillId);
		break;
	case 2:
		return SkillButton("Tate.png", 200, skillId);
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
		return SkillButton("HelloWorld.png", 200, 1);
		break;
	}
	return SkillButton("HelloWorld.png", 200, 1);
}
