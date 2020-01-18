package cat.urv.miv.mivandroid2d;

import android.content.Context;

import javax.microedition.khronos.opengles.GL10;

public class LevelHUD {
    private CharacterManager characterManagerUnity, characterManagerTen;
    private int level = 0;
    Integer unity = 0, ten = 0;
    private GL10 gl;
    private Context context;

    public LevelHUD(GL10 gl, Context context) {
        this.gl = gl;
        this.context = context;
        characterManagerTen = new CharacterManager(gl, context, R.drawable.fount, R.raw.fount);
        characterManagerUnity = new CharacterManager(gl, context, R.drawable.fount, R.raw.fount);

    }

    public void addLevel()
    {
        level++;
        if (level == 100)
        {
            level = 0;
        }
        else
        {
            unity = level % 10;
            ten = level / 10;
            characterManagerUnity.setAnimation(unity.toString());
            characterManagerUnity.setAnimation(ten.toString());
        }

    }

    public void draw()
    {
        gl.glPushMatrix();

        gl.glTranslatef(0, 3f, -35.0f);
        characterManagerUnity.draw();
        gl.glTranslatef(2, 0, 0);
        characterManagerTen.draw();

        gl.glPopMatrix();
    }
}
