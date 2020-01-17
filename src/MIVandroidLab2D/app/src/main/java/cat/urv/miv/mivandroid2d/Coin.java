package cat.urv.miv.mivandroid2d;

import android.content.Context;

import javax.microedition.khronos.opengles.GL10;


public class Coin {
    private CharacterManager coin;
    private GL10 gl;
    private Context context;
    private float position, upPosition = -2.5f;
    private int upFrames=0;
    private boolean isCaught = false, destroyable = false;

    public Coin(GL10 gl, Context context){
        this.gl = gl;
        this.context = context;
        position = 13;
        coin = new CharacterManager(gl, context, R.drawable.foreground_tiles, R.raw.coin);
        coin.setAnimation("move");
        coin.setSpeed("move", 150);
    }

    public void drawCoin(long time){
        gl.glPushMatrix();
        if(isCaught){
            upPosition+=0.3f;
            upFrames++;
        }
        else position -=0.1f;

        if(upFrames == 20) destroyable = true;

        gl.glTranslatef(position, upPosition, -40.0f);
        coin.draw();
        coin.update(time);
        gl.glPopMatrix();

    }

    public float getPosition() {
        return position;
    }

    public void isCaught() {
        this.isCaught=true;
    }

    public boolean isDestroyable(){
        return destroyable;
    }

    public boolean getIsCaught(){
        return isCaught;
    }
}
