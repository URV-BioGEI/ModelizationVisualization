package cat.urv.miv.mivandroid2d;

import android.content.Context;

import javax.microedition.khronos.opengles.GL10;

public class Block {
    private CharacterManager characterManager;
    private GL10 gl;
    private Context context;
    private float position;
    private boolean isSmashed = false;

    public Block(GL10 gl, Context context){
        this.gl = gl;
        this.context = context;
        position = 50;
        characterManager = new CharacterManager(gl, context, R.drawable.foreground_tiles, R.raw.block);
        characterManager.setAnimation("idle");
    }

    public void draw(long time){
        gl.glPushMatrix();

        gl.glTranslatef(position, 3.5f, -35.0f);

        characterManager.draw();
        characterManager.update(time);
        gl.glPopMatrix();
        position -=0.1f;
    }

    public CharacterManager getCharacterManager() {
        return characterManager;
    }
}

