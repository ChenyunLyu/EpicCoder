import java.io.*;

public class codefunc
{
	private coder codeBook = new coder();
	public String message = "";
	public String cover = "";

	codefunc() throws IOException
	{
		message = " ";
		cover = " ";
	}
	
	codefunc( String m , String c ) throws IOException
	{
		codeBook = new coder();
		
		message = m;
		cover = c;
		
		if( message.equals( " " ) )
		{
			decodeAll();
		}
		
		if( cover.equals( " " ) )
		{
			encodeAll();
		}
	}
	
	codefunc( String name , String m , String c ) throws IOException
	{
		if( name == "default_codebook" )
		{
			codeBook = new coder();
		}
		else
		{
			codeBook = new coder( name );
		}
		message = m;
		cover = c;
		
		if( message.equals( " " ) )
		{
			decodeAll();
		}
		
		if( cover.equals( " " ) )
		{
			encodeAll();
		}
	}
	
	public void encodeAll()
	{
		char[] list = message.toLowerCase().toCharArray();
		String ss = "";
		for( int i = 0 ; i < list.length ; i++ )
		{
			for( int a = 0 ; a < codeBook.length ; a++ )
			{
				if( list[i] == codeBook.codeList[a].decode() )
				{
					ss = ss + codeBook.codeList[a].encode();
					//System.out.println( codeBook.codeList[a] );
				}
			}
		}
		cover = ss;
	}
	
	public void decodeAll()
	{
		int l = cover.length();
		int process = l / 3;
		char[] list = cover.toCharArray();
		String s = "";
		for( int i = 1 ; i <= process ; i++ )
		{
			String ss = "";
			ss = (list[i*3-3] + "" ) + (list[i*3-2] + "") + ( list[i*3-1] + "" );
			//System.out.println( ss );
			for( int a = 0 ; a < codeBook.length ; a++ )
			{
				//System.out.println( ( a + "" )+ ss.equals( codeBook.codeList[a].encode() ) );
				if( ss.equals( codeBook.codeList[a].encode() ) )
				{
					
					s = s + codeBook.codeList[a].decode();
				}
			}
		}
		message = s;
	}
}
