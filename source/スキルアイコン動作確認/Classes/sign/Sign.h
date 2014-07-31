/*
 * Sign.h
 *
 *  Created on: 2014/07/21
 *      Author: scc313
 */

#ifndef SIGN_H_
#define SIGN_H_

#include "cocos2d.h"

class Sign : public cocos2d::Node{
protected:
	cocos2d::Node* sprite;
	// position ç¿ïW
	cocos2d::Vec2 position;
	int count;

public:
	Sign(const char* path, cocos2d::Vec2& position, int& count);
	virtual ~Sign();
	// update
	virtual void update() = 0;
	bool canAttack();
};

#endif /* SIGN_H_ */
