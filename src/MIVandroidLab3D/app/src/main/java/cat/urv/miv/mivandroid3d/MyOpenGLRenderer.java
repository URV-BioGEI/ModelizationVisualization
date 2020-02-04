package cat.urv.miv.mivandroid3d;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;


public class MyOpenGLRenderer implements Renderer{

	private Context context;
	private Object3D sphere, synth, cube, monkey;
	private Light l1, l2, l3;
	private Camera camera;
	private float angle;

	public MyOpenGLRenderer(Context context){
		this.context = context;
	}

	// Start
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		// Image Background color
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);

		//Enable Depth Testing
		gl.glEnable(GL10.GL_DEPTH_TEST);

		//Enable Smooth Shading
		gl.glShadeModel(GL10.GL_FLAT);

		//Enable Lights
		gl.glEnable(GL10.GL_LIGHTING);

		// Camera
		camera = new Camera(gl);
		camera.moveBackward(45);

		// Lights
		// Light 1
		l1 = new Light( gl, gl.GL_LIGHT0 );
		float[] color = { 0f, 0f, 1.0f, 0.0f };  // Blue light
		l1.setAmbientColor( color );
		l1.setDiffuseColor( color );
		l1.setPosition(new float[]{1, 0.3f, 1, 0});
		l1.enable();

		// Light 2
		l2 = new Light( gl, gl.GL_LIGHT1 );
		float[] color2 = { 1.0f, 0f, 0f, 0.0f };  // red light
		l2.setAmbientColor( color2 );
		l2.setDiffuseColor( color2 );
		l2.setPosition(new float[]{-1f, -1f, 0.8f, 0});
		l2.enable();


		// Light 3
		l3 = new Light( gl, gl.GL_LIGHT2 );
		float[] color3 = { 0f, 1.0f, 0f, 0.0f };  // green light
		l3.setAmbientColor( color3 );
		l3.setDiffuseColor( color3 );
		l3.setPosition(new float[]{-1f, 1f, 0.8f, 0});
		l3.enable();


		// 3D-Objects
        sphere = new Object3D(context, R.raw.earth);
        synth = new Object3D(context, R.raw.synth);
        cube = new Object3D(context, R.raw.cube);
        monkey = new Object3D(context, R.raw.monkey);


	}

	// Update
	public void onDrawFrame(GL10 gl) {
		float midAngle = 0;

        // Clears the screen and depth buffer.
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();

		// Cmaera and lights
		gl.glPushMatrix();
		// Camera
		camera.look();

		// Lights
		l1.draw();
		l2.draw();
		l3.draw();
		gl.glPopMatrix();

		//Draw the sphere
		gl.glPushMatrix();
		gl.glTranslatef(0,5,-15f);
		gl.glScalef(0.4f, 0.4f, 0.4f);
		sphere.draw(gl);
		gl.glPopMatrix();

		// Draw syntethizer
		gl.glPushMatrix();
		gl.glTranslatef(0,-4,-8);
		synth.draw(gl);
		gl.glPopMatrix();

		// Draw cube
		gl.glPushMatrix();
		gl.glScalef(0.4f, 0.4f, 0.4f);
		gl.glTranslatef(angle % 15 - 10,-5.4f,-24);
		cube.draw(gl);
		gl.glPopMatrix();

		// Draw monkey
		gl.glPushMatrix();
		gl.glScalef(0.4f, 0.4f, 0.4f);
		gl.glRotatef(angle % 360, 0, 0, 1);

		gl.glTranslatef(0,2f,-15);
		monkey.draw(gl);
		gl.glPopMatrix();


		angle += 0.3;
	}

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

	public Camera getCamera()
	{
		return camera;
	}


}
