package TAD;

import Interface.TAD_ABC;
import Tipus.Index;

public class ABCdinamic<K extends Comparable<K>, V> implements TAD_ABC<K, V>, Cloneable {
	@SuppressWarnings("hiding")
	
	
	private class NodeABC<K extends Comparable<K>, V> implements Cloneable {
		private K k;
		private V v;
		private ABCdinamic<K, V> fe;
		private ABCdinamic<K, V> fd;
		private ABCdinamic<K, V> p;
		private int balance;
        private int height;
		
		/**public NodeABC(K k, V v) {
			this.k=k;
			this.v = (V) new Index();
			fe=null;
			fd=null;
		}**/
		
		@SuppressWarnings("unchecked")
		public NodeABC(K k, V v, ABCdinamic<K,V> p) {
			this.k=k;
			this.v = (V) new Index();
			fe=null;
			fd=null;
			this.p = p;
		}

		@Override
		public String toString() {
			return "NodeABC [k=" + k + ", v=" + v + ", fe=" + fe + ", fd=" + fd + "]";
		}

		@SuppressWarnings("unchecked")
		@Override
		protected NodeABC<K,V> clone() {
			NodeABC<K, V> obj=null;
			try{
	            obj=(NodeABC<K, V>)super.clone();
	        }catch(CloneNotSupportedException ex){
	            System.out.println("No es pot duplicar");
	        }
			return obj;
		}
		
		
	}
	
	private NodeABC<K, V> arrel;
	//private int numNodes; el trec, sino cada subarbre te el seu numNodes i no s'actualitza correctament
	// per a tenir el numNodes, els fe i fd, haurien de ser punters a NodeABC...
	
	public ABCdinamic() {
		arrel=null;
	}
	/**
	public ABCdinamic(K k, V v) {
		arrel=new NodeABC<K,V>(k,v);
	}**/
	
	public ABCdinamic(K k, V v, ABCdinamic<K,V> p) {
		arrel=new NodeABC<K,V>(k,v, p);
	}
	
	public ABCdinamic(NodeABC<K,V> n) {
		arrel = n;
	}
	/**
	 * Reequilibra un node rebut per peràmetre
	 * @param n
	 */
	private void rebalance(NodeABC<K,V> n) {
        setBalance(n);	/** Recalculem el factor d'equilibri de l'arbre**/
        if (n.balance == -2) /** Si el factor d'equilibri és 2 dos, vol dir que tenim més nodes a a la esquerra**/
        { /** Si l'altura fe del fe és més gran que el fd del fe llavors fem rotació simple a la dreta **/
        	if (height(n.fe.arrel.fe) >= height(n.fe.arrel.fd)) n = rotateRight(n);		 
            else n = rotateLeftThenRight(n); /** Sinó vol dir que l'arbre necessita doble rotació esquerra i després dreta **/
        } 
        else if (n.balance == 2) 
        { /** Si l'altura fd del fd és més gran que el fe del fd llavors fem rotació simple a l'esquerre **/
            if (height(n.fd.arrel.fd) >= height(n.fd.arrel.fe)) n = rotateLeft(n);
            else n = rotateRightThenLeft(n); /** Sinó vol dir que l'arbre necessita doble rotació dreta i després esquerra **/
        }
        if (n.p != null) rebalance(n.p.arrel);	/** Si tenim un arbre pare, llavors balancegem el node arrel del pare **/
       // else arrel = n;		/** fem un set del nou arbre equilibrat **/
    }
 
	/**
	 * Mètode que retorna true si afegeix un nou element de manera ordenada a l'arbre AVL i reequilibra tots 
	 * els nodes per a arribar a l'arrel. Si hi ha un amb el mateix identificador retorna false.  
	 */
	public boolean afegir(K k, V v) {
		if (esBuit())	/** Si és buit creem el node a l'arrel **/
		{
			arrel=new NodeABC<K,V>(k,v, null);
		} 
		else 
		{
			if (arrel.k.equals(k)) return false;	/** Si ja s'ha afegit retorna fals **/
			else if (arrel.k.compareTo(k)>0) 
			{	/** Arbre esquerra **/
				if (arrel.fe!=null) arrel.fe.afegir(k, v);	/** Si el fill esquerra està buit, executem afegir sobre ell **/
				else 
				{
					arrel.fe=new ABCdinamic<K,V>(k,v, this);	/** Si està buit, creem el nou node **/
					rebalance(this.arrel); 	/** I rebalancegem l'arrel **/
				}
			}
			else if (arrel.k.compareTo(k)<0) 	
			{ /** Arbre dret **/
				if (arrel.fd!=null) arrel.fd.afegir(k, v); /** Si el fill esquerra està buit, executem afegir sobre ell **/
				else 
				{
					arrel.fd=new ABCdinamic<K,V>(k,v, this); /** Si està buit, creem el nou node **/
					rebalance(this.arrel); /** I rebalancegem l'arrel **/
				}
			}
		}
		return true; /** Hem afegit, per tant retornem cert **/
	}

	/**
	 * Métode que efectua una rotació simple a l'esquerra sobre el node passat per paràmetre i recalcula els factors equilibri 
	 * del node retornat
	 * @param a Node al que se li fa la rotació
	 * @return Node equilibrat
	 */
    private NodeABC<K,V> rotateLeft(NodeABC<K,V> a) {
    	ABCdinamic<K,V> B = a.fd; /** Creem un arbre auxiliar que sigui el subarbre dret de l'arbre que s'ha d'equilibrar **/
    	ABCdinamic<K,V> A = new ABCdinamic<K,V>(a); /** Creem un arbre auxiliar que sigui l'arbre a equilibrar **/
    	B.arrel.p = A.arrel.p; /** Fem que el fill dret ara apunti al pare de a (fem la primera colocació de punters) **/
        A.arrel.fd = B.arrel.fe;	/** Ara el fill dret de a, apuntarà al subarbre que tenia b a la seva esquerra **/
        if (A.arrel.fd != null) A.arrel.fd.arrel.p = A;	/** Si el fill dret de a no és buit llavors fem que el fill dret apunti a A com a pare **/
        B.arrel.fe = A;		/** Fem que el fill esquerre de b apunti al subarbre a **/
        A.arrel.p = B;				/** Fem que el pare de a apunti a b **/
 
        if (B.arrel.p != null) {
            if (B.arrel.p.arrel.fd.arrel.k.equals(a.k)) B.arrel.p.arrel.fd = B;	/** Si fill dret del pare de A era A, llavors hem de conectar els punters a la dreta **/
            else B.arrel.p.arrel.fe = B; /** Sinó a l'esquerra **/
        }
        setBalance(A.arrel);	/** Recalculem els factors d'equilibri **/
        setBalance(B.arrel);
        return B.arrel;	/** Retornem el node ja equilibrat **/
    }
 
	/**
	 * Métode que efectua una rotació simple a la dreta sobre el node passat per paràmetre i recalcula els factors equilibri 
	 * del node retornat
	 * @param a Node al que se li fa la rotació
	 * @return Node equilibrat
	 */
    private NodeABC<K,V> rotateRight(NodeABC<K,V> a) {
    	ABCdinamic<K,V> B = a.fe; /** Creem un arbre auxiliar que sigui el subarbre esquerra de l'arbre que s'ha d'equilibrar **/
    	ABCdinamic<K,V> A = new ABCdinamic<K,V>(a); /** Creem un arbre auxiliar que sigui l'arbre a equilibrar **/
    	B.arrel.p = A.arrel.p; /** Fem que el fill dret ara apunti al pare de a (fem la primera colocació de punters) **/
        A.arrel.fe = B.arrel.fd;	/** Ara el fill esquerra de a, apuntarà al subarbre que tenia b a la seva dreta **/ 
        if (A.arrel.fe != null) A.arrel.fe.arrel.p = A; /** Si el fill dret de a no és buit llavors fem que el fill dret apunti a A com a pare **/
        B.arrel.fd = A; /** Fem que el fill esquerre de b apunti al subarbre a **/
        A.arrel.p = B; /** Fem que el pare de a apunti a b **/
        		
        if (B.arrel.p != null) 
        {
        	if (B.arrel.p.arrel.fd.arrel.k.equals(a.k)) B.arrel.p.arrel.fd = B;	/** Si fill dret del pare de A era A, llavors hem de conectar els punters a la dreta **/
        	else B.arrel.p.arrel.fe = B; /** Sinó a l'esquerra **/
        }
        setBalance(A.arrel);	/** Recalculem els factors d'equilibri **/
        setBalance(B.arrel);
        return B.arrel;	/** Retornem el node ja equilibrat **/
    }
    
    /**
     * Aplica una rotació a la esquerra al 
     * @param n
     * @return
     */
    private NodeABC<K,V> rotateLeftThenRight(NodeABC<K,V> n) {
        n.fe.arrel = rotateLeft(n.fe.arrel);
        return rotateRight(n);
    }
 
    private NodeABC<K,V> rotateRightThenLeft(NodeABC<K,V> n) {
        n.fd.arrel = rotateRight(n.fd.arrel);
        return rotateLeft(n);
    }
 
    private int height(ABCdinamic<K, V> n) {
        if (n == null)
            return -1;
        return n.arrel.height;
    }
 
    private void setBalance(NodeABC<K, V> n) {
            reheight(n);
			n.balance = height(n.fd) - height(n.fe);
    }
    
    private void reheight(NodeABC<K,V> node){
        if(node!=null){
            node.height=1 + Math.max(height(node.fe), height(node.fd));
        }
    }
	public K arrel() {
		if (arrel!=null) return (arrel.k);
		return null;
	}
	
	/**
	 * Métode que afegeix una aparició a l'element V. Crida el métode d'afegir aparició sobre l'element consultat
	 */
	public boolean afegirAparicio(K k, int plana, int linia){
		V v = consultar(k);	/** Creem un auxiliar per al consultar **/
		if (v != null)	/** si no està buit **/
		{
			((Index)v).AfegirAparicio(plana, linia);	/** Fem el cast i afegim aparició sobre ell **/
			return true;	/** Retornem cert **/
		}
		else return false;	/** Vol dir que l'element era null, per tant no hem afegit res **/
	}
	
	public boolean esborrar(K k) {
		if (arrel!=null) 
		{
			if (arrel.k.compareTo(k)>0) {
				if (arrel.fe!=null){
					arrel.fe=esborrar(k, arrel.fe);
					rebalance(arrel.p.arrel);
					return true;
				}		
			}
			else if (arrel.k.compareTo(k)<0) 
			{
				if (arrel.fd!=null)
				{
					arrel.fd=esborrar(k, arrel.fd);
					rebalance(arrel.p.arrel);
					return true;
				}
					
					
			} else {
				// he d'esborrar l'arrel
				// arrel no te fills
				if ((arrel.fd==null) && (arrel.fe==null)) {
					arrel=null;
					return true;
				}
				else {
					// arrel te un fill o dos
					if ((arrel.fd==null) && (arrel.fe!=null)) {
						//l'arrel te el fe
						arrel=arrel.fe.arrel;
						rebalance(arrel.p.arrel);
						return true;
					}
					if ((arrel.fd!=null) && (arrel.fe==null)) {
						//l'arrel te el fd
						arrel=arrel.fd.arrel;
						rebalance(arrel.p.arrel);
						return true;
					}
					if ((arrel.fd!=null) && (arrel.fe!=null)) {
						// arrel te dos fills
						K succKlau=arrel.fd.minim();
						V succValor=consultar(succKlau);
						esborrar(succKlau);
						arrel.k=succKlau;
						arrel.v=succValor;
					}
				}
			}
		}
		return true; // cal cambiar el restorn i colocarlo be
	}

	private ABCdinamic<K,V> esborrar(K k, ABCdinamic<K,V> arbre) {
		if (arbre!=null) {
			if (arbre.arrel.k.compareTo(k)>0) {
				if (arbre.arrel.fe!=null){
					arbre.arrel.fe=esborrar(k, arbre.arrel.fe);
					rebalance(arrel.p.arrel);
				}
			}
			if (arbre.arrel.k.compareTo(k)<0) {
				if (arbre.arrel.fd!=null){
					arbre.arrel.fd=esborrar(k, arbre.arrel.fd);
					rebalance(arrel.p.arrel);
				}
			}
			if (arbre.arrel.k.compareTo(k)==0) {
				if ((arbre.arrel.fe!=null) && (arbre.arrel.fd!=null)) {
					// te dos fills
					K succKlau=arbre.arrel.fd.minim();
					V succValor=consultar(succKlau);
					esborrar(succKlau);
					rebalance(arrel.p.arrel);
					arbre.arrel.k=succKlau;
					arbre.arrel.v=succValor;
					
				} else {
					// te un o cap fill
					if (arbre.arrel.fe!=null) {
						arbre=(ABCdinamic<K, V>) arbre.fillEsq();
					} else arbre=(ABCdinamic<K, V>) arbre.fillDret();
				}
			}
		}
		return arbre;
	}
	
	public V consultar(K k) {
		if (arrel==null) return null;
		else if (arrel.k.equals(k)) return arrel.v;
		else if (arrel.k.compareTo(k)>0) {
			if (arrel.fe!=null) return(arrel.fe.consultar(k));
			else return null;
		}
		else {
			if (arrel.fd!=null) return(arrel.fd.consultar(k));
			else return null;
		}
	}


	public boolean existeix(K k) {
		if (arrel==null) return false;
		else if (arrel.k.equals(k)) return true;
		else if (arrel.k.compareTo(k)>0) {
			if (arrel.fe!=null)
				return(arrel.fe.existeix(k));
			else return false;
		}
		else {
			if (arrel.fd!=null)
				return(arrel.fd.existeix(k));
			else return(false);
		}
	}

	public int numNodes() {
		LlistaGenericaNoOrd<K> llista=this.inordre();
		return llista.getNum();
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

	public K maxim() {
		// tenim dos opcions per a calcular el resultat
		// 1. ultim element del recorregut inordre - cost O(n)
		LlistaGenericaNoOrd<K> llista=this.inordre();
		return llista.consultarIessim(llista.getNum()-1);
		// 2. seguiment pels fills drets fins arribar a un que no en te - cost O(alçada arbre)
		/*if (arrel==null) return null;
		else {
			if (arrel.fd!=null) {
				ABCdinamic<K, V> aux=arrel.fd;
				while (aux.fillDret()!=null) {
					aux=(ABCdinamic<K, V>) aux.fillDret();
				}
				return (aux.arrel.k);
			} else return arrel.k;
		}*/
	}

	public K minim() {
		// tenim dos opcions per a calcular el resultat
		// 1. primer element del recorregut inordre - cost O(n)
		/*LlistaGenericaNoOrd<K> llista=this.inordre();
		return llista.consultarIessim(0);*/
		// 2. seguiment pels fills esquerres fins arribar a un que no en te - cost O(alçada arbre)
		if (arrel==null) return null;
		else {
			if (arrel.fe!=null) {
				ABCdinamic<K, V> aux=arrel.fe;
				while (aux.fillEsq()!=null) {
					aux=(ABCdinamic<K, V>) aux.fillEsq();
				}
				return (aux.arrel.k);
			} else return arrel.k;
		}
	}

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
				  }
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

	public TAD_ABC<K, V> fillEsq() {
		if ((arrel!=null)&&(arrel.fe!=null)) return(arrel.fe.clone());
		return null;
	}

	public TAD_ABC<K, V> fillDret() {
		if ((arrel!=null)&&(arrel.fd!=null)) return(arrel.fd.clone());
		return null;
	}

	public boolean esBuit() {
		return (arrel==null);
	}

	public LlistaGenericaNoOrd<K> preordre() {
		LlistaGenericaNoOrd<K> llista=new LlistaGenericaNoOrd<K>(10);
		if (arrel!=null) {
			llista.afegirElement(arrel.k);
			if (arrel.fe!=null) llista.afegirElement(arrel.fe.preordre());
			if (arrel.fd!=null) llista.afegirElement(arrel.fd.preordre());
		}
		return llista;
	}

	public LlistaGenericaNoOrd<K> inordre() {
		LlistaGenericaNoOrd<K> llista=new LlistaGenericaNoOrd<K>(10);
		if (arrel!=null) {
			if (arrel.fe!=null) llista.afegirElement(arrel.fe.inordre());
			llista.afegirElement(arrel.k);
			if (arrel.fd!=null) llista.afegirElement(arrel.fd.inordre());
		}
		return llista;
	}

	public LlistaGenericaNoOrd<K> postordre() {
		LlistaGenericaNoOrd<K> llista=new LlistaGenericaNoOrd<K>(10);
		if (arrel!=null) {
			if (arrel.fe!=null) llista.afegirElement(arrel.fe.postordre());
			if (arrel.fd!=null) llista.afegirElement(arrel.fd.postordre());
			llista.afegirElement(arrel.k);
		}
		return llista;
	}

	@SuppressWarnings("unchecked")
	public TAD_ABC<K, V> clone() {
		ABCdinamic<K, V> obj=null;
		try{
            obj=(ABCdinamic<K, V>)super.clone();
        }catch(CloneNotSupportedException ex){
            System.out.println("No es pot duplicar");
        }
		obj.arrel=obj.arrel.clone();
		if (obj.fillEsq()!=null) obj.arrel.fe = (ABCdinamic<K,V>)obj.fillEsq().clone();
		if (obj.fillDret()!=null) obj.arrel.fd = (ABCdinamic<K,V>)obj.fillDret().clone();
        return obj;
	}

	@Override
	public String toString() {
		return "ABCdinamic [arrel=" + arrel + "]";
	}

	
}