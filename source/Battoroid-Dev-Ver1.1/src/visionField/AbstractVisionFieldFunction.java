package visionField;

import object2D.Object2D;

public abstract class AbstractVisionFieldFunction implements VisionFieldFunction{
	@Override
    public VisionFieldFunction compose(final VisionFieldFunction f) {		// 関数合成用関数
        return new AbstractVisionFieldFunction() {
			@Override
			public boolean isInVisionField(Object2D obj1, Object2D obj2) {
				if(!f.isInVisionField(obj1,obj2))		return false;
				return AbstractVisionFieldFunction.this.isInVisionField(obj1,obj2);
			}
        };
    }
	
	@Override
	public abstract boolean isInVisionField(Object2D obj1,Object2D obj2);
}