package TAD;

import Tipus.Index;

/**
 * Wrapper de l'element genèric E, que conté els punters als nodesAVL fill esquerre i fill dret
 * @author amt
 *
 * @param <E>
 */
public class NodeAVL<E extends Index> extends Node<E> {
	
	private NodeAVL<E> fe;
	private NodeAVL<E> fd;
	
	public NodeAVL(E e) {
		super (e);
		this.fe=null;
		this.fd=null;
	}
	
	public NodeAVL(E e, NodeAVL<E> fe, NodeAVL<E> fd) {
		super (e);
		this.fe=fe;
		this.fd=fd;
	}

	@Override
	public String toString() {
		return super.toString()+"NodeAVL [fe=" + fe + ", fd=" + fd + "]";
	}
/**
 * Per la manera com està definit el nostre arbre, el métode de recòrrer en inordre s'ha
 * d'implementar en la classe NodeABC
 * @return
 */
	public LlistaNode<E> inordre() {
		LlistaNode<E> llista=new LlistaNode<E>(1000);
		if (this!=null) {
			if (this.getFe()!=null) llista.afegirElement(this.getFe().inordre());
			llista.afegirElement(this);
			if (this.fd!=null) llista.afegirElement(this.fd.inordre());
		}
		return llista;
	}
	
	public boolean esBuit() {
		return (this==null);
	}
	/* Falta fer-lo AVL!!!!! es a dir, reequilibrarlo al pujar, caldrà afegir doncs, 
	 * punters al pare... TT
	 */
	/**
	 * Rep un element E i busca la posició on ha de ser inserit. Utilitza crides recursives a
	 * altres nodesAVL per a trobar la posició final 
	 */
	public void afegir(E e) {
		if (this.getElement().equals(e)) this.setElement(e);
		else if (this.getElement().compareTo(e)>0) 
		{
			if (this.getFe()!=null) this.getFe().afegir(e);
			else this.setFe(new NodeAVL<E>(e));
		}
		else
		{
			if (this.getFd()!=null) this.getFd().afegir(e);
			else this.setFd(new NodeAVL<E>(e));
		}
	}
	
	public NodeAVL<E> getFe() {
		return fe;
	}

	public void setFe(NodeAVL<E> fe) {
		this.fe = fe;
	}

	public NodeAVL<E> getFd() {
		return fd;
	}

	public void setFd(NodeAVL<E> fd) {
		this.fd = fd;
	}
}
