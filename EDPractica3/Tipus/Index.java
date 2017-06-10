package Tipus;

import java.util.Arrays;

public class Index{
		private String paraula;
		private Aparicio[] llista;
		private int pos;
		
		public Index(String paraula){
			this.paraula = paraula;
			this.pos = 0;
		}
		
		/**
		 * Mètode per afegir una aparició per a la paraula. Rep la la plana i la linia on 
		 * ha hagut l'aparició. Retorna true si s'ha afegit una aparició. Retorna false si s'ha 
		 * registrat una aparició en aquella mateixa linia i per tant no s'afegeix res.
		 * @param plana
		 * @param linia
		 * @return
		 */
		public boolean AfegirAparicio(int plana, int linia){
			if (this.llista[pos].equals(plana, linia)) return false;	
			else
			{
				this.pos++;
				this.llista[pos] = new Aparicio (plana, linia);
				return true;
			}
		}
		
		public boolean equals(String paraula){
			return this.paraula.equals(paraula);
		}
		
		public int compareTo(Index i){
			return this.paraula.compareTo(i.getParaula());
		}
		
		public int compareTo(String paraula){
			return this.paraula.compareTo(paraula);
		}
		
		public String getParaula() {
			return paraula;
		}
		
		public void setParaula(String paraula) {
			this.paraula = paraula;
		}
		
		public Aparicio[] getLlista() {
			return llista;
		}
		
		public void setLlista(Aparicio[] llista) {
			this.llista = llista;
		}

		public String toString() {
			return "Index [paraula=" + paraula + ", llista="
					+ Arrays.toString(llista) + "]";
		}
		
		
}
