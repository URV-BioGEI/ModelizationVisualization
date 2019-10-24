package cat.urv.miv.mivandroid2d;

import android.content.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

import javax.microedition.khronos.opengles.GL10;

public class TextureAtlas {
    private HashMap<String, Square> textureAtlas;
    private Texture texture;

    public TextureAtlas(GL10 gl, Context context, int resource_image, int resource_text){
        textureAtlas = new HashMap<>();
        texture=new Texture(gl, context, resource_image);
        readFile(context, resource_text);
    }

    public Square getSquare(String name){
        return textureAtlas.get(name);
    }

    public void  readFile(Context context, int resourceId) {
        String[] parts;
        String name;
        Square square;
        int tileWidth=texture.getWidth(), tileHeight=texture.getHeight(), xIni, yIni,
                texWidth, texHeight;
        InputStream inputStream = context.getResources().openRawResource(resourceId);

        BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
        try{
            for (String line; (line = r.readLine()) != null; ) {

                parts = line.split("\\s+");
                if(parts.length==6){
                    name = parts[0]+parts[1];
                    xIni=Integer.parseInt(parts[2]);
                    yIni=Integer.parseInt(parts[3]);
                    texWidth=Integer.parseInt(parts[4]);
                    texHeight=Integer.parseInt(parts[5]);

                    square = new Square();
                    square.setTexture(texture,new float[]{
                            (float)(xIni-texWidth)/tileWidth,(float)yIni/tileHeight,
                            (float)(xIni-texWidth)/tileWidth,(float)(yIni-texHeight)/tileHeight,
                            (float)xIni/tileWidth,(float)(yIni-texHeight)/tileHeight,
                            (float)xIni/tileWidth,(float)yIni/tileHeight});

                    textureAtlas.put(name,square);
                }
            }
        }
        catch (IOException e){
        }


    }

}
