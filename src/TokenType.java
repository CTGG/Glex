
public enum TokenType {
	KEYWORD,ID,OPERATOR,DELIMITER;
	public static String toString(TokenType type){
		switch (type) {
		case KEYWORD:
			return "¹Ø¼ü×Ö";
		case ID:
			return "ID";
		case OPERATOR:
			return "²Ù×÷·û";
		case DELIMITER:
			return "·Ö¸ô·û";
		default:
			break;
		}
		return null;
	}
	
}
