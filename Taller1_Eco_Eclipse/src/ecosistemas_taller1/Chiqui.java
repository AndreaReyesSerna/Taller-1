package ecosistemas_taller1;

import processing.core.PApplet;
import server.Receptor;

public class Chiqui extends Personaje {

	public Chiqui(PApplet app, int x, int y, Receptor receptor) {
		super(app, x, y, receptor);
		chiqui = new Animacion[4];
        chiqui[0] = new Animacion(app, "chiqui/chiarrib", 6, x, y);
		chiqui[1] = new Animacion(app, "chiqui/chiaba", 6, x, y);
		chiqui[2] = new Animacion(app, "chiqui/chi", 6, x, y);
		chiqui[3] = new Animacion(app, "chiqui/chiizq", 6, x, y);	
	}

	private Animacion[] chiqui;
	
	
	

	@Override
	public void pintar() {
		if(contVidas > 1) {
			if(app.frameCount % 2 == 0) {
				chiqui[posActual].pintar(x,y);
				movimiento();
			}
			
			
		} else {
		chiqui[posActual].pintar(x,y);
		movimiento();
		}
		
	}




	@Override
	public void mensajeRecibido(String mensaje) {
		moverTCP(mensaje);
		
		
	}




	@Override
	public int compareTo(Personaje o) {
		
		return estadar_puntuacion - o.estadar_puntuacion;
	}

}
