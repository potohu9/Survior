package particle;

import openGLES20.Common;
import function.ColorData;
import function.Vector2D;
import function.Vector2DDomain;
import gameObject.GameObject;

public class Particle extends GameObject{
	private Vector2DDomain vector2DDomain;
	private float disappearanceDistance;	//���ł܂ł̋���
	private ColorData colorData;			//�F�f�[�^
	private float scale;					//�傫��
	private ParticleSystem system;
	
	public Particle(Vector2DDomain vector2DDomain){
		this.vector2DDomain = vector2DDomain;
	}
	
	/*�e�Z�b�^�[*/
	public void setDisappearanceDistance(float disappearanceDistance) {
		this.disappearanceDistance = disappearanceDistance;
	}
	public void setColorData(ColorData colorData) {
		this.colorData = colorData;
	}
	public void setScale(float scale) {
		this.scale = scale;
	}
	public void setSystem(ParticleSystem system) {
		this.system = system;
	}
	
	/*�e�Q�b�^�[*/
	public float getDisappearanceDistance()	{return disappearanceDistance;}
	public ColorData getColorData()			{return colorData;}
	public float getScale()					{return scale;}
	public ParticleSystem getSystem()		{return system;}
	
	/*�����F�������W�A�ړI���W�A�F�f�[�^(RGBA)�A���x�A��������*/
	/*����������*/
	public void initialize(Vector2D generatPoint,Vector2D targetPoint,ColorData colorData,float initialVelocity,float scale){
		this.colorData = colorData;
		this.scale = scale/Math.max(Common.getScreenProportionW(),Common.getScreenProportionH())*Math.max(Common.getScreenW(),Common.getScreenH());
		rad = this.scale * 0.5f;
		pos = generatPoint;
		disappearanceDistance = (float)Vector2D.Length(vector2DDomain,
				Vector2D.Sub(vector2DDomain,
				generatPoint,targetPoint));
		Vector2D directionVector;
		directionVector = Vector2D.Normalize(vector2DDomain,
				Vector2D.Sub(vector2DDomain,
				targetPoint,generatPoint));	//�����x�N�g��������
		Vector2D.Scale(vec,directionVector,initialVelocity);				//�����x�Ŋ|���Z���A�ړ��x�N�g�������߂�
		isExistence = true;
	}
	
	/*�X�V����*/
	public void update(){
		super.update();
		rad = this.scale * 0.5f;
		if(getDisappearanceDistance() <= 0){
			isExistence = false;
		}
		disappearanceDistance = (float)(getDisappearanceDistance() - Vector2D.Length(vector2DDomain,vec));
		Vector2D.Add(pos,pos,vec);	//���݂̕\���ʒu�ƈړ��x�N�g���𑫂��A�V�����\���ʒu���Z�o
	}
	public void finalize(){
		colorData = null;
	}
}