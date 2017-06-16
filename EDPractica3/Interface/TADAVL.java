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
	
	public boolean afegir( K k, V v);
		
	public boolean esborrar( K k);
	
	public boolean existeix( K k);
	
	public V consultar(K k);
	
	public int numNodes();
	
	public int height(AVLdinamic<K, V> k);
		
	public K maxim();
	
	public K minim();
	
	public boolean esBuit();
	
	public LlistaGenericaNoOrd<K> preordre();
	
	public LlistaGenericaNoOrd<K> inordre();
	
	public LlistaGenericaNoOrd<K> postordre();
	
	public TADAVL<K,V> clone();

}