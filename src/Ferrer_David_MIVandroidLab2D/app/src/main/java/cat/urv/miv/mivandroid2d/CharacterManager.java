package cat.urv.miv.mivandroid2d;

import android.content.Context;
import android.text.TextDirectionHeuristic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import javax.microedition.khronos.opengles.GL10;

public class CharacterManager {

    private HashMap<String,Animation> animations;  // dictionary that relates the name of an animation with an animation
    private Texture texture;  // Texture spritesheet
    private Animation currentAnimation;  // Animation that is currently being displayed
    private GL10 gl;  // Reference to my OpenGL renderer

    public CharacterManager(GL10 gl, Context context, int resource_image, int resource_text) {
        this.gl = gl;
        this.animations = new HashMap<>();
        texture = new Texture(gl, context, resource_image);
        readFile(context, resource_text);  // Read txt file to find the coordinate of the animations
    }

    public void setAnimation(String name)
    {
        try
        {
            currentAnimation=animations.get(name);
        }
        catch (NullPointerException e)
        {
            System.out.println(e);
        }
    }

    public void draw(){
        currentAnimation.draw();
    }

    public void update(long time){
        currentAnimation.update(time);
    }

    public void  readFile(Context context, int resourceId) {
        String[] parts;
        String previousName = null;
        Square square;
        int totalWidth = texture.getWidth(), totalHeight = texture.getHeight(), xIni, yIni, texWidth, texHeight;

        BufferedReader r = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(resourceId)));
        try
        {
            for (String line; (line = r.readLine()) != null; ) {

                parts = line.split("\\s+");
                if (parts.length == 6)
                {
                    // Quan trobem un nou nom creem una nova animació
                    if (!parts[0].equals(previousName))
                    {
                        animations.put(parts[0], new Animation(gl));
                        previousName = parts[0];
                    }
                    xIni = Integer.parseInt(parts[2]);
                    yIni = Integer.parseInt(parts[3]);
                    texWidth = Integer.parseInt(parts[4]);
                    texHeight = Integer.parseInt(parts[5]);
                    yIni = texture.getHeight() - yIni - texHeight;  // variable change


                    square = new Square();
                    /* L'ordre correcte és (0,0) (0,1) (1,1) (1,0) */
                    square.setTexture(texture,new float[]{
                            (float) (xIni) / totalWidth,(float) (yIni + texHeight) /totalHeight,
                            (float)xIni / totalWidth,(float)(yIni)/totalHeight,
                            (float)(xIni + texWidth)/totalWidth,(float)(yIni)/totalHeight,
                            (float)(xIni+texWidth)/totalWidth,(float)(yIni + texHeight)/totalHeight});
                    animations.get(parts[0]).addSquare(square);
                }
            }
        }
        catch (IOException e){
        }
    }

    public void setCurrentFrame(int frame) {
        currentAnimation.setCurrentFrame(frame);
    }

    public void setSpeed(String name, float speed) {
        this.animations.get(name).setSpeed(speed);
    }
}
