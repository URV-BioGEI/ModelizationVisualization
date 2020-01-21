package cat.urv.miv.mivandroid2d;

import android.content.Context;

import javax.microedition.khronos.opengles.GL10;

/**
 * Class to manage the collection of coins and its gam logic
 */
public class Coin {
    private CharacterManager characterManager;  // Character manager of the characterManager
    private GL10 gl;
    private Context context;

    // Coin game logic
    private float
            positionX,  // current position of the characterManager
            positionY = 0,  // Position to move when the characterManager is caught
            positionYInit = 0,
            displacement = 0.1f;

    private int upFrames = 0;  // Counter to know how many frames the characterManager has been in the air when caught
    private boolean
            isCaught = false,  // Is the coin already caught? so we start playing the upward animation
            destroyable = false;  // true when we can delete the characterManager from the scene

    public Coin(GL10 gl, Context context){
        this.gl = gl;
        this.context = context;

        positionX = 13 + (float)Math.random() * 100;
        characterManager = new CharacterManager(gl, context, R.drawable.foreground_tiles, R.raw.coin);  // create new character manager

        // When created a new characterManager set moving animation of 150 speed
        characterManager.setAnimation("move");
        characterManager.setSpeed("move", 150);
    }

    public void draw(long time){
        gl.glPushMatrix();

        if (isCaught)
        {
            positionY += 0.3f;  // If is caught move the characterManager upwards
            upFrames++;  // count frames in the air
            if (upFrames >= 20)
            {
                upFrames = 0;
                isCaught = false;
                positionY = positionYInit;
                positionX = 13 + 100 * (float)Math.random();
            }
        }
        else positionX -= displacement;  // else we move the characterManager forward
        if (positionX < -15) positionX = 13 + 100 * (float)Math.random();

        gl.glTranslatef(positionX, positionY, -40.0f);
        characterManager.draw();
        characterManager.update(time);
        gl.glPopMatrix();

    }

    public float getPositionX() {
        return positionX;
    }

    public void setCaught(boolean caught) {
        isCaught = caught;
    }

    public boolean isDestroyable(){
        return destroyable;
    }

    public boolean getIsCaught(){
        return isCaught;
    }

    public void setSpeed(float speed)
    {
        for (Animation animation: characterManager.getAnimations().values())
        {
            animation.setSpeed(speed);
        }
    }

    public void setDisplacement(float disp)
    {
        this.displacement = disp;
    }

    public CharacterManager getCharacterManager() {
        return characterManager;
    }

    public void setPositionYInit(float positionYInit) {
        this.positionY = positionYInit;
        this.positionYInit = positionYInit;
    }

    public float getPositionY() {
        return positionY;
    }


}
