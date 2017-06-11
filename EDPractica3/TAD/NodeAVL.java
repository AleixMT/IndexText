package TAD;

public class NodeAVL<K extends Comparable<K>, E> extends Node<K,E>{
	
	private AVLdinamic<K,E> fe, fd, arrel;
	
	public NodeAVL(K k) {
		super (k);
		this.fe=null;
		this.fd=null;
		this.arrel = null;
	}
	
	public NodeAVL(K k, AVLdinamic<K,E> fe, AVLdinamic<K,E> fd, AVLdinamic<K,E> arrel){
		super (k);
		this.fe = fe;
		this.fd = fd;
		this.arrel = arrel;
	}

	public AVLdinamic<K, E> getFe() {
		return fe;
	}

	public void setFe(AVLdinamic<K, E> fe) {
		this.fe = fe;
	}

	public AVLdinamic<K, E> getFd() {
		return fd;
	}

	public void setFd(AVLdinamic<K, E> fd) {
		this.fd = fd;
	}

	public AVLdinamic<K, E> getArrel() {
		return arrel;
	}

	public void setArrel(AVLdinamic<K, E> arrel) {
		this.arrel = arrel;
	}

	@Override
	public String toString() {
		return "NodeAVL [fe=" + fe + ", fd=" + fd + ", arrel=" + arrel + "]";
	}

	
}
