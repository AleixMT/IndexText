package TAD;


public class ArbreBinariDinamic<K extends Comparable<K, E> implements TADAVL<K, E> {
	
	private Node<K,E> arrel;
	
	public AVLdinamic(){
		arrel = null;
	}
	public AVLdinamic(K k) {
		arrel=new NodeAVL<K, E>(k);
	}
	/**
	 * Rep un element E, si l'arrel de l'arbre és buida la plena, sinó crida al mètode 
	 * afegir del NodeAVL, que efectua una crida recursiva d'aquest mètode sobre els
	 * diferents NodesAVL
	 */
	public boolean afegir(K k) {
		
	}

	/*
	 * FALTA!!!! 
	 */
	/**
	 * Esborra un Node a partir de la seva clau k
	 */
	public boolean esborrar(K k) {
		return false;
	}

	public int alcada() {
		return this.arrel.alcada();
	}

	@Override
	public K predecessor(K k) {
		if (arrel==null) return null;
		else {
		  if (arrel.k.equals(k)) {
			// hem trobat l'element a l'arrel de l'arbre
			  if (arrel.fe!=null) return(arrel.fe.maxim());
			  else return null; // si l'arrel no te fill esquerre es que no te predecessor
		  }
		  else {
			  // anirem buscant l'element en alguna de les branques de l'arbre
			  ABCdinamic<K, V> copia;
			  NodeABC<K, V> avantpassat=null;
			  boolean trobat=false;
			  if (arrel.k.compareTo(k)<0) {
				  	copia=arrel.fd;
				  	avantpassat=arrel; // anem guardant l'ultim avantpassat en el qual ens hem desviat cap al subarbre dret
			  }
			  else {
				  copia=arrel.fe;
			  }
			  while (copia!=null && !trobat) {
				  if (copia.arrel.k.equals(k)) {
					  // hem trobat l'element, mirem si te fill esquerre
					  trobat=true;
					  if (copia.arrel.fe!=null) return(copia.arrel.fe.maxim());
					  else 
						  if (avantpassat!=null) return(avantpassat.k);
						  else return(null);
				  }
				  if (copia.arrel.k.compareTo(k)<0) {
					  avantpassat=copia.arrel;
					  copia=copia.arrel.fd;
				  }
				  else {
					  copia=copia.arrel.fe;
				  }
			  }
			  return(null);
		  }
		}
	}

	@Override
	public K successor(K k) {
		if (arrel==null) return null;
		else {
		  if (arrel.k.equals(k)) {
			// hem trobat l'element a l'arrel de l'arbre
			  if (arrel.fd!=null) return(arrel.fd.minim());
			  else return null; // si l'arrel no te fill dret es que no te successor
		  }
		  else {
			  // anirem buscant l'element en alguna de les branques de l'arbre
			  ABCdinamic<K, V> copia;
			  NodeABC<K, V> avantpassat=null;
			  boolean trobat=false;
			  if (arrel.k.compareTo(k)<0) {
				  	copia=arrel.fd;
			  }
			  else {
				  copia=arrel.fe;
				  avantpassat=arrel; // anem guardant l'ultim avantpassat en el qual ens hem desviat cap al subarbre esquerre
			  }
			  while (copia!=null && !trobat) {
				  if (copia.arrel.k.equals(k)) {
					  // hem trobat l'element, mirem si te fill dret
					  trobat=true;
					  if (copia.arrel.fd!=null) return(copia.arrel.fd.minim());
					  else 
						  if (avantpassat!=null) return(avantpassat.k);
						  else return(null);
				  }arrel.getFe()
				  if (copia.arrel.k.compareTo(k)<0)
					  copia=copia.arrel.fd;
				  else {
					  avantpassat=copia.arrel;
					  copia=copia.arrel.fe;
				  }
				  
			  }
			  return(null);
		  }
		}
	}

	@Override
	public TAD_ABC<K, V> fillEsq() {
		if ((arrel!=null)&&(arrel.fe!=null)) return(arrel.fe.clone());
		return null;
	}

	@Override
	public TAD_ABC<K, V> fillDret() {
		if ((arrel!=null)&&(arrel.fd!=null)) return(arrel.fd.clone());
		return null;
	}

	public boolean esBuit() {
		return (this==null);
	}
	public void arrelar(NodeAVL<K, E> arrel, NodeAVL<K, E> fe, NodeAVL<K, E> fd) {
		// TODO Auto-generated method stub
		
	}
	public boolean afegirAparicio(K k) {
		// TODO Auto-generated method stub
		return false;
	}
	public E consultar(K k) {
		if (this.arrel==null) return null;
		else if (this.arrel.getK().equals(k)) return this.arrel.getE();
		else if (arrel.getK().compareTo(k)>0) 
		{
			if (arrel.fe!=null) return(arrel.fe.buscarElement(k));
			else return null;
		}
		else {
			if (arrel.fd!=null) 
				return(arrel.fd.buscarElement(k));
			else return null;
		}		return null;
	}
	public boolean esEquilibrat() {
		// TODO Auto-generated method stub
		return false;
	}
	public LlistaGenericaNoOrd<E> inordre() {
		// TODO Auto-generated method stub
		return null;
	}
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
