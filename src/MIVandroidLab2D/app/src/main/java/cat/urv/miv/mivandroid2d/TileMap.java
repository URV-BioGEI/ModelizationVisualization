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
    private int tile_width, tile_height;  // sizes of each tile in the tilemap
    private int tilemapRows, tilemapColumns;  // number of columns and row in the tilemap txt

    // Scroll parallax control
    private float
            //init_position = -20,  // Initial position of the tilemap
            position,  // Used to control the movement of the tilemap
            speed = 0.05f;  // Relative speed of the tilemap compared to the movement of the camera

    public TileMap(GL10 gl, Context context, int resource_image, int resource_text, float speed, float position)
    {
        this(gl, context, resource_image, resource_text);  // Constructor overload
        this.speed = speed;
        this.position = position;
    }


    public TileMap(GL10 gl, Context context, int resource_image, int resource_text)
    {
        this.gl = gl;
        texture = new Texture(gl, context, resource_image);

        String[] parts;
        Square square;
        int total_rows, row, column;

        BufferedReader r = new BufferedReader(new InputStreamReader(context.getResources().openRawResource(resource_text)));
        try
        {
            // Llegeixo l'amplada i alçada de les tiles en pixels
            parts = r.readLine().split("\\s+");
            tile_width = Integer.parseInt(parts[0]);
            tile_height = Integer.parseInt(parts[1]);

            total_rows = texture.getWidth() / tile_width;  // Total rows in the tilemap texture
            //total_columns = texture.getHeight() / tile_height;

            //Llegeixo el numero de columnes i files del fitxer
            parts = r.readLine().split("\\s+");
            tilemapRows = Integer.parseInt(parts[1]);
            tilemapColumns = Integer.parseInt(parts[0]);
            tilemap = new Square[tilemapRows][tilemapColumns];

            String line;
            for (int i = 0; i < tilemapRows; i++)
            {
                line = r.readLine();
                parts = line.split("\\s+");
                for (int j = 0; j < tilemapColumns; j++)
                {
                    column = (Integer.parseInt(parts[j]) % total_rows);
                    row = Integer.parseInt(parts[j]) / total_rows;
                    square = new Square();

                    square.setTexture(texture, new float[]{
                            (float) (column * tile_width) / texture.getWidth(), (float) ((row + 1) * tile_height) / texture.getHeight(),
                            (float) (column * tile_width) / texture.getWidth(), (float) (row * tile_height) / texture.getHeight(),
                            (float) ((column + 1) * tile_width) / texture.getWidth(), (float) (row * tile_height) / texture.getHeight(),
                            (float) ((column + 1) * tile_width) / texture.getWidth(), (float) ((row + 1) * tile_height) / texture.getHeight(),});
                    tilemap[i][j] = square;
                }
            }
        }
        catch (IOException e)
        {

        }
    }

    public void draw(){
        gl.glPushMatrix();
        gl.glTranslatef(position,0,0);

        // Coordinate origin: bottom left
        for (int i = 0; i < tilemapRows; i++){  // First indexed squares are the ones at the top
            gl.glPushMatrix();
            for (int j = 0; j < tilemapColumns; j++)
            {
                gl.glTranslatef(2f, 0 ,0);
                tilemap[i][j].draw(gl);
            }
            gl.glPopMatrix();
            gl.glTranslatef(0, -2f ,0);
        }

        position -= speed;
        if (position + tilemapColumns * 2f < -20)  // When the tilemap is out of the screen
        {
            position = position + tilemapColumns * 2f;  // Move 2f * tilemapColumns * 2 to the right so we reach the end of the second tilemap
        }
        gl.glPopMatrix();
    }

    public void setSpeed(float speed){
        this.speed = speed;
    }

    public int getTilemapColumns() { return this.tilemapColumns; }


}
