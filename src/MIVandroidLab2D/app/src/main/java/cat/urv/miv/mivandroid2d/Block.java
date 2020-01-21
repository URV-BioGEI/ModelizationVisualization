package cat.urv.miv.mivandroid2d;

import android.content.Context;

import javax.microedition.khronos.opengles.GL10;

public class Block {
    private CharacterManager characterManager;
    private GL10 gl;
    private Context context;
    private float position, displacement;
    private boolean isSmashed = false;

    public Block(GL10 gl, Context context){
        this.gl = gl;
        this.context = context;
        position = 50;
        characterManager = new CharacterManager(gl, context, R.drawable.foreground_tiles, R.raw.block);
        characterManager.setAnimation("idle");
        characterManager.setSpeed("idle", 120);
    }

    public void draw(long time){
        gl.glPushMatrix();

        if (isSmashed)
        {
            characterManager.setAnimation("smashed");
        }
        if (position < -15f)
        {
            position = 12f + (float)Math.random() * 100;
            characterManager.setAnimation("idle");
            isSmashed = false;
        }
        gl.glTranslatef(position, 3.5f, -35.0f);

        characterManager.draw();
        characterManager.update(time);
        gl.glPopMatrix();
        position -= displacement;
    }

    public CharacterManager getCharacterManager() {
        return characterManager;
    }

    public float getPosition() {
        return position;
    }

    public void setSmashed(boolean smashed) {
        isSmashed = smashed;
    }

    public void setDisplacement(float displacement) {
        this.displacement = displacement;
    }
}

