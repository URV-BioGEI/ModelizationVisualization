package cat.urv.deim.miv.laboratoris;

import cat.urv.deim.miv.Application;

import java.nio.file.attribute.UserDefinedFileAttributeView;

public class Laboratori1 extends Application {

	public static final long serialVersionUID = 1L;
	
	public Laboratori1() {
		super("Laboratori 1");
	}

	public void paint() {
		//defineDrawLine(0, 0, 100, 100);
		//defineDrawLine(200, 200, 600, 700);
		defineDrawLine(200, 200, 200, 300);  // vertical
		defineDrawLine(200, 300, 200, 200);  // vertical inversa

		defineDrawLine(200, 200, 400, 200);  // horitzontal
		defineDrawLine(500, 300, 300, 300);  // horitzontal inversa

		defineDrawLine(100, 100, 200, 200);  // m = 1
		defineDrawLine(400, 400, 300, 300);  // m = 1 inversa


		//defineDrawLine(0, 0, -15, -16);
		//defineDrawLine(2, 20, 1, -7);
		//defineDrawLine(-24, 20, 46, -71);


		/*int width = getPanelWidth();
		int height = getPanelHeight();
		
		int nlines = 10;
		int curWidth = 0;
		int curHeight = 0;

		for(int i = 0; i < nlines; i++) {
			setColor((float) i / nlines, 1.0f - (float) i / nlines, 0.0f);
			drawLine(0, curHeight, width - curWidth, 0);
			curWidth = width * i / nlines;
			curHeight = height * i / nlines;
		} */

	}

	// Practica 1, implementa defineDrawLine
	// Hint: Pots utilitzar l'algorisme de Bresenham
	// Hint: Per dibuixar un punt a la pantalla utilitza el metode drawPoint(x, y);

	/**
	 * Bresenham Algorithm to draw lines
	 * @author Aleix Mariné Tena
 	 * @param x1 x coordinate origin point
	 * @param y1 y coordinate origin point
	 * @param x2 x coordinate destination point
	 * @param y2 y coordinate destination point
	 */
	public void defineDrawLine(int x1, int y1, int x2, int y2) {
		// displacement for each axis
		int dY = y2 - y1;
		int dX = x2 - x1;

		// Obtain dY and dX as module
		// Locate line in one of the four quadrants
		int IncYi, IncXi;
		if (dY >= 0) IncYi = 1;
		else
		{
			dY = -dY;
			IncYi = -1;
		}

		if (dX >= 0) IncXi = 1;
		else
		{
			dX = -dX;
			IncXi = -1;
		}

		// Locate which axis needs to be increased by 1 all the time
		int IncXr, IncYr;
		if (dX >= dY)
		{
			IncYr = 0;
			IncXr = IncXi;
		}
		else
		{
			IncXr = 0;
			IncYr = IncYi;

			// When dY > dX we swap them to only use one loop
			int tmp = dX;
			dX = dY;
			dY = tmp;
		}

		// Initialize values to handle decimal error with integers
		int X = x1, Y = y1, avR = 2 * dY, av = avR - dX, avI = av - dX;

		// Line draw
		do {
			drawPoint(X, Y);
			if (av >= 0)
			{
				X += IncXi;
				Y += IncYi;
				av += avI;
			}
			else
			{
				X += IncXr;
				Y += IncYr;
				av += avR;
			}
		} while (X != x2 || Y != y2);
	}

	// Fi codi de l'alumne
	
	public static void main(String[] args) {
		Laboratori1 example = new Laboratori1();
		example.run();
	}

}
