import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Analyzer {

	private static char input[] = new char[100];// �洢������ַ�����
	private static ArrayList<TokenS> output = new ArrayList<>();//���
	private static char word[];// ���ʷ���
	private static int num;// ����
	private static int code;// �����ֱ���
	private static char ch;// ��ǰ�����ַ�
	private static int p, sp, row;
	private static String inputFile;

	// �����ǿ�ʶ��ĵ��ʷ���
	private static String[] reservedWords = { "class", "public", "protected",
			"private", "void", "static", "int", "char", "float", "double",
			"string", "if", "else", "do", "while", "try", "catch", "switch",
			"case" ,"for" };

//	 private static String[] operators =
//	 {"+","+=","-","-=","*","*=","/","/=","=","==","&","|","&&","||","!","!="
//	 ,"<","<=",">",">="};
//	 private static String[] notes = {"//","/*","*/"};
//	 private static String[] others =
//	 {"(",")","[","]","{","}",";",",",":","'","\""};

	private static void getInput() throws IOException {
		System.out.println("Please input file name:");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		inputFile = br.readLine();
		BufferedReader br2 = new BufferedReader(new InputStreamReader(
				new FileInputStream(new File(inputFile))));
		String line = null;
		char[] tmp = null;
		p = 0;
		while ((line = br2.readLine()) != null) {
			tmp = line.toCharArray();
			for (int i = 0; i < tmp.length; i++) {
				if (tmp[i] == ' '|| tmp[i]=='\t')
					continue;
				input[p++] = tmp[i];
			}
			input[p++] = '\n';
		}
		input[p] = '#';
		br2.close();
	}

	private static void output() throws IOException{

		String[] splits = inputFile.split("\\.");
		File outputFile = new File(splits[0]+".output");
		if (!outputFile.exists())
			outputFile.createNewFile();
		BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile, false));
		for(TokenS t:output){
			System.out.println(t.toString());
			bw.write(t.toString());
			bw.newLine();
		}
		bw.flush();
		bw.close();
	}
	
	private static void scanner() {
		word = new char[9];
		ch = input[p++];

		if ((ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z')) {// �����Ǳ����ֻ�����������������ȣ�
			sp = 0;
			while ((ch >= '0' && ch <= '9') || (ch >= 'a' && ch <= 'z')
					|| (ch >= 'A' && ch <= 'Z')) {
				word[sp++] = ch;
				ch = input[p++];
				word[sp] = '\0';
				for (int i = 0; i < reservedWords.length; i++) {
					if (ch2s(word).equals(reservedWords[i])) {
						code = i + 1;
						p --;
						return;
					}
				}
			}
			word[sp++] = '\0';
			p--; // �Żض����
			code = 56;

		} else if (ch >= '0' && ch <= '9') { // ������������
			num = 0;
			while (ch >= '0' && ch <= '9') {
				num = num * 10 + ch - '0';
				ch = input[p++];
			}
			p--;
			code = 57;
			if (num < 0)
				code = -2;// �����������ֵ��ɸ���������
		} else { // �����ַ�
			sp = 0;
			word[sp++] = ch;
			switch (ch) {
			case '+':
				ch = input[p++];
				if (ch == '=') {// +=
					code = 23;
					word[sp++] = ch;
				} else {// +
					code = 22;
					p--;
				}
				break;
			case '-':
				ch = input[p++];
				if (ch >= '0' && ch <= '9') { // �����Ǹ�����
					num = 0;
					while (ch >= '0' && ch <= '9') {
						num = num * 10 + ch - '0';
						ch = input[p++];
					}
					p--;
					code = 57;
					if (num < 0)
						code = -2;
					num *= -1;// ��ɸ���
				} else if (ch == '=') {// -=
					code = 25;
					word[sp++] = ch;
				} else {// -
					code = 24;
					p--;
				}
				break;
			case '*':
				ch = input[p++];
				if (ch == '=') {// *=
					code = 27;
					word[sp++] = ch;
				} else if (ch == '/') {// */
					code = 44;
					word[sp++] = ch;
				} else {// *
					code = 26;
					p--;
				}
				break;
			case '/':
				ch = input[p++];
				if (ch == '=') {// /=
					code = 29;
					word[sp++] = ch;
				} else if (ch == '/') {// //
					code = 42;
					word[sp++] = ch;
				} else if (ch == '*') {// /*
					code = 26;
					word[sp++] = ch;
				} else {// /
					code = 28;
					p--;
				}
				break;
			case '=':
				ch = input[p++];
				if (ch == '=') { // ==
					code = 31;
					word[sp++] = ch;
				} else { // =
					code = 30;
					p--;
				}
				break;
			case '<':
				ch = input[p++];
				if (ch == '=') { // <=
					code = 39;
					word[sp++] = ch;
				} else { // <
					code = 38;
					p--;
				}
				break;
			case '>':
				ch = input[p++];
				if (ch == '=') { // >=
					code = 41;
					word[sp++] = ch;
				} else { // >
					code = 40;
					p--;
				}
				break;
			case '&':
				ch = input[p++];
				if (ch == '&') { // &&
					code = 33;
					word[sp++] = ch;
				} else { // &
					code = 32;
					p--;
				}
				break;
			case '|':
				ch = input[p++];
				if (ch == '|') { // ||
					code = 35;
					word[sp++] = ch;
				} else { // |
					code = 34;
					p--;
				}
				break;
			case '!':
				ch = input[p++];
				if (ch == '=') { // !=
					code = 37;
					word[sp++] = ch;
				} else { // !
					code = 36;
					p--;
				}
				break;

			case '(':code = 45; break;
			case ')':code = 46; break;
			case '[':code = 47; break;
			case ']':code = 48; break;
			case '{':code = 49; break;
			case '}':code = 50; break;
			case ',':code = 51; break;
			case ':':code = 52; break;
			case ';':code = 53; break;
			case '\'':code = 54; break;
			case '\"':code = 55; break;
			case '\n':code = -1; break;
			default:code = -3; break;
			}
		}
	}

	private static String ch2s(char[] c) {
		int len = 0;
		for (int i = 0; i < c.length; i++) {
			if (c[i] != '\0')
				len++;
		}
		return String.valueOf(c).substring(0, len);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			getInput();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		p = 0;
		row = 1;
		do {
			scanner();
			switch (code) {
			case 57: // ����
				output.add(new TokenS(code, num+""));
				break;
			case -1: // ����
				row++;
				break;
			case -2: // ���͹���
				output.add(new TokenS("integer overflow at row "+row));
				break;
			case -3: // δ�����ַ�
				output.add(new TokenS("undefined character at row "+row));
				break;
			default:// һ�㵥�ʷ���
				output.add(new TokenS(code,ch2s(word)));
				break;
			}
		} while (input[p] != '#');
		try {
			output();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
