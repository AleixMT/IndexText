package TAD;
public class NodeHash<K,V> {
	private K clau;
	private V valor;
	private int seguent;
	
	public NodeHash(K k, V v) {
		clau=k;
		valor=v;		
		seguent=-1;
	}
	
	public K getClau() {
		return(clau);
	}
	
	public V getValor() {
		return(valor);
	}
	
	public int getSeguent() {
		return(seguent);
	}

	public void setClau(K clau) {
		this.clau = clau;
	}

	public void setValor(V valor) {
		this.valor = valor;
	}

	public void setSeguent(int seguent) {
		this.seguent = seguent;
	}
	
	
}
