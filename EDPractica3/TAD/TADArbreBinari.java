package TAD;
/**
 * Interface per a definir l'arbre binari
 * 
 * @author Professors de l'assignatura 16-17
 *
 */
public interface TADArbreBinari<E> {
	/**
	 * Es construeix l'arbre format per l'arbre amb el node arrel a1 que passa a ser el fill esquerre 
	 * l'element que passa a ser l'arrel
	 * i l'arbre amb el node arrel a2 passa a ser el fill dret
	 * @param e - nova arrel de l'arbre
	 * @param a2 - fill dret del nou arbre
	 */
	public void arrelar(TADArbreBinari<E> a1, E e, TADArbreBinari<E> a2);
	/**
	 * Retorna l'element de l'arrel de l'arbre
	 * @return arrel de l'arbre
	 */
	public E arrel();
	/** 
	 * Retorna el fill esquerre de l'arrel
	 * @return l'arbre corresponent al fill esquerre de l'arrel de l'arbre actual
	 */
	public TADArbreBinari<E> fillEsq();
	/**
	 * Retorna el fill dret de l'arrel
	 * @return l'arbre corresponent al fill dret de l'arrel de l'arbre actual
	 */
	public TADArbreBinari<E> fillDret();
	/**
	 * Retorna un boolea que indica si l'arbre es buit
	 * @return si l'arbre es buit
	 */
	public boolean esBuit();
	
	// Operacions que complementen l'especificació bàsica dels arbres binaris
	
	/**
	 * Comprova si l'arbre binari es equilibrat
	 * @return cert si l'arbre es equilibrat
	 */
	public boolean esEquilibrat();
	/**
	 * Mètode que retorna la llista d'elements de l'arbre segons el recorregut preordre
	 * @return llista d'elements de l'arbre en preordre
	 */
	public LlistaGenericaNoOrd<E> preordre();
	/**
	 * Mètode que retorna la llista d'elements de l'arbre segons el recorregut inordre
	 * @return llista d'elements de l'arbre en inordre
	 */
	public LlistaGenericaNoOrd<E> inordre();
	/**
	 * Mètode que retorna la llista d'elements de l'arbre segons el recorregut postordre
	 * @return llista d'elements de l'arbre en postordre
	 */
	public LlistaGenericaNoOrd<E> postordre();
	
	public int alcada();

}
