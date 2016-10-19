
public enum TokenType {
	KEYWORD(1),ID(1),OPERATOR(2),DELIMITER(3);
	private int typevalue;
	private TokenType(int typevalue) {
		this.typevalue = typevalue;
	}
	public int getTypeValue() {
		return typevalue;
	}
	
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
