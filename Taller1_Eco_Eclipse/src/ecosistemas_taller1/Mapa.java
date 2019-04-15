package ecosistemas_taller1;

import java.awt.Image;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Mapa {
	private PApplet app;
	private int width, height;
	static int nFilas, nColumnas;
	private static Ficha[] fichas;
	private int[] tipo;
	private PImage fondo;
	private boolean ganador;
	private ArrayList<Personaje> personajes;

	public Mapa(PApplet app) {
		this.app = app;
		
		this.nColumnas = 13;
		this.nFilas = 7;

		this.width = app.width / nColumnas;
		this.height = app.height / nFilas;

		this.fichas = new Ficha[nColumnas * nFilas];

		int espacioX = 0;
		int espacioY = 0;
		fondo = app.loadImage("fondo.png");

		cargarMatrix();
		for (int i = 0; i < fichas.length; i++) {

			fichas[i] = new Ficha(app, (i - espacioX) * width, espacioY, tipo[i]);

			if ((i + 1) % 13 == 0) {
				espacioY += height;
				espacioX += nColumnas;
			}
		}

		personajes = new ArrayList<Personaje>();

		/*
		 * merlin = new Merlin(app, Ficha.width * 0, Ficha.height * 6); dori = new
		 * Dori(app, Ficha.width * 12, Ficha.height * 6); chiqui = new Chiqui(app,
		 * Ficha.width * 6, Ficha.height * 6);
		 */
	}

	public void pintar() {
		app.imageMode(PConstants.CORNER);
		app.image(fondo, 0, 0);
		for (int i = 0; i < fichas.length; i++) {

			Ficha f = fichas[i];
			f.pintar();
		}
		for (int i = 0; i < personajes.size(); i++) {
			Personaje p = personajes.get(i);
			if(p.getVidas() > 0) {
				p.pintar();
				p.colision();
			}
			
			if(getPositionMatrix(p.getMatrixX(), p.getMatrixY()) == 6 && this.ganador == false){
				p.gano();
				Logica.log.pantalla = 2;
				Collections.sort(personajes);
				this.ganador = true;
				int space = app.width/ personajes.size();
				for (int j = 0; j < personajes.size(); j++) {
					Personaje per = personajes.get(j);
					per.x = space*j;
					per.y = ((app.height/2) + 100);
				}
			}

		}

	}
	
	public void pintarP() {

		for (int i = 0; i < personajes.size(); i++) {
			Personaje p = personajes.get(i);
			p.pintar();
		}

	}

	public void controles() {
		// merlin.mover();
		// dori.mover();
		// chiqui.mover();

	}

	public void controlesReleased() {
		// merlin.moverReleased();
		// dori.mover();
		// chiqui.mover();

	}

	public void cargarMatrix() {
		tipo = new int[] { 0, 1, 2, 1, 2, 0, 6, 0, 2, 1, 2, 1, 0, 3, 0, 1, 0, 3, 0, 1, 0, 2, 0, 1, 0, 3, 2, 1, 2, 0, 2,
				1, 2, 1, 2, 0, 2, 1, 2, 3, 0, 1, 0, 3, 0, 0, 0, 2, 0, 1, 0, 3, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 3,
				0, 1, 0, 3, 0, 3, 0, 3, 0, 1, 0, 3, 3, 3, 2, 1, 0, 1, 2, 1, 0, 1, 2, 3, 3 };
	}

	public static int getPositionMatrix(int x, int y) {
		int fila = 0;
		int val = -4;

		if (x <= nColumnas && x > 0 && y <= nFilas && y > 0) {

			fila = ((fichas.length / nFilas * (y - 1)) + (x - 1));
			val = fichas[fila].getTipo();
		}

		return val;

	}

	public static void setPositionMatrix(int x, int y, int c) {
		int fila = 0;
		int val = -4;

		if (x <= nColumnas && x > 0 && y <= nFilas && y > 0) {

			fila = ((fichas.length / nFilas * (y - 1)) + (x - 1));

			fichas[fila].cargarTipo(c);
		}
	}

	public static void setPositionMatrix(int x, int y, int c, Personaje p) {
		int fila = 0;
		int val = -4;

		if (x <= nColumnas && x > 0 && y <= nFilas && y > 0) {

			fila = ((fichas.length / nFilas * (y - 1)) + (x - 1));
			fichas[fila].setPersonaje(p);
			fichas[fila].cargarTipo(c);
		}
	}

	public static int getPosition(int x, int y) {
		int fila = 0;

		if (x <= nColumnas && x > 0 && y <= nFilas && y > 0) {

			fila = ((fichas.length / nFilas * (y - 1)) + (x - 1));
		}

		return fila;

	}
	
	public void añadirPersonaje(Personaje p) {
		personajes.add(p);
	}

}

