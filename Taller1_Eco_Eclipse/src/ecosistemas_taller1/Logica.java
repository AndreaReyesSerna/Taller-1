package ecosistemas_taller1;

import java.awt.Image;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;
import server.ControlServer;
import server.ControlServer.ObserverServidor;
import server.Receptor;

public class Logica implements ObserverServidor {

	private PApplet app;
	static public Logica log;
	public Mapa mapa;
	private PImage instrucciones;
	private PImage pantallaFinal;
	public int pantalla;
	ControlServer controlServer;
	public ArrayList<Receptor>receptores;
	String ip_local;
	String ip;


	public Logica(PApplet app) {
	this.app = app;
	log = this;
	this.mapa = new Mapa(app);
	
	
	receptores = new ArrayList<>();
	controlServer = ControlServer.getIntance();
	controlServer.start();
	controlServer.setObservador(this);
	
	instrucciones = app.loadImage("intrucciones.png");
	pantallaFinal = app.loadImage("pantallaFinal.png");
	pantalla = 0;
	try {
		ip_local = InetAddress.getLocalHost().getHostAddress();
		this.ip =ip_local.toString();
	} catch (UnknownHostException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	}
	
	
	public void pintar() {
		app.background(255);
		
		switch (pantalla) {
		case 0:
			app.imageMode(PConstants.CORNER);
			app.image(instrucciones, 0, 0);
			app.fill(255);
			app.textSize(40);
			app.text(ip, app.width/2, 50);
			break;
			
		case 1:
			app.background(255);
			mapa.pintar();	
			break;
			
		case 2:
			app.imageMode(PConstants.CORNER);
			app.image(pantallaFinal, 0, 0);
			mapa.pintarP();
			
		default:
			break;
		}
		
		
	}
	
	public void controles() {
		mapa.controles();
		
	}

	
	
	public void controlesReleased() {
		mapa.controlesReleased();
		
		
	}


	@Override
	public void conexionRecibida(Receptor receptor) {
		this.receptores.add(receptor);
		
	}
}





