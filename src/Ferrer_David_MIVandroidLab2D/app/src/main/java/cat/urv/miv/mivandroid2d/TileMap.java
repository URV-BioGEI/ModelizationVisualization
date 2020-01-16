package cat.urv.miv.mivandroid2d;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.microedition.khronos.opengles.GL10;

/**
 * Class to store and control everything related with a tilemap
 */
public class TileMap {
    private Texture texture;  // Texture of the tilemap
    private GL10 gl;  // Reference to openGl renderer
    private Square tilemap[][];  // matrix to store each square of the tilemap
    private float  init_position = -20, position;  // Used to control the movement of the tilemap
    private float speed = 0.05f;  // Relative speed of the tilemap compared to the movmenet of the camera
    private int tile_width, tile_height;  // sizes of each tile in the tilemap


    public TileMap(GL10 gl, Context context, int resource_image, int resource_text){
        this.gl = gl;
        texture = new Texture(gl, context, resource_image);
        readFile(context, resource_text);
        position = init_position;
    }

    public void  readFile(Context context, int resourceId) {

        String[] parts;
        Square square;
        int total_rows, total_columns, row, column, i, previous_num =Integer.MIN_VALUE;

        InputStream inputStream = context.getResources().openRawResource(resourceId);

        BufferedReader r = new BufferedReader(new InputStreamReader(inputStream));
        try {
            // Llegeixo l'amplada i alçada de les tiles en pixels
            parts = r.readLine().split("\\s+");
            tile_width = Integer.parseInt(parts[0]);
            tile_height = Integer.parseInt(parts[1]);

            total_rows = texture.getWidth() / tile_width;
            total_columns = texture.getHeight() / tile_height;

            //Llegeixo el numero de columnes i files del fitxer
            parts = r.readLine().split("\\s+");
            tilemap = new Square[Integer.parseInt(parts[1])][Integer.parseInt(parts[0])];

            i=0;
            for (String line; (line = r.readLine()) != null;) {
                if(!line.contentEquals("")) {
                    parts = line.split("\\s+");
                    for (int j = 0; j < parts.length; j++) {
                        column = (Integer.parseInt(parts[j]) % total_rows);
                        row = Integer.parseInt(parts[j]) / total_rows;
                        square = new Square();

                        square.setTexture(texture, new float[]{
                                //0,1
                                (float) column*tile_width/ texture.getWidth(), (float) (row*tile_height + tile_height) / texture.getHeight(),
                                //0,0
                                (float) column*tile_width / texture.getWidth(), (float)  row*tile_height / texture.getHeight(),
                                //1,0
                                (float) (column*tile_width + tile_width) / texture.getWidth(), (float) row*tile_height / texture.getHeight(),
                                //1,1
                                (float) (column*tile_width + tile_width) / texture.getWidth(), (float) (row*tile_height + tile_height) / texture.getHeight(),
                                });
                        tilemap[i][j]=square;
                    }
                    i++;
                }
            }
        }
        catch (IOException e){
        }
    }

    public void draw(){
        gl.glPushMatrix();
        gl.glTranslatef(position,0,0);


        for(int i=0;i<tilemap.length;i++){
            gl.glPushMatrix();
            for(int j=0;j<tilemap[0].length;j++){
                gl.glTranslatef(2f, 0 ,0);
                tilemap[i][j].draw(gl);
            }
            gl.glPopMatrix();
            gl.glTranslatef(0, -2f ,0);
        }

        position-=speed;
        //if()
        if(position < (-tilemap[0].length)){
            position = init_position;
        }
        gl.glPopMatrix();
    }

    public void setSpeed(float speed){
        this.speed = speed;
    }

}
