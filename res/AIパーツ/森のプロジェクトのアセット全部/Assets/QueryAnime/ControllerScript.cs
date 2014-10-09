using UnityEngine;
using System.Collections;

public class ControllerScript : MonoBehaviour {

	private GameObject obj;
	private QueryAnimationController qAnime;
	private int count=0;

	//EnemyMoveFactory enemyMoveFactory;
	private AbstractEnemyAIController straight;
	private AbstractEnemyAIController wait;
	private AbstractEnemyAIController smallSlalom;
	private AbstractEnemyAIController damage;
	private AbstractEnemyAIController disappo;
	private AbstractEnemyAIController turnRight;
	private AbstractEnemyAIController turnLeft;
	private AbstractEnemyAIController attack;


	// Use this for initialization
	void Start () {

		obj = GameObject.Find ("Query-Chan");
		qAnime =  obj.GetComponent<QueryAnimationController>();
		straight = new Straight (obj,qAnime);
		wait = new Wait (obj, qAnime);
		smallSlalom = new SmallSlalom(obj,qAnime);
		damage = new Damage (obj, qAnime);
		disappo = new Disappo (obj, qAnime);
		turnRight = new TurnRight (obj, qAnime);
		turnLeft = new TurnLeft (obj, qAnime);
		attack = new Attack (obj, qAnime);
	}
	
	// Update is called once per frame
	void Update () {
		if (Input.GetKey("up")) {
			StraightMove(count);
		} else if(Input.GetKey("down")){
			WaitMove(count);
		}
		if(Input.GetKey(KeyCode.W)){
			SmallSlalomMove(count);
			count++;
		}
		if(Input.GetKey(KeyCode.D)){
			DamageMove(count);
		}
		if(Input.GetKey(KeyCode.H)){
			DisappoMove(count);
		}
		if(Input.GetKey(KeyCode.E)){
			TurnRightMove(count);
		}
		if(Input.GetKey(KeyCode.Q)){
			TurnLeftMove(count);
		}
		if(Input.GetKey(KeyCode.A)){
			AttackMove(count);
		}

		foreach(Touch touch in Input.touches){
			if (touch.phase == TouchPhase.Began) {
				AttackMove(count);
			}
		}


		if (Input.GetKey("right")) {
			transform.Rotate(0, 10, 0);
		} else if (Input.GetKey ("left")) {
			transform.Rotate(0, -10, 0);
		}

		if(Input.GetKey(KeyCode.X)){
			straight.speed+=0.01f;
			smallSlalom.speed+=0.01f;
			turnRight.speed+=0.01f;
			turnLeft.speed+=0.01f;
		} else if(Input.GetKey(KeyCode.Z)){
			straight.speed-=0.01f;
			smallSlalom.speed-=0.01f;
			turnRight.speed-=0.01f;
			turnLeft.speed-=0.01f;
		}
	}

	public void StraightMove(int count){
		straight.move (count);
	}
	public void WaitMove(int count){
		wait.move (count);
	}
	public void SmallSlalomMove(int count){
		smallSlalom.move(count);
	}
	public void DamageMove(int count){
		damage.move(count);
	}
	public void DisappoMove(int count){
		disappo.move(count);
	}
	public void TurnRightMove(int count){
		turnRight.move(count);
	}
	public void TurnLeftMove(int count){
		turnLeft.move(count);
	}
	public void AttackMove(int count){
		attack.move(count);
	}

}
