package TAD;

import Interface.TADIndex;
import Tipus.Index;

import java.util.Hashtable;

public class JavaUtil <K extends Comparable<K>, V> implements TADIndex<K, V> {
	private Hashtable<K,V> taula;
	
	public JavaUtil(int capacitat){
		this.taula = new Hashtable<K,V>(capacitat);
	}

	public boolean afegir(K k) {
		taula.put(k, null); //afegim la clau, sense valors
		return true;
	}

	public boolean afegirAparicio(K k, int plana, int linia) {		
		if (taula.containsKey(k)){ //si existeix la paraula
			Index index = (Index)(taula.get(k)); //trobem el valor
			//aqui tengo un problema porque nosotros en verdad nunca usamos el valor
			//entonces el "valor" que nosotros cogemos es la palabra, que es k y aqui va diferente porque si que usan valor entonces WTF TENGO QUE HACER?!
			//claro, para los valores hemos hecho una lista a parte totalmente independiente asi que... 
			//quizas hay que hacer un tad para esta y asi poner V y hacerlo todo diferente, sin el afegir aparicio
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

}
