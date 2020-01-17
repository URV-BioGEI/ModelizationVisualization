package cat.urv.miv.mivandroid2d;

import android.content.Context;

import javax.microedition.khronos.opengles.GL10;

/**
 * Class to manage the collection of coins and its gam logic
 */
public class Coin {
    private CharacterManager coin;  // Character manager of the coin
    private GL10 gl;
    private Context context;

    // Coin game logic
    private float
            position,  // current position of the coin
            upPosition = -2.5f;  // Position to move when the coin is caught
    private int upFrames = 0;  // Counter to know how many frames the coin has been in the air when caught
    private boolean
            isCaught = false,  // Is the coin already caught? so we start playing the upward animation
            destroyable = false;  // true when we can delete the coin from the scene

    public Coin(GL10 gl, Context context){
        this.gl = gl;
        this.context = context;

        position = 13;
        coin = new CharacterManager(gl, context, R.drawable.foreground_tiles, R.raw.coin);  // create new character manager

        // When created a new coin set moving animation of 150 speed
        coin.setAnimation("move");
        coin.setSpeed("move", 150);
    }

    public void drawCoin(long time){
        gl.glPushMatrix();

        if (isCaught)
        {
            upPosition += 0.3f;  // If is caught move the coin upwards
            upFrames++;  // count frames in the air
        }
        else position -= 0.1f;

        if (upFrames == 20) destroyable = true;  // When the coin reaches maximum position is destroyed

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
