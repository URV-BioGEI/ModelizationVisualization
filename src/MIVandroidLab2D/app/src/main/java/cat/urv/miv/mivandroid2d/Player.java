package cat.urv.miv.mivandroid2d;

import android.content.Context;
import android.os.Debug;

import javax.microedition.khronos.opengles.GL10;

import static java.lang.System.currentTimeMillis;

public class Player {
    private CharacterManager characterManager;
    private GL10 gl;
    private Context context;
    private MusicPlayer musicPlayer;

    private Goomba goomba;
    private Block block;

    private final float POSITION_Y_GROUND = -1.5f;
    private final float POSITION_X = -5.0f, INITIAL_JUMP_SPEED = 22f, GRAVITY = -43.05f, MAX_Y = INITIAL_JUMP_SPEED * INITIAL_JUMP_SPEED / (2 * GRAVITY);

    private boolean
            isJumping = false, jumpStarted = false,
            isHitted = false,
            soundkickPlayed=false;

    private int framesJumping = 0, framesLanding = 0;
    private float positionY = POSITION_Y_GROUND, jumpSpeed = INITIAL_JUMP_SPEED;
    private long lastFrameTime;

    public Player(GL10 gl, Context context, MusicPlayer musicPlayer) {
        this.gl = gl;
        this.context = context;
        this.musicPlayer = musicPlayer;


        characterManager = new CharacterManager(gl, context, R.drawable.mario, R.raw.mario);
        characterManager.setAnimation("walk");

    }

    public void draw(long time){

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
            if (positionY < POSITION_Y_GROUND)  // Si detectem final de la parábola
            {
                positionY = POSITION_Y_GROUND;  // Corregim trajectoria
                isJumping = false;  // I deixem de saltar
                jumpStarted = false;  // Ressetegem salt
                jumpSpeed = INITIAL_JUMP_SPEED;  // I ressetetem salt inicial
            }
            else if (positionY > POSITION_Y_GROUND)
            {
                if (goomba.getPosition() <= POSITION_X + 0.5 && goomba.getPosition() >= POSITION_X - 1)  // Colisió en l'eix X
                {
                    if (positionY <= POSITION_Y_GROUND + 1.5f && !goomba.getIsDead())  // Si colisionem de costat amb el goomba mentre saltem
                    {
                        if (!isHitted)
                        {
                            musicPlayer.PlaySound(context, R.raw.mario_hurt);
                            isHitted = true;
                        }
                    }
                    else if (positionY <= POSITION_Y_GROUND + 2f)
                    {
                        goomba.setIsDead(true);
                        if (!soundkickPlayed)
                        {
                            musicPlayer.PlaySound(context, R.raw.kick);
                            soundkickPlayed = true;
                        }

                        //playerJump(20);
                    }
                }
                else if (goomba.getPosition() <= -15)
                {
                    soundkickPlayed = false;
                }
                else
                {
                    isHitted = false;
                }
            }
        }
        else  // If is not jumping is walking
        {
            characterManager.setAnimation("walk");
        }
        lastFrameTime = time;  // Update frame time
    /*public void goombaGameLogic(){

        if (goomba != null)
        {
            goomba.drawEnemy(currentTimeMillis());
            if (goomba.getPosition() <= positionX + 0.5 && goomba.getPosition() >= positionX - 1)
            {
                if (actualPositionY >= GROUND && actualPositionY <= GROUND + 20 * 0.1f && !jumpTop && !goomba.getIsDead())
                {

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
    }*/
        gl.glPushMatrix();

        gl.glTranslatef(POSITION_X, positionY, -30.0f);
        gl.glScalef(1.0f,1.5f, 1.0f);
        gl.glRotatef(180, 0,1,0);
        characterManager.draw();
        characterManager.update(time);

        gl.glPopMatrix();
    }

    // Set by a trigger on MainActivity
    public void isJumping(){
        this.isJumping = true;
    }

    public void setGoomba(Goomba goomba) {
        this.goomba = goomba;
    }

    public void setBlock(Block block) {
        this.block = block;
    }

}
