/*
 * Sign.cpp
 *
 *  Created on: 2014/07/21
 *      Author: scc313
 */

#include  "Sign.h"

USING_NS_CC;
/*
Sign::Sign(char* path) {
	sprite = Sprite::create(path);

	count = 0;
}

Sign::Sign(auto sprite) {
	this.sprite = sprite;
}*/

Sign::Sign(const char* path, Vec2& position, int& count)
:Node(){
	sprite = Sprite::create(path);
	sprite->setPosition(position);
	this->addChild(sprite, 1);
	this->count = count;
}

Sign::~Sign() {

}

bool Sign::canAttack() {
	return count <= 0 ? true : false;
}



