import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;

import static com.mongodb.client.model.Filters.*;

import java.net.UnknownHostException;
import java.util.ArrayList;

import org.bson.Document;

public class PokemonDB {

	public PokemonDB() {
		DBConnection.setDBName("pokemon");
	}

	public Pokemon searchByName(String name) {
		MongoCursor<Document> cursor = null;
		Pokemon pokemon = null;
		try {
			MongoDatabase db = DBConnection.getDB();
			MongoCollection<Document> coll = db.getCollection("pokemon");
			cursor = coll.find(eq(PokeFields.NAME, name)).iterator();
			if (cursor.hasNext()) {
				Document doc = cursor.next();
				pokemon = buildPokemon(doc);
			}
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
		return pokemon;

	}

	public ArrayList<Pokemon> searchByType(String type){
		MongoCursor<Document> cursor = null;
		ArrayList<Pokemon> pokemon = new ArrayList<Pokemon>();
		try {
			DBConnection.setDBName("pokemon");
			MongoDatabase db = DBConnection.getDB();
			MongoCollection<Document> coll = db.getCollection("pokemon");

			cursor = coll.find(and(eq("type", type))).iterator();
			pokemon = buildPokemonList(cursor);
			cursor.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
	
		return pokemon;

	}

	public ArrayList<Pokemon> searchByMoveAndType(String move, String type){
		MongoCursor<Document> cursor = null;
		ArrayList<Pokemon> pokemon = new ArrayList<Pokemon>();
		try {
			DBConnection.setDBName("pokemon");
			MongoDatabase db = DBConnection.getDB();
			MongoCollection<Document> coll = db.getCollection("pokemon");

			cursor = coll.find(and(eq("moves", move), eq("type", type))).iterator();
			pokemon = buildPokemonList(cursor);
			cursor.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
	
		return pokemon;
		
	}
	
	public ArrayList<Pokemon> searchByDefenseAndAttack(int defenseMin, int attackMin){
		
		MongoCursor<Document> cursor = null;
		ArrayList<Pokemon> pokemon = new ArrayList<Pokemon>();
		try {
			DBConnection.setDBName("pokemon");
			MongoDatabase db = DBConnection.getDB();
			MongoCollection<Document> coll = db.getCollection("pokemon");

			cursor = coll.find(and(eq("defense", defenseMin), eq("attack", attackMin))).iterator();
			pokemon = buildPokemonList(cursor);
			cursor.close();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} finally {
			if (cursor != null) {
				cursor.close();
			}
		}
	
		return pokemon;
		
	}

	
	private Pokemon buildPokemon(Document pokemonDoc) {
		Pokemon pokemon = new Pokemon();
		if (pokemonDoc.containsKey(PokeFields.NAME))
			pokemon.setName(pokemonDoc.getString(PokeFields.NAME));
		if (pokemonDoc.containsKey(PokeFields.ATTACK))
			pokemon.setAttack(pokemonDoc.getInteger(PokeFields.ATTACK));
		if (pokemonDoc.containsKey(PokeFields.DEFENSE))
			pokemon.setDefense(pokemonDoc.getInteger(PokeFields.DEFENSE));
		if (pokemonDoc.containsKey(PokeFields.EVOLVELEVEL))
			pokemon.setEvolveLevel(pokemonDoc.getInteger(PokeFields.EVOLVELEVEL));
		if (pokemonDoc.containsKey(PokeFields.EVOLVETO))
			pokemon.setEvolveTo(pokemonDoc.getString(PokeFields.EVOLVETO));
		if (pokemonDoc.containsKey(PokeFields.TYPE))
			pokemon.setType(pokemonDoc.getString(PokeFields.TYPE));
		if (pokemonDoc.containsKey(PokeFields.LEVELS)) {
			ArrayList<Integer> levelList = (ArrayList<Integer>) pokemonDoc.get(PokeFields.LEVELS);
			pokemon.setLevels(levelList);
		}

		if (pokemonDoc.containsKey(PokeFields.MOVES)) {
			ArrayList<String> moveList = (ArrayList<String>) pokemonDoc.get(PokeFields.MOVES);

			pokemon.setMoves(moveList);
		}
		return pokemon;
	}
	
	private ArrayList<Pokemon> buildPokemonList(MongoCursor<Document> cursor) {
		ArrayList<Pokemon> zips = new ArrayList<Pokemon>();
		while (((MongoCursor<Document>) cursor).hasNext()) {
			Document pokemonDoc = ((MongoCursor<Document>) cursor).next();
			Pokemon pokemon = buildPokemon(pokemonDoc);
			zips.add(pokemon);
		}
		return zips;
	}

	
	


}
