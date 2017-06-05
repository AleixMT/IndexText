package TAD;

public class ABCdinamic<K extends Comparable<K>, V> implements TAD_ABC<K, V>, Cloneable {
	
	
	private NodeABC<K, V> arrel;
	//private int numNodes; el trec, sino cada subarbre te el seu numNodes i no s'actualitza correctament
	// per a tenir el numNodes, els fe i fd, haurien de ser punters a NodeABC...
	
	public ABCdinamic() {
		arrel=null;
	}
	
	public ABCdinamic(K k, V v) {
		arrel=new NodeABC<K,V>(k,v);
	}
	
	@Override
	public void inserir(K k, V v) {
		if (esBuit()) {
			arrel=new NodeABC<K,V>(k,v);
		} else {
			if (arrel.k.equals(k)) arrel.v=v;
			else if (arrel.k.compareTo(k)>0) {
				if (arrel.fe!=null)
					arrel.fe.inserir(k, v);
				else {
					arrel.fe=new ABCdinamic<K,V>(k,v);
				}
			}
			else if (arrel.k.compareTo(k)<0) {
				if (arrel.fd!=null)
					arrel.fd.inserir(k, v);
				else {
					arrel.fd=new ABCdinamic<K,V>(k,v);
				}
			}
		}
	}

	@Override
	public K arrel() {
		if (arrel!=null) return (arrel.k);
		return null;
	}

	@Override
	public void esborrar(K k) {
		if (arrel!=null) {
			if (arrel.k.compareTo(k)>0) {
				if (arrel.fe!=null)
					arrel.fe=esborrar(k, arrel.fe);
			}
			else if (arrel.k.compareTo(k)<0) {
				if (arrel.fd!=null)
					arrel.fd=esborrar(k, arrel.fd);
			} else {
				// he d'esborrar l'arrel
				// arrel no te fills
				if ((arrel.fd==null) && (arrel.fe==null)) {
					arrel=null;
				}
				else {
					// arrel te un fill o dos
					if ((arrel.fd==null) && (arrel.fe!=null)) {
						//l'arrel te el fe
						arrel=arrel.fe.arrel;
					}
					if ((arrel.fd!=null) && (arrel.fe==null)) {
						//l'arrel te el fd
						arrel=arrel.fd.arrel;
					}
					if ((arrel.fd!=null) && (arrel.fe!=null)) {
						// arrel te dos fills
						K succKlau=arrel.fd.minim();
						V succValor=buscarElement(succKlau);
						esborrar(succKlau);
						arrel.k=succKlau;
						arrel.v=succValor;
					}
				}
			}
		}
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
					V succValor=buscarElement(succKlau);
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
	
	@Override
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

	@Override
	public V buscarElement(K k) {
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

	@Override
	public int numNodes() {
		LlistaGenericaNoOrd<K> llista=this.inordre();
		return llista.getNum();
	}

	@Override
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
	public K maxim() {
		// tenim dos opcions per a calcular el resultat
		// 1. ultim element del recorregut inordre - cost O(n)
		LlistaGenericaNoOrd<K> llista=this.inordre();
		return llista.consultarIessim(llista.getNum()-1);
		// 2. seguiment pels fills drets fins arribar a un que no en te - cost O(al�ada arbre)
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

	@Override
	public K minim() {
		// tenim dos opcions per a calcular el resultat
		// 1. primer element del recorregut inordre - cost O(n)
		/*LlistaGenericaNoOrd<K> llista=this.inordre();
		return llista.consultarIessim(0);*/
		// 2. seguiment pels fills esquerres fins arribar a un que no en te - cost O(al�ada arbre)
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

	@Override
	public boolean esBuit() {
		return (arrel==null);
	}

	@Override
	public LlistaGenericaNoOrd<K> preordre() {
		LlistaGenericaNoOrd<K> llista=new LlistaGenericaNoOrd<K>(10);
		if (arrel!=null) {
			llista.afegirElement(arrel.k);
			if (arrel.fe!=null) llista.afegirElement(arrel.fe.preordre());
			if (arrel.fd!=null) llista.afegirElement(arrel.fd.preordre());
		}
		return llista;
	}

	@Override
	public LlistaGenericaNoOrd<K> inordre() {
		LlistaGenericaNoOrd<K> llista=new LlistaGenericaNoOrd<K>(10);
		if (arrel!=null) {
			if (arrel.fe!=null) llista.afegirElement(arrel.fe.inordre());
			llista.afegirElement(arrel.k);
			if (arrel.fd!=null) llista.afegirElement(arrel.fd.inordre());
		}
		return llista;
	}

	@Override
	public LlistaGenericaNoOrd<K> postordre() {
		LlistaGenericaNoOrd<K> llista=new LlistaGenericaNoOrd<K>(10);
		if (arrel!=null) {
			if (arrel.fe!=null) llista.afegirElement(arrel.fe.postordre());
			if (arrel.fd!=null) llista.afegirElement(arrel.fd.postordre());
			llista.afegirElement(arrel.k);
		}
		return llista;
	}

	@Override
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
