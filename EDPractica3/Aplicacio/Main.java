package Aplicacio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;


public class Main {
	static Scanner teclat=new Scanner(System.in);
	/**
	 * Funcio principal del programa
	 * @param args
	 */
	public static void main(String[] args) {
		while (true) //bucle infinit del menu
		{
			TAD tad = menu(); //preguntem al usuari quina estructura vol i la inicialitzem
			llegirFitxer(tad); //Passem el tad per a que llegir fitxer afegeixi les paraules indicades
			consultes(tad);	// menu d'opcions
		}
	}
	/**
	 * Metode per a escollir la estructura a implementar.
	 * @return TAD - estructura creada.
	 */
	public static TAD<Node> menu(){ //mostra el menu i inicialitza el TAD
		int opt=0;
		TAD<Node> tad = null;
		while (tad==null) //iterarem mentre que el usuari no indiqui l'estructura
		{
			System.out.println("Quina versio vols utilitzar?");
			System.out.println("1.- Estructura en Taula de Hash.");
			System.out.println("2.- Estructura en arbre AVL.");
			System.out.println("3.- Estructura de les java.util.collections.");
			try
			{
				opt=teclat.nextInt();
				switch(opt) {
				case 1: 
					//tad = new TAD (((TADGeneric<Assignatura>) new TaulaHash<E>());	
					break;
				case 2: 
					//tad = new TAD (((TADGeneric<Assignatura>) new ArbreAVL<E>());
					break; 
				case 3: 
					//tad = new TAD (((TADGeneric<Assignatura>) new JavaUtil<E>());
					break;
				default: System.out.println("Aquesta opcio no esta a la llista. \n");
				break;	//Funciona com una excepcio per a un valor numeric no acceptat
				}
			}
			catch (InputMismatchException e) 
			{
				System.out.println("Exceptions.InputMismatchException: ERROR:Has introduit una opcio incorrecta, torna-ho a intentar. \n");
                teclat.nextLine(); 
			}
		}
		return tad; 
	}
	/**
	 * Metode per a consultar les dades de l'estructura
	 */
	public static void consultes(/*Multillista tad*/){ //mostra les consultes
		int opt=0;
		long ti, tf; // temps per a mesurar l'eficiencia de l'algorisme
		boolean exit = false;
		String entry = ""; //entrada generica de teclat.
		while (!exit) //mentre que el usuari no indiqui l'estructura iterarem
		{
			System.out.println("Quina operacio vols fer?");
			System.out.println("1.- Mostrar index.");
			System.out.println("2.- Consultar paraula a l'estructura.");
			System.out.println("3.- Esborrar paraula.");
			System.out.println("4.- Reinicia el programa.");
			System.out.println("5.- Sortir del programa.");
			try
			{
				ti=System.nanoTime();
				opt=teclat.nextInt();
				switch(opt) {
				case 1:
					//iterator de l'estructura
					break;
				case 2: 
					System.out.print("Indica la paraula que vols consultar: ");
					entry = teclat.next();
					ti=System.nanoTime();
					if 	(true/*!tad.sumariAlumne(entry)*/){
						System.out.print("Aquesta paraula no es troba en l'estructura.");
					}
					break;
				case 3: 
					System.out.print("Indica la paraula que vols esborrar: ");
					entry = teclat.next();
					ti=System.nanoTime();
					if (true/*!tad.sumariAssignatura(entryint)*/){
						System.out.print("Aquesta paraula no es troba en l'estructura.");
					}
					break; 
				case 4: 
					exit=true;
					break;
				case 5: //apagar la consola
					for (int i = 0; i < 100; ++i) System.out.println();//netejem la pantalla omplint-la d'espais
					System.exit(0); break; //termina l'aplicacio que s'esta executant al moment
				default: System.out.println("Aquesta opcio no esta a la llista... \n");
				break;	//Funciona com una excepcio per a un valor numeric no acceptat
				}
				tf = System.nanoTime();
				System.out.println("Ha tardat "+ (tf-ti)/Math.pow(10, 9)+ " segons");
			}
			catch (InputMismatchException e) 
			{
				System.out.println("Exceptions.InputMismatchException: ERROR:Has introduit una opcio incorrecta, torna-ho a intentar \n");
                teclat.nextLine(); 
			}
		}
	}
	
	/**
	 * Metode per a llegir fitxers i obtenir dades
	 */
	public static void llegirFitxer(){
		long ti, tf; // temps per a mesurar l'eficiencia de l'algorisme
		teclat.nextLine(); //flush
		String linia;
		int nLinia=0;
		String fitxer = null;
		boolean correcte = false;
		while (!correcte)//mentre que quedin dades per llegir
		{
			while (!correcte)//mentre que no introdueixi el nom del fitxer be
			{
				try{
					System.out.println("Quin es el nom del fitxer de dades?");
					fitxer = teclat.nextLine();
					correcte = true;
				}catch (InputMismatchException e){
					System.out.println("El nom del fitxer es incorrecte, torna-ho a intentar:");
					fitxer = teclat.nextLine();
				}
			}
			correcte = false;
			try
			{
				BufferedReader buffer = new BufferedReader(new FileReader(fitxer)); //Inicialitzem el buffer de fitxer
				ti=System.nanoTime();
				while((linia = buffer.readLine()) != null) 
				{
					StringTokenizer stringToken = new StringTokenizer(linia, " ");
					nLinia++; //augmentem el numero de linia
					System.out.println("Linia: "+nLinia);
					if(linia.startsWith("<Plana") && linia.endsWith(">")){
						char plana = linia.charAt(14);//obtenim el numero de plana
						nLinia=0;//reiniciem el numero de linia
						System.out.println("Plana: "+plana);
					}
					while (stringToken.hasMoreElements()){
						//llegim paraula a paraula i ho posem a un string
						String paraula = stringToken.nextElement().toString();
						if(paraula.startsWith("$")){ //es la primera vegada que es troba aquesta paraula, s'ha de guardar a l'estructura
							String aux = paraula.substring(1, paraula.length());
							if (aux.endsWith(",") || aux.endsWith(".")){
								aux = aux.substring(0, aux.length()-1); //eliminem el punt o coma
							}
							//hola a�adir
							System.out.println("Nova Paraula: "+aux);
						}
						//si la paraula ja es troba a l'estructura
						else{
							//buscar coincidencia en l'estructura
						}
					}
				}
				buffer.close();
				correcte = true; //ja s'han acabat d'afegir les dades
				tf=System.nanoTime();
				System.out.println("Ha trigat "+ (tf-ti)+ " segons.");
					

			}
			catch (IOException e) //Problema general de IO
			{
				System.out.println("ERROR: No existeix aquest fitxer!");
			}
		}
			
	}
	
		/*TADArbreBinari<Integer> arbre=new ArbreBinariDinamic<Integer>();
		TADArbreBinari<Integer> aux1=new ArbreBinariDinamic<Integer>();
		TADArbreBinari<Integer> aux2=new ArbreBinariDinamic<Integer>();
		Node<Integer> dotze=new Node<Integer>(null, 12, null);
		Node<Integer> tres=new Node<Integer>(null, 3, null);
		Node<Integer> u=new Node<Integer>(null, 1, null);
		
		aux1.arrelar(new ArbreBinariDinamic<Integer>(dotze), 7, null);
		aux2.arrelar(new ArbreBinariDinamic<Integer>(tres), 4, new ArbreBinariDinamic<Integer>(u));
		
		arbre.arrelar(aux1, 5, aux2);
		//arbre.arrelar(null, 5, aux2);
		
		System.out.println("Preordre "+arbre.preordre());
		System.out.println("Inordre "+arbre.inordre());
		System.out.println("Postordre "+arbre.postordre());
		
		System.out.println("Es equilibrat "+arbre.esEquilibrat());
		
		System.out.println("Al�ada arbre "+arbre.alcada());*/

}
