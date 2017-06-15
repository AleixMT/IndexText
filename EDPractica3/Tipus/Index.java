package Tipus;

import java.util.Arrays;

public class Index{
		private Aparicio[] llista;
		private int pos;
		
		public Index(){
			this.pos = -1;
			this.llista = new Aparicio[1000];
			}
		
		/**
		 * Metode per afegir una aparicio per a la paraula. Rep la la plana i la linia on 
		 * ha hagut l'aparicio. Retorna true si s'ha afegit una aparicio. Retorna false si s'ha 
		 * registrat una aparicio en aquella mateixa linia i per tant no s'afegeix res.
		 * @param plana
		 * @param linia
		 * @return
		 */
		public boolean AfegirAparicio(int plana, int linia){
			if (pos == -1)
			{
				pos++;
				this.llista[pos] = new Aparicio (plana, linia);
				return true;
			}
			else
			{
				if (this.llista[pos].equals(plana, linia)) return false;	
				else
				{
					this.pos++;
					this.llista[pos] = new Aparicio (plana, linia);
					return true;
				}
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
			String out = "";
			for (int i=-1; i<pos; i++){
				out+=llista[pos];
			}
			return out;
		}
		
		
		

		
		
		
}
