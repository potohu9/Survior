/*
 * AttackSign.h
 *
 *  Created on: 2014/07/21
 *      Author: scc313
 */

#ifndef ATTACKSIGN_H_
#define ATTACKSIGN_H_

#include <vector>
#include "cocos2d.h"
#include "Sign.h"

//class Sing;

class AttackSign {
private:
	int index;
	// Sing‚Ì”z—ñ
	std::vector<Sign*> array;
	cocos2d::Node* parent;
public:
	// Ÿ‚ÉUŒ‚‚·‚éêŠ‚ğw’è
	void setPosition(cocos2d::Vec2& pos);
	void create(cocos2d::Vec2 pos, int count);

	//
	// UŒ‚—\’›(update)
	void update();
	void init(cocos2d::Node* parent, int size);
	void attackCallBack(cocos2d::Vec2 pos);
};

#endif /* ATTACKSIGN_H_ */
