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
			// U

			// EnemyΙΐWΖUΜνήπ³¦ιS //

			// Α·S
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
