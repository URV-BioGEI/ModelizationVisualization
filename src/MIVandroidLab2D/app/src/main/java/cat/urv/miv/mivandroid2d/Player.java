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

    private final float POSITION_Y_GROUND = -1.5f;
    private final float POSITION_X = -5.0f, INITIAL_JUMP_SPEED = 22f, GRAVITY = -50.8f;

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
            jumpSpeed = jumpSpeed + (float)(time - lastFrameTime) / 1000 * GRAVITY;
            positionY += jumpSpeed * (float) (time - lastFrameTime) / 1000 + 0.5f * (GRAVITY) * ((float) (time - lastFrameTime) / 1000) * ((float) (time - lastFrameTime) / 1000);
            if (!jumpStarted)
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
        }
        else
        {
            characterManager.setAnimation("walk");
        }

        lastFrameTime = time;

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


}
