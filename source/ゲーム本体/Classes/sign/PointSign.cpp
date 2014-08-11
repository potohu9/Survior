/*
 * PointSign.cpp
 *
 *  Created on: 2014/07/31
 *      Author: scc313
 */

#include "sign/PointSign.h"

USING_NS_CC;

PointSign::PointSign(const char* path, Vec2 position, int count)
:Sign(path, position, count) {
	rot = 1.0f;
}

PointSign::~PointSign() {
}

void PointSign::update() {

	/*if(rot <= 0.5f) {
		rot = 1.0f;
	}
	else {
		rot -= 0.015f;
	}

	this->sprite->setScale(rot);*/

	rot += 1.5f;
	sprite->setRotation(rot);

	if(count == 0) {

	}

	count--;
}


