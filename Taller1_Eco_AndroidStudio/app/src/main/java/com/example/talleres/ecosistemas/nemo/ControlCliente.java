package com.example.talleres.ecosistemas.nemo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ControlCliente extends Thread {

	Socket paquete;
	InetAddress ip;

	static ControlCliente controlClienteActual;

	DataInputStream entrada;
	DataOutputStream salida;
	private Observer observador;
	private String ipg;

	static public ControlCliente getIntance(String ipg){
		if(controlClienteActual == null){
			controlClienteActual = new ControlCliente(ipg);
		}
		return controlClienteActual;
	}

	public ControlCliente(String ipg) {
	this.ipg = ipg;
		start();

	}

	@Override
	public void run() {

		try {

			ip = InetAddress.getByName(ipg);

			// Espera un cliente
			paquete = new Socket(ip, 5000);

			entrada = new DataInputStream(paquete.getInputStream());
			salida = new DataOutputStream(paquete.getOutputStream());


			enviar(Personajes.PERSONAJE);

		} catch (IOException e) {
			e.printStackTrace();
		}

		recibir();
	}

	public void recibir() {
		while (true) {

			try {
				// Esperando datos
				// Cuanto puedo recibir
				byte[] capacidad = new byte[50];

				entrada.read(capacidad);

				String informacion = new String(capacidad).trim();

				if (this.observador != null) {
					this.observador.mensajeRecibido(informacion);
				}
				
				mensajeRecibido(informacion);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

	public void enviar(final String datos) {
		new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					salida.write(datos.getBytes());
					salida.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}).start();

	}
	

	public void mensajeRecibido(String mensaje) {
		
	}
	
	public void setObservador(Observer observador) {
		this.observador = observador;
	}

}
