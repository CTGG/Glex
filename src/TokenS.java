
public class TokenS {

	private int code;
	private String str;
	private String error;
	
	public TokenS(int c,String s){
		this.code = c;
		this.str = s;
		this.error = null;
	}
	
	public TokenS(String error){
		this.error = error;
	}
	
	public String toString(){
		if(this.error != null)
			return "Error:" + this.error;
		return "<" + this.code + "," + this.str + ">";
	}
	
}
