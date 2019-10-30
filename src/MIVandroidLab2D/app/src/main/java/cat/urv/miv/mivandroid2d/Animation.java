package cat.urv.miv.mivandroid2d;

import java.util.LinkedList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

import static java.lang.System.currentTimeMillis;

public class Animation {

    //variables: speed, num_frames, current_frame, List<Square>
    List<Square> squareList;
    float speed = 3.0F;
    int currentFrame;
    long currentTime;

    public Animation() {
        squareList = new LinkedList<>();
        currentFrame = 0;
        currentTime = currentTimeMillis();
    }

    public void addSquare(Square square){
        squareList.add(square);
    }

    public  void draw(GL10 gl){
        squareList.get(currentFrame).draw(gl);
    }

    public void update(){
        if (currentTimeMillis()-currentTime>=(long)(speed*10)){
            currentTime=currentTimeMillis();
            currentFrame++;
            if(currentFrame>=squareList.size()) currentFrame=0;
        }
    }


}
