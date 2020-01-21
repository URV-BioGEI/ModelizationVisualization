package cat.urv.miv.mivandroid2d;

import android.content.Context;

import javax.microedition.khronos.opengles.GL10;

public class Lifebar {
    private GL10 gl;
    private Context context;

    private CharacterManager[] hearts;
    private int life = 4 * 3;
    private int numHearts = 3;

    public Lifebar(GL10 gl, Context context) {
        this.gl = gl;
        this.context = context;
        hearts = new CharacterManager[numHearts];
        for (int i = 0; i < numHearts; i++)
        {
            hearts[i] = new CharacterManager(gl, context, R.drawable.hearts, R.raw.hearts);
            hearts[i].setAnimation("4");
        }
    }

    public void takeDamage(int damage)
    {
        life -= damage;
        if (life < 0) life = 0;
        if (life > numHearts * 4) life = numHearts * 4;
        int numHeartsFull = life / 4;
        for (int i = 0; i < numHeartsFull; i++)
        {
            hearts[i].setAnimation("4");
        }
        if (numHeartsFull == numHearts) return;
        String quarters = ((Integer)(life % 4)).toString();
        hearts[numHeartsFull].setAnimation(quarters);
        if (numHeartsFull + 1 == numHearts) return;
        for (int i = numHeartsFull + 1; i < numHearts; i++)
        {
            hearts[i].setAnimation("0");
        }
    }

    public void draw(float time)
    {
        gl.glPushMatrix();

        gl.glTranslatef(-8, -8, -35);

        for (int i = 0; i < numHearts; i++)
        {
            hearts[i].draw();
            gl.glTranslatef(2.1f, 0, 0);
        }

        gl.glPopMatrix();
    }

    public int getLife() {
        return life;
    }
}
