
import java.io.File;
import java.util.*;

import util.*;
import tree.*;
public class MainReader {
	public static void main(String[] args) throws Exception
	{	        
		// pass the path to the file as a parameter
		File file = new File("/home/fred/Desktop/HuffmanCode/esempi_testi/esempio2.txt");
		Scanner sc = new Scanner(file);
		Map<Character, MutableInt> freq = new HashMap<Character, MutableInt>();
		long inizio = System.currentTimeMillis();
		int counter = 0;
		while(sc.hasNextLine()){
		String word = sc.nextLine();
		for(char c : word.toCharArray()) {
			MutableInt count = freq.get(c);
			if (count == null) {
				freq.put(c, new MutableInt(1, ""));
			}
			else {
				count.increment();
			}		}      
			counter++;
		}
		sc.close();
		freq.put('\n', new MutableInt(counter, ""));
		System.out.println("TEMPO COSTRUZIONE TAB FREQUENZA " + (System.currentTimeMillis()-inizio));
		freq.forEach((key, value) -> System.out.println((key) + "-->" + (value.get())));
		inizio = System.currentTimeMillis();
		PriorityQueue<BinaryTreeNode> pq = new PriorityQueue<BinaryTreeNode>(freq.size() , new NodoComparator());
		PriorityQueue<BinaryTreeNode> pq2 = new PriorityQueue<BinaryTreeNode>(freq.size() , new NodoComparator());

		freq.forEach((key, value) -> pq.add(new BinaryTreeNode(new FrequencyChar(key, value.get()))));
		freq.forEach((key, value) -> pq2.add(new BinaryTreeNode(new FrequencyChar(key, value.get()))));

		while (!pq2.isEmpty()) {
			BinaryTreeNode btn = pq2.poll();
			System.out.print("NODO " + btn.getInfo_C() + " PRIORITY " + btn.getInfo_Freq() + "  --->");
		}

		System.out.println("TEMPO COSTRUZIONE CODA DI PRIORITA " + (System.currentTimeMillis()-inizio));
		inizio = System.currentTimeMillis();
		for(int i = 0; i < freq.size()-1 ; i++){
			BinaryTreeNode extract1 = pq.poll();
			BinaryTreeNode extract2 = pq.poll();

			BinaryTreeNode z = new BinaryTreeNode( new FrequencyChar('£', extract1.getInfo_Freq() + extract1.getInfo_Freq()));
			z.setLeft(extract1);
			z.setRight(extract2);
			extract1.setParent(z);
			extract2.setParent(z);
			pq.add(z);
		}
		BinaryTreeNode root = pq.poll();
		TreeVisualizer tV = new TreeVisualizer(new BinaryTree(root));
		tV.show("Results");
		System.out.println("TEMPO COSTRUZIONE ALBERO DI CODIFICA " + (System.currentTimeMillis()-inizio));
		//WriteText.write("albero.txt", Printer.TreePrinter(root));
		inizio = System.currentTimeMillis();
		Print(root, "", freq);
		System.out.println("TEMPO AGGIORNAMENTO TABELLA E CODIFICA " + (System.currentTimeMillis()-inizio));

		//freq.forEach((key, value) -> System.out.println((key) + "-->" + (value.get() + "  encoding " + value.getenc())));
		inizio = System.currentTimeMillis();

		Map<String, Character> encode_words = new HashMap<String,Character>();
		Map< Character, String> words_encode = new HashMap<Character, String>();

		for (var entry : freq.entrySet()) {
			var key = entry.getKey();
			var value = entry.getValue();
			words_encode.put(key, value.getenc());
			encode_words.put(value.getenc(), key);
		}
		encode_words.forEach((key, value) -> System.out.println((key) + "-->" + (value)));
		inizio = System.currentTimeMillis();

		Scanner sc2 = new Scanner(file);
		StringBuilder bitsString = new StringBuilder();

		while(sc2.hasNextLine()){

			String word = sc2.nextLine();
			for(char c : word.toCharArray()) {
				bitsString.append(words_encode.get(c));

			}
			bitsString.append(words_encode.get('\n'));
		}
		sc2.close();
		System.out.println("TEMPO DI ENCODING " + (System.currentTimeMillis()-inizio));
		inizio = System.currentTimeMillis();
		WriteBinary.WriteBin(bitsString.toString(), "codifica.txt");
		
		System.out.println("TEMPO SCRITTURA CODIFICA " + (System.currentTimeMillis()-inizio));
		inizio = System.currentTimeMillis();

		StringBuilder coding = new StringBuilder();
		coding = (ReadBinary.ReadBin("codifica.txt"));
		System.out.println("TEMPO LETTURA CODIFICA " + (System.currentTimeMillis()-inizio));
		inizio = System.currentTimeMillis();

		StringBuilder result = new StringBuilder();

		StringBuilder compare = new StringBuilder();


		for(int c = 0; c < coding.length(); c++){

		compare.append(coding.charAt(c));

		if(encode_words.containsKey(compare.toString())){

			result.append(encode_words.get(compare.toString()));

			compare.setLength(0);

			}

		}
		coding.setLength(0);
		System.out.println("TEMPO  DECODIFICA " + (System.currentTimeMillis()-inizio));
		inizio = System.currentTimeMillis();

		util.WriteText.write("decodifica.txt", result.toString());		
		System.out.println("TEMPO SCRITTURA DECODIFICA " + (System.currentTimeMillis()-inizio));

	}
	public static void Print(BinaryTreeNode root, String Encoding, Map<Character, MutableInt> freq){
		if(root.getLeft() == null && root.getRight() == null){
			freq.put(root.getInfo_C(), new MutableInt( root.getInfo_C(), Encoding));
			return ;
		}	

		else{
			Print(root.getLeft(), Encoding+"1", freq);
			if(root.getRight() != null){
				Print(root.getRight(), Encoding+"0", freq);

			}

		}
	}

}
