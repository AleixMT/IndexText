package TAD;

import Tipus.Index;

/**
 * Classe Wrapper per a l'element genéric en un NodeHash.
 * Al ser encadenada indirecta necessita contenir un punter al següent NodeHash
 * @author amt
 *
 * @param <E>
 */
public class NodeHash<E extends Index> extends Node<E> {
	private NodeHash<E> ref;
	
	public NodeHash(E e) {
		super (e);
		this.ref = null;
	}
	
	public NodeHash(E e, NodeHash<E> ref) {
		super (e);
		this.ref = ref;
	}

	public NodeHash<E> getRef() {
		return ref;
	}

	public void setRef(NodeHash<E> ref) {
		this.ref = ref;
	}

	@Override
	public String toString() {
		return super.toString()+"NodeHash [ref=" + ref + "]";
	}
	
	
	
	
}
