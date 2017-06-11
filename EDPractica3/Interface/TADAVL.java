package Interface;

import TAD.LlistaGenericaNoOrd;
import TAD.NodeAVL;

/**
 * Interface per a definir l'arbre binari AVL per a la pràctica 3
 * 
 * @author Aleix Mariné
 *
 */
public interface TADAVL<K extends Comparable<K>, E> extends TADIndex<K, E>{
	/**
	 * Es construeix l'arbre format per l'arbre amb el node arrel a1 que passa a ser el fill esquerre 
	 * l'element que passa a ser l'arrel
	 * i l'arbre amb el node arrel a2 passa a ser el fill dret
	 */
	public void arrelar(NodeAVL<K, E> arrel, NodeAVL<K,E> fe, NodeAVL<K,E> fd);
	/**
	 * Afegeix un element del que es fa seguiment a l'index
	 * @param k
	 */
	public boolean afegir(K k);
	/**
	 * Afegim una nova aparició de l'element K
	 * @param k
	 */
	public boolean afegirAparicio(K k, int plana, int linia);
	/**
	 * Esborra un element de l'index (deixa de fer el seguiment)
	 */
	public boolean esborrar(K k);
	/**
	 * Consulta aparicions d'un element de l'índex
	 */	
	public E consultar(K k);
	/**
	 * Mostra per pantalla tot l'index
	 */
	public String toString();
	/**
	 * Comprova si l'arbre binari es equilibrat
	 * @return cert si l'arbre es equilibrat
	*/
	public boolean esEquilibrat();
	/**
	 * M�tode que retorna la llista d'elements de l'arbre segons el recorregut inordre
	 * @return llista d'elements de l'arbre en preordre
	 */
	public LlistaGenericaNoOrd<E> inordre();
	/**
	 * Retorna l'alçada de l'arbre, -1 si l'arbre està buit
	 * @return
	 */
	public int alcada();

}
