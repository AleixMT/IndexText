package TAD;

import Interface.TADIndex;
import Tipus.Index;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class JavaUtil <K extends Comparable<K>, V> implements TADIndex<K, V> {
	private Hashtable<K,V> taula;
	
	public JavaUtil(int capacitat){
		this.taula = new Hashtable<K,V>(capacitat);
	}

	@SuppressWarnings("unchecked")
	public boolean afegir(K k, V v) {
		taula.put(k, (V) new Index()); //afegim la clau, sense valors
		return true;
	}

	public boolean afegirAparicio(K k, int plana, int linia) {		
		if (taula.containsKey(k)){ //si existeix la paraula
			Index index = (Index)(taula.get(k)); //trobem el valor
			
			index.AfegirAparicio(plana, linia);
			return true;
		}
		else return false;
	}

	public boolean esborrar(K k) {
		if (taula.remove(k) != null) return true;
		else return false;
	}

	public V consultar(K k) {
		return taula.get(k);
	}

	public boolean existeix(K k) {
		if (taula.containsKey(k)) return true;
		else return false;
	}

	@Override
	public String toString() {
		Iterator<Entry<K, V>>  it;
		Map.Entry<K,V> entry;
		String out= "";
		it = taula.entrySet().iterator();
		while (it.hasNext()) {
		    entry = it.next();
		   out+=(entry.getKey().toString() + " " +entry.getValue().toString());
		}
		return out;
	}

}
