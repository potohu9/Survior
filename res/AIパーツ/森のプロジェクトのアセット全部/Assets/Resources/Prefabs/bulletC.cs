using UnityEngine;
using System.Collections;

public class bulletC : MonoBehaviour {

	public float speed=150.0f;
	// Use this for initialization
	void Start () {
		//rigidbody.AddForce(transform.forward + transform.right ) * Speed,ForceMode.VelocityChange);
		rigidbody.AddForce( ( transform.forward ) * speed, ForceMode.VelocityChange );
	}
	
	// Update is called once per frame
	void Update () {
	
	}
}
