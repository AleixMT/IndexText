
package TAD;
public class NodeHash<K,V> {
	private K clau;
	private V valor;
	private NodeHash<K,V> seguent;
	
	public NodeHash(K k, NodeHash<K,V> ant) {
		clau=k;
		seguent=ant;
	}
	
	public K getClau() {
		return(clau);
	}
	
	public V getValor() {
		return(valor);
	}
	
	public NodeHash<K,V> getSeguent() {
		return(seguent);
	}

	public void setClau(K clau) {
		this.clau = clau;
	}

	public void setValor(V valor) {
		this.valor = valor;
	}

	public void setSeguent(NodeHash<K, V> seguent) {
		this.seguent = seguent;
	}

	@Override
	public String toString() {
		return "NodeHash [clau=" + clau + ", valor=" + valor + ", seguent=" + seguent + "]";
	}
}
