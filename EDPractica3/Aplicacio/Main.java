package Aplicacio;

import TAD.*;

public class Main {

	public static void main(String[] args) {
		TADArbreBinari<Integer> arbre=new ArbreBinariDinamic<Integer>();
		TADArbreBinari<Integer> aux1=new ArbreBinariDinamic<Integer>();
		TADArbreBinari<Integer> aux2=new ArbreBinariDinamic<Integer>();
		Node<Integer> dotze=new Node<Integer>(null, 12, null);
		Node<Integer> tres=new Node<Integer>(null, 3, null);
		Node<Integer> u=new Node<Integer>(null, 1, null);
		
		aux1.arrelar(new ArbreBinariDinamic<Integer>(dotze), 7, null);
		aux2.arrelar(new ArbreBinariDinamic<Integer>(tres), 4, new ArbreBinariDinamic<Integer>(u));
		
		arbre.arrelar(aux1, 5, aux2);
		//arbre.arrelar(null, 5, aux2);
		
		System.out.println("Preordre "+arbre.preordre());
		System.out.println("Inordre "+arbre.inordre());
		System.out.println("Postordre "+arbre.postordre());
		
		System.out.println("Es equilibrat "+arbre.esEquilibrat());
		
		System.out.println("Al�ada arbre "+arbre.alcada());
	
	}

}
