package cat.urv.deim.miv.laboratoris;

import cat.urv.deim.miv.Application;

public class Laboratori1 extends Application {

	public static final long serialVersionUID = 1L;
	
	public Laboratori1() {
		super("Laboratori 1");
	}

	public void paint() {
		int width = getPanelWidth();
		int height = getPanelHeight();
		
		int nlines = 10;
		int curWidth = 0;
		int curHeight = 0;

		for(int i = 0; i < nlines; i++) {
			setColor((float) i / nlines, 1.0f - (float) i / nlines, 0.0f);
			drawLine(0, curHeight, width - curWidth, 0);
			curWidth = width * i / nlines;
			curHeight = height * i / nlines;
		}

	}

	// Practica 1, implementa defineDrawLine
	// Hint: Pots utilitzar l'algorisme de Bresenham
	// Hint: Per dibuixar un punt a la pantalla utilitza el metode drawPoint(x, y);
	
	// Inici codi de l'alumne
	
	public void defineDrawLine(int x1, int y1, int x2, int y2) {
		// TODO: has de ficar aqui el codi!

	}

	// Fi codi de l'alumne
	
	public static void main(String[] args) {
		Laboratori1 example = new Laboratori1();
		example.run();
	}

}
