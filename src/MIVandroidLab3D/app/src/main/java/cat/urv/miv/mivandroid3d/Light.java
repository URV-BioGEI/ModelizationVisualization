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

	}

	public void setPosition() {

	}



	//To set the light colors
	public void setAmbientColor(float[] color) {

	}

	public void setDiffuseColor(float[] color) {

	}

}
