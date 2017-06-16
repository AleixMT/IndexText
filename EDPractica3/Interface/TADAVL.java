package Interface;

import TAD.AVLdinamic;
import TAD.LlistaGenericaNoOrd;
import Interface.TADIndex;

/**
 * Interface per a definir l'arbre binari AVL per a la pràctica 3
 * 
 * @author Aleix Mariné
 *
 **/
public abstract interface TADAVL<K extends Comparable<K>, V> extends TADIndex<K, V>{
	
	/**
	 * Afegeix un nou element a l'arbre
	 */
	public boolean afegir( K k, V v);
		
	/**
	 * Esborra un element de l'arbre utilitzant la seva clau.
	 */
	public boolean esborrar( K k);
	/**
	 * Comprova si exiteix un element cercant-lo amb la seva clau. retorna cert si hi és, retorna fals si no
	 */
	public boolean existeix( K k);
	/**
	 * Retorna les dades d'un element a partir de la clau
	 */
	public V consultar(K k);
	/**
	 * Calcula el nombre de nodes i el retorna
	 * @return retorna nombre de nodes
	 */
	public int numNodes();
	/**
	 * Retorna l'altura d'un arbre rebut per paràmetre
	 * @param k arbre del que es vol calcular l'altura
	 * @return altura máxima
	 */
	public int height(AVLdinamic<K, V> k);
		/**
		 * Retorna l'element més gran de tot l'arbre
		 * @return clau de l'element
		 */
	public K maxim();
	/**
	 * Retorna l'element més petit de tot l'arbre
	 * @return clau de l'element
	 */
	public K minim();
	/**
	 * retorna cert si l'arbre està buit, sino retorna fals
	 * @return cert si l'estructura està buida flas si està plena
	 */
	public boolean esBuit();
	
	/**
	 * Retorna una llista amb el recorregut preordre 
	 * @return llista
	 */
	public LlistaGenericaNoOrd<K> preordre();
	
	/**
	 * Retorna una llista amb el recorregut inordre
	 * @return llista
	 */
	public LlistaGenericaNoOrd<K> inordre();
	/**
	 * Retorna una llista amb el recorregut postordre
	 * @return llista
	 */
	public LlistaGenericaNoOrd<K> postordre();
	/**
	 * retorna una instància de l'arbre clonada
	 * @return isntància de l'arbre
	 */
	public TADAVL<K,V> clone();

}