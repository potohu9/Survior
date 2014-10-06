using UnityEngine;
using System.Collections;

public class ControllerScript : MonoBehaviour {

	//private EnemyMoveScript enemyMoveScript;
	private Animator animator;
	private GameObject oya;

	private bool animeFlag=true;
	private int animeMode=0;
	private int count=0;

	//EnemyMoveFactory enemyMoveFactory;
	AbstractEnemyAIController straight;
	AbstractEnemyAIController wait;


	// Use this for initialization
	void Start () {
		animator = GetComponent<Animator> ();
		oya = gameObject.transform.parent.gameObject;

		//enemyMoveScript = new EnemyMoveScript (oya,animator);
		animator.SetInteger ("isAnime", 0);

		//enemyMoveFactory = new EnemyMoveFactory(oya,animator);
		straight = new Straight1 (oya,animator);
		wait = new Wait1 (oya, animator);
	}
	
	// Update is called once per frame
	void Update () {
		if(animeFlag){
			if (Input.GetKey("up")) {
				animeFlag=false;
				animeMode=1;
			} else if(Input.GetKey("down")){
				animeFlag=false;
				animeMode=2;
			}
			if (Input.GetKey("right")) {
				//oya.transform.Rotate (0, 1, 0);
				animeFlag=false;
				animeMode=3;
			}else if (Input.GetKey ("left")) {
				oya.transform.Rotate (0, -1, 0);
				animeFlag=false;
				animeMode=4;
			}
		} else {
			count++;
		}

		switch(animeMode){
		case 0:
			//enemyMoveScript.wait();
			wait.move (count);
			break;
		case 1:
			//enemyMoveScript.straight();
			straight.move (count);
			break;
		case 2:
			//enemyMoveScript.smallSlalom(count);
			break;
		case 3:
			//enemyMoveScript.RlargeSlalom(count);
			break;
		case 4:
			//enemyMoveScript.LlargeSlalom(count);
			break;
		}
	}

	void setAnimeFlag(){
		animeFlag=true;
		animeMode=0;
		count = 0;
	}
}
