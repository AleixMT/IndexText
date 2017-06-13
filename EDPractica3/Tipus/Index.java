package Tipus;

import java.util.Arrays;

public class Index{
		private Aparicio[] llista;
		private int pos;
		
		public Index(){
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

		public Aparicio[] getLlista() {
			return llista;
		}

		public void setLlista(Aparicio[] llista) {
			this.llista = llista;
		}

		public int getPos() {
			return pos;
		}

		public void setPos(int pos) {
			this.pos = pos;
		}

		@Override
		public String toString() {
			return "Index [llista=" + Arrays.toString(llista) + ", pos=" + pos
					+ "]";
		}
		
		

		
		
		
}
