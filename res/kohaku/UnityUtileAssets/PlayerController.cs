using UnityEngine;
using System.Collections;

public class PlayerController : MonoBehaviour {

	public float spd = 1;
	public float maxSpeed = 5;
	public Vector3 vel = new Vector3(0,0,0);
	
	void Start () {
	
	}

	void Update () {
		bool isTouch = false;
		foreach (Touch touch in Input.touches) {
			if (touch.phase != TouchPhase.Ended || touch.phase != TouchPhase.Canceled) {
				// 現在のタッチ位置からRaycast
				Ray ray = Camera.main.ScreenPointToRay (touch.position);
				RaycastHit hit = new RaycastHit();
				if (Physics.Raycast(ray,out hit)){
					isTouch = true;
					Vector3 targetPos = new Vector3(hit.point.x,hit.point.y,hit.point.z);
					Vector3 sub = targetPos - transform.position;
					Vector3 nor = sub.normalized;
					vel = nor * spd;
					vel.y = 0;
				}
			}
		}
		if(!isTouch){
			vel.x = 0;
			vel.z = 0;
		}
		rigidbody.AddForce(
			vel,
			ForceMode.Impulse);
		if(rigidbody.velocity.magnitude > maxSpeed){
			rigidbody.velocity = Vector3.ClampMagnitude(rigidbody.velocity,maxSpeed);
		}
	}
}
