#include "Enemy.h"
Enemy::Enemy(cocos2d::Vec3 pos, cocos2d::Vec3 vec, cocos2d::Sprite* sprite, int hp,/*AI,テンプレート*/cocos2d::Node* node) : Character(pos, vec, sprite, hp, node){
	//moveAi
	//atakkuAi
	//攻撃サイン(予兆）/滝口
	//サイン.ini(nod,10);
	//Attack
	//アタックシステム
	//アタックテンプレート
}
Enemy::~Enemy(){

}
void Enemy::Update(){
	//AIのアップデート
	//攻撃サインのアップデート
	//アタックシステムのアップデート
}
//Attackを作る
void teachAttack(){
	//アタックテンプレートを見てアタックを作る
	//作ったアタックをアタックシステムに教える
}

