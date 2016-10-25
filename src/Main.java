import java.util.ArrayList;

public class Main {
	private static String filePath = "/input.txt";
	private static String outPath = "/output.txt";
	public static void main(String[] args) {
		LexicalAnalyzer ana = new LexicalAnalyzer();
		//put input lines into a list
		ArrayList<String> list = IOHelper.readFile(filePath);
//		//prepare the output list
//		ArrayList<Token> outlist = new ArrayList<Token>();
//		//for each list check char by char
//		for (String str : list) {
//			//1.get chars
//			char[] tempchars = ana.str2chars(str);
//			//2.analyze char by char and store each token in the list
//			outlist = ana.analyzeChars(tempchars, outlist);
//		}
//		ArrayList<String> outlistStr = new ArrayList<String>();
//		for(Token t:outlist){
//			outlistStr.add(t.toString());
//		}
////		IOHelper.saveFile(outlistStr, outPath);
	}
}
