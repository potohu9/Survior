/*
 * move.h
 *
 *  Created on: 2014/07/11
 *      Author: scc313
 */

#ifndef MOVE_H_
#define MOVE_H_

#include "cocos2d.h"
#include "sign/SignSystem.h"

#include "SkillButtonFactory.h"

class Move : public cocos2d::Layer{
private:
	cocos2d::Point tp;	// touchPoint
	cocos2d::Point vec;
	cocos2d::Point world;
	bool canMove;
	float margin;

	AttackSign as;

	SkillButton skillButton[5];

public:
	static cocos2d::Scene* createScene();
	virtual bool init();
	CREATE_FUNC(Move);
	virtual bool onTouchBegan(cocos2d::Touch* touch, cocos2d::Event* event);
	virtual void onTouchMoved(cocos2d::Touch* touch, cocos2d::Event* event);
	virtual void onTouchEnded(cocos2d::Touch* touch, cocos2d::Event* event);
	void update(float delta);
};

#endif /* MOVE_H_ */
