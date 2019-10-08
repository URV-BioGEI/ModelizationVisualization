package cat.urv.deim.miv.laboratoris;

import cat.urv.deim.miv.Application;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Laboratori2 extends Application {

	public static final long serialVersionUID = 1L;
	
	public Laboratori2() {
		super("Laboratori 2");
	}

	public void paint() {
		int width = getPanelWidth();
		int height = getPanelHeight();

		setColor(1.0f, 0.0f, 0.0f);
		fillPolygon(
				(int) (0.2f * width), (int) (0.6f * height),
				(int) (0.3f * width), (int) (0.1f * height),
				(int) (0.8f * width), (int) (0.4f * height),
				(int) (0.7f * width), (int) (0.7f * height),
				(int) (0.4f * width), (int) (0.9f * height));

		setColor(0.0f, 1.0f, 0.0f);
		drawPolygon(
				(int) (0.2f * width), (int) (0.6f * height),
				(int) (0.3f * width), (int) (0.1f * height),
				(int) (0.8f * width), (int) (0.4f * height),
				(int) (0.7f * width), (int) (0.7f * height),
				(int) (0.4f * width), (int) (0.9f * height));
	}


	// Practica 2, implementa defineDrawPolygon i defineFillPolygon
	// Hint: Pots utilitzar l'algorisme Scan line fill polygon
	
	// Inici codi de l'alumne
	/*
	* PRE: Ens passen per paràmetre un array de mida 2p on p es el nombre de punts d'un polígon tancat
	* POST: Dibuixem p linies entre cada parell de punts que ens passen
	 */
	public void defineDrawPolygon(Integer... p) {
		try
		{
			if (p.length % 2 == 1) throw new NumberFormatException();
			int numPoints = p.length / 2;

			int x1 = p[0], y1 = p[1], x2, y2;
			for (int i = 2; i < numPoints * 2; i += 2)
			{
				x2 = p[i];
				y2 = p[i + 1];
				defineDrawLine(x1, y1, x2, y2);
				x1 = x2;
				y1 = y2;
			}
			defineDrawLine(x1, y1, p[0], p[1]);
		}
		catch (NumberFormatException e)
		{
			System.out.println("\nNombre de components imparell. Abortant...");
		}
	}

	public void defineFillPolygon(Integer... p) {
		List<Vertex> vertex = new LinkedList<Vertex>();
		List<Aresta> arestas = new LinkedList<Aresta>();

		int ymin = Integer.MAX_VALUE;
		int ymax = Integer.MIN_VALUE;
		Vertex vertexTmp = null;

		try{
			if (p.length < 6 || p.length % 2 == 1)
			{
				throw new NumberFormatException();
			}
		}
		catch (NumberFormatException e)
		{
			System.out.println("Format incorrecte. Abortant...");
			return;
		}
		/*
		 * PRE: ens passen per parametre 3 punts o més
		 * POST: Obtenim requadre englobant del poligon i llista de vertex i arestes. On els vertex comencen amb la component més baixa.
		 */
		for (int i = 0; i < p.length; i += 2)
		{
			Vertex currentVertex = new Vertex(p[i], p[i + 1]);
			vertex.add(currentVertex);

			if (p[i + 1] < ymin )
			{
				ymin = p[i + 1];
			}
			if (p[i + 1] > ymax)
			{
				ymax = p[i + 1];
			}
			if (vertexTmp != null)
			{
				Aresta currentAresta = new Aresta(vertexTmp, currentVertex);
				arestas.add(currentAresta);
			}
			vertexTmp = currentVertex;
		}
		arestas.add(new Aresta(vertexTmp, vertex.get(0)));

		/*
		* PRE: Previous POST
		* POST: Get the X component of the intersections with active edges (arestas) and paint
		 */
		for (int y = ymin; y < ymax; y++)
		{
			List<Integer> interseccionsX = new LinkedList<>();

			for (Aresta tmpAresta : arestas) {
				int interseccioX = tmpAresta.intersectar(y);
				if (interseccioX > -1) interseccionsX.add(interseccioX);
			}

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
		Laboratori2 example = new Laboratori2();
		example.run();
	}

}
