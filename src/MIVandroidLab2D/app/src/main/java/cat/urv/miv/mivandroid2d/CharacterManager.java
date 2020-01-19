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

        String[] parts;
        String previousName = null;
        Square square;
        int totalWidth = texture.getWidth(), totalHeight = texture.getHeight(), xIni, yIni, texWidth, texHeight;

        BufferedReader r = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(resource_text)));
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
                    /*
                     * En aquest punt referenciem el tile actual utilitzant com a eix de referencia y el bottom de la imatge
                     * i l'eix de referencia x desde left. Apliquem una transformació a Y per a canviar l'eix de coordinades a top
                     * i restem la longitud d'altura per a que ens apunti a la cantonada superior esquerre de la imatge, que sera
                     * el punt del tile actual mes proper als eixos que acabem de configurar: X es left i Y es top
                     */
                    yIni = texture.getHeight() - yIni - texHeight;  // Apliquem transformació

                    square = new Square();
                    square.setTexture(texture, new float[]{
                            (float) xIni / totalWidth, (float) (yIni + texHeight) / totalHeight,
                            (float) xIni / totalWidth, (float) (yIni) / totalHeight,
                            (float) (xIni + texWidth) / totalWidth, (float) (yIni) / totalHeight,
                            (float) (xIni + texWidth) / totalWidth, (float) (yIni + texHeight) / totalHeight});
                    animations.get(parts[0]).addSquare(square);
                }
            }
        }
        catch (IOException e){
        }
    }

    public void setAnimation(String name)
    {
        try
        {
            currentAnimation = animations.get(name);
        }
        catch (NullPointerException e)
        {
            System.out.println(e);
        }
    }

    public void draw(){ currentAnimation.draw(); }

    public void update(long time){
        currentAnimation.update(time);
    }

    public void setCurrentFrame(int frame) {
        currentAnimation.setCurrentFrame(frame);
    }

    public void setSpeed(String name, float speed) {
        this.animations.get(name).setSpeed(speed);
    }

    public HashMap<String, Animation> getAnimations() { return animations; }
}
