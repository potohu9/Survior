using UnityEngine;
using System.Collections;

public class RectChenger {
	
	private static int windowLarge = 1280;
	private static int windowSmall = 720;

	public static Rect changeWindowRect(Rect rect){
		Rect result;

		int width;
		int height;

		if(Screen.width > Screen.height){
			width = windowLarge;
			height = windowSmall;
		}
		else{
			width = windowSmall;
			height = windowLarge;
		}

		float widthRatio = (float)Screen.width / (float)width;
		float heightRatio = (float)Screen.height / (float)height;

		result = new Rect(rect.x * widthRatio,rect.y * heightRatio,
		                  rect.width * widthRatio,rect.height * heightRatio);

		return result;
	}
}
