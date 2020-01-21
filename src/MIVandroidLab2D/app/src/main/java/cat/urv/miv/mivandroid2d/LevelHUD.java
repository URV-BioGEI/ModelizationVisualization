package cat.urv.miv.mivandroid2d;

import android.content.Context;

import javax.microedition.khronos.opengles.GL10;

public class LevelHUD {
    private CharacterManager characterManagerUnity, characterManagerTen, l, e, v, space;
    private int level = 1;
    private GL10 gl;
    private Context context;

    public LevelHUD(GL10 gl, Context context) {
        this.gl = gl;
        this.context = context;
        characterManagerTen = new CharacterManager(gl, context, R.drawable.fount, R.raw.fount);
        characterManagerUnity = new CharacterManager(gl, context, R.drawable.fount, R.raw.fount);
        l = new CharacterManager(gl, context, R.drawable.fount, R.raw.fount);
        e = new CharacterManager(gl, context, R.drawable.fount, R.raw.fount);
        v = new CharacterManager(gl, context, R.drawable.fount, R.raw.fount);
        space = new CharacterManager(gl, context, R.drawable.fount, R.raw.fount);

        l.setAnimation("L");
        e.setAnimation("E");
        v.setAnimation("V");
        space.setAnimation("space");
        characterManagerTen.setAnimation("0");
        characterManagerUnity.setAnimation("1");

    }

    public void addLevel()
    {
        level++;
        if (level > 99) level = 99;
        updateAnimations();

    }

    public void updateAnimations()
    {
            characterManagerUnity.setAnimation(((Integer)(level % 10)).toString());
            characterManagerTen.setAnimation(((Integer)(level / 10)).toString());
    }

    public void loseLevel()
    {
        level--;
        if (level < 1) level = 1;
        updateAnimations();
    }

    public void draw(float time)
    {
        gl.glPushMatrix();

        gl.glTranslatef(-7, 15f, -35.0f);
        l.draw();
        gl.glTranslatef(2, 0, 0);
        e.draw();
        gl.glTranslatef(2, 0, 0);
        v.draw();
        gl.glTranslatef(2, 0, 0);
        e.draw();
        gl.glTranslatef(2, 0, 0);
        l.draw();
        gl.glTranslatef(2, 0, 0);
        space.draw();
        gl.glTranslatef(2, 0, 0);
        characterManagerTen.draw();
        gl.glTranslatef(2, 0, 0);
        characterManagerUnity.draw();


        gl.glPopMatrix();
    }

    public int getLevel() {
        return level;
    }
}
