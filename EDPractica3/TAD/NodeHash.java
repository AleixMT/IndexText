package TAD;

import Tipus.Index;

/**
 * Classe Wrapper per a l'element generic en un NodeHash.
 * Al ser encadenada indirecta necessita contenir un punter al seguent NodeHash
 * @author amt
 *
 * @param <E>
 */
public class NodeHash<K extends Comparable <K>, E> {
	private NodeHash<K, E> ref;
	
	public NodeHash(K k) {
		super (k);
		this.ref = null;
	}
	
	public NodeHash(K k, NodeHash<K, E> ref) {
		super (k);
		this.ref = ref;
	}

	public NodeHash<K, E> getRef() {
		return ref;
	}

	public void setRef(NodeHash<K, E> ref) {
		this.ref = ref;
	}

	public String toString() {
		return super.toString()+"NodeHash [ref=" + ref + "]";
	}
}