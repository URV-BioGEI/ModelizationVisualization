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
		// TODO: has de ficar aqui el codi!
	}
	
	public void defineGlViewport(int x, int y, int width, int height) {
		// TODO: has de ficar aqui el codi!
	}
	
	public void defineGluPerspective(float fovy, float aspect, float zNear, float zFar) {
		// TODO: has de ficar aqui el codi!
	}
	
	public void defineGlLoadIdentity() {
		// TODO: has de ficar aqui el codi!
	}
	
	public void defineGlPushMatrix() {
		// TODO: has de ficar aqui el codi!
	}
	
	public void defineGlPopMatrix() {
		// TODO: has de ficar aqui el codi!
	}
	
	public void defineGlTranslatef(float x, float y, float z) {
		// TODO: has de ficar aqui el codi!
	}
	
	public void defineGlScalef(float x, float y, float z) {
		// TODO: has de ficar aqui el codi!
	}
	
	public void defineGlRotatef(float angle, float x, float y, float z) {
		// TODO: has de ficar aqui el codi!
	}
	
	public void defineGlBeginPolygon() {
		// TODO: has de ficar aqui el codi!
	}
	
	public void defineGlVertex3f(float x, float y, float z) {
		// TODO: has de ficar aqui el codi!
	}
	
	public void defineGlEndPolygon() {
		// TODO: has de ficar aqui el codi!
	}
	
	// Fi codi de l'alumne
	
	public static void main(String[] args) {
		Laboratori3 example = new Laboratori3();
		example.run();
	}

}
