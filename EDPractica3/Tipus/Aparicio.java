package Tipus;
/**
 * Classe tipus Aparicio, contindra la plana i la linia on apareix cada paraula
 * @author Aleix Marine i Cristina Izquierdo
 *
 */
public class Aparicio {
	private int plana;
	private int linia;
	
	/**
	 * Constructor de la classe
	 */
	public Aparicio(int plana, int linia){
		this.plana = plana;
		this.linia = linia;
	}
	
	/**
	 * Getter de la plana
	 * @return plana d'aparicio
	 */
	public int getPlana() {
		return plana;
		
	}
	
	/**
	 * Setter de la plana
	 * @param plana
	 */
	public void setPlana(int plana) {
		this.plana = plana;
	}
	
	/**
	 * Getter de la linia
	 * @return linia d'aparicio
	 */
	public int getLinia() {
		return linia;
	}
	
	/**
	 * Setter de la linia
	 * @param linia
	 */
	public void setLinia(int linia) {
		this.linia = linia;
	}
	
	/**
	 * Metode per a comparar si aquesta aparicio ja s'ha afegit abans
	 * @param plana on apareix
	 * @param linia on apareix
	 * @return false - no s'ha afegit
	 * @return true - s'ha afegit
	 */
	public boolean equals(int plana, int linia){
		return (this.plana==plana && this.linia==linia);
	}
	
	/**
	 * Metode toString de les aparicions "plana:linia"
	 */
	public String toString() {
		return plana + ":" + linia+" ";
	}
	
}
