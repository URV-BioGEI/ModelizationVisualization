package cat.urv.miv.mivandroid2d;

import android.content.Context;

import javax.microedition.khronos.opengles.GL10;

public class Enemy {
    private CharacterManager enemy;
    private GL10 gl;
    private Context context;
    private float position;
    private boolean isDead = false;

    public Enemy(GL10 gl, Context context, int resource_id, int resource_id_text){
        this.gl = gl;
        this.context = context;
        position = 13;
        enemy = new CharacterManager(gl, context, resource_id, resource_id_text);
        enemy.setAnimation("walk");
    }

    public void drawEnemy(long time){
        gl.glPushMatrix();

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
