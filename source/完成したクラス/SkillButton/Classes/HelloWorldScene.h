/*====================*/
//	作成者：森勇介
//	作成日：2014/07/28
/*====================*/
#pragma once
#include "cocos2d.h"
#include "SkillButtonFactory.h"

class HelloWorld : public cocos2d::Layer
{
private:
	SkillButton skillButton[5];
public:
    // there's no 'id' in cpp, so we recommend returning the class instance pointer
    static cocos2d::Scene* createScene();

    // Here's a difference. Method 'init' in cocos2d-x returns bool, instead of returning 'id' in cocos2d-iphone
    virtual bool init();

    bool onTouchBegan(cocos2d::Touch* touch, cocos2d::Event* event);	//タッチ時
    void onTouchMoved(cocos2d::Touch* touch, cocos2d::Event* event);	//タッチ中移動
    void onTouchEnded(cocos2d::Touch* touch, cocos2d::Event* event);	//タッチ終了時

    // a selector callback
    //void menuCloseCallback(cocos2d::Ref* pSender);
    void update(float delta);
    // implement the "static create()" method manually
    CREATE_FUNC(HelloWorld);
};
