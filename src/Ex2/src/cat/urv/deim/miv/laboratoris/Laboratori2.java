package cat.urv.deim.miv.laboratoris;

import cat.urv.deim.miv.Application;

import java.util.*;

public class Laboratori2 extends Application {

	public static final long serialVersionUID = 1L;
	
	public Laboratori2() {
		super("Laboratori 2");
	}

	public void paint() {
		int width = getPanelWidth();
		int height = getPanelHeight();

		//Poligon exemple
		/*setColor(1.0f, 0.0f, 0.0f);
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
	*/
		// doble piramide caqui
		setColor(0.3f, 0.4f, 0.1f);
		drawPolygon(
				(int) (0.6f * width), (int) (0.3f * height),
				(int) (0.7f * width), (int) (0.4f * height),
				(int) (0.8f * width), (int) (0.3f * height),
				(int) (0.9f * width), (int) (0.4f * height),
				(int) (0.5f * width), (int) (0.4f * height)

		);
		setColor(0.3f, 0.4f, 0.1f);
		fillPolygon(
				(int) (0.6f * width), (int) (0.3f * height),
				(int) (0.7f * width), (int) (0.4f * height),
				(int) (0.8f * width), (int) (0.3f * height),
				(int) (0.9f * width), (int) (0.4f * height),
				(int) (0.5f * width), (int) (0.4f * height)
		);

		// poligon punxegut
		setColor(0.2f, 0.3f, 1.0f);
		drawPolygon(
				(int) (0.1f * width), (int) (0.3f * height),
				(int) (0.2f * width), (int) (0.4f * height),
				(int) (0.3f * width), (int) (0.3f * height),
				(int) (0.4f * width), (int) (0.4f * height),
				(int) (0.3f * width), (int) (0.5f * height),
				(int) (0.2f * width), (int) (0.45f * height),
				(int) (0.1f * width), (int) (0.5f * height),
				(int) (0.0f * width), (int) (0.4f * height)
		);
		setColor(0.1f, 0.4f, 1.0f);
		fillPolygon(
				(int) (0.1f * width), (int) (0.3f * height),
				(int) (0.2f * width), (int) (0.4f * height),
				(int) (0.3f * width), (int) (0.3f * height),
				(int) (0.4f * width), (int) (0.4f * height),
				(int) (0.3f * width), (int) (0.5f * height),
				(int) (0.2f * width), (int) (0.45f * height), // Si es canvia la component y per 0.4 per fer una linea diagonal el bug canvia
				(int) (0.1f * width), (int) (0.5f * height),
				(int) (0.0f * width), (int) (0.4f * height)
		);



		// rombe verd clar
		setColor(0.6f, 0.f, 1.0f);
		drawPolygon(
				(int) (0.4f * width), (int) (0.1f * height),
				(int) (0.5f * width), (int) (0.2f * height),
				(int) (0.4f * width), (int) (0.3f * height),
				(int) (0.3f * width), (int) (0.2f * height)

				);
		setColor(0.2f, 0.9f, 0.5f);
		fillPolygon(
				(int) (0.4f * width), (int) (0.1f * height),
				(int) (0.5f * width), (int) (0.2f * height),
				(int) (0.4f * width), (int) (0.3f * height),
				(int) (0.3f * width), (int) (0.2f * height)
		);

		// rombe gris
		setColor(0.6f, 0.f, 1.0f);
		drawPolygon(
				(int) (0.9f * width), (int) (0.1f * height),
				(int) (1.0f * width), (int) (0.2f * height),
				(int) (0.9f * width), (int) (0.3f * height),
				(int) (0.8f * width), (int) (0.2f * height)

		);
		setColor(0.3f, 0.3f, 0.3f);
		fillPolygon(
				(int) (0.9f * width), (int) (0.1f * height),
				(int) (1.0f * width), (int) (0.2f * height),
				(int) (0.9f * width), (int) (0.3f * height),
				(int) (0.8f * width), (int) (0.2f * height)
		);

		// quadradet blau cel
		setColor(0.6f, 1.0f, 1.0f);
		drawPolygon(
				(int) (0.1f * width), (int) (0.1f * height),
				(int) (0.1f * width), (int) (0.2f * height),
				(int) (0.2f * width), (int) (0.2f * height),
				(int) (0.2f * width), (int) (0.1f * height)
		);
		setColor(0.6f, 1.0f, 1.0f);
		fillPolygon(
				(int) (0.1f * width), (int) (0.1f * height),
				(int) (0.1f * width), (int) (0.2f * height),
				(int) (0.2f * width), (int) (0.2f * height),
				(int) (0.2f * width), (int) (0.1f * height)
		);

		// quadradet blau marí regular al costat
		setColor(0.6f, 0.5f, 1.0f);
		drawPolygon(
				(int) (0.6f * width), (int) (0.1f * height),
				(int) (0.6f * width), (int) (0.1f * height),
				(int) (0.7f * width), (int) (0.2f * height),
				(int) (0.7f * width), (int) (0.1f * height)
		);
		setColor(0.1f, 0.1f, 0.9f);
		fillPolygon(
				(int) (0.6f * width), (int) (0.1f * height),
				(int) (0.6f * width), (int) (0.2f * height),
				(int) (0.7f * width), (int) (0.2f * height),
				(int) (0.7f * width), (int) (0.1f * height)
		);

		// quadradet irregular
		/*
		setColor(0.6f, 0.5f, 1.0f);
		drawPolygon(
				(int) (0.3f * width), (int) (0.3f * height),
				(int) (0.3f * width), (int) (0.5f * height),
				(int) (0.4f * width), (int) (0.4f * height),
				(int) (0.4f * width), (int) (0.3f * height)
		);
		fillPolygon(
				(int) (0.3f * width), (int) (0.3f * height),
				(int) (0.3f * width), (int) (0.5f * height),
				(int) (0.4f * width), (int) (0.4f * height),
				(int) (0.4f * width), (int) (0.3f * height)
		);
		*/

		// Poligon porpra irregular (Amb diverses interseccions per cada linia d'escombratge)
		setColor(0.0f, 0.0f, 1.0f);
		drawPolygon(
				(int) (0.9f * width), (int) (0.9f * height),
				(int) (0.1f * width), (int) (0.9f * height),
				(int) (0.1f * width), (int) (0.6f * height),
				(int) (0.3f * width), (int) (0.6f * height),
				(int) (0.3f * width), (int) (0.7f * height),
				(int) (0.3f * width), (int) (0.7f * height),
				(int) (0.4f * width), (int) (0.7f * height),
				(int) (0.4f * width), (int) (0.5f * height),
				(int) (0.7f * width), (int) (0.5f * height)
				);
		setColor(0.9f, 0.0f, 1.0f);
		fillPolygon(
				(int) (0.9f * width), (int) (0.9f * height),
				(int) (0.1f * width), (int) (0.9f * height),
				(int) (0.1f * width), (int) (0.6f * height),
				(int) (0.3f * width), (int) (0.6f * height),
				(int) (0.3f * width), (int) (0.7f * height),
				(int) (0.3f * width), (int) (0.7f * height),
				(int) (0.4f * width), (int) (0.7f * height),
				(int) (0.4f * width), (int) (0.5f * height),
				(int) (0.7f * width), (int) (0.5f * height)
		);


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
		try
		{
			if (p.length < 6 || p.length % 2 == 1)  // Esperem punts a R2 que defineixin com a minim un triangle
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
		 * POST: Obtenim requadre englobant del poligon a coordinada y i creem llista de vertex i arestes
		 * on els vertex comencen amb la component més baixa (\/ vertex a vertexos; vertex.v1.Y < vertex.v2.Y)
		 */
		List<Vertex> vertexos = new LinkedList<>();
		List<Aresta> arestes = new LinkedList<>();
		int ymin = Integer.MAX_VALUE, ymax = Integer.MIN_VALUE;
		Vertex vertexTmp = null;
		for (int i = 0; i < p.length; i += 2)
		{
			Vertex currentVertex = new Vertex(p[i], p[i + 1]);
			vertexos.add(currentVertex);

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
				arestes.add(currentAresta);
			}
			vertexTmp = currentVertex;
		}
		arestes.add(new Aresta(vertexTmp, vertexos.get(0)));

		/*
		* PRE: POST previ
		* POST: Les coordenades x resultants de la intersecció amb les arestes actives s'ordenen i es pinten de dos en dos.
		 */
		for (int y = ymin; y < ymax; y++)
		{
			List<Integer> interseccionsX = new LinkedList<>();

			for (Aresta tmpAresta : arestes) {
				int interseccioX = tmpAresta.intersectar(y);
				if (interseccioX > -1 && !interseccionsX.contains(interseccioX)) interseccionsX.add(interseccioX);
			}
			//if (interseccionsX.size() != 2) System.out.println("Aqui hi ha dif de 2: " + interseccionsX);  //RF

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
		Laboratori2 example = new Laboratori2();
		example.run();
	}

}
