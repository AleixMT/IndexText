package TAD;


public class Node<E> {
	private E element;
	private TADArbreBinari<E> fillEsq, fillDret;
	
	public Node (E elem) {
		element=elem;
		fillEsq=null;
		fillDret=null;
	}
	
	public Node (TADArbreBinari<E> fllEsq, E elem, TADArbreBinari<E> fllDret) {
		element=elem;
		fillEsq=fllEsq;
		fillDret=fllDret;
	}

	public E getElement() {
		return element;
	}

	public void setElement(E element) {
		this.element = element;
	}

	public TADArbreBinari<E> getFillEsq() {
		return fillEsq;
	}

	public void setFillEsq(TADArbreBinari<E> fillEsq) {
		this.fillEsq = fillEsq;
	}

	public TADArbreBinari<E> getFillDret() {
		return fillDret;
	}

	public void setFillDret(TADArbreBinari<E> fillDret) {
		this.fillDret = fillDret;
	}

	@Override
	public String toString() {
		return "Node [element=" + element + ", fillEsq=" + fillEsq + ", fillDret=" + fillDret + "]";
	}
	
	
}
