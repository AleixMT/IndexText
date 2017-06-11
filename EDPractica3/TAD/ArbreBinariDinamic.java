package TAD;

public class ArbreBinariDinamic<E> implements TADArbreBinari<E> {

	private Node<E> arrel;
	
	public ArbreBinariDinamic (){ //ja tenim la operacio arrelar, no cal construir aqui els fills per a connectar els diferents nodes
		this.arrel = null; //creem l'arbre buit
	}
	
	public ArbreBinariDinamic (Node<E> node){  //node que assignem a l'arrel
		this.arrel = node; 
	}
	public void arrelar(TADArbreBinari<E> a1, E e, TADArbreBinari<E> a2) {
		arrel = new Node<E>(a1, e, a2); //fill e, fill d, element
	}

	
	public E arrel() { //retorna el contingut del node
		//consultem si esta buida
		if (arrel != null) return(arrel.getElement());//passem l'element
		return null;
	}

	//hem definit que els fills son tipus arbre i no referencia tipus node
	public TADArbreBinari<E> fillEsq() {
		//consultem si esta buida
		if (arrel != null) return(arrel.getFe()); //retornem fill esquerre
		return null;
	}

	
	public TADArbreBinari<E> fillDret() {
		//consultem si esta buida
		if (arrel != null) return(arrel.getFd()); //retornem fill dret
		return null;
	}

	
	public boolean esBuit() {
		return  arrel==null;
	}

	public boolean esEquilibrat() {
		if (arrel != null) return true;
		else{
			int alcFE = 0, alcFD = 0;
			boolean equilFE = true, equilFD = true;
			if (arrel.getFe() != null){
				alcFE = arrel.getFe().alcada();
				equilFE = arrel.getFe().esEquilibrat();
			}
			if (arrel.getFd() != null){
				alcFD = arrel.getFd().alcada();
				equilFD = arrel.getFd().esEquilibrat();
			}
			int dif = Math.abs(alcFE-alcFD);
			return ((dif<=1) && equilFE&&equilFD);
		}
	}

	//es fan sobre un arbre, es a dir, des de el node on estem (l'objecte actual), que pot ser nomes una part d'un arbre (pero un arbre igualment)
	
	//1.arrel 2.fe 3.fd
	public LlistaGenericaNoOrd<E> preordre() {
		LlistaGenericaNoOrd<E> llista = new LlistaGenericaNoOrd<E> (10);
		//crida recursiva
		if (arrel != null){
			llista.afegirElement(arrel.getElement());
			if (arrel.getFe() != null){
				llista.afegirElement(arrel.getFe().preordre());//segueix mentre tingui fill esquerre
			}
			if (arrel.getFd() != null){
				llista.afegirElement(arrel.getFd().preordre()); //segueix mentre tingui fd
			}
		}
		return llista;
	}

	//1.fe 2.arrel 3.fd
	public LlistaGenericaNoOrd<E> inordre() {
		LlistaGenericaNoOrd<E> llista = new LlistaGenericaNoOrd<E> (10);
		if (arrel != null){
			if (arrel.getFe() != null){
				llista.afegirElement(arrel.getFe().inordre());
			}
			llista.afegirElement(arrel.getElement());
			if (arrel.getFd() != null){
				llista.afegirElement(arrel.getFd().inordre());
			}
		}
		return llista;
	}

	//1.fe 2.fd 3.arrel
	public LlistaGenericaNoOrd<E> postordre() {
		LlistaGenericaNoOrd<E> llista = new LlistaGenericaNoOrd<E> (10);
		if (arrel != null){
			if (arrel.getFe() != null){
				llista.afegirElement(arrel.getFe().postordre());
			}
			if (arrel.getFd() != null){
				llista.afegirElement(arrel.getFd().postordre());
			}
			llista.afegirElement(arrel.getElement());
		}
		return llista;
	}

	
	public int alcada() {
		if (arrel == null) return(-1);
		else{
			int alcFE = -1, alcFD = -1;
			if (arrel.getFe() != null){
				alcFE = arrel.getFe().alcada();
			}
			if (arrel.getFd() != null){
				alcFD = arrel.getFd().alcada();
			}
			int max = Math.max(alcFE, alcFD);
			return max+1;
		}
	}

}
