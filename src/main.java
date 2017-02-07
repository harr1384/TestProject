import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.HashMap;


class HelloWorldApp{
	public static void main(String args[]) throws FileNotFoundException{
		//System.out.println("Hello World");
		
		File file = new File("2000010.txt");
		HashMap<String, Integer> hash = parseText(file);
		int count = wordCount(hash);
		instanceCount(hash, "water");
		instanceCount(hash, "satan");
		
		float density = getWordDensity(hash, count);
		
		System.out.println("Word Count: " + count);
		System.out.println("Word Density: " + density + "%");
		

		Object[] keys = hash.keySet().toArray();
		//Object[] values = hash.values().toArray();
		
	
		//calculate density as a percentage.
		//float density = ((float)hash.size()/wordCount)*100;
	
		
		//System.out.println("Verne used " + hash.size() + " different words and " + wordCount + " words overall in the novel \"20,000 Leagues Under the Sea\"!");
		//System.out.println("Vocabulary Density: " + density + "%" );
		
		int max = 0;
		int index = 0;
		
		for (int i = 0; i < keys.length; i++){
			
			if(hash.get(keys[i]) > 100 && hash.get(keys[i]) < 500){
				
				max = hash.get(keys[i]);
				index = i;
				System.out.println("The word " + keys[index] + " was used " + max + " times.");
				
			}
			else {
				
				//System.out.println("The word " + keys[i] + " was used " + hash.get(keys[i]) + " times.");
				
			}
			
			
		}
		
		
	
	}
	
	public static HashMap<String, Integer> parseText(File f) throws FileNotFoundException{
		
		HashMap<String, Integer> hash = new HashMap<String, Integer>();
		
		Scanner parser = new Scanner(f);
		while (parser.hasNextLine()){
			String line = parser.nextLine();
			
			String[] splits = line.split("\\P{L}+");
			for(int i = 0; i < splits.length; i++){
				
				if (hash.containsKey(splits[i].toLowerCase())){
					
					hash.put(splits[i].toLowerCase(), hash.get(splits[i].toLowerCase()) + 1) ;
				}
				else {
					
					hash.put(splits[i].toLowerCase(), 1);
				}
					
			}
			
		}
		parser.close();
		
		return hash;
		
	}
	
	//returns overall word count for the text after the hash map has been created.
	public static int wordCount(HashMap<String, Integer> hash){
		
		int count = 0;
		Object[] keys = hash.keySet().toArray();
		
		for(int i = 0; i < keys.length; i++ ){
			
			count = count + hash.get(keys[i]);
		}
		return count;
	}
	//counts the number of instances a specific word appears in the text.
	public static void instanceCount(HashMap<String, Integer> hash, String word){
		
		int count = 0;
		if(hash.get(word) != null){
			
			count = hash.get(word);
			System.out.println("The word " + word + " is used " + count + " times in the text.");
		}
		else {
			
			System.out.println("The word " + word + " is not present in the text.");
		}
		
		return ;
	}
	
	//returns a percentage of unique words used in the text
	public static float getWordDensity(HashMap<String, Integer> hash, int totalWordCount){
		
		float density;
		float uniqueWordCount = hash.keySet().size();
		
		density = (uniqueWordCount/totalWordCount)*100;
		
		return density;
		 
	}
	
}