package TAD;

import Interface.TADAVL;
import Tipus.Index;

public class AVLdinamic<K extends Comparable<K>, E> implements TADAVL<E> {
	
	private NodeAVL arrel;
	
	public AVLdinamic(){
		arrel = null;
	}
	public AVLdinamic(E e) {
		arrel=new NodeAVL<E>(e, null, null);
	}
	/**
	 * Rep un element E, si l'arrel de l'arbre és buida la plena, sinó crida al mètode 
	 * afegir del NodeAVL, que efectua una crida recursiva d'aquest mètode sobre els
	 * diferents NodesAVL
	 */
	public void afegir(E e) {
		if (this.esBuit()) 
		{
			this.arrel = new NodeAVL<E>(e);
		} 
		else 
		{
			this.arrel.afegir(e);
		}
	}


	public void esborrar(E e) {
		
	}

	@Override
	public Node buscarElement(E e) {
		if (arrel==null) return null;
		else if (arrel.k.equals(k)) return arrel.v;
		else if (arrel.k.compareTo(k)>0) {
			if (arrel.fe!=null)
				return(arrel.fe.buscarElement(k));
			else return null;
		}
		else {
			if (arrel.fd!=null) 
				return(arrel.fd.buscarElement(k));
			else return null;
		}
	}

	public int alcada() {
		if (arrel==null) return -1;
		else {
			int alcFE;
			if (arrel.fe!=null) alcFE=arrel.fe.alcada();
			else alcFE=-1;
			int alcFD;
			if (arrel.fd!=null) alcFD=arrel.fd.alcada();
			else alcFD=-1;
			int max;
			if (alcFE>alcFD) max=alcFE;
			else max=alcFD;
			return max+1;
		}
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

	
	
}
