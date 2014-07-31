/*
 * move.cpp
 *
 *  Created on: 2014/07/11
 *      Author: scc313
 */

#include <math.h>
#include "Move.h"



USING_NS_CC;

static const int BACK = 100;
static const int GAME = 101;
static const int SKILL = 102;

//ScrollViewの使用に必要 Android.mkの編集も必須
//#include "extensions/cocos-ext.h"
//USING_NS_CC_EXT;
// ======================================

Scene* Move::createScene() {
	auto scene = Scene::create();
	auto layer = Move::create();

	scene->addChild(layer);

	return scene;

}

bool Move::init() {
	if(!Layer::init()) {
		return false;
	}

	// initVariable
	canMove = false;
	margin = 10.0f;


	Size size = Director::getInstance()->getVisibleSize();

	// createSprite
	/*auto back = Sprite::create("nono.jpg");
	back->setTag(2);
	back->setScale(2.0f, 2.0f);
	this->addChild(back, 0);*/

	// BatchNode なんか高速に処理できるらしい
	auto batch = SpriteBatchNode::create("background3.png");
	this->addChild(batch);

	Size temp;
	temp.width  = 2.0f;
	temp.height = 2.0f;

	float scale = 2.0f;

	auto back = Layer::create();
	this->addChild(back, 0);

	// create Background
	for(int i = 0; i < 20; i++) {
		for(int j = 0; j < 20; j++) {
			auto spr = Sprite::createWithTexture(batch->getTexture());
			spr->setScale(scale);
			spr->setPosition(Point(i * (spr->getContentSize().width * scale), j * (spr->getContentSize().height * scale)));
			back->addChild(spr, 0);
		}
	}

	auto obo = Sprite::create("enemy.png");
	obo->setTag(3);
	//this->addChild(obo,2);

	// create Player
	auto sprite = Sprite::create("player.png");
	sprite->setTag(1);
	sprite->setPosition(size.width / 2, size.height / 2);
	sprite->setScale(0.5f, 0.5f);
	this->addChild(sprite, 2);

	// set worldPosition
	world.x = sprite->getPositionX();
	world.y = sprite->getPositionY();

	// init SignSystem（本来はEnemyクラスの中）
	as.init(this, 10);

	//
	this->runAction(Follow::create(sprite));

	//auto camera = new ActionCamera();
	//camera->autorelease();
	//camera->setTarget(sprite);

	// createScrollView
	/*auto size = Director::getInstance()->getVisibleSize();
	auto scroll = ScrollView::create(size, nullptr);

	auto sSprite = Sprite::create("nono.jpg");
	scroll->setContainer(sSprite);
	scroll->setContentSize(sSprite->getContentSize());*/

	// touchEvent
	auto dispatcher = Director::getInstance()->getEventDispatcher();
	auto listener = EventListenerTouchOneByOne::create();

	listener->onTouchBegan = CC_CALLBACK_2(Move::onTouchBegan, this);
	listener->onTouchMoved = CC_CALLBACK_2(Move::onTouchMoved, this);
	listener->onTouchEnded = CC_CALLBACK_2(Move::onTouchEnded, this);

	dispatcher->addEventListenerWithSceneGraphPriority(listener, this);

	// create Button

	auto skill = Layer::create();
	skill->setTag(SKILL);
	//skill->setAnchorPoint(Vec2(0.5f, 0.5f));
	this->addChild(skill);

	SkillButtonFactory skillButtonFactory;		//ファクトリーを作る

	for(int i=0; i<5; i++){
	  	skillButton[i] = skillButtonFactory.makeSkillButtonFactory(i+1);	//ファクトリーでスキルボタンを作る

	  	Sprite* sb = skillButton[i].getButton();

	  	Size sprSize = sb->getContentSize();
	  	float spriteSize = (size.height * 0.2f) / sprSize.height;
	  	sb->setScale(spriteSize);

	   	//skillButton[i].setPosition( Vec2(size.width - spriteSize, (i * (size.height * 0.2f)) + spriteSize));	//位置
	  	sb->setPosition(Vec2(size.width - sb->getContentSize().width * spriteSize * 0.5f, (i * (size.height * 0.2f)) + sb->getContentSize().height * spriteSize * 0.5f));
	   	skill->addChild(sb, 3);	//追加
	}

	// update
	this->scheduleUpdate();

	return true;
}

/**
 * touchEvent
 */
bool Move::onTouchBegan(Touch* touch, Event* event) {
	canMove = true;
	tp = this->convertToNodeSpace(touch->getLocation());

	as.create(Vec2(tp.x, tp.y), 180);


	return true;
}

void Move::onTouchMoved(Touch* touch, Event* event) {
	tp = this->convertToNodeSpace(touch->getLocation());
}

void Move::onTouchEnded(Touch* touch, Event* event) {
	canMove = false;
}

/**
 * update
 */
void Move::update(float delta) {
	// プレイヤー移動部分実験 ==
	float speed = 12;

	if(canMove) {
		auto sprite = this->getChildByTag(1);
		//sprite->setPosition(ccp(10, 10));
		float x = sprite->getPositionX();
		float y = sprite->getPositionY();

		//Point tes = Director::getInstance()->convertToGL(tp);
		CCLOG("p=%f",x);
		CCLOG("y=%f",y);
		// 画面の限界の部分
		//if(tp.x > x - world.x + margin || tp.x < x - world.x - margin
		//|| tp.y > y - world.y + margin || tp.y < y - world.y - margin) {
			float rad = atan2f(tp.y - y, tp.x - x);

			vec.x = cos(rad) * speed;
			vec.y = sin(rad) * speed;

			world.x += vec.x;
			world.y += vec.y;

			sprite->setPosition(x + vec.x, y + vec.y);

			Size size = Director::getInstance()->getVisibleSize();
			auto layer = this->getChildByTag(SKILL);
			//layer->setPosition(sprite->getPositionX() - size.width * 0.5f, sprite->getPositionY() - size.height * 0.5f);
		//}
	}
	// ==

	// 敵の攻撃予兆部分テスト
	as.update();
	// ==
}

