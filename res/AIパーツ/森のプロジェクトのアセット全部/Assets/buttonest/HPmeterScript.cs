using UnityEngine;
using System.Collections;
using UnityEngine.UI;

public class HPmeterScript : MonoBehaviour {

	Image image;

	// Use this for initialization
	void Start () {
		image = GetComponent<Image>();
	}

	// Update is called once per frame
	void Update () {
		image.fillAmount -= 0.0001f;

	}
}
