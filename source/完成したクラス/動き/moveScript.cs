using UnityEngine;
using System.Collections;
// 待機1
public class Wait1 : AbstractEnemyAIController {
	GameObject oyaobj;
	Animator animator;
	public Wait1(GameObject obj,Animator ani){
		oyaobj = obj;
		animator = ani;
	}
	public override void move(int count){
		animator.SetInteger ("isAnime", 0);
	}
}
// 直進1
public class Straight1 : AbstractEnemyAIController {
	GameObject oyaobj;
	Animator animator;
	public Straight1(GameObject obj,Animator ani){
		oyaobj = obj;
		animator = ani;
	}
	public override void move(int count){
		animator.SetInteger ("isAnime", 1);
		oyaobj.transform.Translate(Vector3.forward * 0.1f);
	}
}
// 細かいジグザグ
public class SmallSlalom1 : AbstractEnemyAIController {
	GameObject oyaobj;
	Animator animator;
	public SmallSlalom1(GameObject obj,Animator ani){
		oyaobj = obj;
		animator = ani;
	}
	public override void move(int count){
		animator.SetInteger ("isAnime", 2);
		oyaobj.transform.Translate(Vector3.forward * 0.1f);
		float sinnum = 0.1f * Mathf.Cos (count * 0.1f);
		oyaobj.transform.Translate(Vector3.right * sinnum);
	}
}
//大きなジグザグ右
public class RlargeSlalom1 : AbstractEnemyAIController {
	GameObject oyaobj;
	Animator animator;
	public RlargeSlalom1(GameObject obj,Animator ani){
		oyaobj = obj;
		animator = ani;
	}
	public override void move(int count){
		animator.SetInteger ("isAnime", 3);
		oyaobj.transform.Translate(Vector3.forward * 0.1f);
		float sinnum = 0.7f * Mathf.Sin (count/13 * 0.75f);
		oyaobj.transform.Translate(Vector3.right * sinnum);
	}
}
// 大きなジグザグ左
public class LlargeSlalom1 : AbstractEnemyAIController {
	GameObject oyaobj;
	Animator animator;
	public LlargeSlalom1(GameObject obj,Animator ani){
		oyaobj = obj;
		animator = ani;
	}
	public override void move(int count){
		animator.SetInteger ("isAnime", 3);
		oyaobj.transform.Translate(Vector3.forward * 0.1f);
		float sinnum = 0.7f * Mathf.Sin (count/13 * 0.75f);
		oyaobj.transform.Translate(Vector3.right * -sinnum);
	}
}
