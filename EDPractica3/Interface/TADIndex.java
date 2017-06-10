package Interface;

import TAD.Node;
import Tipus.Index;

public abstract interface TADIndex <E extends Index>{
	/**
	 * Afegeix un element a l'índex
	 */
	public void afegir(E e);
	
	/**
	 * Esborra un element (paraula) de l'índex
	 */	
	public void esborrar(E e);
	
	/**
	 * Consulta un element (paraula) de l'índex
	 */	
	public Node<E> consultar(E e);
}
