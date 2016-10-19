
public class Token {
	private TokenType type;
	private String name;
	
	public Token(TokenType type, String name) {
		this.type = type;
		this.name = name;
	}
	

	public static String tokenToString(Token token){
		return "<"+token.type.toString()+" , "+token.name+">";
	}
}
