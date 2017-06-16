package TAD;

import Interface.TADAVL;
import Tipus.Index;

public class AVLdinamic<K extends Comparable<K>, V> implements TADAVL<K, V>, Cloneable {
	/**
	 * Classe interna dels nodes de l'arbre
	 * @author Aleix Mariné i Tena i Cristina Izquierdo 
	 *
	 * @param <K> Clau per accedir a l'element
	 * @param <V> Dades
	 */
	@SuppressWarnings("hiding")
	private class NodeABC<K extends Comparable<K>, V> implements Cloneable{
		private K k;
		private V v;
		private AVLdinamic<K, V> fe;
		private AVLdinamic<K, V> fd;
		private AVLdinamic<K, V> p;
		private int balance;
        private int height;
		
		@SuppressWarnings("unchecked")
		
		/**
		 * Crea un nou NodeABC passant-li un punter per a inicialitzar el pare
		 * @param k Clau
		 * @param v Valor (si el valor és nul s'inicialitza un element buit, sinó es sobreescriu)
		 * @param p	punter al pare
		 */
		public NodeABC(K k, V v, AVLdinamic<K,V> p) {
			this.k=k;
			if (v == null) this.v = (V) new Index(); else this.v = v;
			fe=null;
			fd=null;
			this.p = p;
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
		
		public String toString() {
			return "NodeABC [k=" + k + ", v=" + v + ", fe=" + fe + ", fd=" + fd + "]";
		}
	}
	
	/**
	 * Tots els arbres contenen un node arrel
	 */
	private NodeABC<K, V> arrel;
	
	/**
	 * Inicialitzem un arbre buit
	 */
	public AVLdinamic() {
		arrel=null;
	}
	
	/**
	 * Creem un nou arbre on l'arrel té un punter a un arbre pare
	 * @param k Clau
	 * @param v Dades
	 * @param p Pare
	 */
	public AVLdinamic(K k, V v, AVLdinamic<K,V> p) {
		arrel=new NodeABC<K,V>(k,v, p);
	}
	
	/**
	 * Inicialitzem un arbre amb un node
	 * @param n Node que serà l'arrel
	 */
	public AVLdinamic(NodeABC<K,V> n) {
		arrel = n;
	}

	/**
	 * Métode que afegeix una aparició a l'element V. Crida el métode d'afegir aparició sobre l'element consultat.
	 * És un métode no genèric ja que tracta l'element V com un Inde, tot i que no pot ser
	 * @param k clau de l'element utilitzada per a cercar
	 * @param plana plana d'aparició
	 * @param linia linia d'aparició
	 * @return true si troba l'element k rebut per paràmetre i afegeix una aparició, false si no el troba
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
	/**
	 * Mètode que retorna true si afegeix un nou element de manera ordenada a l'arbre AVL i reequilibra tots 
	 * els nodes per a arribar a l'arrel. Si hi ha un amb el mateix identificador retorna false.  
	 */
	public boolean afegir(K k, V v) {
		if (esBuit())	/** Si és buit creem el node a l'arrel **/
		{
			arrel=new NodeABC<K,V>(k,v, null);
		} 
		else /** Sinó utilitzem el métode privat insert **/
		{
			this.insert(k, v);
		}
		while (this.arrel.p!=null)		/** PROBLEMA DELS AVL: El node des d'on es fa la crida pot no ser l'arrel 
		de l'arbre després d'afegir un nou element
		degut a la modificació a temps real de la ED, perdent informació **/
		{
			this.arrel = this.arrel.p.arrel;
		}
		return true; /** Hem afegit, per tant retornem cert **/
	}
	/**
	 * Mètode que retorna true si afegeix un nou element de manera ordenada a l'arbre AVL i reequilibra tots 
	 * els nodes per a arribar a l'arrel. Si hi ha un amb el mateix identificador retorna false.  
	 * @param k clau de l'element a afegir
	 * @param v dades que ja porta l'element
	 * @return
	 */
	private boolean insert(K k, V v) {
		if (esBuit())	/** Si és buit creem el node a l'arrel **/
		{
			arrel=new NodeABC<K,V>(k,v, null);
		} 
		else 
		{
			if (arrel.k.equals(k)) return false;	/** Si ja s'ha afegit retorna fals **/
			else if (arrel.k.compareTo(k)>0) 
			{	/** Arbre esquerra **/
				if (arrel.fe!=null) arrel.fe.insert(k, v);	/** Si el fill esquerra està buit, executem afegir sobre ell **/
				else 
				{
					arrel.fe=new AVLdinamic<K,V>(k,v, this);	/** Si està buit, creem el nou node **/
					rebalance(this.arrel); 	/** I rebalancegem l'arrel **/
				}
			}
			else if (arrel.k.compareTo(k)<0) 	
			{ /** Arbre dret **/
				if (arrel.fd!=null) arrel.fd.insert(k, v); /** Si el fill esquerra està buit, executem afegir sobre ell **/
				else 
				{
					arrel.fd=new AVLdinamic<K,V>(k,v, this); /** Si està buit, creem el nou node **/
					rebalance(this.arrel); /** I rebalancegem l'arrel **/
				}
			}
		}
		return true; /** Hem afegit, per tant retornem cert **/
	}

	public boolean esborrar(K k) 
	{
		if (arrel!=null) 
		{
			if (arrel.k.compareTo(k)>0) 
			{
				if (arrel.fe!=null)
				{
					arrel.fe=esborrar(k, arrel.fe);
				}
			}
			else if (arrel.k.compareTo(k)<0) 
			{
				if (arrel.fd!=null)
				{
					arrel.fd=esborrar(k, arrel.fd);
				}
			} 
			else 
			{
				// he d'esborrar l'arrel
				// arrel no te fills
				if ((arrel.fd==null) && (arrel.fe==null)) 
				{
					arrel=null;
					return true;
				}
				else 
				{
					// arrel te un fill o dos
					if ((arrel.fd==null) && (arrel.fe!=null)) 
					{
						//l'arrel te el fe
						arrel=arrel.fe.arrel;
					}
					if ((arrel.fd!=null) && (arrel.fe==null)) 
					{
						//l'arrel te el fd
						arrel=arrel.fd.arrel;
					}
					if ((arrel.fd!=null) && (arrel.fe!=null)) 
					{
						// arrel te dos fills
						K succKlau=arrel.fd.minim();
						V succValor=consultar(succKlau);
						esborrar(succKlau);
						arrel.k=succKlau;
						arrel.v=succValor;
					}
					rebalance(arrel);
				}
				return true;
			}
		}
		return false;
	}
	private AVLdinamic<K,V> esborrar(K k, AVLdinamic<K,V> arbre) {
		if (arbre!=null) 
		{
			if (arbre.arrel.k.compareTo(k)>0) 
			{
				if (arbre.arrel.fe!=null)
					arbre.arrel.fe=esborrar(k, arbre.arrel.fe);
			}
			if (arbre.arrel.k.compareTo(k)<0) {
				if (arbre.arrel.fd!=null)
					arbre.arrel.fd=esborrar(k, arbre.arrel.fd);
			}
			if (arbre.arrel.k.compareTo(k)==0) 
			{
				if ((arbre.arrel.fe!=null) && (arbre.arrel.fd!=null)) 
				{
					// te dos fills
					K succKlau=arbre.arrel.fd.minim();
					V succValor=consultar(succKlau);
					esborrar(succKlau);
					arbre.arrel.k=succKlau;
					arbre.arrel.v=succValor;
				} 
				else 
				{
					// te un o cap fill
					if (arbre.arrel.fe!=null) {
						arbre=(AVLdinamic<K, V>) arbre.fillEsq();
					} else arbre=(AVLdinamic<K, V>) arbre.fillDret();
				}
			if (arbre!=null) rebalance(arbre.arrel);

			}
		}
		return arbre;
	}
	/**
	 * Reequilibra un node rebut per peràmetre
	 * @param n node a reequilibrar
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
    }
	
	/**
	 * Recalcula el factor d'equilibri (balance) del node rebut per paràmetre
	 * @param n node que conté el factor d'quilibri a recalcular
	 */
    public void setBalance(NodeABC<K, V> n) {
        reheight(n);
		n.balance = height(n.fd) - height(n.fe);
	}
    
    /**
     * recalcula l'altura del node passat per paràmetre
     * @param node node el cual es vol recalcular
     */
    public void reheight(NodeABC<K,V> node){
    	if(node!=null) node.height=1 + Math.max(height(node.fe), height(node.fd));
    }
    
	/**
	 * Métode que efectua una rotació simple a l'esquerra sobre el node passat per paràmetre i recalcula els factors equilibri 
	 * del node retornat
	 * @param a Node al que se li fa la rotació
	 * @return Node equilibrat
	 */
    public NodeABC<K,V> rotateLeft(NodeABC<K,V> a) {
    	AVLdinamic<K,V> B = a.fd; /** Creem un arbre auxiliar que sigui el subarbre dret de l'arbre que s'ha d'equilibrar **/
    	AVLdinamic<K,V> A = new AVLdinamic<K,V>(a); /** Creem un arbre auxiliar que sigui l'arbre a equilibrar **/
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
    	AVLdinamic<K,V> B = a.fe; /** Creem un arbre auxiliar que sigui el subarbre esquerra de l'arbre que s'ha d'equilibrar **/
    	AVLdinamic<K,V> A = new AVLdinamic<K,V>(a); /** Creem un arbre auxiliar que sigui l'arbre a equilibrar **/
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
     * Aplica una rotació a la esquerra al fill esquerre del node passat per paràmetre i una rotació a la dreta al node passat
     * per paràmetre
     * @param n Node al que se li aplica la doble rotació
     * @return Node reequilibrat
     */
    private NodeABC<K,V> rotateLeftThenRight(NodeABC<K,V> n) {
        n.fe.arrel = rotateLeft(n.fe.arrel);
        return rotateRight(n);
    }
 
    /**
     * Aplica una rotació a la dreta al fill dret del node passat per paràmetre i una rotació a la esquerra al node passat
     * per paràmetre
     * @param n Node al que se li aplica la doble rotació
     * @return Node reequilibrat
     */
    private NodeABC<K,V> rotateRightThenLeft(NodeABC<K,V> n) {
        n.fd.arrel = rotateRight(n.fd.arrel);
        return rotateLeft(n);
    }
 
    /**
     * Retorna l'altura d'un node
     * @param n Node el qual se'n vol saber l'altura
     * @return retorna l'altura del node rebut per paràmetre
     */
    public int height(AVLdinamic<K, V> n) {
        if (n == null)
            return -1;
        return n.arrel.height;
    }

    /**
     * 
     * @param k clau de l'element a consultar
     * @return retorna null si no troba l'element K, retorna V si el troba
     */
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

	/**
	 * Comprova que l'element existeixi i retorna el corresponent booleà en conseqüencia
	 * @param k clau de l'element que s'ha comprovat
	 * @return true si l'element està en l'ED i sinó fals
	 */
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

	/**
	 * Métode que retorna el nombre de nodes a partir de l'arbre des d'on es crida
	 * @return Nombre de nodes
	 */
	public int numNodes() {
		LlistaGenericaNoOrd<K> llista=this.inordre();
		return llista.getNum();
	}

	/**
	 * Métode per a calcular l'element màxim de l'arbre. Recorrem l'arbre pels fills drets
	 * @return Dades de l'element màxim
	 */
	public K maxim() {
		if (arrel==null) return null;
		else 
		{
			if (arrel.fd!=null) 
			{
				AVLdinamic<K, V> aux=arrel.fd;
				while (aux.arrel.fe!=null) aux=aux.arrel.fe;
				return (aux.arrel.k);
			} 
			else return arrel.k;
		}
	}
	/**
	 * Métode per a calcular l'element mínim de l'arbre. Recorrem l'arbre pels fill esquerre
	 * @return Dades de l'element mínim
	 */
	public K minim() {
		if (arrel==null) return null;
		else 
		{
			if (arrel.fe!=null) 
			{
				AVLdinamic<K, V> aux=arrel.fe;
				while (aux.arrel.fe!=null) aux= aux.arrel.fe;
				return (aux.arrel.k);
			} 
			else return arrel.k;
		}
	}

	/**
	 * Métode per a generar una llista que contingui tots els elements de l'arbre ordenats segons preordre
	 * @return llista d'elements preordre
	 */
	public LlistaGenericaNoOrd<K> preordre() {
		LlistaGenericaNoOrd<K> llista=new LlistaGenericaNoOrd<K>(10);
		if (arrel!=null) 
		{
			llista.afegirElement(arrel.k);
			if (arrel.fe!=null) llista.afegirElement(arrel.fe.preordre());
			if (arrel.fd!=null) llista.afegirElement(arrel.fd.preordre());
		}
		return llista;
	}

	/**
	 * Métode per a generar una llista que contingui tots els elements de l'arbre ordenats segons inrdre
	 * @return llista d'elements preordre
	 */
	public LlistaGenericaNoOrd<K> inordre() {
		LlistaGenericaNoOrd<K> llista=new LlistaGenericaNoOrd<K>(10);
		if (arrel!=null) 
		{
			if (arrel.fe!=null) llista.afegirElement(arrel.fe.inordre());
			llista.afegirElement(arrel.k);
			if (arrel.fd!=null) llista.afegirElement(arrel.fd.inordre());
		}
		return llista;
	}

	/**
	 * Métode per a generar una llista que contingui tots els elements de l'arbre ordenats segons postordre
	 * @return llista d'elements postordre
	 */
	public LlistaGenericaNoOrd<K> postordre() {
		LlistaGenericaNoOrd<K> llista=new LlistaGenericaNoOrd<K>(10);
		if (arrel!=null) {
			if (arrel.fe!=null) llista.afegirElement(arrel.fe.postordre());
			if (arrel.fd!=null) llista.afegirElement(arrel.fd.postordre());
			llista.afegirElement(arrel.k);
		}
		return llista;
	}

	/**
	 * Métode per a mostrar l'índex. És un métode no genèric ja que tracta l'element V com a Índex
	 * @return String del índex
	 */
	public String mostrarIndex() {
		String out = "";
		MeuIterator<K> it = new MeuIterator<K> (this.inordre());
		while (it.hasNext())
		{
			K aux = it.next();
			out += aux + " ";
			out += consultar(aux);
			
		}
		return out;
	}
	
	/**
	 * Comprova que l'arbre des d'on s'executi estigui buit.
	 * @return Si ho està retorna cert, sinó fals
	 */
	public boolean esBuit() {
		return (arrel==null);
	}
	
	/**
	 * Métode per a imprimir l'índex, toString per defecte de l'eclipse
	 * @return String amb l'índex
	 */
	public String toString() {
		return "AVLdinamic [arrel=" + arrel + "]";
	}
	
	public TADAVL<K, V> fillEsq() {
		if ((arrel!=null)&&(arrel.fe!=null)) return(arrel.fe.clone());
		return null;
	}

	@SuppressWarnings("unchecked")
	public TADAVL<K, V> clone() {
		AVLdinamic<K, V> obj=null;
		try{
            obj=(AVLdinamic<K, V>)super.clone();
        }catch(CloneNotSupportedException ex){
            System.out.println("No es pot duplicar");
        }
		obj.arrel=obj.arrel.clone();
		if (obj.fillEsq()!=null) obj.arrel.fe = (AVLdinamic<K,V>)obj.fillEsq().clone();
		if (obj.fillDret()!=null) obj.arrel.fd = (AVLdinamic<K,V>)obj.fillDret().clone();
        return obj;
	}
	
	public TADAVL<K, V> fillDret() {
		if ((arrel!=null)&&(arrel.fd!=null)) return(arrel.fd.clone());
		return null;
	}
	
}