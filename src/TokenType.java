
public enum TokenType {
	KEYWORD,ID,OPERATOR,DELIMITER;
	public static String toString(TokenType type){
		switch (type) {
		case KEYWORD:
			return "�ؼ���";
		case ID:
			return "ID";
		case OPERATOR:
			return "������";
		case DELIMITER:
			return "�ָ���";
		default:
			break;
		}
		return null;
	}
	
}
