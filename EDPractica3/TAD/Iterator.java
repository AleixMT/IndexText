package TAD;

import Tipus.Index;


public class Iterator<E extends Index>{
	private LlistaNode<E> llista;	//nou atribut que ens guardar� una copia de la llista actual de punts
	private int posicioIterator;
	
	public Iterator(LlistaNode<E> ll) {
		llista=new LlistaNode<E>(ll.getNum());
		for (int i=0; i<ll.getNum(); i++) {
			llista.afegirElement(ll.consultarIessim(i));
		}
		posicioIterator=0; 	// ens preparem per a retornar els elements a partir de la posicio 0
	}
	
	public boolean hasNext() {
		return ((posicioIterator<llista.getNum()));
	}

	public Node<E> next() {
		Node<E> aux=llista.consultarIessim(posicioIterator);
		posicioIterator++;
		return aux;
	}

}
