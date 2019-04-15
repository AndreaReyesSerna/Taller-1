package ecosistemas_taller1;

import processing.core.PApplet;
import server.Receptor;

public class Merlin extends Personaje {

	public Merlin(PApplet app, int x, int y, Receptor receptor) {
		super(app, x, y, receptor);
		merlin = new Animacion[4];
        merlin[0] = new Animacion(app, "merlin/merlinarrib", 6, x, y);
		merlin[1] = new Animacion(app, "merlin/merlinaba", 6, x, y);
		merlin[2] = new Animacion(app, "merlin/merlin", 6, x, y);
		merlin[3] = new Animacion(app, "merlin/merlinizq", 6, x, y);	
	}



	private Animacion[] merlin;
	
	

	@Override
	public void pintar() {
		if(contVidas > 1) {
			if(app.frameCount % 2 == 0) {
				merlin[posActual].pintar(x,y);
				movimiento();
			}
			
			
		} else {
		merlin[posActual].pintar(x,y);
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
