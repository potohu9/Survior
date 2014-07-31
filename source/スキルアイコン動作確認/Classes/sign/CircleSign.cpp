/*
 * Circle.cpp
 *
 *  Created on: 2014/07/21
 *      Author: scc313
 */

#include "sign/CircleSign.h"

USING_NS_CC;

CircleSign::CircleSign(const char* path, Vec2 position, int count)
:Sign(path, position, count) {
	rot = 0;
}

CircleSign::~CircleSign() {
	CCLog("%s", "Circle Delete");
}

void CircleSign::update() {
	//rot = rot + 0.5f <= 360.0f ? rot + 0.5f : 0;
	rot += 1.5f;
	sprite->setRotation(rot);

	if(count == 0) {

	}

	count--;
}




