package TAD;

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

		if (taulaElements[clauHash] == null){
			taulaElements[clauHash] = new NodeHash<K, E>(k, e, null);
			this.numElements++;
			// analitzar colisions
			numColisions[clauHash]++;
			if (numColisions[clauHash]>maxNumColisions) maxNumColisions=numColisions[clauHash];
		}
		else {
			NodeHash<K, E> nant = taulaElements[clauHash];
			NodeHash<K, E> n = nant.getSeguent();

			while (n != null && !nant.getClau().equals(k)) {
				nant = n;
				n = n.getSeguent();
			}

			if (nant.getClau().equals(k))	//substituir
				nant.setValor(e);
			else {							//inserir
				NodeHash<K, E> nouNode = new NodeHash<K, E>(k, e, null);
				nant.setSeguent(nouNode);
				this.numElements++;
				// analitzar colisions
				numColisions[clauHash]++;
				if (numColisions[clauHash]>maxNumColisions) maxNumColisions=numColisions[clauHash];
			}
		}
	}

	@Override
	public E esborrar(K k) {
		int clauHash = k.hashCode() % capacitatTaula;

		NodeHash<K, E> nant = taulaElements[clauHash];

		if (nant != null) {
			if (nant.getClau().equals(k)){
				taulaElements[clauHash] = nant.getSeguent();
				numElements--;
				return nant.getValor();
			}
			else {

				NodeHash<K, E> n = nant.getSeguent();
				while (n != null && !n.getClau().equals(k)) {
					nant = n;
					n = n.getSeguent();
				}
				
				if (n==null)
					return null;
				else{
					nant.setSeguent(n.getSeguent());
					numElements--;
					return n.getValor();
				}					
			}			
		}

		return null;
	}

	public E consultar(K k) {
		int clauHash = k.hashCode() % capacitatTaula;
		NodeHash<K, E> n = taulaElements[clauHash];

		while (n != null && !n.getClau().equals(k))
			n = n.getSeguent();

		return (n != null) ? n.getValor() : null;
	}

	public float getFactorDeCarrega() {
		return (float) numElements / capacitatTaula;
	}

	public void mostrarTaula() {
		for (int i = 0; i < this.capacitatTaula; i++) {
			System.out.print(i + ": ");

			NodeHash<K, E> nant = taulaElements[i];

			while (nant != null) {
				System.out.print(nant.getClau().toString() + "("
							+ nant.getValor().toString() + ") ");
				nant = nant.getSeguent();
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
}
