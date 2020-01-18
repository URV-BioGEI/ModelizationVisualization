package cat.urv.miv.mivandroid2d;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.view.Display;
import android.view.MotionEvent;

public class MainActivity extends Activity {

    private MyOpenGLRenderer glRenderer =  new MyOpenGLRenderer(this);
    private static int width, height;
    private MusicPlayer musicPlayer;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        super.onCreate(savedInstanceState);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        height = size.y;
        width = size.x;

        musicPlayer = new MusicPlayer();
        musicPlayer.PlayAudio(this, R.raw.new_super_mario_theme);

        GLSurfaceView view = new GLSurfaceView(this);
        view.setRenderer(glRenderer);
        setContentView(view);

    }

    @Override
    public boolean onTouchEvent(MotionEvent e) {
        if (e.getAction() == MotionEvent.ACTION_DOWN)
        {

            glRenderer.isJumping();

        }
        return true;
    }

    public static int getScreenHeight(){
        return  height;
    }

    public static int getScreenWidth(){
        return  width;
    }



}