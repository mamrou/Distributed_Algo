package TP;

import java.io.IOException;


import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.stream.Collector;
import java.util.stream.Collectors;



public class WordCount {
	
	
	// Lis le fichier ligne par ligne
	public static ArrayList<String> readLines(String filename) {
		   List<String> lines = null;
		   try {
		       lines = Files.readAllLines(Paths.get(filename), Charset.forName("UTF-8"));
		   } catch (IOException e) {
		       System.out.println("Erreur lors de la lecture de " + filename);
		       System.exit(1);
		   }
		   return (ArrayList<String>) lines;
	}
	
	
	// Transforme ligne par ligne en List de mots
	public static ArrayList<String> wordsList(ArrayList<String> file_lines) {			
		String full_texte = file_lines.get(0);
		for(int i=1;i<file_lines.size();i++) {
			full_texte = full_texte.concat(" "+file_lines.get(i));			
		}		
		full_texte = full_texte.toLowerCase();
		String[] txt_split = full_texte.split("\\s+");
		ArrayList<String> result = new ArrayList<String>(Arrays.asList(txt_split));
		return result;		
	}
	
	
	
	// Fréquence associée à chaque mot du texte
	public static HashMap<String, Integer> wordCount(ArrayList<String> list_words) {		
		HashMap<String, Integer> result = new HashMap<String, Integer>();		
		for(String word :list_words) {
			if(result.containsKey(word)) {
				result.put(word, result.get(word) + 1);
			}
			else {
				result.put(word, 1);
			}			
		}		
		return result;		
	}
	
	
	// Tri le WordCount par ordre de fréquence 
	public static TreeMap<String, Integer> sortWordCount(HashMap<String, Integer> wordCount) {	
		ValueComparator comparateur = new ValueComparator(wordCount);
		TreeMap<String,Integer> wordcount_sorted = new TreeMap<String,Integer>(comparateur);
		wordcount_sorted.putAll(wordCount);
				

		return wordcount_sorted;		
	}
	
	
	public static void main(String[] args) {

		//Compter les occurences
		long startTime = System.currentTimeMillis();
		HashMap<String, Integer> wordcount = wordCount(wordsList(readLines("corpus.fr.txt")));
		long endTime   = System.currentTimeMillis();
		long totalTime = endTime - startTime;
		System.out.println("Algo a compté les occurences en " + totalTime + " ms");
		
		// Trier les occurences
		startTime = System.currentTimeMillis();
		TreeMap<String, Integer> result = sortWordCount(wordcount);
		endTime = System.currentTimeMillis();
		totalTime = endTime - startTime;
		
		System.out.println("Algo a trié les occurences en " + totalTime + " ms");
		System.out.println(sortWordCount(wordcount));



	}


}
