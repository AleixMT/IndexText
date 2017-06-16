package TAD;
/**
 * Llista generica no ordenada
 * 
 * @author Aleix Marine i Cristina Izquierdo
 *
 */
import java.util.*;

public class LlistaGenericaNoOrd<T extends Comparable<T>> implements Iterable<T> {
	private T[] llista;
	private int num;
	
	/**
	 * Constructor de la llista
	 * @param dim - dimensio
	 */
	@SuppressWarnings("unchecked")
	public LlistaGenericaNoOrd(int dim) {
		llista=(T[]) new Comparable[dim];
		num=0;
	}

	/**
	 * Metode per a afegir un element
	 * @param p - element a afegir
	 */
	public void afegirElement(T p) {
		if (num>=llista.length) {
			// amplio
			@SuppressWarnings("unchecked")
			T[] nova=(T[]) new Object[llista.length*2];
			for (int i=0; i<llista.length; i++)
				nova[i]=llista[i];
			llista=nova;
		}
		// segur que tinc espai
		llista[num]=p;
		num++;
	}
	
	/**
	 * Metode per a afegir un element a la llista
	 * @param ll - llista on afegir-ho
	 */
	public void afegirElement(LlistaGenericaNoOrd<T> ll) {
		int numElems=ll.getNum();
		for (int i=0; i<numElems; i++) {
			afegirElement(ll.consultarIessim(i));
		}
	}
	
	/**
	 * Metode per a consultar l'element de la posicio
	 * @param i - posicio
	 * @return element
	 */
	public T consultarIessim(int i) {
		if (i<num) return(llista[i]);
		else return(null);
	}
	
	/**
	 * Getter del numero d'elements
	 * @return numero d'elements
	 */
	public int getNum() {
		return num;
	}

	/**
	 * toString de la classe
	 */
	@Override
	public String toString() {
		return "LlistaPunts [llista=" + Arrays.toString(llista) + ", num=" + num + "]";
	}

	/**
	 * Metode iterator
	 */
	public MeuIterator<T> iterator() {
		MeuIterator<T> pI=new MeuIterator<T>(this);
		return pI;
	}	
}