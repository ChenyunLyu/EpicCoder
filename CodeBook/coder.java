import java.io.*;

public class coder
{
	
	public char[] charList = loadList().toCharArray();
	public int length = charList.length;
	//charList[ length - 1 ] = '\n';
	public code[] codeList = new code[length];
	//public char[] charList = {' ','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',',','.',':','!','?',
							
	                         //1   2   3   4   5   6   7   8   9   10  11  12  13  14  15  16  17  18  19  20  21  22  23  24  25  26  27  28  29  30  31  32
	                         
	                         // '=','(',')','-',};
	                         //33  34  35  36  37  38  39
	
	coder() throws IOException
	{
		//resetMessage();
		charList[ length - 1 ] = '\n';
		for( int i = 0 ; i < charList.length ; i++ )
		{
			//System.out.println( i + " " + charList.length + " " + codeList[i].decode());
			codeList[i] = new code();
			codeList[i].setMessage( charList[i] );
			codeList[i].setCover( " " );
		}
		loadCover();
		//for( int i = 0 ; i < length ; i++ )
		//{
		//	System.out.println( codeList[i] );
		//}
		
	}
	
	coder( String ss ) throws IOException
	{
		//resetMessage();
		charList[ length - 1 ] = '\n';
		for( int i = 0 ; i < charList.length ; i++ )
		{
			//System.out.println( i + " " + charList.length + " " + codeList[i].decode());
			codeList[i] = new code();
			codeList[i].setMessage( charList[i] );
			codeList[i].setCover( " " );
		}
		loadCover( ss );
		//for( int i = 0 ; i < length ; i++ )
		//{
		//	System.out.println( codeList[i] );
		//}
		
	}
	
	public void resetMessage()
	{
		for( int i = 0 ; i < charList.length ; i++ )
		{
			codeList[i].setMessage( charList[i] );
		}
	}
	
	public void loadCover() throws IOException
	{
		File f = new File( "Books" , "default_codebook.codebook" );
		FileReader fr = new FileReader( f );
		BufferedReader in = new BufferedReader( fr );
		
		for( int i = 0 ; i < charList.length ; i++ )
		{
			codeList[i].setCover( in.readLine() );
		}
		in.close();
	}
	
	public String loadList() throws IOException
	{
		File f = new File( "charList.txt" );
		FileReader fr = new FileReader( f );
		BufferedReader in = new BufferedReader( fr );
		
		String ss = in.readLine();
		
		in.close();
		
		return ss;
	}
	
	public void loadCover( String ss ) throws IOException
	{
		ss = ss + ".codebook";
		File f = new File( "Books" , ss );
		FileReader fr = new FileReader( f );
		BufferedReader in = new BufferedReader( fr );
		
		for( int i = 0 ; i < charList.length ; i++ )
		{
			codeList[i].setCover( in.readLine() );
		}
		in.close();
	}
}
