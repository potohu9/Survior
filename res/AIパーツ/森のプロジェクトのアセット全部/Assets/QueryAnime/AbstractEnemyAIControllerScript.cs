using UnityEngine;
using System.Collections;

public abstract class AbstractEnemyAIController{
	/*
	// 待機
	public override abstract void wait();
	// 直進
	public override abstract void straight();
	// 細かいジグザグ
	public override abstract void smallSlalom(int count);
	// 大きなジグザグ右
	public override abstract void RlargeSlalom(int count);
	// 大きなジグザグ左
	public override abstract void LlargeSlalom(int count);
	*/
	public float speed = 0.1f;
	public abstract void move(int count);
}
