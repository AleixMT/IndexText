package Interface;

import TAD.NodeAVL;
import Tipus.Index;

/**
 * Interface per a definir l'arbre binari AVL per a la pràctica 3
 * 
 * @author Aleix Mariné
 *
 */
public interface TADAVL<E extends Index> extends TADIndex<E>{
	/**
	 * Insereix un element E a l'arbre AVL mantenint l'arbre equilibrat
	 * @param e
	 */
	public void afegir(E e);
	
	/**
	 * Esborra un element E de l'arbre AVL
	 * @param e
	 */
	public void esborrar(E e);

	/**
	 * El següent métode busca un element E dins l'arbre i retorna el node que el conté (NodeAVL)
	 * Retorna null si no el troba, pel que aquest métode funciona com un existeix.
	 * @param k
	 * @return
	 */
	public NodeAVL<E> Consultar(E e);

}
