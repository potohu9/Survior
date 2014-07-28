/*====================*/
//	作成者：森勇介
//	作成日：2014/07/28
/*====================*/
#include "HelloWorldScene.h"
USING_NS_CC;

Scene* HelloWorld::createScene()
{
    // 'scene' is an autorelease object
    auto scene = Scene::create();
    
    // 'layer' is an autorelease object
    auto layer = HelloWorld::create();

    // add layer as a child to scene
    scene->addChild(layer);

    // return the scene
    return scene;
}

// on "init" you need to initialize your instance
bool HelloWorld::init()
{
    //////////////////////////////
    // 1. super init first
    if ( !Layer::init() )
    {
        return false;
    }
    
    Size visibleSize = Director::getInstance()->getVisibleSize();
    Vec2 origin = Director::getInstance()->getVisibleOrigin();

    // add "HelloWorld" splash screen"
    auto sprite = Sprite::create("HelloWorld.png");

    // position the sprite on the center of the screen
    sprite->setPosition(Vec2(visibleSize.width/2 + origin.x, visibleSize.height/2 + origin.y));

    // add the sprite as a child to this layer
    this->addChild(sprite, 0);
    
    //ボタン
    SkillButtonFactory skillButtonFactory;		//ファクトリーを作る
    //スキルボタンを作る
    for(int i=0; i<5; i++){
    	skillButton[i] = skillButtonFactory.makeSkillButtonFactory(i+1);	//ファクトリーでスキルボタンを作る
    	skillButton[i].setPosition( Vec2(visibleSize.width-200,950-(i*200)) );	//位置
    	this->addChild( skillButton[i].getButton() );	//追加
    }


    // イベント
    //タッチ処理
    auto listener = EventListenerTouchOneByOne::create();
    listener->setSwallowTouches(true);
    listener->onTouchBegan = CC_CALLBACK_2(HelloWorld::onTouchBegan, this);	//タッチ時
    listener->onTouchMoved = CC_CALLBACK_2(HelloWorld::onTouchMoved,this);	//タッチ中の移動
    listener->onTouchEnded = CC_CALLBACK_2(HelloWorld::onTouchEnded, this);	//タッチ終了時
    auto dip = Director::getInstance()->getEventDispatcher();
    dip->addEventListenerWithSceneGraphPriority(listener, this);

    //更新処理
    this->scheduleUpdate();
    return true;
}

bool HelloWorld::onTouchBegan(cocos2d::Touch* touch,cocos2d::Event* event){
    //画面をタッチした時の処理

	Point point = touch->getLocation(); //クリック座標
	for(int i=0; i<5; i++){
		Sprite* sprite = skillButton[i].getButton();
		Rect  rect = sprite->boundingBox(); //画像の大きさの矩形
		if(rect.containsPoint(point)){
		//当たった(true)場合の処理
			skillButton[i].myButtonCallback();
		}
	}

	return true;
}

void HelloWorld::onTouchMoved(cocos2d::Touch* touch,cocos2d::Event* event) {
    //タッチ中の処理
}

void HelloWorld::onTouchEnded(cocos2d::Touch *touch, cocos2d::Event *event){
     //タッチが終わった時の処理
}

void HelloWorld::update(float delta) {
      //更新処理
}
