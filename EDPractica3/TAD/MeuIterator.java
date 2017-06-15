package TAD;

import java.util.Iterator;
import TAD.TaulaHashEncadenadaIndirecta;

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
	
	@SuppressWarnings("unchecked")
	public MeuIterator(TaulaHashEncadenadaIndirecta taula){
		this.llista = new LlistaGenericaNoOrd<T>(taula.getCapacitatTaula());
		for (int i = 0; i < taula.getCapacitatTaula(); i++) {
			if (taula.getTaulaElements()[i]!=null){
				this.llista.afegirElement((T) taula.getTaulaElements()[i]);
			}
		}
		posicioIterator=0;
	}
	
	public boolean hasNext() {
		return ((posicioIterator<llista.getNum()));
	}

	public T next() {
		T aux=llista.consultarIessim(posicioIterator);
		posicioIterator++;
		return aux;
	}

}
