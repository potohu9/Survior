package visionField;

import object2D.Object2D;

public interface VisionFieldFunction {
	boolean isInVisionField(Object2D obj1,Object2D obj2);
	VisionFieldFunction compose(VisionFieldFunction f);
}
