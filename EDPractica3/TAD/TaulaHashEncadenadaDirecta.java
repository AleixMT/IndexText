package TAD;
import Exceptions.*;
import Interface.TADTaulaHashGenerica;

public class TaulaHashEncadenadaDirecta<K, V> implements TADTaulaHashGenerica<K, V> {
	private NodeHash<K, V>[] taulaElements;
	private int capacitatZonaPrinc, capacitatZonaExc, numElements;
	private TADPilaEnters pila;
	
	public TaulaHashEncadenadaDirecta(int capacitatTaulaHash) {
		this.capacitatZonaPrinc = capacitatTaulaHash;
		this.capacitatZonaExc=capacitatTaulaHash/4; // afegim un 25% per a la zona d'excedents
		this.taulaElements = new NodeHash[capacitatTaulaHash+capacitatZonaExc];
		this.numElements = 0;
		this.pila=new PilaEntVector(capacitatZonaExc);
		// afegim a la pila totes les posicions de la zona d'excedents
		for (int i=taulaElements.length-1; i>=capacitatZonaPrinc; i--) {
			try {
				pila.apilar(i);
			} catch (PilaPlena e) {
			  System.out.println("Tamany de pila insuficient per a la zona d'excedents "+pila.numElem());
			}
		}
	}

	@Override
	public void afegir(K k, V v) {
		int clauHash = k.hashCode() % capacitatZonaPrinc;
		int posLliure=0;
		
		if (taulaElements[clauHash] == null){
			taulaElements[clauHash] = new NodeHash<K, V>(k, v);
			this.numElements++;
		}
		else {
			int posAnt = clauHash;
			int pos = taulaElements[posAnt].getSeguent();

			while (pos != -1 && !taulaElements[posAnt].getClau().equals(k)) {
				posAnt = pos;
				pos = taulaElements[pos].getSeguent();
			}

			if (taulaElements[posAnt].getClau().equals(k))	//substituir
				taulaElements[posAnt].setValor(v);
			else {							//inserir
				NodeHash<K, V> nouNode = new NodeHash<K, V>(k, v);
				try {
					posLliure=pila.desapilar();
					taulaElements[posAnt].setSeguent(posLliure);
					taulaElements[posLliure]=nouNode;
					this.numElements++;
				} catch (PilaBuida e) {
					  System.out.println("Falten espais a la zona d'excedents ");
				}
			}
		}
	}

	@Override
	public V esborrar(K k) {
		
		int clauHash = k.hashCode() % capacitatZonaPrinc;
		V valorTrobat;
		int posicioLliure;

		if (taulaElements[clauHash] != null) {
			// si fos null voldria dir que l'element de clau k no existeix
			if (taulaElements[clauHash].getClau().equals(k)){
				// el valor que busquem esta a la zona principal de la taula
				valorTrobat= taulaElements[clauHash].getValor(); // guardo el valor trobat per retornar
				if (taulaElements[clauHash].getSeguent()!=-1) {
					// tinc sinonims (col�lisions) i pujo el primer sin�nim i poso el seu lloc a la pila
					posicioLliure=taulaElements[clauHash].getSeguent();
					taulaElements[clauHash] = taulaElements[posicioLliure];
					taulaElements[posicioLliure]=null;
					try {
						pila.apilar(posicioLliure);
					} catch (PilaPlena e) {
						  System.out.println("Falten espais a la pila ");
					}
				} else taulaElements[clauHash]=null;
				numElements--;
				return valorTrobat;
			}
			else {
				// el valor que busquem no esta a la zona principal de la taula
				int posAnt = clauHash;
				int posSeg = taulaElements[clauHash].getSeguent();
				while (posSeg != -1 && !taulaElements[posSeg].getClau().equals(k)) {
					posAnt = posSeg;
					posSeg = taulaElements[posSeg].getSeguent();
				}
				
				if (posSeg != -1){
					// hem trobat el valor i esta a la posicio posSeg de la Zona d'excedents
					taulaElements[posAnt].setSeguent(taulaElements[posSeg].getSeguent());
					valorTrobat= taulaElements[posSeg].getValor();
					taulaElements[posSeg]=null;
					try {
						pila.apilar(posSeg);
					} catch (PilaPlena e) {
						  System.out.println("Falten espais a la pila ");
					}
					numElements--;
					return valorTrobat;
				}				
			}	
		}
		// no hem trobat el valor que estavem buscant
		return null;
	}

	@Override
	public V consultar(K k) {
		/*
		 * falta adaptar
		 * 
		int clauHash = k.hashCode() % capacitatZonaPrinc;
		NodeHash<K, V> n = taulaElements[clauHash];

		while (n != null && !n.getClau().equals(k))
			n = n.getSeguent();

		return (n != null) ? n.getValor() : null;*/
		return null;
	}

	@Override
	public float getFactorDeCarrega() {
		return (float) numElements / capacitatZonaPrinc;
	}

	@Override
	public void mostrarTaula() {
		for (int i = 0; i < this.capacitatZonaPrinc; i++) {
			System.out.print(i + ": ");

			NodeHash<K, V> nant = taulaElements[i];
			if (nant!=null) {
				System.out.print(nant.getClau().toString() + "("
							+ nant.getValor().toString() + ") ");
			} else System.out.print(" cap element ");

			System.out.println("");
		}
		System.out.println("Zona d'excedents");
		for (int i = this.capacitatZonaPrinc; i < this.taulaElements.length; i++) {
			System.out.print(i + ": ");

			NodeHash<K, V> nant = taulaElements[i];
			if (nant!=null) {
				System.out.print(nant.getClau().toString() + "("
							+ nant.getValor().toString() + ") ");
			} else System.out.print(" cap element ");

			System.out.println("");
		}
		
		
		System.out.println("Factor c�rrega:"+this.getFactorDeCarrega());
		System.out.println("");
	}

	
}
