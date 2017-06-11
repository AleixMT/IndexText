package TAD;
/**
 * Llista gen�rica no ordenada
 * 
 * @author Professors de l'assignatura 16-17
 *
 */
import java.util.*;

public class LlistaGenericaNoOrd<T> implements Iterable<T> {
	private T[] llista;
	private int num;
	
	public LlistaGenericaNoOrd(int dim) {
		llista=(T[])new Object[dim];
		num=0;
	}

	public void afegirElement(T p) {
		if (num>=llista.length) {
			// amplio
			T[] nova=(T[]) new Object[llista.length*2];
			for (int i=0; i<llista.length; i++)
				nova[i]=llista[i];
			llista=nova;
		}
		// segur que tinc espai
		llista[num]=p;
		num++;
	}
	
	public void afegirElement(LlistaGenericaNoOrd<T> ll) {
		int numElems=ll.getNum();
		for (int i=0; i<numElems; i++) {
			afegirElement(ll.consultarIessim(i));
		}
	}
	
	public T consultarIessim(int i) {
		if (i<num) return(llista[i]);
		else return(null);
	}
	
	public int getNum() {
		return num;
	}

	public String toString() {
		return "LlistaPunts [llista=" + Arrays.toString(llista) + ", num=" + num + "]";
	}


	
	
}