package cat.urv.miv.mivandroid3d;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;
import java.util.ArrayList;

import javax.microedition.khronos.opengles.GL10;

import android.content.Context;

public class Object3D {

	// Color enabled or not
	boolean colorEnabled = false;

	// Texture enabled or not
	boolean textureEnabled = false;

	// Our vertex buffer.
	private FloatBuffer vertexBuffer;

	// Our normal buffer.
	private FloatBuffer normalBuffer;

	// Our index buffer.
	private ShortBuffer indexBuffer;

	// Our texture buffer.
	private FloatBuffer texcoordBuffer;

	int[] textures;
	int numFaceIndexs = 0;

	public Object3D(Context ctx, int filenameId) {

		try {
			String line;
			String[] tmp,ftmp;

			ArrayList<Float> vlist = new ArrayList<Float>();
			ArrayList<Float> tlist = new ArrayList<Float>();
			ArrayList<Float> nlist = new ArrayList<Float>();
			ArrayList<Integer> vindex = new ArrayList<Integer>();
			ArrayList<Integer> tindex = new ArrayList<Integer>();
			ArrayList<Integer> nindex = new ArrayList<Integer>();

			InputStream is = ctx.getResources().openRawResource(filenameId);
			BufferedReader inb = new BufferedReader(new InputStreamReader(is), 1024);
			while ((line = inb.readLine()) != null) {
				tmp = line.split(" ");
				if (tmp[0].equalsIgnoreCase("v")) {

					for (int i = 1; i < 4; i++) {
						vlist.add( Float.parseFloat(tmp[i]) );
					}

				}
				if (tmp[0].equalsIgnoreCase("vn")) {

					for (int i = 1; i < 4; i++) {
						nlist.add( Float.parseFloat(tmp[i]) );
					}

				}
				if (tmp[0].equalsIgnoreCase("vt")) {
					for (int i = 1; i < 3; i++) {
						tlist.add( Float.parseFloat(tmp[i]) );
					}

				}
				if (tmp[0].equalsIgnoreCase("f")) {
					for (int i = 1; i < 4; i++) {
						ftmp = tmp[i].split("/");

						vindex.add(Integer.parseInt(ftmp[0]) - 1);
						if (tlist.size()>0)
							tindex.add(Integer.parseInt(ftmp[1]) - 1);
						if (nlist.size()>0)
							nindex.add(Integer.parseInt(ftmp[2]) - 1);

						numFaceIndexs++;
					}
				}
			}

			ByteBuffer vbb = ByteBuffer.allocateDirect(vindex.size() * 4 * 3);
			vbb.order(ByteOrder.nativeOrder());
			vertexBuffer = vbb.asFloatBuffer();

			for (int j = 0; j < vindex.size(); j++) {
				vertexBuffer.put(vlist.get( vindex.get(j)*3 ));
				vertexBuffer.put(vlist.get( vindex.get(j)*3+1 ));
				vertexBuffer.put(vlist.get( vindex.get(j)*3+2 ));
			}
			vertexBuffer.position(0);


			if (tindex.size()>0)  {
				ByteBuffer vtbb = ByteBuffer.allocateDirect(tindex.size() * 4 * 2);
				vtbb.order(ByteOrder.nativeOrder());
				texcoordBuffer = vtbb.asFloatBuffer();

				for (int j = 0; j < tindex.size(); j++) {
					texcoordBuffer.put(tlist.get( tindex.get(j)*2 ));
					texcoordBuffer.put(tlist.get( tindex.get(j)*2+1 ));
				}
				texcoordBuffer.position(0);
			}

			if(nindex.size()>0) {
				ByteBuffer nbb = ByteBuffer.allocateDirect(nindex.size() * 4 * 3);
				nbb.order(ByteOrder.nativeOrder());
				normalBuffer = nbb.asFloatBuffer();

				for (int j = 0; j < nindex.size(); j++) {
					normalBuffer.put(nlist.get( nindex.get(j)*3 ));
					normalBuffer.put(nlist.get( nindex.get(j)*3+1 ));
					normalBuffer.put(nlist.get( nindex.get(j)*3+2 ));
				}
				normalBuffer.position(0);
			}

			ByteBuffer ibb = ByteBuffer.allocateDirect(numFaceIndexs * 2);
			ibb.order(ByteOrder.nativeOrder());
			indexBuffer = ibb.asShortBuffer();

			for (short j = 0; j < numFaceIndexs; j++) {
				indexBuffer.put(j);
			}
			indexBuffer.position(0);

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void draw(GL10 gl) {
		// Enabled the vertices buffer for writing and to be used during
		// rendering.
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		if(textureEnabled) gl.glEnableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

		//////////////////////// NEW ////////////////////////////////
		gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
		//////////////////////// NEW ////////////////////////////////

		// Specifies the location and data format of an array of vertex
		// coordinates to use when rendering.
		gl.glVertexPointer(3, GL10.GL_FLOAT, 0, vertexBuffer);

		//////////////////////// NEW ////////////////////////////////
		gl.glNormalPointer(GL10.GL_FLOAT, 0, normalBuffer);
		//////////////////////// NEW ////////////////////////////////

		if(textureEnabled) {
			gl.glTexCoordPointer(2, GL10.GL_FLOAT,0,texcoordBuffer);
			gl.glBindTexture(GL10.GL_TEXTURE_2D, textures[0]);
		}

		gl.glDrawElements(GL10.GL_TRIANGLES, numFaceIndexs, GL10.GL_UNSIGNED_SHORT, indexBuffer);

		// Disable the vertices buffer.
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		if(textureEnabled) gl.glDisableClientState(GL10.GL_TEXTURE_COORD_ARRAY);

		//////////////////////// NEW ////////////////////////////////
		gl.glDisableClientState(GL10.GL_NORMAL_ARRAY);
		//////////////////////// NEW ////////////////////////////////
	}
}
