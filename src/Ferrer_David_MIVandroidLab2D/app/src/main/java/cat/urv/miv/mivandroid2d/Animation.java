package cat.urv.miv.mivandroid2d;

import java.util.LinkedList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import static java.lang.System.currentTimeMillis;

public class Animation {

    private List<Square> squareList;
    private float speed = 30;
    private int currentFrame;
    private long currentTime;
    private GL10 gl;

    public Animation(GL10 gl) {
        this.gl = gl;
        squareList = new LinkedList<>();
        currentFrame = 0;
        currentTime = currentTimeMillis();
    }

    public void addSquare(Square square){
        squareList.add(square);
    }

    public  void draw(){
        squareList.get(currentFrame).draw(gl);
    }

    public void update(long time){
        if (time - currentTime >= (long)speed){
            currentTime = time;
            currentFrame++;
            if(currentFrame >= squareList.size()) currentFrame=0;
        }
    }

    public void setCurrentFrame(int frame)
    {
        this.currentFrame = frame;
    }

    public void setSpeed(float speed){
        this.speed=speed;
    }

}
