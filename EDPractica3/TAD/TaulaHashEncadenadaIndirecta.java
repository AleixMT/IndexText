package TAD;

import java.util.Arrays;
import Tipus.Index;
import Interface.TADTaulaHashGenerica;

public class TaulaHashEncadenadaIndirecta<K extends Comparable <K>, E> implements TADTaulaHashGenerica<K, E> {

	private NodeHash<K, E>[] taulaElements;
	private int capacitatTaula, numElements;
	// taula per a analitzar el numero de colisions que tenim en cada posicio
	private int[] numColisions; 
	private int maxNumColisions;

	@SuppressWarnings("unchecked")
	public TaulaHashEncadenadaIndirecta(int capacitatTaulaHash) {
		this.capacitatTaula = capacitatTaulaHash;
		this.taulaElements = new NodeHash[capacitatTaulaHash];
		this.numElements = 0;
		
		// analitzar colisions
		numColisions=new int[capacitatTaulaHash];
		maxNumColisions=0;
	}
	public void afegir(K k, E e) {
		int clauHash = 	Math.abs(e.hashCode() % capacitatTaula);

		if (taulaElements[clauHash] == null){//si esta buida
			taulaElements[clauHash] = new NodeHash<K, E>(k); //creem node
			this.numElements++;
			// analitzar colisions
			numColisions[clauHash]++;
			if (numColisions[clauHash]>maxNumColisions) maxNumColisions=numColisions[clauHash];
		}
		else {
			NodeHash<K, E> nant = taulaElements[clauHash];
			NodeHash<K, E> n = nant.getRef();

			while (n != null && !nant.getK().equals(k)) {
				nant = n;
				n = n.getRef();
			}

			if (nant.getK().equals(k))	//substituir
				nant.setE(e);
			else {							//inserir
				NodeHash<K, E> nouNode = new NodeHash<K, E>(k);
				nant.setRef(nouNode);
				this.numElements++;
				// analitzar colisions
				numColisions[clauHash]++;
				if (numColisions[clauHash]>maxNumColisions) maxNumColisions=numColisions[clauHash];
			}
		}
	}

	public void afegirAparicio(K k, int plana, int linia) {
		int clauHash = k.hashCode() % capacitatTaula; //calculem la posicio de la clau
		NodeHash<K, E> n = taulaElements[clauHash];
		
		while (!n.getK().equals(k)){
			n= n.getRef();//anar al seguent
		}
		Index index = (Index)(n.getE());
		index.AfegirAparicio(plana, linia);
	}
	
	public E esborrar(K k) {
		int clauHash = k.hashCode() % capacitatTaula;

		NodeHash<K, E> nant = taulaElements[clauHash];

		if (nant != null) {
			if (nant.getK().equals(k)){
				taulaElements[clauHash] = nant.getRef();
				numElements--;
				return (E) nant.getE();
			}
			else {

				NodeHash<K, E> n = nant.getRef();
				while (n != null && !n.getK().equals(k)) {
					nant = n;
					n = n.getRef();
				}
				
				if (n==null)
					return null;
				else{
					nant.setRef(n.getRef());
					numElements--;
					return (E) n.getE();
				}					
			}			
		}

		return null;
	}

	@SuppressWarnings("unchecked")
	public E consultar(K k) {
		int clauHash = k.hashCode() % capacitatTaula;
		NodeHash<K, E> n = taulaElements[clauHash];

		while (n != null && !n.getK().equals(k))
			n = n.getRef();

		return (E) ((n != null) ? n.getE() : null);
	}

	public float getFactorDeCarrega() {
		return (float) numElements / capacitatTaula;
	}

	public void mostrarTaula() {
		for (int i = 0; i < this.capacitatTaula; i++) {
			System.out.print(i + ": ");

			NodeHash<K, E> nant = taulaElements[i];

			while (nant != null) {
				System.out.print(nant.getK().toString() + "("
							+ nant.getE().toString() + ") ");
				nant = nant.getRef();
			}

			System.out.println("");
		}
		System.out.println("Factor càrrega:"+this.getFactorDeCarrega());
		System.out.println("");
	}

	// analitzar colisions
	public void mostrarColisions() {
		int[] frequencia=new int[maxNumColisions+1];
		int num, totalElements=0;
		for (int i = 0; i < this.capacitatTaula; i++) {
			num=numColisions[i];
			frequencia[num]++;
		}
		System.out.println("Analisi de les colisions. X (quantes posicions de la taula tenen X colisions)");
		for (int i = 0; i <= maxNumColisions; i++) {
			totalElements=totalElements+frequencia[i]*i;
			System.out.print(i+":("+frequencia[i]+") ");
			//for(int j=0; i<frequencia[i]; j++) System.out.print("*");
			System.out.println();
		}
		System.out.println("El numero de valors diferents que tenim a la taula es de "+totalElements);
	}
	@Override
	public String toString() {
		return "Index: " + Arrays.toString(taulaElements);
	}
	
}
