package Interface;


/**
 * Interface per a definir una taula de hash generica.
 * 
 * @author Professors de l'assignatura 16-17
 *
 */

public abstract interface TADTaulaHashGenerica<K extends Comparable<K>,V> extends TADIndex <K, V>{

	/**
	 * Afegeix un element a la taula de hash
	 * @param k - clau de l'element a afegir
	 * @param v - element a afegir
	 */
	public boolean afegir(K k, V v);
	
	/**
	 * Afegeix una aparicio d'un element a la taula de hash
	 * @param k - clau de l'element on hem d'afegir l'aparicio
	 * @param plana - plana on es troba l'element
	 * @param linia - linia on es troba l'element
	 */
	public boolean afegirAparicio(K k, int plana, int linia);
	
	/**
	 * Esborra un element a la taula de hash
	 * @param k - clau de l'element a esborrar
	 */	
	public boolean esborrar(K k);
	
	/**
	 * Consulta un element a la taula de hash
	 * @param k - clau de l'element a consultar
	 */	
	public V consultar(K k);	
	
	/**
	 * Retorna el factor de carrega actual de la taula de hash
	 */
	public float getFactorDeCarrega();
	/**
	 * Mostra per pantalla tot l'index
	 */
	public String toString();
}
