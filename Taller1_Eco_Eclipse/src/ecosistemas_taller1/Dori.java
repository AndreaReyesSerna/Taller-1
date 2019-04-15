package ecosistemas_taller1;

import processing.core.PApplet;
import server.Receptor;

public class Dori extends Personaje {

	public Dori(PApplet app, int x, int y, Receptor receptor) {
		super(app, x, y, receptor);
		dori = new Animacion[4];
        dori[0] = new Animacion(app, "dory/doryarrib", 6, x, y);
		dori[1] = new Animacion(app, "dory/doryaba", 6, x, y);
		dori[2] = new Animacion(app, "dory/dory", 6, x, y);
		dori[3] = new Animacion(app, "dory/doryizq", 6, x, y);	
	}




	private Animacion[] dori;
	
	


	@Override
	public void pintar() {
		if(contVidas > 1) {
			if(app.frameCount % 2 == 0) {
				dori[posActual].pintar(x,y);
				movimiento();
			}
			
			
		} else {
		dori[posActual].pintar(x,y);
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
