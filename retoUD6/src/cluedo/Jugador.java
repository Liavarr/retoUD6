package cluedo;

import java.time.LocalDateTime;

public class Jugador {
	//Atributos
	String nombre;
	String arma;
	String habitacion;
	LocalDateTime horaPartida;
	
	//Constructores
	Jugador (String nombre, String arma, String habitacion, LocalDateTime horaPartida){
		this.nombre = nombre;
		this.arma = arma;
		this.habitacion = habitacion;
		this.horaPartida = horaPartida;
	}
	
	Jugador (){
		
	}
	
	
	//Getters and setters
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getArma() {
		return arma;
	}

	public void setArma(String arma) {
		this.arma = arma;
	}

	public String getHabitacion() {
		return habitacion;
	}

	public void setHabitacion(String habitacion) {
		this.habitacion = habitacion;
	}

	public LocalDateTime getHoraPartida() {
		return horaPartida;
	}

	public void setHoraPartida(LocalDateTime horaPartida) {
		this.horaPartida = horaPartida;
	}
	
	//to String
	
	public String mostrarJugador () {
		return "Jugador [nombre = "+this.getNombre()+", arma = "+this.getArma()+", lugar = "
				+this.getHabitacion()+", hora = "+this.getHoraPartida()+"]";
	}
	
}
