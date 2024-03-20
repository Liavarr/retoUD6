package cluedo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;

public class mainCluedo {
	public static void main(String[] args) throws IOException {
		//Invocar Scanner
		Scanner sc = new Scanner(System.in);
		//Crear la clave:
		String CLAVE = "123";
		//Guardamos el directorio de trabajo y el noombre del archivo a usar
		String rutaTrabajo = System.getProperty("user.dir")+File.separator+"Cluedo.txt";
		File archivo = new File(rutaTrabajo);
		boolean bucleCompleto = true;
		while (bucleCompleto) {
			//Arrays de personajes, armas y lugar
			//Crear un ArrayList vacío
	        ArrayList<String> personajes = new ArrayList<>();
	        //Agregar los valores uno por uno
	        personajes.add("Amapola");
	        personajes.add("Celeste");
	        personajes.add("Prado");
	        personajes.add("Mora");
	        personajes.add("Rubio");
	        personajes.add("Blanco");
	        
	        //Crear un ArrayList para las armas
	        ArrayList<String> armas = new ArrayList<>();
	        //Agregar elementos al ArrayList utilizando el método add
	        armas.add("bate");
	        armas.add("pistola");
	        armas.add("candelabro");
	        armas.add("cuchillo");
	        armas.add("cuerda");
	        armas.add("hacha");
	        armas.add("pesa");
	        armas.add("veneno");
	        armas.add("trofeo");
	        
	        //Crear un ArrayList para los lugares
	        ArrayList<String> lugares = new ArrayList<>();
	        
	        //Agregar elementos al ArrayList utilizando el método add
	        lugares.add("casa de invitados");
	        lugares.add("teatro");
	        lugares.add("spa");
	        lugares.add("observatorio");
	        lugares.add("comedor");
	        lugares.add("terraza");
	        lugares.add("salón");
	        lugares.add("cocina");
	        lugares.add("vestíbulo");
	        
	        
			//Condiciones para entrar al menú
			boolean pregunta = true;
			boolean menu = true;
			
			//Bienvenida
			System.out.println("**¡Bienvenidos al Cluedo en la Mansión Misteriosa! ¿Están listos para resolver el enigma del asesinato? ¡Que empiece la investigación!**");
			System.out.println("**Este programa se encarga de barajar por sí mismo, así que sientate y disfruta**");
			
			//Mostrar personajes, armas y lugares disponibles actuales
			System.out.println("\n**Estas son las características actuales del juego**");
			System.out.println("\nPersonajes:");
			mostrarArray(personajes);
			System.out.println("\nArmas:");
			mostrarArray(armas);
			System.out.println("\nLugares:");
			mostrarArray(lugares);
			
			//Preguntar acceso menú
			while (pregunta) {
				System.out.println("\n¿Quieres añadir algo más a las listas? Introduzca Si o No");
				
				String accesoMenu = sc.nextLine();
				switch (accesoMenu.toLowerCase()){
					case "si": 
						menu = true;
						pregunta = false;
						break;
					case "no":
						menu = false;
						pregunta = false;
						break;
					default:
						System.out.println("El valor introducido no es válido: " + accesoMenu.toLowerCase());
				}
			}
			
			//Mostramos el menú
			while (menu) {
				menu = mostrarMenu(personajes, armas, lugares, sc);
			}
			
			//Seguimos para barajar
			System.out.println("¡Genial, sigamos…!");
			
			System.out.println("\n**Procedemos a barajar**");
			
			//Creamos el objeto jugador
			Jugador jugador = barajar(personajes, armas, lugares);
			
			String culpable = jugador.mostrarJugador();
			
			//Escribimos el culpable en el archivo
			escribirFichero(archivo, culpable);
			
			System.out.println("\n**Todo está listo y el culpable está en el edificio, si quieres mostrar al culpable introduce la clave secreta:**");
			String claveEscrita = sc.nextLine();
			if (Objects.equals(claveEscrita, CLAVE)) {
				System.out.println("Los datos del culpable son: "+culpable);
			} else {
				System.out.println("No tienes permisos para ver al culpable");
			}
			
			System.out.println("\n**¿Quieres crear una nueva combinación de cartas? Escribe 'Si' si es así o cualquier otro valor para terminar el programa**");
			String repetir = sc.nextLine();
			repetir = repetir.toLowerCase();
			if ("si".equals(repetir)) {
				bucleCompleto = true;
			} else {
				System.out.println("Muchas gracias por jugar, aquí finaliza la sesión.");
				bucleCompleto = false;
			}
		}
		
		System.out.println("\n**Todos los datos del fichero so far son los siguientes:**");
		leerDeFichero(archivo);
	}
	
	
	//METODOS===================================================================
	
	//Método para mostrar el array
	static void mostrarArray(ArrayList<String> lista) {
		for (String actual : lista) {
			System.out.println(actual);
		}
	}
	
	//Método para el menú
	static boolean mostrarMenu(ArrayList<String> personajes, ArrayList<String> armas, ArrayList<String> lugares,Scanner sc){
		System.out.println("Introduzca un valor para la acción del menú a completar: ");
		System.out.println("1. Personajes");
		System.out.println("2. Armas");
		System.out.println("3. Lugares");
		System.out.println("4. Salir");
		int accion = sc.nextInt();
		sc.nextLine();
		switch (accion) {
			case 1: {
				String queModifica = "personaje";
				System.out.println("¿Cuantos personajes quieres añadir?");
				int cantidad = sc.nextInt();
				sc.nextLine();
				modificarLista(personajes, cantidad, sc, queModifica);
				return true;
			} case 2:{
				String queModifica = "arma";
				System.out.println("¿Cuantas armas quieres añadir?");
				int cantidad = sc.nextInt();
				sc.nextLine();
				modificarLista(armas, cantidad, sc, queModifica);
				return true;
			} case 3:{
				String queModifica = "lugar";
				System.out.println("¿Cuantos lugares quieres añadir?");
				int cantidad = sc.nextInt();
				sc.nextLine();
				modificarLista(lugares, cantidad, sc, queModifica);
				return true;
			} case 4:{
				return false;
			}
			default:
				System.out.println("El valor introducido no es válido");
				return true;
			}
		
	}
	
	
	//Modificar Lista
	static void modificarLista(ArrayList<String> lista, int cantidad, Scanner sc, String queModifica) {
		for (int i = 0; i < cantidad; i++) {
			System.out.println("Introduce el/la nuevo/a "+queModifica+" a la lista:");
			String nuevoDato = sc.nextLine();
			lista.add(nuevoDato);
		}
		System.out.println(queModifica+" añadido/s\n");
	}
	
	//Método para barajar y crear objeto Jugador
	static Jugador barajar(ArrayList<String> personajes, ArrayList<String> armas, ArrayList<String> lugares) {
		int cantidadPersonajes = personajes.size();
		int cantidadArmas = armas.size();
		int cantidadLugares = lugares.size();
		
		int numeroAleatorioPersonajes = (int) (Math.random() * (cantidadPersonajes));
		
		int numeroAleatorioArmas = (int) (Math.random() * (cantidadArmas));
		
		int numeroAleatorioLugares = (int) (Math.random() * (cantidadLugares));
		
		//Guardamos los valores para pasarlo al objeto jugador
		String nombre = personajes.get(numeroAleatorioPersonajes);
		String arma = armas.get(numeroAleatorioArmas);
		String habitacion = lugares.get(numeroAleatorioLugares);
		LocalDateTime fecha = LocalDateTime.now();
		
		return new Jugador(nombre, arma, habitacion, fecha);
	}
	
	//Para escribir en el fichero
	public static void escribirFichero(File fichero, String datos) throws IOException {
		FileWriter input = null;
		BufferedWriter output = null;
		try {
			input = new FileWriter(fichero, true);
			output = new BufferedWriter(input);
			Date current = new Date();
			output.write("Cabecera - Fecha: " + current);
			output.newLine();
			output.write(datos);
			output.newLine();
			
			System.out.println("Se ha añadido el culpable al archivo: "+fichero.getAbsolutePath());
			output.close();
		} catch (Exception e) {
			System.out.println("Algo ha ido mal a la hora de escribir en escribirEnFichero");
			e.printStackTrace();
		} finally {
			input.close();
			output.close();
		}
	}
	
	//Para leer el fichero
	public static void leerDeFichero(File archivo) {
		try {
			FileReader in = new FileReader(archivo.getAbsolutePath());
			BufferedReader out = new BufferedReader(in);
			String linea;
			while ((linea=out.readLine())!= null) {
				System.out.println(linea);
			}
			out.close();
		} catch (FileNotFoundException e) {
			System.err.println("El archivo no existe: " + e.getMessage());
		} catch (IOException e) {
			System.err.println("Ocurrió un error de E/S " + e.getMessage());
		}
	}

}
