package Interface;

import TAD.LlistaGenericaNoOrd;
import Interface.TADIndex;

/**
 * Interface per a definir l'arbre binari AVL per a la pràctica 3
 * 
 * @author Aleix Mariné
 *
 ***
	 * Es construeix l'arbre format per l'arbre amb el node arrel a1 que passa a ser el fill esquerre 
	 * l'element que passa a ser l'arrel
	 * i l'arbre amb el node arrel a2 passa a ser el fill dret
	 *
	public void arrelar(NodeAVL<K, V> arrel, NodeAVL<K, V> fe, NodeAVL<K, V> fd);
	**
	 * Afegeix un element del que es fa seguiment a l'index
	 * @param k
	 *
	public boolean afegir(K k);
	**
	 * Afegim una nova aparició de l'element K
	 * @param k
	 *
	public boolean afegirAparicio(K k, int plana, int linia);
	**
	 * Esborra un element de l'index (deixa de fer el seguiment)
	 *
	public ABCdinamic<K,V> esborrar(K k, ABCdinamic<K,V> arbre);
	**
	 * Consulta aparicions d'un element de l'índex
	 *
	public V consultar(K k);
	**
	 * Mostra per pantalla tot l'index
	 *
	public String toString();
	**
	* Comprova si l'arbre binari es equilibrat
	 * @return cert si l'arbre es equilibrat
	*
	public boolean esEquilibrat();
	**
	 * M�tode que retorna la llista d'elements de l'arbre segons el recorregut inordre
	 * @return llista d'elements de l'arbre en preordre
	 *
	public LlistaGenericaNoOrd<E> inordre();
	**
	 * Retorna l'alçada de l'arbre, -1 si l'arbre està buit
	 * @return
	 *
	public int alcada();
	**
	 * Mira si existeix l'element k a l'estructura i retorna cert si hi és, fals si no hi és
	 * @param k
	 * @return
	 *
 */
/**
 * Interface per a definir l'arbre binari
 * 
 * @author Professors de l'assignatura 16-17
 *
 */
public abstract interface TAD_ABC<K extends Comparable<K>, V> extends TADIndex<K, V>{
	
	public void inserir( K k, V v);
	
	public K arrel();
	
	public boolean esborrar( K k);
	
	public boolean existeix( K k);
	
	public V buscarElement(K k);
	
	public int numNodes();
	
	public int alcada();
	
	public K maxim();
	
	public K minim();
	
	public K predecessor(K k);
	
	public K successor(K k);
	
	public TAD_ABC<K, V> fillEsq();
	
	public TAD_ABC<K, V> fillDret();
	
	public boolean esBuit();
	
	public LlistaGenericaNoOrd<K> preordre();
	
	public LlistaGenericaNoOrd<K> inordre();
	
	public LlistaGenericaNoOrd<K> postordre();

	public TAD_ABC<K, V> clone();

}