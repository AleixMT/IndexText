package TAD;


public class Node<K extends Comparable<K>, E>{
	private E e;
	private K k;
	
	public Node (K k) {
		this.k = k;
	}

	public E getE() {
		return e;
	}

	public void setE(E e) {
		this.e = e;
	}

	public K getK() {
		return k;
	}

	public void setK(K k) {
		this.k = k;
	}

	@Override
	public String toString() {
		return "Node [e=" + e + ", k=" + k + "]";
	}

	
	
}
