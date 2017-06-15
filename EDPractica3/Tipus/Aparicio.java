package Tipus;

public class Aparicio {
	private int plana;
	private int linia;
	
	public Aparicio(int plana, int linia){
		this.plana = plana;
		this.linia = linia;
	}
	public int getPlana() {
		return plana;
		
	}
	public void setPlana(int plana) {
		this.plana = plana;
	}
	
	public int getLinia() {
		return linia;
	}
	
	public void setLinia(int linia) {
		this.linia = linia;
	}
	
	public boolean equals(int plana, int linia){
		return (this.plana==plana && this.linia==linia);
	}
	
	public String toString() {
		return plana + ":" + linia+" ";
	}
	
}
