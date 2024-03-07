import java.util.ArrayList;
import java.util.Scanner;

public class Homework7 {

	public static void main(String[] args) {
		PokemonDB pdb = new PokemonDB();
		//Scanner myObj = new Scanner(System.in); 
	     System.out.println("**************************************");
	     System.out.println("Retrieving the record for Tentacruel");
	     //String test = myObj.nextLine();
         Pokemon pokemon = pdb.searchByName("Tentacruel");
         printPokemon(pokemon);
         
         System.out.println("**************************************");
	     System.out.println("Retrieving the Pokemons whose type is  electric");
	     ArrayList<Pokemon> pokemons = pdb.searchByType("electric");
	     for (Pokemon curr : pokemons) {
	    	 printPokemon(curr);
	     }
	     System.out.println(pokemons.size() + " pokemons met the criteria");
	     
	     System.out.println("**************************************");
	     System.out.println("Retrieving the Pokemons who have the bubble move and the water type");
	     pokemons = pdb.searchByMoveAndType("bubble","water");
	     for (Pokemon curr : pokemons) {
	    	 printPokemon(curr);
	     }
	     System.out.println(pokemons.size() + " pokemons met the criteria");
	     
	     System.out.println("**************************************");
	     System.out.println("Retrieving Pokemons whose defense is greater than 75 and attack greater than 80");
	     pokemons = pdb.searchByDefenseAndAttack(75, 80);
	     for (Pokemon curr : pokemons) {
	    	 printPokemon(curr);
	     }
	     System.out.println(pokemons.size() + " pokemons met the criteria");
	}
	
	
	 private static void printPokemon(Pokemon pokemon){
	        System.out.println("--------------------POKEMON INFO---------------------------");
	        System.out.println("Our little friend is " + pokemon.getName() + " whose type is " + pokemon.getType());

	   
	    }


}
