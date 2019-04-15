package ecosistemas_taller1;

import java.awt.Image;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Ficha {
	private PApplet app;
	private int x;
	private int y;
	static PImage coral1, coral2, roca, vacia, nemo, explot;
	static Animacion bomba;
	static boolean cargaImagen;
	public PImage ficha;
	public static int width;
	public static int height;
	private int fichaCont;
	private boolean boom, boomTime;
	private int tipo;
	private Personaje p;

	public Ficha(PApplet app, int x, int y, int tipo) {
		this.app = app;
		this.x = x;
		this.y = y;

		width = app.width / Mapa.nColumnas;
		height = app.height / Mapa.nFilas;
this.tipo = tipo;
		cargarTipo(tipo);

	}

	public void pintar() {
		app.fill(255, 0, 0);
		
		if(tipo != 4) {
			app.imageMode(PConstants.CORNER);
			app.image(ficha, x, y);
		}else {
			app.pushMatrix();
			app.translate(0, -20);
			bomba.pintar(x, y);
			app.popMatrix();
		}
		

		if (boom) {
			fichaCont++;
			//System.out.println(fichaCont);
		}
		if (boomTime) {
			fichaCont++;
		}

		if (fichaCont > 120 && boomTime) {
			boomTime = false;
			fichaCont = 0;

			 Mapa.setPositionMatrix(this.getMatrixX(), this.getMatrixY(), 3);

		}

		if (fichaCont > 120 && boom) {
			boom = false;
			fichaCont = 0;
			Mapa.setPositionMatrix(this.getMatrixX(), this.getMatrixY(), 3);

			int left = Mapa.getPositionMatrix(this.getMatrixX() - 1, this.getMatrixY());
			int right = Mapa.getPositionMatrix(this.getMatrixX() + 1, this.getMatrixY());
			int up = Mapa.getPositionMatrix(this.getMatrixX(), this.getMatrixY() - 1);
			int down = Mapa.getPositionMatrix(this.getMatrixX(), this.getMatrixY() + 1);
			if (left != 0) {
				Mapa.setPositionMatrix(this.getMatrixX() - 1, this.getMatrixY(), 5);
				this.sumatoria(left);
			}
			if (right != 0) {
				Mapa.setPositionMatrix(this.getMatrixX() + 1, this.getMatrixY(), 5);
				this.sumatoria(right);
			}
			if (up != 0) {
				Mapa.setPositionMatrix(this.getMatrixX(), this.getMatrixY() - 1, 5);
				this.sumatoria(up);
			}
			if (down != 0) {
				Mapa.setPositionMatrix(this.getMatrixX(), this.getMatrixY() + 1, 5);
				this.sumatoria(down);

			}
			
			//System.out.println("sdsdsdsdsdsds");
		}
	}
	
	private void sumatoria(int direccion) {
		
		if(p != null) {
			 if( direccion == 1) {
				
				p.sumarPuntos(30);
			}else if(direccion == 2) {
				
				p.sumarPuntos(50);
			}
			System.out.println(p.getPuntuacion());
		} 
		
	}

	public void cargarTipo(int tipo) {
		this.tipo = tipo;

		if (cargaImagen == false) {
			coral1 = app.loadImage("coral1.png");
			coral2 = app.loadImage("coral2.png");
			roca = app.loadImage("roca.png");
			vacia = app.loadImage("ficha_vacia.png");
			bomba = new Animacion(app, "bomba/b", 29, x, y);
			nemo = app.loadImage("merlin0.png");
			explot = app.loadImage("explosion.png");
			cargaImagen = true;
		}

		switch (tipo) {
		case 0:
			this.ficha = roca;
			break;

		case 1:
			this.ficha = coral1;
			break;

		case 2:
			this.ficha = coral2;
			break;

		case 4:
			this.boom = true;
			break;

		case 5:
			this.ficha = explot;
			boomTime = true;
			break;
			
		case 6:
			this.ficha = nemo;
			break;

		default:
			this.ficha = vacia;
			break;
		}

	}
	


	public void setPersonaje(Personaje p) {
		this.p = p;
	}

	public int getTipo() {
		return tipo;
	}

	public int getMatrixX() {
		int pos = 0;
		pos = (int) ((x) / Ficha.width) + 1;

		return pos;
	}

	public int getMatrixY() {
		int pos = 0;
		pos = (int) ((y) / Ficha.height) + 1;

		return pos;
	}

}
