package TAD;

import Tipus.Index;
import Interface.TADTaulaHashGenerica;

public class TaulaHashEncadenadaIndirecta<K extends Comparable <K>, V> implements TADTaulaHashGenerica<K,V> {

	private NodeHash<K, V>[] taulaElements;
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

	public boolean afegir(K k) {
		int clauHash = 	k.hashCode() % capacitatTaula;

		if (taulaElements[clauHash] == null)
		{
			taulaElements[clauHash] = new NodeHash<K, V>(k, null);
			this.numElements++;
			// analitzar colisions
			numColisions[clauHash]++;
			if (numColisions[clauHash]>maxNumColisions)
			{
				maxNumColisions=numColisions[clauHash];
			}
			return true;
		}
		else 
		{
			/*
			NodeHash<K, V> nant = taulaElements[clauHash];
			NodeHash<K, V> n = nant.getSeguent();

			while (n != null && !nant.getClau().equals(k)) {
				nant = n;
				n = n.getSeguent();
			}
			*/
			NodeHash<K, V> n = taulaElements[clauHash];

			while (n != null && !n.getClau().equals(k)) {
				n = n.getSeguent();
			}
			if (n.getClau().equals(k))	
				return false;	// error paraula ja afegida
			else 
			{							//inserir
				NodeHash<K, V> nouNode = new NodeHash<K, V>(k, null);
				n.setSeguent(nouNode);
				this.numElements++;
				// analitzar colisions
				numColisions[clauHash]++;
				if (numColisions[clauHash]>maxNumColisions) maxNumColisions=numColisions[clauHash];
				return true;
			}
		}
	}

	public boolean afegirAparicio(K k, int plana, int linia) {
		int clauHash = k.hashCode() % capacitatTaula; //calculem la posicio de la clau
		NodeHash<K, V> n = taulaElements[clauHash];
		
		while (!n.getClau().equals(k))
		{
			n= n.getSeguent();//anar al seguent
			if (n==null) return false;
		}
		Index index = (Index)(n.getValor());
		index.AfegirAparicio(plana, linia);
		return true;
	}
	
	public boolean esborrar(K k) {
		int clauHash = k.hashCode() % capacitatTaula;

		NodeHash<K, V> nant = taulaElements[clauHash];

		if (nant != null) 
		{
			if (nant.getClau().equals(k))
			{
				taulaElements[clauHash] = nant.getSeguent();
				numElements--;
				return true;
			}
			else 
			{
				NodeHash<K, V> n = nant.getSeguent();
				while (n != null && !n.getClau().equals(k)) 
				{
					nant = n;
					n = n.getSeguent();
				}
				if (n==null) return false;	// No l'hem trobat
				else
				{
					nant.setSeguent(n.getSeguent());
					numElements--;
					return true;
				}					
			}			
		}
		return false;
	}

	public V consultar(K k) {
		int clauHash = k.hashCode() % capacitatTaula;
		NodeHash<K, V> n = taulaElements[clauHash];

		while (n != null && !n.getClau().equals(k))
			n = n.getSeguent();

		return (n != null) ? n.getValor() : null;
	}

	public float getFactorDeCarrega() {
		return (float) numElements / capacitatTaula;
	}

	public String toString() 
	{
		String out = "";
		for (int i = 0; i < this.capacitatTaula; i++) {
			out+=i + ": ";

			NodeHash<K, V> nant = taulaElements[i];

			while (nant != null) {
				out+= nant.getClau().toString() + "(" + nant.getValor().toString() + ") ";
				nant = nant.getSeguent();
			}

			out+= "\n";
		}
		out += "Factor càrrega:"+this.getFactorDeCarrega();
		out+= "\n";
		return out;
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

	public boolean existeix(K k) {
		return false;
	}
		
}
