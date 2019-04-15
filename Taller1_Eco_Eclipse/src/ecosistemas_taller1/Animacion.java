package ecosistemas_taller1;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

public class Animacion extends Thread{
	private PImage[] imagen;
	private PApplet app;
	private int imagenActual;
	private int width;
	private int height;

	public boolean stop;
	
	
	public Animacion(PApplet app,String ruta, int num, int x, int y ){
				this.app = app;
				imagen = new PImage[num];
		for(int i = 0; i < imagen.length; i ++) {
			imagen[i] = app.loadImage(ruta+i+".png");
			imagen[i].resize(150, 150);
			this.width = imagen[i].width;
			this.height = imagen[i].width;
		}		
		start();
	}
	
	
	public void run() {
		while (true) {
			try {
				
				if(stop == false) {
				imagenActual ++;}
				if(imagenActual > imagen.length-1) {
					imagenActual = 0;
					
				}
				
				sleep(60);
				
			} catch (Exception e) {
			}
			
		}
		
	}
	
	public void pintar(int x, int y){
		app.imageMode(PConstants.CENTER);
		app.image(imagen[imagenActual], x + Ficha.width/2 , y + Ficha.height/2);
		//app.image(imagen[imagenActual], x , y);
		
		
	}

}
