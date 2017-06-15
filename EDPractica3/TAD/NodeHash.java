
package TAD;

import Tipus.Index;
/**
 * Classe per a crear nodes de la taula de Hash
 * @author Aleix Marine i Cristina Izquierdo
 *
 * @param <K> - clau
 * @param <V> - valor
 */
public class NodeHash<K,V> {
	private K clau;
	private V valor;
	private NodeHash<K,V> seguent;
	
	/**
	 * Constructor de la classe
	 * @param k - clau
	 * @param ant - anterior
	 */
	@SuppressWarnings("unchecked")
	public NodeHash(K k, NodeHash<K,V> ant) {
		clau=k;
		this.valor = (V) new Index();
		seguent=ant;
	}
	
	/**
	 * Getter de la clau
	 * @return clau
	 */
	public K getClau() {
		return(clau);
	}
	
	/**
	 * Getter del valor
	 * @return valor
	 */
	public V getValor() {
		return(valor);
	}
	
	/**
	 * Getter del seguent
	 * @return seguent
	 */
	public NodeHash<K,V> getSeguent() {
		return(seguent);
	}

	/**
	 * Setter de la clau
	 * @param clau
	 */
	public void setClau(K clau) {
		this.clau = clau;
	}

	/**
	 * Setter del valor
	 * @param valor
	 */
	public void setValor(V valor) {
		this.valor = valor;
	}

	/**
	 * Setter del seguent
	 * @param seguent
	 */
	public void setSeguent(NodeHash<K, V> seguent) {
		this.seguent = seguent;
	}

	/**
	 * toString de la classe
	 */
	@Override
	public String toString() {
		return "NodeHash [clau=" + clau + ", valor=" + valor + ", seguent=" + seguent + "]";
	}
}
