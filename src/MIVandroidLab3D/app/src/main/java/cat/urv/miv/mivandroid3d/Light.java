package cat.urv.miv.mivandroid3d;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL10;

public class Light {
	ByteBuffer vtbb;
	FloatBuffer posicio;
	FloatBuffer ambient;
	FloatBuffer difuse;
	FloatBuffer specular;
	GL10 gl;
	int lightid;

	public Light(GL10 gl, int lightid) {
		this.gl = gl;
		this.lightid = lightid;
    }

	//To enable and disable the light
	public void enable() {gl.glEnable(lightid);}
	public void disable() {gl.glDisable(lightid);}

	//To position the light
	public void setPosition(float[] pos) {
		vtbb = ByteBuffer.allocateDirect(pos.length * 4);
		vtbb.order(ByteOrder.nativeOrder());
		posicio = vtbb.asFloatBuffer();
		posicio.put(pos);
		posicio.position(0);
	}

	public void setPosition() {
		float[] pos = {1.0f, 1.0f, 1.0f, 0.0f};
		vtbb = ByteBuffer.allocateDirect(pos.length * 4);
		vtbb.order(ByteOrder.nativeOrder());
		posicio = vtbb.asFloatBuffer();
		posicio.put(pos);
		posicio.position(0);
	}

	//To set the light colors
	public void setAmbientColor(float[] color) {
		vtbb = ByteBuffer.allocateDirect(color.length * 4);
		vtbb.order(ByteOrder.nativeOrder());
		ambient = vtbb.asFloatBuffer();
		ambient.put(color);
		ambient.position(0);
	}

	public void setDiffuseColor(float[] color) {
		vtbb = ByteBuffer.allocateDirect(color.length * 4);
		vtbb.order(ByteOrder.nativeOrder());
		difuse = vtbb.asFloatBuffer();
		difuse.put(color);
		difuse.position(0);
	}

}
