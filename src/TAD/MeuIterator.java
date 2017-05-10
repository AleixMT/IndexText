package TAD;

import java.util.Iterator;

public class MeuIterator<T> implements Iterator<T> {
	private LlistaGenericaNoOrd<T> llista;	//nou atribut que ens guardarà una copia de la llista actual de punts
	private int posicioIterator;
	
	public MeuIterator(LlistaGenericaNoOrd<T> ll) {
		llista=new LlistaGenericaNoOrd<T>(ll.getNum());
		for (int i=0; i<ll.getNum(); i++) {
			llista.afegirElement(ll.consultarIessim(i));
		}
		posicioIterator=0; 	// ens preparem per a retornar els elements a partir de la posicio 0
	}
	
	@Override
	public boolean hasNext() {
		return ((posicioIterator<llista.getNum()));
	}

	@Override
	public T next() {
		T aux=llista.consultarIessim(posicioIterator);
		posicioIterator++;
		return aux;
	}

}
