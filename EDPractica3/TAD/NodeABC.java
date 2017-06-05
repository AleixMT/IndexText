package TAD;

import TAD.ABCdinamic.NodeABC;

private class NodeABC<K extends Comparable<K>, V> implements Cloneable {
	private K k;
	private V v;
	private ABCdinamic<K, V> fe;
	private ABCdinamic<K, V> fd;
	
	public NodeABC(K k, V v) {
		this.k=k;
		this.v=v;
		fe=null;
		fd=null;
	}

	@Override
	public String toString() {
		return "NodeABC [k=" + k + ", v=" + v + ", fe=" + fe + ", fd=" + fd + "]";
	}

	@Override
	protected NodeABC<K,V> clone() {
		NodeABC<K, V> obj=null;
		try{
            obj=(NodeABC<K, V>)super.clone();
        }catch(CloneNotSupportedException ex){
            System.out.println("No es pot duplicar");
        }
		return obj;
	}
	
	
}
