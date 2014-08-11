/*
 * PointSign.h
 *
 *  Created on: 2014/07/31
 *      Author: scc313
 */

#ifndef POINTSIGN_H_
#define POINTSIGN_H_

#include "sign/Sign.h"

class PointSign : public Sign {
private:
	float rot;

public:
	PointSign(const char* path, cocos2d::Vec2 position, int count);
	~PointSign();
	void update();
};

#endif /* POINTSIGN_H_ */
