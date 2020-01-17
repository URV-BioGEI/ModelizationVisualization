package cat.urv.miv.mivandroid2d;

import java.util.LinkedList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import static java.lang.System.currentTimeMillis;

/**
 * Class that stores an animation. Basically is a list of squares
 */
public class Animation {

    private List<Square> squareList;  // List of frames of the animation
    private float speed = 30;  // Speed of animation run (in ms)
    private int currentFrame;  // Current frame that is being displayed in the program
    private long currentTime;  // Time that this animation has been displayed
    private GL10 gl;  // Reference to OpenGL renderer

    public Animation(GL10 gl)
    {
        this.gl = gl;
        squareList = new LinkedList<>();
        currentFrame = 0;
        currentTime = currentTimeMillis();
    }

    public void addSquare(Square square)
    {
        squareList.add(square);
    }

    public  void draw()
    {
        squareList.get(currentFrame).draw(gl);
    }

    public void update(long time)
    {
        if (time - currentTime >= (long)speed){
            currentTime = time;
            currentFrame++;
            if(currentFrame >= squareList.size()) currentFrame = 0;
        }
    }

    public void setCurrentFrame(int frame)
    {
        this.currentFrame = frame;
    }

    public void setSpeed(float speed)
    {
        this.speed = speed;
    }

}
