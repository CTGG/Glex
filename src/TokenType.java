
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
