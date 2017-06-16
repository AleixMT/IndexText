package TAD;
import Interface.TADTaulaHashGenerica;
import Tipus.Index;
/**
 * Classe per a crear una taula de hash encadenada indirecta
 * @author Cristina Izquierdo i Aleix Marine
 *
 * @param <K> - clau
 * @param <V> - valor
 */
public class TaulaHashEncadenadaIndirecta<K extends Comparable <K>, V> implements TADTaulaHashGenerica<K,V> {

	private NodeHash<K, V>[] taulaElements;
	private int capacitatTaula, numElements;
	// taula per a analitzar el numero de colisions que tenim en cada posicio
	private int[] numColisions; 
	private int maxNumColisions;

	/**
	 * Constructor de la classe
	 * @param capacitatTaulaHash
	 */
	@SuppressWarnings("unchecked")
	public TaulaHashEncadenadaIndirecta(int capacitatTaulaHash) {
		this.capacitatTaula = capacitatTaulaHash;
		this.taulaElements = new NodeHash[capacitatTaulaHash];
		this.numElements = 0;
		
		// analitzar colisions
		numColisions=new int[capacitatTaulaHash];
		maxNumColisions=0;
	}
	
	/**
	 * Getter de la taula d'elements
	 * @return taula d'elements
	 */
	public NodeHash<K, V>[] getTaulaElements() {
		return taulaElements;
	}

	/**
	 * Setter de la taula d'elements
	 * @param taulaElements
	 */
	public void setTaulaElements(NodeHash<K, V>[] taulaElements) {
		this.taulaElements = taulaElements;
	}

	/**
	 * Getter de la capacitat de la taula
	 * @return capacitat de la taula
	 */
	public int getCapacitatTaula() {
		return capacitatTaula;
	}

	/**
	 * Setter de la capacitat de la taula
	 * @param capacitatTaula
	 */
	public void setCapacitatTaula(int capacitatTaula) {
		this.capacitatTaula = capacitatTaula;
	}

	/**
	 * Getter del numero d'elements a la taula
	 * @return numero d'elements a la taula
	 */
	public int getNumElements() {
		return numElements;
	}

	/**
	 * Setter del numero d'elements a la taula
	 * @param numElements
	 */
	public void setNumElements(int numElements) {
		this.numElements = numElements;
	}

	/**
	 * Getter del numero de colisions
	 * @return numero de colisions
	 */
	public int[] getNumColisions() {
		return numColisions;
	}

	/**
	 * Setter del numero de colisions
	 * @param numColisions
	 */
	public void setNumColisions(int[] numColisions) {
		this.numColisions = numColisions;
	}

	/**
	 * Getter del numero maxim de colisions
	 * @return numero maxim de colisions
	 */
	public int getMaxNumColisions() {
		return maxNumColisions;
	}

	/**
	 * Setter del numero maxim de colisions
	 */
	public void setMaxNumColisions(int maxNumColisions) {
		this.maxNumColisions = maxNumColisions;
	}

	/**
	 * Metode per a afegir un element a la taula
	 */
	public boolean afegir(K k, V v) {
		int clauHash = 	Math.abs(k.hashCode() % capacitatTaula);

		if (taulaElements[clauHash] == null)
		{
			taulaElements[clauHash] = new NodeHash<K, V>(k, null);
			this.numElements++;
			// analitzar colisions
			numColisions[clauHash]++;
			if (numColisions[clauHash]>maxNumColisions)
			{
				maxNumColisions=numColisions[clauHash];
			}
			return true;
		}
		else 
		{
			NodeHash<K, V> n = taulaElements[clauHash];

			while (n != null && !n.getClau().equals(k)) {
				n = n.getSeguent();
			}
			if (n.getClau().equals(k))	
				return false;	// error paraula ja afegida
			else 
			{							//inserir
				NodeHash<K, V> nouNode = new NodeHash<K, V>(k, null);
				n.setSeguent(nouNode);
				this.numElements++;
				// analitzar colisions
				numColisions[clauHash]++;
				if (numColisions[clauHash]>maxNumColisions) maxNumColisions=numColisions[clauHash];
				return true;
			}
		}
	}

	/**
	 * Metode per a afegir una aparicio a una paraula de la taula (valors)
	 */
	public boolean afegirAparicio(K k, int plana, int linia) {
		int clauHash = Math.abs(k.hashCode() % capacitatTaula); //calculem la posicio de la clau
		NodeHash<K, V> n = taulaElements[clauHash];
		
		while (!n.getClau().equals(k))
		{
			n= n.getSeguent();//anar al seguent
			if (n==null) return false;
		}
		Index index = (Index)(n.getValor());
		index.AfegirAparicio(plana, linia); //afegir l'aparicio
		return true;
	}
	 /**
	  * Metode per a esborrar un element de la taula
	  */
	public boolean esborrar(K k) {
		int clauHash = Math.abs(k.hashCode() % capacitatTaula);

		NodeHash<K, V> nant = taulaElements[clauHash];

		if (nant != null) 
		{
			if (nant.getClau().equals(k))
			{
				taulaElements[clauHash] = nant.getSeguent();
				numElements--;
				return true;
			}
			else 
			{
				NodeHash<K, V> n = nant.getSeguent();
				while (n != null && !n.getClau().equals(k)) 
				{
					nant = n;
					n = n.getSeguent();
				}
				if (n==null) return false;	// No l'hem trobat
				else
				{
					nant.setSeguent(n.getSeguent());
					numElements--;
					return true;
				}					
			}			
		}
		return false;
	}

	/**
	 * Metode per a consultar un element de la taula
	 */
	public V consultar(K k) {
		int clauHash = Math.abs(k.hashCode() % capacitatTaula);
		NodeHash<K, V> n = taulaElements[clauHash];

		while (n != null && !n.getClau().equals(k)){ //busquem per la clau
			n = n.getSeguent();
		}
		return (n != null) ? n.getValor() : null; //si l'hem trobat, retornem el valor, sino null
	}
 
	/**
	 * Getter del factor de carrega
	 */
	public float getFactorDeCarrega() {
		return (float) numElements / capacitatTaula;
	}

	/**
	 * toString de la classe. Utilitza el MeuIterator per a recorrer la taula
	 */
	public String mostrarIndex() 
	{
		MeuIterator<NodeHash<K,V>> it = new MeuIterator<NodeHash<K,V>>(this);
		LlistaGenericaOrd<NodeHash<K,V>> ll = new LlistaGenericaOrd<NodeHash<K,V>>(10);
		NodeHash<K,V> aux; 
		while (it.hasNext()){
			aux=it.next(); //guardem la paraula per anar fent comprovacions
			while (aux!=null)
			{ //si no es null
				ll.afegirElement(aux);	// afegim a la llista ordenadament
				aux=aux.getSeguent();
			}
		}
		MeuIterator<NodeHash<K,V>> i = new MeuIterator<NodeHash<K,V>>(ll);
		String out= "";
		while (i.hasNext())
		{
			aux = i.next();
			out+= aux.getClau() + " "; //posem la paraula del index (clau)
			if(aux.getValor()!=null) out+=aux.getValor(); //posem les aparicions que te (llista valors)
		}
		return out;
	}

	/**
	 * Metode per a mostrar les colisions
	 */
	// analitzar colisions
	public void mostrarColisions() {
		int[] frequencia=new int[maxNumColisions+1];
		int num, totalElements=0;
		for (int i = 0; i < this.capacitatTaula; i++) {
			num=numColisions[i];
			frequencia[num]++;
		}
		System.out.println("Analisi de les colisions. X (quantes posicions de la taula tenen X colisions)");
		for (int i = 0; i <= maxNumColisions; i++) {
			totalElements=totalElements+frequencia[i]*i;
			System.out.print(i+":("+frequencia[i]+") ");
			System.out.println();
		}
		System.out.println("El numero de valors diferents que tenim a la taula es de "+totalElements);
	}

	/**
	 * Metode per a buscar l'existencia d'un element a la taula
	 */
	public boolean existeix(K k) {
		int clauHash = Math.abs(k.hashCode() % capacitatTaula);
		NodeHash<K, V> n = taulaElements[clauHash];

		//mentre que hi hagi seguent seguira iterant buscant la clau amb coincidecia
		//si arriba al final i no troba coincidencia es quedara null
		while (n != null && !n.getClau().equals(k)){ //busquem per la clau
			n = n.getSeguent();
		}
		return (n != null); //si l'hem trobat, retornem el true, sino false
	}
		
}
