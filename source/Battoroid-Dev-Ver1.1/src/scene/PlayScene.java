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
	
	GestureArea area1;			// タッチエリア1(射撃)
	GestureArea area2;			// タッチエリア2(ジャンプ、弾切り替え)
	Player player;				// プレイヤー
	Enemy[] enemy;				// 敵
	
	Line[] lineData;			// ステージのライン(障害物、床等全て)

	TriggerEffect hitEffect;	// ヒット時エフェクト
	TriggerEffect deadEffect;	// 死亡時エフェクト
	
	Bullet collisionBullet;		// 弾との衝突判定計算用
	ColorData lineColor;		// 線の色
	
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
		
		// ステージデータのロード
		lineData = InitPlaySceneManager.getInitPlaySceneFunction().initLine();
		InitPlaySceneManager.getInitPlaySceneFunction().initSlideLine();
		InitPlaySceneManager.getInitPlaySceneFunction().initRefrectLine();
		player = InitPlaySceneManager.getInitPlaySceneFunction().initPlayer();
		enemy = InitPlaySceneManager.getInitPlaySceneFunction().initEnemy();
		
		player.setHomingObject(enemy);		// プレイヤーの敵を設定
		for(int i=0;i<enemy.length;i++){
			enemy[i].setMyEnemy(player);	// 敵の敵(つまりプレイヤー)を敵に認識させる
		}
		initGestureArea();					// タッチ動作の初期化
		initHitEffect();					// ヒットエフェクトの初期化
		initDeadEffect();					// 死亡時エフェクトの初期化
		
		lineColor = ColorData.getColor(ColorList.WHITE);
		
		// フェードイン
		if(!Fade.getIsPlayFade()){
			Fade.FadeInStart(90,ColorData.getColor(ColorList.WHITE),true);
		}
	}

	@Override
	public void update() {
		// 各オブジェクトのアップデート
		player.update();
		for(int i=0;i<enemy.length;i++){
			enemy[i].update();
			collisionEnemy(player,enemy[i]);
			collisionPlayer(player,enemy[i]);
		}
		
//		if(player.getHitPoint() < changeBGM_HP){
//			symphony.setKey("BGM2");
//		}
		
		// 敵が全滅していたら
		if(Object2D.isAnnihilation(enemy)){
			// リザルトデータの設定
			ResultManager.setResultList(ResultList.WIN);
			// フェードアウト
			if(!Fade.getIsPlayFade()){
				if(isFadeOuted){
					SceneManager.changeScene(SceneList.RESULT);
				}
				Fade.FadeOutStart(90,ColorData.getColor(ColorList.WHITE),false);
				isFadeOuted = true;
			}
		}
		
		// プレイヤーが死んでいたら
		else if(!player.getIsExistence()){
			// リザルトデータの設定
			ResultManager.setResultList(ResultList.LOSE);
			// フェードアウト
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
		// 各オブジェクトの表示
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
		// スレッドを使用しているクラスのスレッド破棄
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
		// タッチ動作をジェスチャークラスに任せる
		area1.touchEvent();
		area2.touchEvent();
		switch(event.getAction()){
		
		case MotionEvent.ACTION_DOWN:
			// 画面がタッチされたときの動作
			break;
		}
	}
	
	private void collisionEnemy(Player player,Enemy enemy){
		// 敵が存在する場合
		if(enemy.getIsExistence()){
			// プレイヤーの弾種ごとに
			int bulletTypeNum = player.getBulletGenerator().length;
			for(int i=0;i<bulletTypeNum;i++){
				// 弾ごとに
				int bulletNum = player.getBulletGenerator(i).getBulletLimit();
				for(int j=0;j<bulletNum;j++){
					// 弾が存在するなら
					collisionBullet = player.getBulletGenerator(i).getBullet(j);
					if(collisionBullet.getIsExistence()){
						// 当たり判定
						if(Collision.circleToCircle(
								collisionBullet.getPos(),
								collisionBullet.getRad(),
								enemy.getPos(),
								enemy.getRad())){
							/*当たっているなら*/
							SEManager.Play("Ban");
							// 血噴射エフェクトの発射方向設定
							hitEffect.getNextUseHitEffect().setGeneratAngle(
								270+(int)Vector2D.Angle(vector2DDomain,
										Vector2D.Sub(vector2DDomain,
										collisionBullet.getPos(),
										enemy.getPos())),
									hitEffect.getNextUseHitEffect().getGeneratAngleMargin());
							// エフェクトの発生
							hitEffect.EffectTriggerOn(calcEffectPos(collisionBullet.getPos(),enemy.getPos()));
							// 敵のHPを減らす
							enemy.setHitPoint(enemy.getHitPoint()-1);
							if(enemy.getHitPoint()<=0){
								/*死んだなら*/
								// エフェクトの合成方式を設定
								deadEffect.getNextUseHitEffect().setBlendMode(BlendMode.SUBTRACTION);
								// エフェクトの発生
								deadEffect.EffectTriggerOn(enemy.getPos());
							}
							// 弾を消失させる
							collisionBullet.setIsExistence(false);
						}
					}
				}
			}
		}
	}
	
	private void collisionPlayer(Player player,Enemy enemy){
		// プレイヤーが存在する場合
		if(player.getIsExistence()){
			// 敵の弾種ごとに
			int bulletTypeNum = enemy.getBulletGenerator().length;
			for(int i=0;i<bulletTypeNum;i++){
				// 敵の弾ごとに
				int bulletNum = enemy.getBulletGenerator(i).getBulletLimit();
				for(int j=0;j<bulletNum;j++){
					// 弾が存在するなら
					collisionBullet = enemy.getBulletGenerator(i).getBullet(j);
					if(collisionBullet.getIsExistence()){
						// 当たり判定
						if(Collision.circleToCircle(collisionBullet.getPos(),
								collisionBullet.getRad(),
								player.getPos(),
								player.getRad())){
							/*当たっているなら*/
							SEManager.Play("Ban");
							// 血噴射エフェクトの発射方向設定
							hitEffect.getNextUseHitEffect().setGeneratAngle(
								270+(int)Vector2D.Angle(vector2DDomain,
										Vector2D.Sub(vector2DDomain,
										collisionBullet.getPos(),
										player.getPos())),
								hitEffect.getNextUseHitEffect().getGeneratAngleMargin());
							// エフェクトの発生
							hitEffect.EffectTriggerOn(calcEffectPos(collisionBullet.getPos(),player.getPos()));
							// プレイヤーのHPを減らす
							player.setHitPoint(player.getHitPoint()-1);
							if(player.getHitPoint()<=0){
								/*死んだなら*/
								// エフェクトの合成方式設定
								deadEffect.getNextUseHitEffect().setBlendMode(BlendMode.ADDITION);
								// エフェクトの発生
								deadEffect.EffectTriggerOn(player.getPos());
							}
							// 弾を消失させる
							collisionBullet.setIsExistence(false);
						}
					}
				}
			}
		}
	}
	
	// エフェクトの発生位置を算出(弾とオブジェクトの中点)
	public Vector2D calcEffectPos(Vector2D bulletPos,Vector2D objectPos){
		return new Vector2D((bulletPos.x+objectPos.x)*0.5f,(bulletPos.y+objectPos.y)*0.5f);
	}
	
	private void initGestureArea(){
		// エリア1がタッチされた時の処理
		area1 = new GestureArea(new Vector2D(Common.getScreenW()/2,0),new Vector2D(Common.getScreenW(),Common.getScreenH()),1,
			(GestureFunction)new AbstractGestureFunction(){
			@Override
			public void gesture(int index,MotionEvent event) {
				if(!Double.isNaN(ProportionMotionEvent.getX(index)) && !Double.isNaN(ProportionMotionEvent.getY(index)))
					switch(event.getActionMasked()){
					case MotionEvent.ACTION_DOWN:
					case MotionEvent.ACTION_MOVE:
						// タッチ位置が弾アイコンだった場合、弾切り替え
						int selectNum = player.getSelectCursor().getSelectObject().length;
						for(int i=0;i<selectNum;i++){
							if(Collision.circleToCircle(new Vector2D(ProportionMotionEvent.getX(index),ProportionMotionEvent.getY(index)),2,
									player.getSelectCursor().getSelectObject(i).getPos(),player.getSelectCursor().getSelectObject(i).getRad())){
								player.getSelectCursor().setNowCursor(i);
								return;
							}
						}
						// タッチ位置を標準点とし、弾の発射
						player.getBulletGenerator(player.getSelectCursor().getNowCursor()).setAimingPoint(new Vector2D(ProportionMotionEvent.getX(index),ProportionMotionEvent.getY(index)));
						player.getBulletGenerator(player.getSelectCursor().getNowCursor()).setIsDischarge(true);
						break;
				}
			}

			@Override
			public void touchCancel(int index,MotionEvent event) {
				
			}
		});
		
		// エリア2がタッチされた時の処理
		area2 = new GestureArea(new Vector2D(),new Vector2D(Common.getScreenW()/2,Common.getScreenH()),1,
			(GestureFunction)new AbstractGestureFunction(){
			@Override
			public void gesture(int index,MotionEvent event) {
				if(!Double.isNaN(ProportionMotionEvent.getX(index)) && !Double.isNaN(ProportionMotionEvent.getY(index)))
					switch(event.getActionMasked()){
					case MotionEvent.ACTION_DOWN:
					case MotionEvent.ACTION_MOVE:
						// それ以外の部分をタッチしたらジャンプ
						player.setIsPlayJump(true);
					}
			}

			@Override
			public void touchCancel(int index,MotionEvent event) {

			}
		});
	}
	
	private void initHitEffect(){
		// 血噴射エフェクト
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
		// 炎発生エフェクト
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
