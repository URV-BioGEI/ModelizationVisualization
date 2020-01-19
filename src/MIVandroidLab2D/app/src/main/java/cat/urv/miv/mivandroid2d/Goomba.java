package cat.urv.miv.mivandroid2d;

import android.content.Context;

import javax.microedition.khronos.opengles.GL10;

import static java.lang.System.currentTimeMillis;

public class Goomba {
    private CharacterManager enemy;
    private GL10 gl;
    private Context context;
    private float position;

    private boolean isDead = false;
    private boolean isHitted = false;

    public Goomba(GL10 gl, Context context, int resource_id, int resource_id_text){
        this.gl = gl;
        this.context = context;
        position = 13;

        enemy = new CharacterManager(gl, context, resource_id, resource_id_text);
        enemy.setAnimation("walk");
    }

    /*public void goombaGameLogic(){

        if (goomba != null)
        {
            goomba.drawEnemy(currentTimeMillis());
            if (goomba.getPosition() <= positionX + 0.5 && goomba.getPosition() >= positionX - 1)
            {
                if (actualPositionY >= GROUND && actualPositionY <= GROUND + 20 * 0.1f && !jumpTop && !goomba.getIsDead())
                {
                    if (!isHitted)
                    {
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
    }*/

    public void drawEnemy(long time){
        gl.glPushMatrix();

        // Goomba logic

        if (!isDead) gl.glTranslatef(position, -2.5f, -35.0f);
        else
        {
          gl.glScalef(1, 0.4f, 1);
          gl.glTranslatef(position, -7.5f, -35.0f);
        }

        enemy.draw();
        enemy.update(time);
        gl.glPopMatrix();
        position -= 0.1f;
    }

    public float getPosition() {
        return position;
    }

    public void setAnimation(String name){
        this.enemy.setAnimation(name);
    }

    public void setIsDead(boolean isDead) {
        this.isDead = isDead;
    }

    public boolean getIsDead() {
        return this.isDead;
    }
}
