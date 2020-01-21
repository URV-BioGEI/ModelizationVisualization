package cat.urv.miv.mivandroid2d;

import android.content.Context;

import javax.microedition.khronos.opengles.GL10;

public class Player {
    private CharacterManager characterManager;
    private GL10 gl;
    private Context context;
    private MusicPlayer musicPlayer;

    private Goomba goomba;
    private Block block;
    private Lifebar lifebar;
    private Coin coinFloor, coinAir;
    private LevelHUD levelHUD;

    private final float POSITION_Y_GROUND = -1.5f;
    private final float POSITION_X = -5.0f, INITIAL_JUMP_SPEED = 22f, GRAVITY = -43.05f, MAX_Y = INITIAL_JUMP_SPEED * INITIAL_JUMP_SPEED / (2 * GRAVITY);

    private boolean
            isJumping = false,

            isTouching,

            jumpStarted = false,
            isHitted = false,
            soundkickPlayed = false;
    private int frameCounter = 0;

    private float positionY = POSITION_Y_GROUND, jumpSpeed = INITIAL_JUMP_SPEED;
    private long lastFrameTime;

    public Player(GL10 gl, Context context, MusicPlayer musicPlayer) {
        this.gl = gl;
        this.context = context;
        this.musicPlayer = musicPlayer;


        characterManager = new CharacterManager(gl, context, R.drawable.mario, R.raw.mario);
        characterManager.setAnimation("walk");
        characterManager.setSpeed("walk", 15);

    }

    public void draw(long time){

        if (isTouching && !isJumping) isJumping = true;
        if (isJumping)  // Si s'activa isJumping per touchEvent
        {
            /*
             * Symbols:
             * Y0: Initial Position
             * Y: Final position
             * v0: Initial Speed
             * v: Final speed
             * t0: Initial time
             * t: Final time
             * g: Gravity constant
             *
             * MRUA Free-fall equations:
             * Position:
             * Y = Y0 + v0(t - t0) + 0.5g * (t - t0) ^ 2
             * Speed:
             * v = v0 + g (t - t0)
             *
             */

            jumpSpeed = jumpSpeed + (float)(time - lastFrameTime) / 1000 * GRAVITY;
            positionY += jumpSpeed * (float) (time - lastFrameTime) / 1000 + 0.5f * (GRAVITY) * ((float) (time - lastFrameTime) / 1000) * ((float) (time - lastFrameTime) / 1000);
            if (!jumpStarted)  // Només s'executa el primer cop que saltem
            {
                characterManager.setAnimation("jump");  // Llavors posem al personatge a saltar
                musicPlayer.PlaySound(context, R.raw.nsmb_jump);  // I fem el so
                jumpStarted = true;
            }
            if (positionY <= POSITION_Y_GROUND)  // Si detectem final de la parábola
            {
                positionY = POSITION_Y_GROUND;  // Corregim trajectoria
                isJumping = false;  // I deixem de saltar
                jumpStarted = false;  // Ressetegem salt
                jumpSpeed = INITIAL_JUMP_SPEED;  // I ressetetem salt inicial
            }
            else if (positionY > POSITION_Y_GROUND)  // Si estem a l'aire
            {
                if (goomba.getPosition() -0.5f <= POSITION_X && goomba.getPosition() + 2.5f >= POSITION_X)  // Colisió en l'eix X
                {
                    if (positionY <= POSITION_Y_GROUND + 1.5f && !goomba.getIsDead())  // Si colisionem de costat amb el goomba mentre saltem
                    {
                        if (!isHitted)
                        {
                            lifebar.takeDamage(4);
                            musicPlayer.PlaySound(context, R.raw.mario_hurt);
                            isHitted = true;
                        }
                    }
                    else if (positionY <= POSITION_Y_GROUND + 2f)  // Si aixafem al goomba
                    {
                        goomba.setIsDead(true);
                        if (!soundkickPlayed)  // Play sound
                        {
                            musicPlayer.PlaySound(context, R.raw.kick);
                            soundkickPlayed = true;
                        }

                    }
                }
                if (block.getPosition() - 0.5f <= POSITION_X && block.getPosition() + 2.5f >= POSITION_X)  // Colisió en l'eix x amb un bloc
                {
                    if (positionY >= 3.5 - 2 && positionY < 3.5 - 1.5)
                    {
                        positionY = 1.5f;
                        jumpSpeed = 0;
                        block.setSmashed(true);
                        musicPlayer.PlaySound(context, R.raw.bounce);  // I fem el so
                        lifebar.takeDamage(-1);
                    }

                }
                if (coinAir.getPositionX() - 0.5 <= POSITION_X && coinAir.getPositionX() + 2.5f >= POSITION_X)  // Colisió en l'eix X
                {
                    if (coinAir.getPositionY() - 2 <= positionY && coinAir.getPositionY() >= positionY)
                    {
                        if (!coinAir.getIsCaught())
                        {
                            lifebar.takeDamage(-1);
                            coinAir.setCaught(true);
                            musicPlayer.PlaySound(context, R.raw.coin_sound);
                        }
                    }
                }
            }
        }
        else  // If is not jumping is walking
        {
            characterManager.setAnimation("walk");
            if (goomba.getPosition() <= POSITION_X + 2 && goomba.getPosition() >= POSITION_X)  // Colisió en l'eix X
            {
                if (!isHitted && !soundkickPlayed)
                {
                    musicPlayer.PlaySound(context, R.raw.mario_hurt);
                    isHitted = true;
                    lifebar.takeDamage(4);
                }
            }
            if (coinFloor.getPositionX() - 0.5 <= POSITION_X && coinFloor.getPositionX() + 2.5f >= POSITION_X)  // Colisió en l'eix X
            {
                if (!coinFloor.getIsCaught())
                {
                    System.out.println("ladelterra");

                    lifebar.takeDamage(-1);
                    coinFloor.setCaught(true);
                    musicPlayer.PlaySound(context, R.raw.coin_sound);
                }
            }
            characterManager.setAnimation("walk");
        }
        if (goomba.getPosition() > 15)  // Quan el goomba torna a estar fora de la pantalla recarreguem els sons i animacions
        {
            soundkickPlayed = false;
            isHitted = false;
        }
        if (lifebar.getLife() == 0)
        {
            musicPlayer.PlaySound(context, R.raw.mario_mamma_mia);
            levelHUD.loseLevel();
            frameCounter = 0;
            lifebar.takeDamage(-16);
        }

        frameCounter++;
        if (frameCounter > 1500)
        {
            levelHUD.addLevel();
            int level = levelHUD.getLevel();
            musicPlayer.PlaySound(context, R.raw.stageclear);
            frameCounter = 0;
            goomba.setDisplacement(0.15f + 0.025f * level);
            coinAir.setDisplacement(0.1f + 0.025f * level);
            coinFloor.setDisplacement(0.1f + 0.025f * level);
            block.setDisplacement(0.1f + 0.025f * level);

        }
        lastFrameTime = time;  // Update frame time

        gl.glPushMatrix();

        gl.glTranslatef(POSITION_X, positionY, -30.0f);
        gl.glScalef(1.0f,1.5f, 1.0f);
        gl.glRotatef(180, 0,1,0);
        characterManager.draw();
        characterManager.update(time);

        gl.glPopMatrix();
    }

    // Set by a trigger on MainActivity
    public void isTouching(boolean param){
        this.isTouching = param;
    }

    public void setGoomba(Goomba goomba) {
        this.goomba = goomba;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

    public void setLifebar(Lifebar lifebar) {
        this.lifebar = lifebar;
    }

    public void setCoinAir(Coin coin) {this.coinAir = coin;}
    public void setCoinFloor(Coin coin) {this.coinFloor = coin;}

    public void setLevelHUD(LevelHUD levelHUD) {
        this.levelHUD = levelHUD;
    }
}
