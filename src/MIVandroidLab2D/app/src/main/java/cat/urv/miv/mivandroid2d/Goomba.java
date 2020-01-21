package cat.urv.miv.mivandroid2d;

import android.content.Context;

import javax.microedition.khronos.opengles.GL10;

import static java.lang.System.currentTimeMillis;

public class Goomba {
    private CharacterManager characterManager;
    private GL10 gl;
    private Context context;
    private float position, displacement = 0.15f;
    private MusicPlayer musicPlayer;

    private boolean isDead = false;
    private boolean isHitted = false;
    private int currentDieFrames = 0;

    private final int DIE_FRAMES = 60;

    public Goomba(GL10 gl, Context context){
        this.gl = gl;
        this.context = context;
        position = 13;

        characterManager = new CharacterManager(gl, context, R.drawable.goomba, R.raw.goomba);
        characterManager.setAnimation("walk");
    }



    public void draw(long time){
        gl.glPushMatrix();

        // Goomba logic

        if (isDead)
        {
            currentDieFrames++;
            this.characterManager.setAnimation("die");
            gl.glScalef(1, 0.4f, 1);
            gl.glTranslatef(position, -7.5f, -35.0f);
        }
        else
        {
            gl.glTranslatef(position, -2.5f, -35.0f);
        }

        if (currentDieFrames > DIE_FRAMES || position < -20f)
        {
            position += 50f;
            characterManager.setAnimation("walk");
            isDead = false;
            currentDieFrames = 0;
        }
        characterManager.draw();
        characterManager.update(time);
        gl.glPopMatrix();
        position -= displacement;
    }

    public float getPosition() {
        return position;
    }

    public void setAnimation(String name){
        this.characterManager.setAnimation(name);
    }

    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }

    public boolean getIsDead() {
        return this.isDead;
    }

    public void setDisplacement(float displacement) {
        this.displacement = displacement;
    }
}
