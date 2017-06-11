package Interface;


import TAD.NodeHash;
import Tipus.Index;

public interface TADTaulaHashGenerica<E extends Index> extends TADIndex<E>{
	/**
	 * Afegeix un element a la taula de hash
	 * @param k - clau de l'element a afegir
	 * @param v - element a afegir
	 */
	public void afegir(E e);
	
	/**
	 * Esborra un element a la taula de hash
	 * @param k - clau de l'element a esborrar
	 */	
	public void esborrar(E e);

	
	/**
	 * Consulta un element a la taula de hash
	 * @param k - clau de l'element a consultar
	 */	
	public NodeHash<E> consultar(E e);	

	
	/**
	 * Retorna el factor de c�rrega actual de la taula de hash
	 */
	public float getFactorDeCarrega();
		
	/**
	 * Mostra la taula de hash per consola
	 */
	public void mostrarTaula();
}
