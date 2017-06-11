package Interface;


/**
 * Interface per a definir una taula de hash generica.
 * 
 * @author Professors de l'assignatura 16-17
 *
 */
public interface TADTaulaHashGenerica<K,E> {
	/**
	 * Afegeix un element a la taula de hash
	 * @param k - clau de l'element a afegir
	 * @param v - element a afegir
	 */
	public void afegir(K k, E e);
	
	/**
	 * Esborra un element a la taula de hash
	 * @param k - clau de l'element a esborrar
	 */	
	public E esborrar(K k);
	
	/**
	 * Consulta un element a la taula de hash
	 * @param k - clau de l'element a consultar
	 */	
	public E consultar(K k);	
	
	/**
	 * Retorna el factor de c�rrega actual de la taula de hash
	 */
	public float getFactorDeCarrega();
		
	/**
	 * Mostra la taula de hash per consola
	 */
	public void mostrarTaula();
	/**
	 * Mostra per pantalla tot l'index
	 */
	public String toString();
}
