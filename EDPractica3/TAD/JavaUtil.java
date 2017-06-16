package TAD;

import Interface.TADIndex;
import Tipus.Index;

import java.util.Enumeration;
import java.util.Hashtable;

/**
 * Classe wrapper per a fer una taula de Hash amb una coleccio del java.util
 * Hashtable
 * @author Cristina Izquierdo i Aleix Marine
 *
 * @param <K> - clau
 * @param <V> - valor
 */
public class JavaUtil <K extends Comparable<K>, V> implements TADIndex<K, V> {
	private Hashtable<K,V> taula;
	
	/**
	 * Constructor de la classe
	 * @param capacitat taula
	 */
	public JavaUtil(int capacitat){
		this.taula = new Hashtable<K,V>(capacitat);
	}

	/**
	 * Metode per afegir elements a la taula
	 */
	@SuppressWarnings("unchecked")
	public boolean afegir(K k, V v) {
		taula.put(k, (V) new Index()); //afegim la clau, els valors sera una llista d'aparicions
		return true;
	}

	/**
	 * Metode per afegir aparicions a un element de la taula (valors)
	 */
	public boolean afegirAparicio(K k, int plana, int linia) {		
		if (taula.containsKey(k)){ //si existeix la paraula
			Index index = (Index)(taula.get(k)); //trobem el valor
			
			index.AfegirAparicio(plana, linia);
			return true;
		}
		else return false;
	}

	/**
	 * Metode per esborrar un element de la taula
	 */
	public boolean esborrar(K k) {
		if (taula.remove(k) != null) return true;
		else return false;
	}

	/**
	 * Metode per a consultar un element de l'index
	 */
	public V consultar(K k) {
		return taula.get(k);
	}

	/**
	 * Metode per a consultar si existeix un element a la taula amb la mateixa clau que se li passa per parametre
	 */
	public boolean existeix(K k) {
		if (taula.containsKey(k)) return true;
		else return false;
	}

	/**
	 * Metode toString de la classe
	 */
	public String mostrarIndex() {
		Enumeration<K> k = taula.keys();
	    LlistaGenericaOrd<NodeHash<K,V>> ll = new LlistaGenericaOrd<NodeHash<K,V>>(10);
		while (k.hasMoreElements())
		{
			K aux = k.nextElement();
			ll.afegirElement(new NodeHash<K,V>(aux, taula.get(aux), null));
		}
		MeuIterator<NodeHash<K,V>> it = new MeuIterator<NodeHash<K,V>>(ll);
		String out= "";
		NodeHash<K,V> auxn;
		while (it.hasNext()) 
		{
		    auxn = it.next();
			out+=(auxn.getClau() + " " +auxn.getValor());
		}
		return out;
	}

}
