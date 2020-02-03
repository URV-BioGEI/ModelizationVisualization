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
		pos = pos.add(side.normalize().mult(-inc));
	}
	
	public void moveRight(float inc) { pos = pos.add(side.normalize().mult(inc)); }
	
	public void moveUp(float inc) { pos = pos.add(up.normalize().mult(-inc)); }
	
	public void moveDown(float inc) { pos = pos.add( up.normalize().mult(inc)); }
	
	public void moveForward(float inc) { pos = pos.add(forward.normalize().mult(inc)); }
	
	public void moveBackward(float inc) { pos = pos.add(forward.normalize().mult(-inc)); }

	/*
	 * Rotate like a shove-it
	 */
	public void yaw(float angle) {
		float angleRadians = (float) (angle * 3.141592653589793D / 180.0D);
		float cos = (float) Math.cos(angleRadians), sin = (float) Math.sin(angleRadians);
		Matriu4 yawMatrix = new Matriu4(new float[]{
				cos, 0, sin, 0,
				0, 1, 0, 0
				-sin, 0, cos, 0,
				0, 0, 0, 1});

		forward.multiply(yawMatrix);
	}

	/*
	 * Rotate like a perfect impossible
	 */
	public void pitch(float angle) {
		float angleRadians = (float) (angle * 3.141592653589793D / 180.0D);
		float cos = (float) Math.cos(angleRadians), sin = (float) Math.sin(angleRadians);
		Matriu4 pitchMatrix = new Matriu4(new float[]{
				1, 0, 0, 0,
				0, cos, -sin, 0,
				0, sin, cos, 0,
				0, 0, 0, 1});

		forward.multiply(pitchMatrix);
	}

	/*
	 * Rotate like a kick-flip
	 */
	public void roll(float angle) {
		float angleRadians = (float) (angle * 3.141592653589793D / 180.0D);

		float cos = (float) Math.cos(angleRadians), sin = (float) Math.sin(angleRadians);
		Matriu4 rollMatrix = new Matriu4(new float[]{
				cos, -sin, 0, 0,
				sin, cos, 0, 0,
				0, 0, 1, 0,
				0, 0, 0, 1});

		forward.multiply(rollMatrix);
	}
	
	private void rotate(float angle, float x, float y, float z) {
		float fovyRadians = (float) ((angle / 2) * 3.141592653589793D / 180.0D);

		float cos = (float) Math.cos(fovyRadians), sin = (float) Math.sin(fovyRadians);

		float a = x * sin, b = y * sin, c = z * sin;

		Matriu4 cameraRotationMatrix = new Matriu4(new float[]{
				2 * ((float) Math.pow(a, 2) + (float)Math.pow(cos, 2)) - 1, 2 * (a * b - c * cos), 2 * (a * c + b * cos), 0,
				2 * (a * b + c * cos), 2 * ((float) Math.pow(b, 2) + (float) Math.pow(cos, 2)) - 1, 2 * (b * c - a * cos), 0,
				2 * (a * c - b * cos), 2 * (b * c + a * cos), 2 * ((float) Math.pow(c, 2) + (float)Math.pow(cos, 2)) - 1, 0,
				0, 0, 0, 1});
		forward.multiply(cameraRotationMatrix);
	}
	
	public void look()
	{
		Vertex4 center = pos.add(forward);

		GLU.gluLookAt(gl, pos.get(0), pos.get(1), pos.get(2), 
						  center.get(0), center.get(1), center.get(2), 
						  up.get(0), up.get(1), up.get(2));
	}
}
