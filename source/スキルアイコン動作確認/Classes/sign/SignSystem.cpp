#include "SignSystem.h"
#include "CircleSign.h"

using namespace std;
USING_NS_CC;

void AttackSign::create(Vec2 pos, int count) {
	Sign* sign = new CircleSign("Circle.png", pos, count);
	//parent->addChild(sign);
	parent->addChild(sign, 1);
	array.push_back(sign);
}

void AttackSign::update() {
	for(vector<Sign*>::iterator it = array.begin(); it != array.end();) {
		Sign* temp = *it;

		temp->update();

		if(temp->canAttack() == true) {
			// UŒ‚ˆ—

			// Enemy‚ÉÀ•W‚ÆUŒ‚‚ÌŽí—Þ‚ð‹³‚¦‚éƒ“ƒS‚—‚—‚— //

			// Á‚·ƒ“ƒS
			temp->removeAllChildrenWithCleanup(true);
			temp->removeFromParent();

			it = array.erase(it);
			delete temp;
		}
		else {
			++it;
		}
	}

}

void AttackSign::init(Node* parent, int size) {
	this->parent = parent;
}
