package Interface;

public abstract interface TADIndex <K extends Comparable<K>, E>{
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
	 * Consulta un element (paraula) de l'índex
	 */	
	public E consultar(K k);
}
