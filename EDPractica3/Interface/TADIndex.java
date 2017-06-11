package Interface;

public abstract interface TADIndex <K extends Comparable<K>, V>{
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
	 * Consulta un element (paraula) de l'índex
	 */	
	public V consultar(K k);
	/**
	 * Mostra per pantalla tot l'index
	 */
	public String toString();
	/**
	 * Mira si existeix l'element amb la clau k
	 * @param k
	 * @return
	 */
	boolean existeix(K k);
}
