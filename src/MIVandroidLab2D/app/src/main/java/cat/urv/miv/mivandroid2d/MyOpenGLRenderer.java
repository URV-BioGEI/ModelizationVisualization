package cat.urv.miv.mivandroid2d;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

import static java.lang.System.currentTimeMillis;

public class MyOpenGLRenderer implements Renderer {

	private Context context;
	private GL10 gl;

	private final float GROUND = -1.5f;

	private Enemy goomba = null, koopa = null;
	private CharacterManager mario, num1, num2, num3;
	private Block block = null;
	private TileMap tileMap1, tileMap2, tileMap3, tileMap4, tileMap5, tileMap1_2, tileMap2_2, tileMap3_2, tileMap4_2, tileMap5_2;
	private Coin coin = null;
	private MusicPlayer musicPlayer;
	private LevelHUD levelHUD;

	private boolean isJumping = false, jumpInit = false, jumpTop = false;
	private int framesJumping = 0, framesLanding = 0;
	private float actualPositionY = GROUND, positionX = -5.0f;
	private int numCoins = 0;
	private boolean isHitted = false, soundCoinPlayed=false, soundkickPlayed=false;

	public MyOpenGLRenderer(Context context){
		this.context = context;
	}

	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		this.gl = gl;
		gl.glEnable(GL10.GL_TEXTURE_2D); // Enable OpenGL textures

		// Image Background color
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);

		// Enable Blend textures and alpha channel
		gl.glEnable(gl.GL_BLEND);
		gl.glBlendFunc(gl.GL_SRC_ALPHA, gl.GL_ONE_MINUS_SRC_ALPHA);

		// Create Music Player
		musicPlayer = new MusicPlayer();
		// Play init sound
		musicPlayer.PlaySound(context, R.raw.mario_lets_go);

		try {
			// Create scrolling parallax tilemaps
			tileMap1 = new TileMap(gl, context, R.drawable.background_tiles, R.raw.tilemap1, 0.02f, -20);  // Top sky clouds
			tileMap1_2 = new TileMap(gl, context, R.drawable.background_tiles, R.raw.tilemap1, 0.02f, -20 + tileMap1.getTilemapColumns() * 2f);  // Top sky clouds
			tileMap2 = new TileMap(gl, context, R.drawable.background_tiles, R.raw.tilemap2, 0.03f, -20);  // Bottom sky clouds
			tileMap2_2 = new TileMap(gl, context, R.drawable.background_tiles, R.raw.tilemap2, 0.03f, -20 + tileMap2.getTilemapColumns() * 2f);  // Bottom sky clouds
			tileMap3 = new TileMap(gl, context, R.drawable.background_tiles, R.raw.tilemap3, 0.1f, -20);  // Back mountains
			tileMap3_2 = new TileMap(gl, context, R.drawable.background_tiles, R.raw.tilemap3, 0.1f, -20 + tileMap3.getTilemapColumns() * 2f);  // Back mountains
			tileMap4 = new TileMap(gl, context, R.drawable.background_tiles, R.raw.tilemap4, 0.2f, -20);  // front mountains
			tileMap4_2 = new TileMap(gl, context, R.drawable.background_tiles, R.raw.tilemap4, 0.2f, -20 + tileMap4.getTilemapColumns() * 2f);  // front mountains
			tileMap5 = new TileMap(gl, context, R.drawable.foreground_tiles, R.raw.tilemap5, 0.5f, -20);  // Foreground ground
			tileMap5_2 = new TileMap(gl, context, R.drawable.foreground_tiles, R.raw.tilemap5, 0.5f, -20 + tileMap5.getTilemapColumns() * 2f);  // Foreground ground

			// Create Mario
			mario = new CharacterManager(gl, context, R.drawable.mario, R.raw.mario);
			mario.setAnimation("walk");

			// Create Level HUD
			levelHUD = new LevelHUD(gl, context);

		}
		catch (Exception e){
			System.out.println(e.toString());
		}

	}

	@Override
	public void onDrawFrame(GL10 gl) {

		// Clears the screen and depth buffer.
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		gl.glLoadIdentity();
		gl.glPushMatrix();

		drawGround();
		drawPlayer();

		coinGameLogic();
		goombaGameLogic();
		koopaGameLogic();
		blockGameLogic();

		playerJump(40);

		//drawHUD();

	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// Define the Viewport
		gl.glViewport(0, 0, width, height);
		// Select the projection matrix
		gl.glMatrixMode(GL10.GL_PROJECTION);
		// Reset the projection matrix
		gl.glLoadIdentity();
		// Calculate the aspect ratio of the window
		GLU.gluPerspective(gl, 60.0f, (float) width / (float) height, 0.1f, 100.0f);
		// Select the modelview matrix
		gl.glMatrixMode(GL10.GL_MODELVIEW);
	}

	/*public void updateNumCoins() {
		int digit;

		if(numCoins<0) numCoins = 0;
		else if(numCoins>999) numCoins = 999;

		num1.setCurrentFrame(0);
		num2.setCurrentFrame(0);

		if(numCoins<10) num3.setCurrentFrame(numCoins);
		else if (numCoins<100) {
			num3.setCurrentFrame(numCoins%10);
			num2.setCurrentFrame(numCoins/10);
		}
		else{
			num3.setCurrentFrame(numCoins%10);
			digit = numCoins/10;
			num2.setCurrentFrame(digit%10);
			num1.setCurrentFrame(digit/10);
		}
	}*/

	private void drawGround(){
		// Tilemap1
		gl.glPushMatrix();
		gl.glTranslatef(0f, 10.0f, -20.0f);
		tileMap1.draw();
		gl.glPopMatrix();

		gl.glPushMatrix();
		gl.glTranslatef(0f, 10.0f, -20.0f);
		tileMap1_2.draw();
		gl.glPopMatrix();

		// Tilemap2
		gl.glPushMatrix();
		gl.glTranslatef(0f, 8.0f, -20.0f);
		tileMap2.draw();
		gl.glPopMatrix();

		gl.glPushMatrix();
		gl.glTranslatef(0f, 8.0f, -20.0f);
		tileMap2_2.draw();
		gl.glPopMatrix();

		// Tilemap3
		gl.glPushMatrix();
		gl.glTranslatef(0f, 4.0f, -20.0f);
		tileMap3.draw();
		gl.glPopMatrix();

		gl.glPushMatrix();
		gl.glTranslatef(0f, 4.0f, -20.0f);
		tileMap3_2.draw();
		gl.glPopMatrix();

		// Tilemap4
		gl.glPushMatrix();
		gl.glTranslatef(0f, 4.0f, -30.0f);
		tileMap4.draw();
		gl.glPopMatrix();

		gl.glPushMatrix();
		gl.glTranslatef(0f, 4.0f, -30.0f);
		tileMap4_2.draw();
		gl.glPopMatrix();

		// Tilemap5 (Foreground)
		gl.glPushMatrix();
		gl.glTranslatef(0.0f, -7.0f, -60.0f);
		tileMap5.draw();
		gl.glPopMatrix();

		gl.glPushMatrix();
		gl.glTranslatef(0.0f, -7.0f, -60.0f);
		tileMap5_2.draw();
		gl.glPopMatrix();
	}

	/*public void drawHUD(){
		// HUD
		gl.glPushMatrix();
		gl.glTranslatef(-7f, 18f, -35.0f);
		coinHUD.draw();
		coinHUD.update(currentTimeMillis());
		gl.glPopMatrix();

		gl.glPushMatrix();
		gl.glTranslatef(-5f, 18f, -35.0f);
		num1.draw();
		gl.glPopMatrix();

		gl.glPushMatrix();
		gl.glTranslatef(-3f, 18f, -35.0f);
		num2.draw();
		gl.glPopMatrix();

		gl.glPushMatrix();
		gl.glTranslatef(-1f, 18f, -35.0f);
		num3.draw();
		gl.glPopMatrix();
	}*/

	public void drawPlayer(){
		// PLAYER
		gl.glPushMatrix();
		gl.glTranslatef(positionX, actualPositionY, -30.0f);
		gl.glScalef(1.0f,1.5f, 1.0f);
		gl.glRotatef(180, 0,1,0);
		mario.draw();
		mario.update(currentTimeMillis());
		gl.glPopMatrix();
	}

	public void isJumping(){
		this.isJumping = true;
	}

	public void playerJump(int jumpForce){
		if(isJumping && !jumpInit) {
			if(jumpForce==40) musicPlayer.PlaySound(context, R.raw.nsmb_jump);
			//framesJumping = 0;
			jumpInit = true;
			mario.setAnimation("jump");
		}
		else if(jumpInit && !jumpTop ) {
			if(framesJumping < jumpForce){
				actualPositionY +=0.1f;
				framesJumping++;
			}
			else jumpTop = true;
		}
		else if(jumpInit && jumpTop) {
			if(framesLanding != framesJumping){
				actualPositionY -=0.1f;
				framesLanding++;
			}
			else {
				jumpTop = false;
				jumpInit = false;
				isJumping = false;
				framesJumping = 0;
				framesLanding = 0;
				mario.setAnimation("walk");
			}
		}
	}

	public void blockGameLogic()
	{
		if (block == null && Math.random() > 0.990)
		{
			block = new Block(gl, context, R.drawable.foreground_tiles, R.raw.block);
			block.getCharacterManager().getAnimations().get("idle").setSpeed(60);
		}

		if (block != null)
		{
			block.drawBlock(currentTimeMillis());
		}
	}

	public  void koopaGameLogic(){
		if (koopa == null && Math.random() > 0.993)
			koopa = new Enemy(gl, context, R.drawable.koopa, R.raw.koopa);
		if (koopa != null)
		{
			koopa.drawEnemy(currentTimeMillis());
			if (koopa.getPosition() <= positionX + 0.5 && koopa.getPosition() >= positionX - 1)
			{
				if (actualPositionY >= GROUND && actualPositionY <= GROUND + 20 * 0.1f && !jumpTop && !koopa.getIsDead())
				{
					if (!isHitted)
					{
						numCoins--;
						musicPlayer.PlaySound(context, R.raw.mario_hurt);
						isHitted = true;
					}
				}
				else if (actualPositionY >= GROUND + 15 * 0.1f && actualPositionY <= GROUND + 20 * 0.1f && jumpTop)
				{
					koopa.setAnimation("die");
					koopa.setIsDead(true);
					if (!soundkickPlayed)
					{
						musicPlayer.PlaySound(context, R.raw.kick);
						soundkickPlayed = true;
					}
					isJumping();
					jumpInit = false;
					jumpTop = false;
					playerJump(20);
				}
			}
			else if (koopa.getPosition() <= -15)
			{
				koopa = null;
				soundkickPlayed = false;
			}
			else
			{
				isHitted = false;
			}
		}
	}

	public void goombaGameLogic(){
		if (goomba == null && Math.random() > 0.999)
			goomba = new Enemy(gl, context, R.drawable.goomba, R.raw.goomba);
		if (goomba != null)
		{
			goomba.drawEnemy(currentTimeMillis());
			if (goomba.getPosition() <= positionX + 0.5 && goomba.getPosition() >= positionX - 1)
			{
				if (actualPositionY >= GROUND && actualPositionY <= GROUND + 20 * 0.1f && !jumpTop && !goomba.getIsDead())
				{
					if (!isHitted)
					{
						numCoins--;
						musicPlayer.PlaySound(context, R.raw.mario_hurt);
						isHitted = true;
					}
				}
				else if (actualPositionY >= GROUND + 15 * 0.1f && actualPositionY <= GROUND + 20 * 0.1f && jumpTop)
				{
					goomba.setAnimation("die");
					goomba.setIsDead(true);
					if (!soundkickPlayed)
					{
						musicPlayer.PlaySound(context, R.raw.kick);
						soundkickPlayed = true;
					}
					isJumping();
					jumpInit = false;
					jumpTop=false;
					playerJump(20);
				}
			}
			else if (goomba.getPosition() <= -15)
			{
				goomba = null;
				soundkickPlayed = false;
			}
			else
			{
				isHitted=false;
			}
		}
	}

	public  void coinGameLogic(){
		if (coin == null && Math.random() > 0.990)
		{
			coin = new Coin(gl, context);
		}
		if (coin != null)
		{
			coin.drawCoin(currentTimeMillis());
			if (coin.getPosition() <= positionX && coin.getPosition() >= positionX - 2)
			{
				if (actualPositionY >= GROUND && actualPositionY <= GROUND + 20 * 0.1f){
					if (!soundCoinPlayed)
					{
						musicPlayer.PlaySound(context, R.raw.coin_sound);
						soundCoinPlayed = true;
					}
					coin.isCaught();
				}
			}
			else if (coin.getPosition() <= -15)
			{
				coin = null;
				soundCoinPlayed = false;
			}

			if (coin != null && coin.getIsCaught() && coin.isDestroyable())
			{
				numCoins++;
				coin = null;
				soundCoinPlayed=false;
			}

		}
	}



}
