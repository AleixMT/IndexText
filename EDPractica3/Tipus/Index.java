package Tipus;
/**
 * Classe tipus Index, contindra una llista d'aparicions de la paraula
 * @author Aleix Marine i Cristina Izquierdo
 *
 */
public class Index{
		private Aparicio[] llista;
		private int pos;
		
		public Index(){
			this.pos = -1;
			this.llista = new Aparicio[1000];
			}
		
		/**
		 * Metode per afegir una aparicio per a la paraula.
		 * @param plana - plana on apareix la paraula
		 * @param linia - linia on apareix la paraula
		 * @return true - si s'ha afegit una aparicio
		 * @return false - si s'ha registrat una aparicio en aquella mateixa linia i per tant no s'afegeix res.
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
				if (this.llista[pos].equals(plana, linia)) return false; //ja existeix
				else
				{
					this.pos++;
					this.llista[pos] = new Aparicio (plana, linia);
					return true;
				}
			}
		}

		/**
		 * Getter de la llista
		 * @return llista d'aparicions
		 */
		public Aparicio[] getLlista() {
			return llista;
		}

		/**
		 * Setter de la llista
		 * @param llista
		 */
		public void setLlista(Aparicio[] llista) {
			this.llista = llista;
		}

		/**
		 * Getter de la posicio
		 * @return posicio de la llista
		 */
		public int getPos() {
			return pos;
		}

		/**
		 * Setter de la posicio
		 * @param pos
		 */
		public void setPos(int pos) {
			this.pos = pos;
		}

		/**
		 * toString de l'index, mostrara una llista d'aparicions
		 */
		@Override
		public String toString() {
			String out = "";
			for (int i=0; i<=pos; i++){
				out+=llista[i];
			}
			return out+"\n";
		}		
}
