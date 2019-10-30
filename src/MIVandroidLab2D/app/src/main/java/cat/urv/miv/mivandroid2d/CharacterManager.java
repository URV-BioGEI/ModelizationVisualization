package cat.urv.miv.mivandroid2d;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import javax.microedition.khronos.opengles.GL10;

public class CharacterManager {
    // Aqui llegim el fitxer i creem animation per cada tipus, walk, run, ...
    HashMap<String,Animation> animations;
    Texture texture;
    Animation  currentAnimation;

    public CharacterManager(GL10 gl, Context context, int resource_image, int resource_text) {
        this.animations = new HashMap<>();
        texture = new Texture(gl, context, resource_image);
        readFile(context, resource_text);
    }

    public void setAnimation(String name){
        try {
            currentAnimation=animations.get(name);
        }catch (NullPointerException e){
            System.out.println(e);
        }
    }

    public void draw(GL10 gl){
        currentAnimation.draw(gl);
    }
    public void update(){
        currentAnimation.update();
    }
    public void  readFile(Context context, int resourceId) {
        String[] parts;
        String name,previousName = null;
        Square square;
        int tileWidth=texture.getWidth(), tileHeight=texture.getHeight(), xIni, yIni,
                texWidth, texHeight;
        InputStream inputStream = context.getResources().openRawResource(resourceId);

        BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
        try{
            for (String line; (line = r.readLine()) != null; ) {

                parts = line.split("\\s+");
                if(parts.length==6){
                    // Quan trobem un nou nom creem una nova animació
                    if(!parts[0].equals(previousName)){
                        animations.put(parts[0],new Animation());
                        previousName=parts[0];
                    }
                    name = parts[0]+parts[1];
                    xIni=Integer.parseInt(parts[2]);
                    yIni=Integer.parseInt(parts[3]);
                    texWidth=Integer.parseInt(parts[4]);
                    texHeight=Integer.parseInt(parts[5]);

                    square = new Square();
                    /* L'ordre correcte és (0,0) (0,1) (1,1) (1,0) */
                    square.setTexture(texture,new float[]{
                            (float)xIni/tileWidth,(float)yIni/tileHeight,
                            (float)xIni/tileWidth,(float)(yIni-texHeight)/tileHeight,
                            (float)(xIni+texWidth)/tileWidth,(float)(yIni-texHeight)/tileHeight,
                            (float)(xIni+texWidth)/tileWidth,(float)yIni/tileHeight});
                    animations.get(parts[0]).addSquare(square);
                }
            }
        }
        catch (IOException e){
        }
    }
}
