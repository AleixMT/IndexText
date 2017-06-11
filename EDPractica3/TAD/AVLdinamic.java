package TAD;

import Interface.TADAVL;
import Tipus.Index;

public class AVLdinamic<K extends Comparable<K>, E> implements TADAVL<K, E> {
	
	private NodeAVL<K, E> arrel;
	
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

	
	
}
