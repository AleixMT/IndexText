package TAD;

import Interface.TADIndex;
import java.util.Hashtable;

public class JavaUtil <K extends Comparable<K>, E> implements TADIndex<K, E> {

	public boolean afegir(K k) {
		return false;
	}

	public boolean afegirAparicio(K k, int plana, int linia) {
		return false;
	}

	public boolean esborrar(K k) {
		return false;
	}

	public E consultar(K k) {
		return null;
	}

}
