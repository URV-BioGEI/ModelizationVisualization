package cat.urv.miv.mivandroid3d;

import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;

public class Camera {
	GL10 gl;
	Vertex4 pos;
	Vertex4 forward, up, side;
	
	public Camera(GL10 gl) {
		this.gl = gl;
		
		pos = new Vertex4(0.0f, 0.0f, 0.0f, 1.0f);
		forward = new Vertex4(0.0f, 0.0f, -1.0f, 0.0f);
		up = new Vertex4(0.0f, 1.0f, 0.0f, 0.0f);
		side = new Vertex4(1.0f, 0.0f, 0.0f, 0.0f);
	}
	
	public void moveLeft(float inc) {
		pos = pos.add( side.normalize().mult(-inc) );
	}
	
	public void moveRight(float inc) {
	}
	
	public void moveUp(float inc) {
	}
	
	public void moveDown(float inc) {
	}
	
	public void moveForward(float inc) {
	}
	
	public void moveBackward(float inc) {
	}
	
	public void yaw(float angle) {
	}
	
	public void pitch(float angle) {
	}
	
	public void roll(float angle) {
	}
	
	private void rotate(float angle, float x, float y, float z) {
	}
	
	public void look()
	{
		Vertex4 center = pos.add(forward);

		GLU.gluLookAt(gl, pos.get(0), pos.get(1), pos.get(2), 
						  center.get(0), center.get(1), center.get(2), 
						  up.get(0), up.get(1), up.get(2));
	}
}
