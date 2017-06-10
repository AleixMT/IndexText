package TAD;

import Tipus.Index;

/**
 * Classe Node. Aquesta classe funciona com un wrapper per a l'element genèric E, de tal 
 * manera que podem utilitzar els punters que estàn continguts al wrapper independentment
 * que vinguin d'arbre o de hashing (classes filles NodeAVL i NodeHash respectivament).
 * 
 * Hem fet això per a intentar utilitzar les dues estructures de dades com un sola (una
 * sola instància) per a poder utilitzar els métodes que necessita l'índex sense saber
 * quina ED estem utilitzant.
 * @author amt
 *
 * @param <E>
 */
public class Node<E extends Index> {
	private E element;
	
	public Node (E elem) {
		element=elem;
	}

	public E getElement() {
		return element;
	}

	public void setElement(E element) {
		this.element = element;
	}

	@Override
	public String toString() {
		return "Node [element=" + element + "]";
	}
	
	
	
}
