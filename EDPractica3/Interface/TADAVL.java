package Interface;
/**
 * Interface per a definir l'arbre binari AVL per a la pràctica 3
 * 
 * @author Aleix Mariné
 *
 */
public interface TADAVL<K extends Comparable<K>, E> extends TADIndex<K, E>{

	/**
	 * Afegeix un element del que es fa seguiment a l'index
	 * @param k
	 */
	public boolean afegir(K k);
	/**
	 * Afegim una nova aparició de l'element K
	 * @param k
	 */
	public boolean afegirAparicio(K k);
	/**
	 * Esborra un element de l'index (deixa de fer el seguiment)
	 */
	public boolean esborrar(K k);
	/**
	 * Consulta aparicions d'un element de l'índex
	 */	
	public E consultar(K k);
}
