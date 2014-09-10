package scene;

import collision.Collision;
import bullet.Bullet;
import bullet.BulletFactory;
import line.Line;
import object2D.Object2D;
import openGLES20.Common;
import particle.GeneratMode;
import particle.ParticleFunctionFactory;
import particle.ParticleManager;
import particle.ParticleSystem;
import particle.TriggerEffect;
import scene.ResultManager.ResultList;
import sound.BGMManager;
import sound.SEManager;
import sound.Sound;
import stage.InitPlaySceneManager;
import symphony.Phrase;
import symphony.Symphony;
import com.drjiro.game.battoroid.s122240.game.R;
import function.ColorData;
import function.Graphics;
import function.TextureManager;
import function.Vector2D;
import function.Vector2DDomain;
import function.ColorData.ColorList;
import function.Graphics.BlendMode;
import gameObject.Enemy;
import gameObject.Player;
import gesture.AbstractGestureFunction;
import gesture.GestureArea;
import gesture.GestureFunction;
import gesture.ProportionMotionEvent;
import android.view.MotionEvent;

public class PlayScene implements Scene {
	boolean isFadeOuted = false;
	private Vector2DDomain vector2DDomain;
	
	GestureArea area1;			// �^�b�`�G���A1(�ˌ�)
	GestureArea area2;			// �^�b�`�G���A2(�W�����v�A�e�؂�ւ�)
	Player player;				// �v���C���[
	Enemy[] enemy;				// �G
	
	Line[] lineData;			// �X�e�[�W�̃��C��(��Q���A�����S��)

	TriggerEffect hitEffect;	// �q�b�g���G�t�F�N�g
	TriggerEffect deadEffect;	// ���S���G�t�F�N�g
	
	Bullet collisionBullet;		// �e�Ƃ̏Փ˔���v�Z�p
	ColorData lineColor;		// ���̐F
	
//	Symphony symphony;
	
	static final int changeBGM_HP = 5;
	
	public PlayScene() {
		
	}

	@Override
	public void intialize() {
		
//		symphony = new Symphony();
//		symphony.setIndex(new int[]{0,1,2,3,4,0,4,1});
//		symphony.setKey("BGM1");
//		
//		Phrase C1 = new Phrase();
//		C1.add("BGM1",new Sound(R.raw.c1));
//		C1.add("BGM2",new Sound(R.raw.c2));
//		Phrase Am1 = new Phrase();
//		Am1.add("BGM1",new Sound(R.raw.am1));
//		Am1.add("BGM2",new Sound(R.raw.am2));
//		Phrase Em1 = new Phrase();
//		Em1.add("BGM1",new Sound(R.raw.em1));
//		Em1.add("BGM2",new Sound(R.raw.em2));
//		Phrase F1 = new Phrase();
//		F1.add("BGM1",new Sound(R.raw.f1));
//		F1.add("BGM2",new Sound(R.raw.f2));
//		Phrase G1 = new Phrase();
//		G1.add("BGM1",new Sound(R.raw.g1));
//		G1.add("BGM2",new Sound(R.raw.g2));
//		
//		symphony.add(C1);
//		symphony.add(G1);
//		symphony.add(Am1);
//		symphony.add(Em1);
//		symphony.add(F1);
//		
//		symphony.start();
		
		BGMManager.soundStart("BGM2");
		
		vector2DDomain = new Vector2DDomain();
		
		BulletFactory.initHitEffect();
		
		// �X�e�[�W�f�[�^�̃��[�h
		lineData = InitPlaySceneManager.getInitPlaySceneFunction().initLine();
		InitPlaySceneManager.getInitPlaySceneFunction().initSlideLine();
		InitPlaySceneManager.getInitPlaySceneFunction().initRefrectLine();
		player = InitPlaySceneManager.getInitPlaySceneFunction().initPlayer();
		enemy = InitPlaySceneManager.getInitPlaySceneFunction().initEnemy();
		
		player.setHomingObject(enemy);		// �v���C���[�̓G��ݒ�
		for(int i=0;i<enemy.length;i++){
			enemy[i].setMyEnemy(player);	// �G�̓G(�܂�v���C���[)��G�ɔF��������
		}
		initGestureArea();					// �^�b�`����̏�����
		initHitEffect();					// �q�b�g�G�t�F�N�g�̏�����
		initDeadEffect();					// ���S���G�t�F�N�g�̏�����
		
		lineColor = ColorData.getColor(ColorList.WHITE);
		
		// �t�F�[�h�C��
		if(!Fade.getIsPlayFade()){
			Fade.FadeInStart(90,ColorData.getColor(ColorList.WHITE),true);
		}
	}

	@Override
	public void update() {
		// �e�I�u�W�F�N�g�̃A�b�v�f�[�g
		player.update();
		for(int i=0;i<enemy.length;i++){
			enemy[i].update();
			collisionEnemy(player,enemy[i]);
			collisionPlayer(player,enemy[i]);
		}
		
//		if(player.getHitPoint() < changeBGM_HP){
//			symphony.setKey("BGM2");
//		}
		
		// �G���S�ł��Ă�����
		if(Object2D.isAnnihilation(enemy)){
			// ���U���g�f�[�^�̐ݒ�
			ResultManager.setResultList(ResultList.WIN);
			// �t�F�[�h�A�E�g
			if(!Fade.getIsPlayFade()){
				if(isFadeOuted){
					SceneManager.changeScene(SceneList.RESULT);
				}
				Fade.FadeOutStart(90,ColorData.getColor(ColorList.WHITE),false);
				isFadeOuted = true;
			}
		}
		
		// �v���C���[������ł�����
		else if(!player.getIsExistence()){
			// ���U���g�f�[�^�̐ݒ�
			ResultManager.setResultList(ResultList.LOSE);
			// �t�F�[�h�A�E�g
			if(!Fade.getIsPlayFade()){
				if(isFadeOuted){
					SceneManager.changeScene(SceneList.RESULT);
				}
				Fade.FadeOutStart(90,ColorData.getColor(ColorList.RED),false);
				isFadeOuted = true;
			}
		}
	}

	@Override
	public void render() {
		Graphics.drawTexture(TextureManager.getTexture("BackGround"),BlendMode.ALPHA);
		// �e�I�u�W�F�N�g�̕\��
		for(int i=0;i<lineData.length;i++){
			lineData[i].drawLine(10,lineColor);
		}
		player.render();
		for(int i=0;i<enemy.length;i++){
			enemy[i].render();
		}
		player.UIRender();
		ParticleManager.render();
	}

	@Override
	public void finalize() {
		// �X���b�h���g�p���Ă���N���X�̃X���b�h�j��
		player.finalize();
		hitEffect.finalize();
		deadEffect.finalize();
		BulletFactory.finalizeEffect();
		
		isFadeOuted = false;
		vector2DDomain = null;
		
		area1 = null;
		area2 = null;
		player = null;
		enemy = null;
		
		lineData = null;

		hitEffect = null;
		deadEffect = null;
		
		collisionBullet = null;
		lineColor = null;
		
//		symphony.stopSound();
//		symphony.stopLoop();
//		symphony = null;
		
		BGMManager.soundStop("BGM2");
	}

	@Override
	public void touchEvent(MotionEvent event) {
		// �^�b�`������W�F�X�`���[�N���X�ɔC����
		area1.touchEvent();
		area2.touchEvent();
		switch(event.getAction()){
		
		case MotionEvent.ACTION_DOWN:
			// ��ʂ��^�b�`���ꂽ�Ƃ��̓���
			break;
		}
	}
	
	private void collisionEnemy(Player player,Enemy enemy){
		// �G�����݂���ꍇ
		if(enemy.getIsExistence()){
			// �v���C���[�̒e�킲�Ƃ�
			int bulletTypeNum = player.getBulletGenerator().length;
			for(int i=0;i<bulletTypeNum;i++){
				// �e���Ƃ�
				int bulletNum = player.getBulletGenerator(i).getBulletLimit();
				for(int j=0;j<bulletNum;j++){
					// �e�����݂���Ȃ�
					collisionBullet = player.getBulletGenerator(i).getBullet(j);
					if(collisionBullet.getIsExistence()){
						// �����蔻��
						if(Collision.circleToCircle(
								collisionBullet.getPos(),
								collisionBullet.getRad(),
								enemy.getPos(),
								enemy.getRad())){
							/*�������Ă���Ȃ�*/
							SEManager.Play("Ban");
							// �����˃G�t�F�N�g�̔��˕����ݒ�
							hitEffect.getNextUseHitEffect().setGeneratAngle(
								270+(int)Vector2D.Angle(vector2DDomain,
										Vector2D.Sub(vector2DDomain,
										collisionBullet.getPos(),
										enemy.getPos())),
									hitEffect.getNextUseHitEffect().getGeneratAngleMargin());
							// �G�t�F�N�g�̔���
							hitEffect.EffectTriggerOn(calcEffectPos(collisionBullet.getPos(),enemy.getPos()));
							// �G��HP�����炷
							enemy.setHitPoint(enemy.getHitPoint()-1);
							if(enemy.getHitPoint()<=0){
								/*���񂾂Ȃ�*/
								// �G�t�F�N�g�̍���������ݒ�
								deadEffect.getNextUseHitEffect().setBlendMode(BlendMode.SUBTRACTION);
								// �G�t�F�N�g�̔���
								deadEffect.EffectTriggerOn(enemy.getPos());
							}
							// �e������������
							collisionBullet.setIsExistence(false);
						}
					}
				}
			}
		}
	}
	
	private void collisionPlayer(Player player,Enemy enemy){
		// �v���C���[�����݂���ꍇ
		if(player.getIsExistence()){
			// �G�̒e�킲�Ƃ�
			int bulletTypeNum = enemy.getBulletGenerator().length;
			for(int i=0;i<bulletTypeNum;i++){
				// �G�̒e���Ƃ�
				int bulletNum = enemy.getBulletGenerator(i).getBulletLimit();
				for(int j=0;j<bulletNum;j++){
					// �e�����݂���Ȃ�
					collisionBullet = enemy.getBulletGenerator(i).getBullet(j);
					if(collisionBullet.getIsExistence()){
						// �����蔻��
						if(Collision.circleToCircle(collisionBullet.getPos(),
								collisionBullet.getRad(),
								player.getPos(),
								player.getRad())){
							/*�������Ă���Ȃ�*/
							SEManager.Play("Ban");
							// �����˃G�t�F�N�g�̔��˕����ݒ�
							hitEffect.getNextUseHitEffect().setGeneratAngle(
								270+(int)Vector2D.Angle(vector2DDomain,
										Vector2D.Sub(vector2DDomain,
										collisionBullet.getPos(),
										player.getPos())),
								hitEffect.getNextUseHitEffect().getGeneratAngleMargin());
							// �G�t�F�N�g�̔���
							hitEffect.EffectTriggerOn(calcEffectPos(collisionBullet.getPos(),player.getPos()));
							// �v���C���[��HP�����炷
							player.setHitPoint(player.getHitPoint()-1);
							if(player.getHitPoint()<=0){
								/*���񂾂Ȃ�*/
								// �G�t�F�N�g�̍��������ݒ�
								deadEffect.getNextUseHitEffect().setBlendMode(BlendMode.ADDITION);
								// �G�t�F�N�g�̔���
								deadEffect.EffectTriggerOn(player.getPos());
							}
							// �e������������
							collisionBullet.setIsExistence(false);
						}
					}
				}
			}
		}
	}
	
	// �G�t�F�N�g�̔����ʒu���Z�o(�e�ƃI�u�W�F�N�g�̒��_)
	public Vector2D calcEffectPos(Vector2D bulletPos,Vector2D objectPos){
		return new Vector2D((bulletPos.x+objectPos.x)*0.5f,(bulletPos.y+objectPos.y)*0.5f);
	}
	
	private void initGestureArea(){
		// �G���A1���^�b�`���ꂽ���̏���
		area1 = new GestureArea(new Vector2D(Common.getScreenW()/2,0),new Vector2D(Common.getScreenW(),Common.getScreenH()),1,
			(GestureFunction)new AbstractGestureFunction(){
			@Override
			public void gesture(int index,MotionEvent event) {
				if(!Double.isNaN(ProportionMotionEvent.getX(index)) && !Double.isNaN(ProportionMotionEvent.getY(index)))
					switch(event.getActionMasked()){
					case MotionEvent.ACTION_DOWN:
					case MotionEvent.ACTION_MOVE:
						// �^�b�`�ʒu���e�A�C�R���������ꍇ�A�e�؂�ւ�
						int selectNum = player.getSelectCursor().getSelectObject().length;
						for(int i=0;i<selectNum;i++){
							if(Collision.circleToCircle(new Vector2D(ProportionMotionEvent.getX(index),ProportionMotionEvent.getY(index)),2,
									player.getSelectCursor().getSelectObject(i).getPos(),player.getSelectCursor().getSelectObject(i).getRad())){
								player.getSelectCursor().setNowCursor(i);
								return;
							}
						}
						// �^�b�`�ʒu��W���_�Ƃ��A�e�̔���
						player.getBulletGenerator(player.getSelectCursor().getNowCursor()).setAimingPoint(new Vector2D(ProportionMotionEvent.getX(index),ProportionMotionEvent.getY(index)));
						player.getBulletGenerator(player.getSelectCursor().getNowCursor()).setIsDischarge(true);
						break;
				}
			}

			@Override
			public void touchCancel(int index,MotionEvent event) {
				
			}
		});
		
		// �G���A2���^�b�`���ꂽ���̏���
		area2 = new GestureArea(new Vector2D(),new Vector2D(Common.getScreenW()/2,Common.getScreenH()),1,
			(GestureFunction)new AbstractGestureFunction(){
			@Override
			public void gesture(int index,MotionEvent event) {
				if(!Double.isNaN(ProportionMotionEvent.getX(index)) && !Double.isNaN(ProportionMotionEvent.getY(index)))
					switch(event.getActionMasked()){
					case MotionEvent.ACTION_DOWN:
					case MotionEvent.ACTION_MOVE:
						// ����ȊO�̕������^�b�`������W�����v
						player.setIsPlayJump(true);
					}
			}

			@Override
			public void touchCancel(int index,MotionEvent event) {

			}
		});
	}
	
	private void initHitEffect(){
		// �����˃G�t�F�N�g
		ParticleSystem particleSystem = new ParticleSystem();
		particleSystem.initialize("Light",
			ParticleFunctionFactory.makeAlphaAddition(-0.008f).compose(
			ParticleFunctionFactory.makeDesignationAlphaIsNotExistence(0.00f).compose(
			ParticleFunctionFactory.makeVectorAddition(vector2DDomain,new Vector2D(0,0.5f)))));
		
		particleSystem.setDefault();
		
		particleSystem.setAngleMargin(20);
		particleSystem.setBlendMode(BlendMode.SUBTRACTION);
		particleSystem.setDisappearanceDistance(400,40);
		particleSystem.setGeneratAngle(0,20);
		particleSystem.setGeneratInterval(0,0);
		particleSystem.setGeneratMode(GeneratMode.SAME_ANGLE);
		particleSystem.setGeneratNum(12,4);
		particleSystem.setGeneratPoint(new Vector2D(),new Vector2D(10,10));
		particleSystem.setInitialVecocity(6,2);
		particleSystem.setIsDischarge(false);
		particleSystem.setParticleScale(50,20);
		particleSystem.setA(0.2f,0.1f);
		particleSystem.setR(0.0f,0.5f);
		particleSystem.setG(1.0f,0.0f);
		particleSystem.setB(1.0f,0.0f);
		
		hitEffect = new TriggerEffect(5,5,particleSystem);
	}
	
	private void initDeadEffect(){
		// �������G�t�F�N�g
		ParticleSystem particleSystem = new ParticleSystem();
		particleSystem.initialize("Light",
			ParticleFunctionFactory.makeAlphaMultiplication(0.9f).compose(
			ParticleFunctionFactory.makeDesignationAlphaIsNotExistence(0.1f).compose(
			ParticleFunctionFactory.makeVectorAddition(vector2DDomain,new Vector2D(0,-3.0f)))));
		
		particleSystem.setDefault();
		
		particleSystem.setAngleMargin(20);
		particleSystem.setBlendMode(BlendMode.SUBTRACTION);
		particleSystem.setDisappearanceDistance(400,40);
		particleSystem.setGeneratAngle(0,20);
		particleSystem.setGeneratInterval(0,3);
		particleSystem.setGeneratMode(GeneratMode.EMISSION);
		particleSystem.setGeneratNum(10,4);
		particleSystem.setGeneratPoint(new Vector2D(),new Vector2D(70,70));
		particleSystem.setInitialVecocity(5,3);
		particleSystem.setIsDischarge(false);
		particleSystem.setParticleScale(160,60);
		particleSystem.setA(0.2f,0.2f);
		particleSystem.setR(1.0f,1.0f);
		particleSystem.setG(0.5f,0.3f);
		particleSystem.setB(0.2f,0.2f);
		
		deadEffect = new TriggerEffect(2,20,particleSystem);
	}
}
