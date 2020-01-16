package cat.urv.miv.mivandroid2d;

import java.util.LinkedList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import static java.lang.System.currentTimeMillis;

/**
 * Animation class stores all the frames from a certain animation of a certain character
 * All animation can be accessed using the corresponding characterManager
 */
public class Animation {

    private List<Square> squareList;  // List of frames from a certain animation
    private float period = 30;  // Speed of frame displacement (in ms)
    private int currentFrame;  // Current frame that it is being shown on screen
    private long currentTime;  // Time that has passed since the last sprite update
    private GL10 gl;  // Reference to GL10

    // Creates a new animation object
    public Animation(GL10 gl) {
        this.gl = gl;
        squareList = new LinkedList<>();
        currentFrame = 0;
        currentTime = currentTimeMillis();
    }

    // Add a new frame to an animation
    public void addSquare(Square square) {
        squareList.add(square);
    }

    // Show frame of the current animation
    public void draw() {
        squareList.get(currentFrame).draw(gl);
    }

    // Advance one frame if the time is enough or reset frame counter if you are on last frame
    public void update(long time) {
        if (time - currentTime >= (long) period)
        {
            currentTime = time;
            currentFrame++;
            if (currentFrame >= squareList.size())
                currentFrame = 0;
        }
    }


    public void setCurrentFrame(int frame) {
        this.currentFrame = frame;
    }

    public void setSpeed(float speed) {
        this.period = speed;
    }

}
