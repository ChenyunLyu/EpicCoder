public class code
{
	private char message = ' ';
	private String cover = "";
	
	code()
	{
		message = ' ';
		cover = "000";
	}
	
	code( char m , String c )
	{
		message = m;
		cover = c;
	}
	
	public void setMessage( char m )
	{
		message = m;
	}
	
	public void setCover( String c )
	{
		cover = c;
	}
	
	public char decode()
	{
		return this.message;
	}
	
	public String encode()
	{
		return this.cover;
	}
	
	public String toString()
	{
		return "Code = " + cover + " , message = " + message;
	}
}
