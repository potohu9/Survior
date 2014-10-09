using UnityEngine;
using System.Collections;
// 待機1
public class Wait : AbstractEnemyAIController {
	private GameObject obj;
	private QueryAnimationController qAnime;
	public Wait(GameObject gobj,QueryAnimationController anime){
		obj = gobj;
		qAnime = anime;
	}
	public override void move(int count){
		qAnime.ChangeAnimation(QueryAnimationController.QueryChanAnimationType.FLY_IDLE);
	}
}
// 直進1
public class Straight : AbstractEnemyAIController {
	private GameObject obj;
	private QueryAnimationController qAnime;
	//public float speed;
	public Straight(GameObject gobj,QueryAnimationController anime){
		obj = gobj;
		qAnime = anime;
	}
	public override void move(int count){
		qAnime.ChangeAnimation(QueryAnimationController.QueryChanAnimationType.FLY_STRAIGHT);
		obj.transform.Translate(Vector3.forward * speed);
	}
}

// 細かいジグザグ
public class SmallSlalom : AbstractEnemyAIController {
	private GameObject obj;
	private QueryAnimationController qAnime;
	//public float speed;
	public SmallSlalom(GameObject gobj,QueryAnimationController anime){
		obj = gobj;
		qAnime = anime;
	}
	public override void move(int count){
		qAnime.ChangeAnimation(QueryAnimationController.QueryChanAnimationType.FLY_STRAIGHT);
		obj.transform.Translate(Vector3.forward * speed/2);
		float sinnum = 0.1f * Mathf.Cos (count * 0.1f);
		obj.transform.Translate(Vector3.right * sinnum);
	}
}

//ダメージ受けた
public class Damage : AbstractEnemyAIController {
	private GameObject obj;
	private QueryAnimationController qAnime;
	public Damage(GameObject gobj,QueryAnimationController anime){
		obj = gobj;
		qAnime = anime;
	}
	public override void move(int count){
		qAnime.ChangeAnimation (QueryAnimationController.QueryChanAnimationType.FLY_DAMAGE);
	}
}
// 死んだ
public class Disappo : AbstractEnemyAIController {
	private GameObject obj;
	private QueryAnimationController qAnime;
	public Disappo(GameObject gobj,QueryAnimationController anime){
		obj = gobj;
		qAnime = anime;
	}
	public override void move(int count){
		qAnime.ChangeAnimation (QueryAnimationController.QueryChanAnimationType.FLY_DISAPPOINTMENT);
	}
}
//右旋回
public class TurnRight : AbstractEnemyAIController {
	private GameObject obj;
	private QueryAnimationController qAnime;
	//public float speed;
	public TurnRight(GameObject gobj,QueryAnimationController anime){
		obj = gobj;
		qAnime = anime;
	}
	public override void move(int count){
		qAnime.ChangeAnimation (QueryAnimationController.QueryChanAnimationType.FLY_TORIGHT);
		obj.transform.Translate(Vector3.forward * speed/2);
		obj.transform.Rotate(0, 2, 0);
	}
}
//左旋回
public class TurnLeft : AbstractEnemyAIController {
	private GameObject obj;
	private QueryAnimationController qAnime;
	//public float speed;
	public TurnLeft(GameObject gobj,QueryAnimationController anime){
		obj = gobj;
		qAnime = anime;
	}
	public override void move(int count){
		qAnime.ChangeAnimation (QueryAnimationController.QueryChanAnimationType.FLY_TOLEFT);
		obj.transform.Translate(Vector3.forward * speed/2);
		obj.transform.Rotate(0, -2, 0);
	}
}
//攻撃
public class Attack : AbstractEnemyAIController {
	private GameObject obj;
	private QueryAnimationController qAnime;
	public GameObject prefab;
	GameObject instant_object;
	//public float speed;
	public Attack(GameObject gobj,QueryAnimationController anime){
		obj = gobj;
		qAnime = anime;
	}
	public override void move(int count){
		qAnime.ChangeAnimation (QueryAnimationController.QueryChanAnimationType.FLY_DOWN);
		// プレハブを取得
		prefab = (GameObject)Resources.Load ("Prefabs/Laser1");
		// プレハブからインスタンスを生成
		//if(instant_object==null){
			//iti
			Vector3 vec3 = obj.transform.position;
			instant_object = (GameObject) GameObject.Instantiate(
				prefab,vec3,Quaternion.identity);
			//kakudo
			vec3 = obj.transform.eulerAngles;
			instant_object.transform.Rotate(vec3);
			//kieru
			GameObject.Destroy(instant_object,3);
		//}
	}
}
