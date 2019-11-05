package cat.urv.deim.gc.laboratoris;

import cat.urv.deim.miv.Application;

public class Laboratori3 extends Application {

	public static final long serialVersionUID = 1L;
	
	private int angle;
	
	public Laboratori3() {
		super("Laboratori 3");
	}

	private void drawRectangle() {
		glBeginPolygon();
		glVertex3f(-0.5f, -0.5f, 0.0f);
		glVertex3f(-0.5f,  0.5f, 0.0f);
		glVertex3f( 0.5f,  0.5f, 0.0f);
		glVertex3f( 0.5f, -0.5f, 0.0f);		
		glEndPolygon();
	}
	
	public void paint() {
		int width = getPanelWidth();
		int height = getPanelHeight();
		float aspect = width / (float) height;
	
		glViewport(0, 0, width, height);
		
		setColor(1.0f, 0.0f, 0.0f);
	
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(60.0f, aspect, 0.1f, 1000.0f);
		
		glMatrixMode(GL_MODELVIEW);
		glLoadIdentity();
		glTranslatef(0.0f, 0.0f, -5.0f);
		
		glPushMatrix();
		int midAngle = angle % 200;
		if (midAngle > 100)
			midAngle = 200 - midAngle;
		glTranslatef(0.0f, 0.0f, midAngle * -0.1f);
		setColor(0.0f, 1.0f, 0.0f);
		drawRectangle();
		glPopMatrix();
		
		glRotatef(angle, 0.0f, 1.0f, 0.0f);

		glPushMatrix();
		glRotatef(angle, 0.0f, 0.0f, 1.0f);
		glTranslatef(-2.0f, 0.0f, 0.0f);
		setColor(1.0f, 0.0f, 0.0f);
		drawRectangle();
		glPopMatrix();
		
		glPushMatrix();
		glRotatef(2.0f * angle, 0.0f, 0.0f, 1.0f);
		glTranslatef( 1.0f, 0.0f, 0.0f);
		setColor(0.0f, 0.0f, 1.0f);
		drawRectangle();
		glPopMatrix();
		
		angle += 5.0f;
	}


	// Practica 3, implementa infraestructura de matrius OpenGL
	// Hint: Pots mirar l'API d'OpenGL

	// Inici codi de l'alumne
	
	public void defineGlMatrixMode(int model) {
		GL.setModel(model);
	}
	
	public void defineGlViewport(int x, int y, int width, int height) {
		GL.setViewport(x, y, width, height);
	}
	
	public void defineGluPerspective(float fovy, float aspect, float zNear, float zFar) {
		GL.gluPerspective(fovy, aspect, zNear, zFar);
	}
	
	public void defineGlLoadIdentity() {
		GL.loadIdentity();
	}
	
	public void defineGlPushMatrix() {
		GL.pushMatrix();
	}
	
	public void defineGlPopMatrix() {
		GL.popMatrix();
	}
	
	public void defineGlTranslatef(float x, float y, float z) {
		GL.translate(x, y, z);
	}
	
	public void defineGlScalef(float x, float y, float z) {
		GL.scale(x, y, z);
	}
	
	public void defineGlRotatef(float angle, float x, float y, float z) {
		GL.rotatef(angle, x, y, z);
	}
	
	public void defineGlBeginPolygon() {
		GL.beginPolygon();
	}
	
	public void defineGlVertex3f(float x, float y, float z) {
		GL.vector3f(x, y, z);
	}
	
	public void defineGlEndPolygon() {
		fillPolygon(GL.endPolygon());
	}
	
	// Fi codi de l'alumne
	
	public static void main(String[] args) {
		GL.init();
		Laboratori3 example = new Laboratori3();
		example.run();
	}

}
