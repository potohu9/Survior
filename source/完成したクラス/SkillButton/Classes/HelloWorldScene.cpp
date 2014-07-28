/*====================*/
//	�쐬�ҁF�X�E��
//	�쐬���F2014/07/28
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
    
    //�{�^��
    SkillButtonFactory skillButtonFactory;		//�t�@�N�g���[�����
    //�X�L���{�^�������
    for(int i=0; i<5; i++){
    	skillButton[i] = skillButtonFactory.makeSkillButtonFactory(i+1);	//�t�@�N�g���[�ŃX�L���{�^�������
    	skillButton[i].setPosition( Vec2(visibleSize.width-200,950-(i*200)) );	//�ʒu
    	this->addChild( skillButton[i].getButton() );	//�ǉ�
    }


    // �C�x���g
    //�^�b�`����
    auto listener = EventListenerTouchOneByOne::create();
    listener->setSwallowTouches(true);
    listener->onTouchBegan = CC_CALLBACK_2(HelloWorld::onTouchBegan, this);	//�^�b�`��
    listener->onTouchMoved = CC_CALLBACK_2(HelloWorld::onTouchMoved,this);	//�^�b�`���̈ړ�
    listener->onTouchEnded = CC_CALLBACK_2(HelloWorld::onTouchEnded, this);	//�^�b�`�I����
    auto dip = Director::getInstance()->getEventDispatcher();
    dip->addEventListenerWithSceneGraphPriority(listener, this);

    //�X�V����
    this->scheduleUpdate();
    return true;
}

bool HelloWorld::onTouchBegan(cocos2d::Touch* touch,cocos2d::Event* event){
    //��ʂ��^�b�`�������̏���

	Point point = touch->getLocation(); //�N���b�N���W
	for(int i=0; i<5; i++){
		Sprite* sprite = skillButton[i].getButton();
		Rect  rect = sprite->boundingBox(); //�摜�̑傫���̋�`
		if(rect.containsPoint(point)){
		//��������(true)�ꍇ�̏���
			skillButton[i].myButtonCallback();
		}
	}

	return true;
}

void HelloWorld::onTouchMoved(cocos2d::Touch* touch,cocos2d::Event* event) {
    //�^�b�`���̏���
}

void HelloWorld::onTouchEnded(cocos2d::Touch *touch, cocos2d::Event *event){
     //�^�b�`���I��������̏���
}

void HelloWorld::update(float delta) {
      //�X�V����
}
