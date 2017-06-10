package TAD;
import java.util.*;

import Tipus.Index;

public class LlistaNode<E extends Index> {
	private Node<E>[] llista;
	private int num;
	
	@SuppressWarnings("unchecked")
	public LlistaNode(int dim) {
		llista=(Node<E>[])new Object[dim];
		num=0;
	}

	public void afegirElement(Node<E> n) {
		if (num>=llista.length) {
			@SuppressWarnings("unchecked")
			Node<E>[] nova=(Node<E>[]) new Object[llista.length*2];
			for (int i=0; i<llista.length; i++)
				nova[i]=llista[i];
			llista=nova;
		}
		// segur que tinc espai
		llista[num]=n;
		num++;
	}
	
	public void afegirElement(LlistaNode<E> ll) {
		int numElems=ll.getNum();
		for (int i=0; i<numElems; i++) {
			afegirElement(ll.consultarIessim(i));
		}
	}
	
	public Node<E> consultarIessim(int i) {
		if (i<num) return(llista[i]);
		else return(null);
	}
	
	public int getNum() {
		return num; 
	}

	
	public String toString() {
		return "LlistaPunts [llista=" + Arrays.toString(llista) + ", num=" + num + "]";
	}

	public Iterator<E> iterator() {
		Iterator<E> pI = new Iterator<E>(this);
		return pI;
	}

	
	
}
