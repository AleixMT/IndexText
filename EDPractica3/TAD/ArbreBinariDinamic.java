package TAD;

import TAD.LlistaGenericaNoOrd;

public class ArbreBinariDinamic<E> implements TADArbreBinari<E> {
	private Node<E> arrel;
	
	public ArbreBinariDinamic () {
		arrel=null;
	}
	
	public ArbreBinariDinamic (Node<E> node) {
		arrel=node;
	}
	
	@Override
	public void arrelar(TADArbreBinari<E> a1, E e, TADArbreBinari<E> a2) {
		arrel=new Node<E>(a1, e, a2);
	}

	@Override
	public E arrel() {
		if (arrel!=null) return(arrel.getElement());
		return null;
	}

	@Override
	public TADArbreBinari<E> fillEsq() {
		if (arrel!=null) return(arrel.getFillEsq());
		return null;
	}

	@Override
	public TADArbreBinari<E> fillDret() {
		if (arrel!=null) return(arrel.getFillDret());
		return null;
	}

	@Override
	public boolean esBuit() {
		return (arrel==null);
	}

	@Override
	public boolean esEquilibrat() {
		if (arrel==null) return true;
		else {
			int alcFE=0, alcFD=0;
			boolean equilFE=true, equilFD=true;
			if (arrel.getFillEsq()!=null) {
				alcFE=arrel.getFillEsq().alcada();
				equilFE=arrel.getFillEsq().esEquilibrat();
			}
			if (arrel.getFillDret()!=null) {
				alcFD=arrel.getFillDret().alcada();
				equilFD=arrel.getFillDret().esEquilibrat();
			}
			int dif=Math.abs(alcFE-alcFD);
			
			return ((dif<=1) && (equilFE) && (equilFD));
		}
	}

	@Override
	public LlistaGenericaNoOrd<E> preordre() {
		LlistaGenericaNoOrd<E> llista=new LlistaGenericaNoOrd<E>(10);
		if (arrel!=null) {
			llista.afegirElement(arrel.getElement());
			if (arrel.getFillEsq()!=null) llista.afegirElement(arrel.getFillEsq().preordre());
			if (arrel.getFillDret()!=null) llista.afegirElement(arrel.getFillDret().preordre());
		}
		return llista;
	}

	@Override
	public LlistaGenericaNoOrd<E> inordre() {
		LlistaGenericaNoOrd<E> llista=new LlistaGenericaNoOrd<E>(10);
		if (arrel!=null) {
			if (arrel.getFillEsq()!=null) llista.afegirElement(arrel.getFillEsq().inordre());
			llista.afegirElement(arrel.getElement());
			if (arrel.getFillDret()!=null) llista.afegirElement(arrel.getFillDret().inordre());
		}
		return llista;
	}

	@Override
	public LlistaGenericaNoOrd<E> postordre() {
		LlistaGenericaNoOrd<E> llista=new LlistaGenericaNoOrd<E>(10);
		if (arrel!=null) {
			if (arrel.getFillEsq()!=null) llista.afegirElement(arrel.getFillEsq().postordre());
			if (arrel.getFillDret()!=null) llista.afegirElement(arrel.getFillDret().postordre());
			llista.afegirElement(arrel.getElement());
		}
		return llista;
	}

	@Override
	public int alcada() {
		if (arrel==null) return -1;
		else {
			int alcFE;
			if (arrel.getFillEsq()!=null) alcFE=arrel.getFillEsq().alcada();
			else alcFE=-1;
			int alcFD;
			if (arrel.getFillDret()!=null) alcFD=arrel.getFillDret().alcada();
			else alcFD=-1;
			int max;
			if (alcFE>alcFD) max=alcFE;
			else max=alcFD;
			return max+1;
		}
	}
	
}
