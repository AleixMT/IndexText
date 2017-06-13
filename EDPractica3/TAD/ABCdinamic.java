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
		
		public NodeABC(K k, V v) {
			this.k=k;
			this.v=v;
			fe=null;
			fd=null;
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
	
	public ABCdinamic(K k, V v) {
		arrel=new NodeABC<K,V>(k,v);
	}
	
	public boolean afegir(K k, V v) {
		if (esBuit()) {
			arrel=new NodeABC<K,V>(k,v);
		} else {
			if (arrel.k.equals(k)) arrel.v=v;
			else if (arrel.k.compareTo(k)>0) {
				if (arrel.fe!=null)
					arrel.fe.afegir(k, v);
				else {
					arrel.fe=new ABCdinamic<K,V>(k,v);
				}
			}
			else if (arrel.k.compareTo(k)<0) {
				if (arrel.fd!=null)
					arrel.fd.afegir(k, v);
				else {
					arrel.fd=new ABCdinamic<K,V>(k,v);
				}
			}
		}
		return true; // cal colocar els booleans
	}

	public K arrel() {
		if (arrel!=null) return (arrel.k);
		return null;
	}

	public boolean afegirAparicio(K k, int plana, int linia){
		Index i;
		if ((i = (Index)(consultar(k)))!=null)
		{
			i.AfegirAparicio(plana, linia);
			return true;
		}
		return false;
	}
	
	public boolean esborrar(K k) {
		if (arrel!=null) {
			if (arrel.k.compareTo(k)>0) {
				if (arrel.fe!=null){
					arrel.fe=esborrar(k, arrel.fe);
					return true;
				}		
			}
			else if (arrel.k.compareTo(k)<0) {
				if (arrel.fd!=null){
					arrel.fd=esborrar(k, arrel.fd);
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
						return true;
					}
					if ((arrel.fd!=null) && (arrel.fe==null)) {
						//l'arrel te el fd
						arrel=arrel.fd.arrel;
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
				if (arbre.arrel.fe!=null)
					arbre.arrel.fe=esborrar(k, arbre.arrel.fe);
			}
			if (arbre.arrel.k.compareTo(k)<0) {
				if (arbre.arrel.fd!=null)
					arbre.arrel.fd=esborrar(k, arbre.arrel.fd);
			}
			if (arbre.arrel.k.compareTo(k)==0) {
				if ((arbre.arrel.fe!=null) && (arbre.arrel.fd!=null)) {
					// te dos fills
					K succKlau=arbre.arrel.fd.minim();
					V succValor=consultar(succKlau);
					esborrar(succKlau);
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
	/* AQUI ESTAN LOS NUEVOS METODOS
	 * @param n
	 */
	private void rebalance(NodeABC<K,V> n) {
        setBalance(n);
        if (n.balance == -2) {
            if (height(n.fe.arrel.fe) >= height(n.fe.arrel.fd))
                n = rotateRight(n);
            else
                n = rotateLeftThenRight(n);
 
        } else if (n.balance == 2) {
            if (height(n.fd.arrel.fd) >= height(n.fd.arrel.fe))
                n = rotateLeft(n);
            else
                n = rotateRightThenLeft(n);
        }
 
        if (n.p.arrel != null) {
            rebalance(n.p.arrel);
        } else {
            arrel = n;
        }
    }
 
    private NodeABC<K,V> rotateLeft(NodeABC<K,V> a) {
 
        NodeABC<K, V> b = a.fd.arrel;
        b.p.arrel = a.p.arrel;
 
        a.fd.arrel = b.fe.arrel;
 
        if (a.fd.arrel != null)
            a.fd.arrel.p.arrel = a;
 
        b.fe.arrel = a;
        a.p.arrel = b;
 
        if (b.p != null) {
            if (b.p.arrel.fd.arrel == a) {
            	b.p.arrel.fd.arrel = b;
            } else {
            	b.p.arrel.fe.arrel = b;
            }
        }
 
        setBalance(a);
        setBalance(b);

        return b;
    }
 
    private NodeABC<K,V> rotateRight(NodeABC<K,V> a) {
 
        NodeABC<K, V> b = a.fe.arrel;
        b.p.arrel = a.p.arrel;
 
        a.fe.arrel = b.fd.arrel;
 
        if (a.fe.arrel != null)
            a.fe.arrel.p.arrel = a;
 
        b.fd.arrel = a;
        a.p.arrel = b;
 
        if (b.p.arrel != null) {
            if (b.p.arrel.fd.arrel == a) {
                b.p.arrel.fd.arrel = b;
            } else {
                b.p.arrel.fe.arrel = b;
            }
        }
 
        setBalance(a);
        setBalance(b);

        return b;
    }
 
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

	public V consultar(K k) {
		if (arrel==null) return null;
		else if (arrel.k.equals(k)) return arrel.v;
		else if (arrel.k.compareTo(k)>0) {
			if (arrel.fe!=null)
				return(arrel.fe.consultar(k));
			else return null;
		}
		else {
			if (arrel.fd!=null) 
				return(arrel.fd.consultar(k));
			else return null;
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