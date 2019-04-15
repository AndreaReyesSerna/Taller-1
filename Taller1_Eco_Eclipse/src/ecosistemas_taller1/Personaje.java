package ecosistemas_taller1;

import processing.core.PApplet;
import processing.core.PConstants;
import server.Observer;
import server.Receptor;

public abstract class Personaje implements Observer, Comparable<Personaje> {
	protected PApplet app;
	protected int x;
	protected int y;
	protected int posActual;
	protected boolean enMovimiento, up, down, right, left;
	private int lapso;
	private int vel;
	private int height, width;
	private int vidas;
	protected int contVidas;
	private int puntuacion;
	public int estadar_puntuacion;
	protected Receptor receptor;

	public Personaje(PApplet app, int x, int y, Receptor receptor) {
		this.app = app;
		this.vel = 5;
		this.x = x;
		this.y = y;
		this.vidas = 3;
		this.puntuacion = 0;
		this.receptor = receptor;
		this.receptor.setObservador(this);
		

	}

	public abstract void pintar();
	
	public void colision() {
		if(Mapa.getPositionMatrix(this.getMatrixX(), this.getMatrixY())== 5 && contVidas == 0) {
			contVidas = 1;
			restarVidar(1);
			
			System.out.println(vidas);
		}
		
		if(contVidas >=1 && contVidas < 160) {
			contVidas++;
		}else {
			
			contVidas = 0;
		}
		
	}

	public void mover() {
		if (enMovimiento == false) {
			int camino = 0;
			switch (app.keyCode) {

			case PConstants.UP:
				camino = Mapa.getPositionMatrix(this.getMatrixX(), this.getMatrixY()-1);
				//System.out.println(getMatrixY() + "  " + this.y);
				posActual = 0;
				lapso = y - Ficha.height;
				if ( camino == 3 || camino == 5){
				up = true;
				enMovimiento = true;
				}

				break;

			case PConstants.DOWN:
				camino = Mapa.getPositionMatrix(this.getMatrixX(), this.getMatrixY()+1);
				//System.out.println(getMatrixY() + "  " + this.y);
				posActual = 1;
				lapso = y + Ficha.height;
				
				if( camino == 3 || camino == 5){
					down = true;
					enMovimiento = true;
				}
				break;

			case PConstants.LEFT:
				camino = Mapa.getPositionMatrix(this.getMatrixX()-1, this.getMatrixY());
			
				posActual = 3;
				lapso = x - Ficha.width;
				if( camino == 3 || camino == 5){
				left = true;
				enMovimiento = true;
				}
				break;
				
			case PConstants.RIGHT:
				camino = Mapa.getPositionMatrix(this.getMatrixX()+1, this.getMatrixY());
				posActual = 2;
				lapso = x + Ficha.width;
				if( camino == 3 || camino == 5){
				right = true;
				enMovimiento = true;
				}
				break;

			default:
				break;
			}
			
			if(app.key == ' ') {
				int pos = Mapa.getPositionMatrix(this.getMatrixX(), this.getMatrixY());
				if(pos == 3) {
					
					ponerBomba();
					
				}
				
			}
			//System.out.println(camino + " " + this.getMatrixX() + " "+  this.getMatrixY() );
			
		}
	}
	
	public void moverTCP(String movimiento) {
		if (enMovimiento == false && Logica.log.pantalla == 1) {
			int camino = 0;
			switch (movimiento) {

			case "up":
				camino = Mapa.getPositionMatrix(this.getMatrixX(), this.getMatrixY()-1);
				//System.out.println(getMatrixY() + "  " + this.y);
				posActual = 0;
				lapso = y - Ficha.height;
				if ( camino == 3 || camino == 5 || camino == 6){
				up = true;
				enMovimiento = true;
				}

				break;

			case "down":
				camino = Mapa.getPositionMatrix(this.getMatrixX(), this.getMatrixY()+1);
				//System.out.println(getMatrixY() + "  " + this.y);
				posActual = 1;
				lapso = y + Ficha.height;
				
				if( camino == 3 || camino == 5 || camino == 6){
					down = true;
					enMovimiento = true;
				}
				break;

			case "left":
				camino = Mapa.getPositionMatrix(this.getMatrixX()-1, this.getMatrixY());
			
				posActual = 3;
				lapso = x - Ficha.width;
				if( camino == 3 || camino == 5 || camino == 6){
				left = true;
				enMovimiento = true;
				}
				break;
				
			case "right":
				camino = Mapa.getPositionMatrix(this.getMatrixX()+1, this.getMatrixY());
				posActual = 2;
				lapso = x + Ficha.width;
				if( camino == 3 || camino == 5 || camino == 6){
				right = true;
				enMovimiento = true;
				}
				break;
				
			case "boom":
				int pos = Mapa.getPositionMatrix(this.getMatrixX(), this.getMatrixY());
				if(pos == 3) {
					
					ponerBomba();
					
				}
				break;

			default:
				break;
			}
		
			//System.out.println(camino + " " + this.getMatrixX() + " "+  this.getMatrixY() );
			
		}
	}

	private void ponerBomba() {
		Mapa.setPositionMatrix(this.getMatrixX(), this.getMatrixY(), 4, this);
		
	}

	public void moverReleased() {
		/*
		 * switch (app.keyCode) {
		 * 
		 * case PConstants.UP: up = false; break;
		 * 
		 * case PConstants.DOWN: down = false; break;
		 * 
		 * case PConstants.RIGHT: right = false; break;
		 * 
		 * case PConstants.LEFT: left = false; break;
		 */
	}

	public void movimiento() {
		if (up) {
			up();
		} else if (down) {
			down();
		} else if (right) {
			right();
		} else if (left) {
			left();
		}
		
	}

	private void up() {
		if (y-vel >= lapso && y - vel > 0) {
			y -= vel;
		} else {
			enMovimiento = false;
			up = false;
			if (lapso >= 0) {
				y = lapso;
			}
			
		}

	}

	private void down() {
		if (y + vel <= lapso && (y + vel + Ficha.height) < app.height) {
			y += vel;
		} else {
			enMovimiento = false;
			down = false;
			if (lapso < app.height) {
				y = lapso;
			}
		}
	
	}

	private void left() {
		if (x - vel >= lapso && x - vel > 0) {
			x -= vel;
		} else {
			enMovimiento = false;
			left = false;
			if (lapso >= 0) {
				x = lapso;

			}
		}

	}
	
	public void sumarPuntos(int puntos) {
		this.puntuacion += puntos;
		this.estadar_puntuacion = puntuacion;
		receptor.enviar("puntuacion:" + this.puntuacion);
		
	}
	
	public void restarVidar(int vidas) {
		this.vidas -= vidas;
		receptor.enviar("vida:" + this.vidas);
	}
	

	public int getVidas() {
		return vidas;
	}

	private void right() {
		if (x+ vel  <= lapso && x + vel < app.width - Ficha.width) {
			x += vel;
		} else {
			enMovimiento = false;
			right = false;
			if (lapso < app.width) {
				x = lapso;
			}
		}
	}

	public int getMatrixX() {
		int pos = 0;
		pos = (int)( (x) / Ficha.width )+ 1;

		return pos;
	}

	public int getMatrixY() {
		int pos = 0;
		pos = (int)(( y) / Ficha.height) + 1;

		return pos;
	}

	public int getPuntuacion() {
		return puntuacion;
	}

	public void gano() {
		this.estadar_puntuacion = 1000000;
		
	}
	
	

}
