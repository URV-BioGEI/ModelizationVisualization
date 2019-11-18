package cat.urv.deim.gc.laboratoris;

import cat.urv.deim.miv.Application;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Laboratori3 extends Application {

	public static final long serialVersionUID = 1L;
	
	private int angle;
	
	public Laboratori3() {
		super("Laboratori 3");
	}

	private void drawTriangle() {
		glBeginPolygon();
		glVertex3f(-0.5f, -0.5f, 0.0f);
		glVertex3f(-0.5f,  0.5f, 0.0f);
		glVertex3f( 0.5f,  0.5f, 0.0f);
		glEndPolygon();
	}

	private void drawRectangle() {
		glBeginPolygon();
		glVertex3f(-0.5f, -0.5f, 0.0f);
		glVertex3f(-0.5f,  0.5f, 0.0f);
		glVertex3f( 0.5f,  0.5f, 0.0f);
		glVertex3f( 0.5f, -0.5f, 0.0f);
		glEndPolygon();
	}

	private void drawDoubleConvexDiamond()
	{
		glBeginPolygon();
		glVertex3f(0.1f, 0.3f, 0.0f);
		glVertex3f(0.2f, 0.44f, 0.0f);
		glVertex3f(0.3f, 0.3f, 0.0f);
		glVertex3f(0.4f, 0.42f, 0.0f);
		glVertex3f(0.3f, 0.52f, 0.0f);
		glVertex3f(0.3f, 0.54f, 0.0f);
		glVertex3f(0.2f, 0.45f, 0.0f);
		glVertex3f(0.1f, 0.55f, 0.0f);
		glVertex3f(0.0f, 0.41f, 0.0f);
		glEndPolygon();
	}

	private void drawIrregularPolygon()
	{
		glBeginPolygon();
		glVertex3f(0.1f, 0.3f, 0.0f);
		glVertex3f(0.2f, 0.4f, 0.0f);
		glVertex3f(0.3f, 0.3f, 0.0f);
		glVertex3f(0.4f, 0.4f, 0.0f);
		glVertex3f(0.3f, 0.5f, 0.0f);
		glVertex3f(0.3f, 0.52f, 0.0f);
		glVertex3f(0.2f, 0.45f, 0.0f);
		glVertex3f(0.1f, 0.5f, 0.0f);
		glVertex3f(0.0f, 0.42f, 0.0f);
		glEndPolygon();
	}

	private void drawPentagon() {
		glBeginPolygon();
		glVertex3f(-0.5f, -0.5f, 0.0f);
		glVertex3f(-0.5f,  0.5f, 0.0f);
		glVertex3f(0.0f,  0.7f, 0.0f);
		glVertex3f( 0.5f,  0.5f, 0.0f);
		glVertex3f( 0.5f, -0.5f, 0.0f);
		glEndPolygon();
	}


	private void drawHoledPolygon()
	{
		glBeginPolygon();
		glVertex3f(0.1f, 0.1f, 0.0f);
		glVertex3f(0.1f, 0.5f, 0.0f);
		glVertex3f(0.5f, 0.5f, 0.0f);
		glVertex3f(0.5f, 0.1f, 0.0f);
		glVertex3f(-1.0f, -1.0f, 0.0f);
		glVertex3f(0.14f, 0.14f, 0.0f);
		glVertex3f(0.14f, 0.4f, 0.0f);
		glVertex3f(0.4f, 0.4f, 0.0f);
		glVertex3f(0.4f, 0.14f, 0.0f);
		glVertex3f(-1.0f, -1.0f, 0.0f);
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
		drawIrregularPolygon();
		glPopMatrix();

		glRotatef(angle, 0.0f, 1.0f, 0.0f);

		glPushMatrix();
		glRotatef(angle, 0.0f, 0.0f, 1.0f);
		glTranslatef(-2.0f, 0.0f, 0.0f);
		setColor(1.0f, 0.0f, 0.0f);
		drawDoubleConvexDiamond();
		glPopMatrix();

		glPushMatrix();
		glRotatef(2.0f * angle, 0.0f, 0.0f, 1.0f);
		glTranslatef( 1.0f, 0.0f, 0.0f);
		setColor(0.0f, 0.0f, 1.0f);
		drawHoledPolygon();
		glPopMatrix();

		glPushMatrix();
		glRotatef(5.0f * angle, 0.0f, 1.0f, 1.0f);
		glTranslatef( 1.0f, 0.0f, 0.0f);
		setColor(1.0f, 0.0f, 1.0f);
		drawTriangle();
		glPopMatrix();

		glPushMatrix();
		glRotatef(1.0f * angle, 1.0f, 0.0f, 1.0f);
		glScalef(1.0f, 1.0f, 1.0f);
		glTranslatef( 1.0f, 1.0f, 1.0f);
		setColor(0.0f, 1.0f, 1.0f);
		drawPentagon();
		glPopMatrix();

		glPushMatrix();
		glRotatef(angle, 1.0f, 2.0f, 1.0f);
		glTranslatef(-2.0f, 1.0f, -3.0f);
		setColor(1.0f, 1.0f, 0.0f);
		drawRectangle();
		glPopMatrix();

		angle += 3.0f;
	}


	// Practica 3, implementa infraestructura de matrius OpenGL
	// Hint: Pots mirar l'API d'OpenGL

	// Inici codi de l'alumne

	public void defineGlMatrixMode(int model) {
		GL.setCurrentStack(model);
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
		Integer[] list = GL.endPolygon();
		defineDrawHoledPolygon(list);
		defineFillHoledPolygon(list);
	}

	public void defineDrawHoledPolygon(Integer... p)
	{
		try  // Ens passen com a mínim un triangle a R2
		{
			if (p.length % 2 == 1 || p.length < 6) throw new NumberFormatException();
			int i = 0;
			while (i < p.length)
			{
				VectorR2 primerVertex = new VectorR2(p[i], p[i + 1]);
				VectorR2 anteriorVertex = primerVertex;
				VectorR2 currentVertex;
				i += 2;
				for (; i < p.length; i += 2)
				{
					if (p[i] == -1 && p[i + 1] == -1)
					{
						i += 2;  // Skip two positions corresponding to scape signal
						break; // Escape with (-1, -1) signal
					}
					currentVertex = new VectorR2(p[i], p[i + 1]);
					drawLine(anteriorVertex.getX(), anteriorVertex.getY(), currentVertex.getX(), currentVertex.getY());
					anteriorVertex = currentVertex;
				}
				drawLine(primerVertex.getX(), primerVertex.getY(), anteriorVertex.getX(), anteriorVertex.getY());
			}

		}
		catch (NumberFormatException e)
		{
			System.out.println("Format incorrecte. Abortant...");
		}
	}

	/*
	 * PRE (forats): Es rep una llista d’enters que representen la posició dels vèrtex d’un conjunt de polígons. El
	 * primer polígon és el polígon extern i la resta de polígons són polígons interns que representen forats continguts
	 * en el polígon extern. S’indica el fi d’un polígon amb el sentinella (-1, -1). L’únic polígon que té el sentinella
	 *  com opcional és l’últim polígon indicat.”
	 * POST: Es pinta el polígon tenint en compte els forats.
	 */
	public void defineFillHoledPolygon(Integer... p) {
		try  // Ens passen com a mínim un triangle a R2
		{
			if (p.length < 6 || p.length % 2 == 1)  throw new NumberFormatException();
		}
		catch (NumberFormatException e)
		{
			System.out.println("Format incorrecte. Abortant...");
			return;
		}

		/*
		 * PRE: ens passen per parametre 3 punts o més
		 * POST: Obtenim requadre englobant del poligon a coordinada y i creem llista de vertex i arestes
		 * on els vertex comencen amb la component més baixa (\/ vertex a vertexos; vertex.v1.Y < vertex.v2.Y)
		 */
		List<List<Aresta>> arestes = new LinkedList<>();

		int ymin = Integer.MAX_VALUE, ymax = Integer.MIN_VALUE;

		int i = 0;
		while (i < p.length)
		{
			List<Aresta> arestesTmp = new LinkedList<>();
			VectorR2 primerVertex = new VectorR2(p[i], p[i + 1]);
			VectorR2 vertexTmp = primerVertex;
			if (p[i + 1] < ymin)
			{
				ymin = p[i + 1];
			}
			if (p[i + 1] > ymax)
			{
				ymax = p[i + 1];
			}
			i += 2;

			for (; i < p.length; i += 2)
			{
				if (p[i] == -1 && p[i + 1] == -1)
				{
					i += 2;  // Move index two more positions
					break; // Escape with (-1, -1) signal
				}

				VectorR2 currentVertex = new VectorR2(p[i], p[i + 1]);

				if (p[i + 1] < ymin)
				{
					ymin = p[i + 1];
				}
				if (p[i + 1] > ymax)
				{
					ymax = p[i + 1];
				}
				Aresta currentAresta = new Aresta(vertexTmp, currentVertex);
				arestesTmp.add(currentAresta);
				vertexTmp = currentVertex;
			}
			arestesTmp.add(new Aresta(vertexTmp, primerVertex));
			arestes.add(arestesTmp);
		}

		/*
		 * PRE: POST previ
		 * POST: Les coordenades x resultants de la intersecció amb les arestes actives s'ordenen i es pinten de dos en dos.
		 * POST2 (polígons en concaus i/o punxa): Si els tres valors de la coordinada y per als tres punts extrems que hi ha en dues arestes consecutives
		 * creix en la mateixa direcció, llavors el comptem com un sol punt. Sino, com dos. (depèn de Aresta.esConvex())
		 */
		List<Aresta> arestesFlat = arestes.stream().flatMap(List::stream).collect(Collectors.toList());
		for (int y = ymin; y < ymax; y++)
		{
			List<Integer> interseccionsX = new LinkedList<>();
			List<Aresta> arestesActives = new LinkedList<>();  // Per a recuperar una aresta si hi ha conflicte

			for (Aresta tmpAresta : arestesFlat)
			{
				int interseccioX = tmpAresta.intersectar(y);

				if (interseccioX > -1)
				{
					/*
					 * Si ja hem trobat una interseccio en x amb el mateix valor pot ser que s'hagi de:
					 * - Comptar com un sol punt si es tracta d'una doble intersecció causada per un vertex convex extern
					 * --> y1, y2, y3, component y dels punts vertex consecutius compleixen que y1 < y2 < y3 o bé y1 > y2 > y3 en aquesta situació
					 * - Comptar com dos punts, quan es una doble intersecció d'un vertex punxa en un vertex concau o intern
					 * --> y1, y2, y3, component y dels punts vertex consecutius compleixen que y1 > y2 < y3 o bé y1 < y2 > y3 en aquesta situació
					 */
					if (interseccionsX.contains(interseccioX))  // Si intersecció repetida, estudiem cas
					{
						Aresta arestaConflictiva = arestesActives.get(interseccionsX.indexOf(interseccioX));  // Obtenim l'aresta anterior que causa el conflicte
						if (!arestaConflictiva.esConvex(tmpAresta))  // Si no es convex guardem el punt actual a més del conflictiu que ja esta a la llista
						{
							interseccionsX.add(interseccioX);
							arestesActives.add(tmpAresta);
						}
						// L'aresta conflictiva esta formada necessariament per una component v1 i una v2 de l'altra aresta degut al ordre que apliquem
					}
					else
					{
						interseccionsX.add(interseccioX);
						arestesActives.add(tmpAresta);
					}
				}
			}
			Comparator<Integer> order = Integer::compare;
			interseccionsX.sort(order.reversed());
			int Xprevia = -1;
			for (Integer interseccioActualX : interseccionsX)
			{
				if (Xprevia == -1) Xprevia = interseccioActualX;
				else
				{
					this.drawLine(Xprevia, y, interseccioActualX, y);
					Xprevia = -1;
				}
			}
		}
	}
	// Fi codi de l'alumne
	
	public static void main(String[] args) {
		GL.init();
		Laboratori3 example = new Laboratori3();
		example.run();
	}

}
