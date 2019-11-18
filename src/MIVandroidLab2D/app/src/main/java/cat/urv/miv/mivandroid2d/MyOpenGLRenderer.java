package cat.urv.miv.mivandroid2d;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;

import static java.lang.System.currentTimeMillis;

public class MyOpenGLRenderer implements Renderer {

	private Square square;
	private Texture texture1;
	private int angle = 0;
	private int actualSprite=1;

	private Context context;
	private CharacterManager mushroom;

	public MyOpenGLRenderer(Context context){
		this.context = context;
	}


	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		gl.glEnable(GL10.GL_TEXTURE_2D); // Enable OpenGL textures

		// Image Background color
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);

		//Create the objects
		//square = new Square();

		/*square.setColor(new float[]{0.0f, 1.0f, 0.0f, 0.0f,
									0.0f, 0.0f, 0.0f, 0.0f,
									1.0f, 0.0f, 0.0f, 0.0f,
									0.0f, 0.0f, 1.0f, 0.0f});*/
		//texture1=new Texture(gl, context, R.drawable.texture);

		/*square.setTexture(texture1,new float[]{
												0.0f,1.0f,
												0.0f,0.0f,
												1.0f,0.0f,
												1.0f,1.0f});*/
		try {

			mushroom = new CharacterManager(gl, context, R.drawable.mushroom, R.raw.mushroom);
			mushroom.setAnimation("walk");
		}
		catch (Exception e){
			System.out.println(e);
		}

	}

	@Override
	public void onDrawFrame(GL10 gl) {

		
		// Clears the screen and depth buffer.
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		
		gl.glLoadIdentity();	

		gl.glTranslatef(0.0f, 0.0f, -10.0f);

		gl.glPushMatrix();

		mushroom.update(currentTimeMillis());
		mushroom.draw();

		gl.glPopMatrix();



		/*
		// Green Square
		gl.glPushMatrix();
		int midAngle = angle % 200;
		if (midAngle > 100)
			midAngle = 200 - midAngle;
		gl.glTranslatef(0.0f, 0.0f, midAngle * -0.1f);
		//gl.glColor4f(0.0f, 1.0f, 0.0f, 0.0f);
		square.draw(gl);
		gl.glPopMatrix();
		
		gl.glRotatef(angle, 0.0f, 1.0f, 0.0f);

		// Red Square
		gl.glPushMatrix();
		gl.glRotatef(angle, 0.0f, 0.0f, 1.0f);
		gl.glTranslatef(-2.0f, 0.0f, 0.0f);
		//gl.glColor4f(1.0f, 0.0f, 0.0f, 0.0f);
		square.draw(gl);
		gl.glPopMatrix();
		
		// Blue Square
		gl.glPushMatrix();
		gl.glRotatef(2.0f * angle, 0.0f, 0.0f, 1.0f);
		gl.glTranslatef( 1.0f, 0.0f, 0.0f);
		//gl.glColor4f(0.0f, 0.0f, 1.0f, 0.0f);
		square.draw(gl);
		gl.glPopMatrix();
		
		angle += 5.0f;
		*/
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		// Define the Viewport
		gl.glViewport(0, 0, width, height);
		// Select the projection matrix
		gl.glMatrixMode(GL10.GL_PROJECTION);
		// Reset the projection matrix
		gl.glLoadIdentity();
		// Calculate the aspect ratio of the window
		GLU.gluPerspective(gl, 60.0f, (float) width / (float) height, 0.1f, 100.0f);
		
		// Select the modelview matrix
		gl.glMatrixMode(GL10.GL_MODELVIEW);	
	}

}
