package Aplicacio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

import Interface.TADIndex;
import TAD.ABCdinamic;
import TAD.JavaUtil;
import TAD.TaulaHashEncadenadaIndirecta;
import Tipus.Index;


public class Main {
	static Scanner teclat=new Scanner(System.in);
	/**
	 * Funcio principal del programa
	 * @param args
	 */
	public static void main(String[] args) {
		while (true) //bucle infinit del menu
		{
			TADIndex<String, Index> tad = menu(); //preguntem al usuari quina estructura vol i la inicialitzem
			llegirFitxer(tad); //Passem el tad per a que llegir fitxer afegeixi les paraules indicades
			consultes(tad);	// menu d'opcions
		}
	}
	/**
	 * Metode per a escollir la estructura a implementar.
	 * @return TAD - estructura creada.
	 */
	public static TADIndex<String, Index> menu(){ //mostra el menu i inicialitza el TAD
		int opt=0;
		TADIndex<String, Index> tad = null;
		//teclat.nextLine(); //flush
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
					tad = (TADIndex<String, Index>) new TaulaHashEncadenadaIndirecta<String, Index>(1000);	
					break;
				case 2: 
					tad = (TADIndex<String, Index>) new ABCdinamic<String, Index>();
					break; 
				case 3: 
					tad = (TADIndex<String, Index>) new JavaUtil<String, Index>(1000);
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
	public static void consultes(TADIndex<String, Index> tad){ //mostra les consultes
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
					System.out.println(tad);
					break;
				case 2: 
					System.out.print("Indica la paraula que vols consultar: ");
					entry = teclat.next();
					ti=System.nanoTime();
					if 	(tad.consultar(entry)==null){
						System.out.print("Aquesta paraula no es troba en l'estructura.");
					}
					break;
				case 3: 
					System.out.print("Indica la paraula que vols esborrar: ");
					entry = teclat.next();
					ti=System.nanoTime();
					if (tad.consultar(entry)==null){
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
	public static void llegirFitxer(TADIndex<String, Index> tad){
		long ti, tf; // temps per a mesurar l'eficiencia de l'algorisme
		teclat.nextLine(); //flush
		String linia;
		int nLinia=0, plana=0;
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
					//System.out.println("Linia: "+nLinia);
					if(linia.startsWith("<Plana") && linia.endsWith(">")){
						plana = Integer.parseInt(linia.substring(14, linia.length()-1));//obtenim el numero de plana
						nLinia=0;//reiniciem el numero de linia
						//System.out.println("Plana: "+plana);
					}
					while (stringToken.hasMoreElements()){
						//llegim paraula a paraula i ho posem a un string
						String paraula = stringToken.nextElement().toString();
						if(paraula.startsWith("$"))
						{ //es la primera vegada que es troba aquesta paraula, s'ha de guardar a l'estructura
							String aux = paraula.substring(1, paraula.length()); //eliminem el $
							if (aux.endsWith(",") || aux.endsWith(".") || aux.endsWith(";")){
								aux = aux.substring(0, aux.length()-1); //eliminem el punt o coma
							}
							tad.afegir(aux, null); //afegim la paraula arreglada (sense $ ni '.' o ',') a l'estructura
							tad.afegirAparicio(aux, plana, nLinia); //afegim
							//System.out.println("Nova Paraula: "+aux);
						}
						//si la paraula no conte un $ pot ser per dues raons:
						//no es la primera vegada que apareix
						//no s'ha d'afegir
						else{ 
							if (tad.consultar(paraula)!=null){//si ja esta afegida
								tad.afegirAparicio(paraula, plana, nLinia);
							}
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
}
