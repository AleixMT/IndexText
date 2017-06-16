package TAD;

import java.util.Iterator;
import TAD.TaulaHashEncadenadaIndirecta;
/**
 * Classe Iterator
 * @author Aleix Marine i Cristina Izquierdo
 *
 * @param <T>
 */
public class MeuIterator<T extends Comparable<T>> implements Iterator<T> {
	private LlistaGenericaNoOrd<T> llista;	//nou atribut que ens guardara una copia de la llista actual de punts
	private int posicioIterator;
	
	/**
	 * Constructor per una llista generica
	 * @param ll - llista
	 */
	public MeuIterator(LlistaGenericaNoOrd<T> ll) {
		llista=new LlistaGenericaNoOrd<T>(ll.getNum());
		for (int i=0; i<ll.getNum(); i++) {
			llista.afegirElement(ll.consultarIessim(i));
		}
		posicioIterator=0; 	// ens preparem per a retornar els elements a partir de la posicio 0
	}
	
	public MeuIterator(LlistaGenericaOrd<T> ll){
		llista=new LlistaGenericaNoOrd<T>(ll.getNum());
		for (int i=0; i<ll.getNum(); i++) {
			llista.afegirElement(ll.consultarIessim(i));
		}
		posicioIterator=0; 	// ens preparem per a retornar els elements a partir de la posicio 0
	}
	/**
	 * Constructor per una taula de hash encadenada indirecta
	 * @param taula - taula de hash
	 * Utilitzem la supressió del warning rawtypes ja que en aquest constructor concretament els parametres K i V donen problemes
	 */
	@SuppressWarnings("unchecked")
	public MeuIterator(@SuppressWarnings("rawtypes") TaulaHashEncadenadaIndirecta taula){
		this.llista = new LlistaGenericaNoOrd<T>(taula.getCapacitatTaula());
		for (int i = 0; i < taula.getCapacitatTaula(); i++) {
			if (taula.getTaulaElements()[i]!=null){
				this.llista.afegirElement((T) taula.getTaulaElements()[i]);
			}
		}
		posicioIterator=0;
	}
	
	/**
	 * Metode hasNext per a saber si queden mes elements
	 */
	public boolean hasNext() {
		return ((posicioIterator<llista.getNum()));
	}

	/**
	 * Metode next per a obtenir el seguent element
	 */
	public T next() {
		T aux=llista.consultarIessim(posicioIterator);
		posicioIterator++;
		return aux;
	}

}
