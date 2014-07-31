/*
 * Circle.h
 *
 *  Created on: 2014/07/21
 *      Author: scc313
 */

#ifndef CIRCLE_H_
#define CIRCLE_H_

#include "sign/Sign.h"

class CircleSign : public Sign {
private:
	float rot;

public:
	CircleSign(const char* path, cocos2d::Vec2 position, int count);
	~CircleSign();
	void update();
};

#endif /* CIRCLE_H_ */
