#include "HelloWorldScene.h"
#include "SkillButtonFactory.h"
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

    /////////////////////////////
    // 2. add a menu item with "X" image, which is clicked to quit the program
    //    you may modify it.

    // add a "close" icon to exit the progress. it's an autorelease object
    /*auto closeItem = MenuItemImage::create(
                                           "CloseNormal.png",
                                           "CloseSelected.png",
                                           CC_CALLBACK_1(HelloWorld::menuCloseCallback, this));
    
	closeItem->setPosition(Vec2(origin.x + visibleSize.width - closeItem->getContentSize().width/2 ,
                                origin.y + closeItem->getContentSize().height/2));

    // create menu, it's an autorelease object
    auto menu = Menu::create(closeItem, NULL);
    menu->setPosition(Vec2::ZERO);
    this->addChild(menu, 1);
*/
    /////////////////////////////
    // 3. add your codes below...

    // add a label shows "Hello World"
    // create and initialize a label
    
    auto label = LabelTTF::create("Hello World", "Arial", 24);
    
    // position the label on the center of the screen
    label->setPosition(Vec2(origin.x + visibleSize.width/2,
                            origin.y + visibleSize.height - label->getContentSize().height));

    // add the label as a child to this layer
    this->addChild(label, 1);

    // add "HelloWorld" splash screen"
    auto sprite = Sprite::create("HelloWorld.png");

    // position the sprite on the center of the screen
    sprite->setPosition(Vec2(visibleSize.width/2 + origin.x, visibleSize.height/2 + origin.y));

    // add the sprite as a child to this layer
    this->addChild(sprite, 0);
    
    //�{�^��
    SkillButtonFactory skillButtonFactory;		//�t�@�N�g���[�����
    SkillButton skillButton1 = skillButtonFactory.makeSkillButtonFactory(1);	//�t�@�N�g���[�ŃX�L���{�^������� ��
    SkillButton skillButton2 = skillButtonFactory.makeSkillButtonFactory(2);	//�t�@�N�g���[�ŃX�L���{�^������� ��
    SkillButton skillButton3 = skillButtonFactory.makeSkillButtonFactory(1);	//�t�@�N�g���[�ŃX�L���{�^������� ��
    SkillButton skillButton4 = skillButtonFactory.makeSkillButtonFactory(2);	//�t�@�N�g���[�ŃX�L���{�^������� ��
    SkillButton skillButton5 = skillButtonFactory.makeSkillButtonFactory(1);	//�t�@�N�g���[�ŃX�L���{�^������� ��

    /*skillButton1.setPosition( Vec2(visibleSize.width-200,800) );	//�ʒu
    skillButton2.setPosition( Vec2(visibleSize.width-200,500) );	//�ʒu
    skillButton3.setPosition( Vec2(visibleSize.width-200,200) );	//�ʒu*/

    skillButton1.setPosition( Vec2(visibleSize.width-200,950) );	//�ʒu
    skillButton2.setPosition( Vec2(visibleSize.width-200,750) );	//�ʒu
    skillButton3.setPosition( Vec2(visibleSize.width-200,550) );	//�ʒu

    skillButton4.setPosition( Vec2(visibleSize.width-200,350) );	//�ʒu
    skillButton5.setPosition( Vec2(visibleSize.width-200,150) );	//�ʒu

    this->addChild( skillButton1.getButton() );	//�ǉ�
    this->addChild( skillButton2.getButton() );	//�ǉ�
    this->addChild( skillButton3.getButton() );	//�ǉ�
    this->addChild( skillButton4.getButton() );	//�ǉ�
    this->addChild( skillButton5.getButton() );	//�ǉ�

    //init()�̒���
    this->scheduleUpdate();
    return true;
}

void HelloWorld::update(float delta) {
      //�X�V����
}

/*void HelloWorld::menuCloseCallback(Ref* pSender)
{
#if (CC_TARGET_PLATFORM == CC_PLATFORM_WP8) || (CC_TARGET_PLATFORM == CC_PLATFORM_WINRT)
	MessageBox("You pressed the close button. Windows Store Apps do not implement a close button.","Alert");
    return;
#endif

    Director::getInstance()->end();

#if (CC_TARGET_PLATFORM == CC_PLATFORM_IOS)
    exit(0);
#endif
}*/
