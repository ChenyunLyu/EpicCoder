import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.io.*;

public class codeDisplay extends JPanel
{
	private coder codes;
	private codeDisplayUnit[] cdu;
	public JPanel panel;
	
	
	codeDisplay() throws IOException
	{
		codes = new coder();
		panel = new JPanel();
		cdu = new codeDisplayUnit[codes.length];
		
		panel.setLayout( new GridLayout( 13 , 4 ) );
		
		for( int i = 0 ; i < codes.length ; i++ )
		{
			cdu[i] = new codeDisplayUnit( codes.codeList[i] );
			panel.add( cdu[i].panel );
		}
		
		panel.setVisible( true );
	}
	
	codeDisplay( String ss ) throws IOException
	{
		codes = new coder( ss );
		panel = new JPanel();
		cdu = new codeDisplayUnit[codes.length];
		
		panel.setLayout( new GridLayout( 15 , 5 ) );
		
		for( int i = 0 ; i < codes.length ; i++ )
		{
			cdu[i] = new codeDisplayUnit( codes.codeList[i] );
			panel.add( cdu[i].panel );
		}
		
		panel.setVisible( true );
	}
	
	public void setAllEditable( boolean b )
	{
		for( int i = 0 ; i < codes.length ; i++ )
		{
			cdu[i].setEditCode( b );
		}
	}
	
	public void setAllVisible( boolean b )
	{
		for( int i = 0 ; i < codes.length ; i++ )
		{
			cdu[i].setVisibleCode( b );
		}
	}
	
	public void saveToCodebook( String name ) throws IOException
	{
		name = name + ".codebook";
		File f = new File( "Books" , name );
		FileWriter fw = new FileWriter( f );
		PrintWriter out = new PrintWriter( fw );
		
		for( int i = 0 ; i < codes.length ; i++ )
		{
						
			out.println( cdu[i].getCode() );
			System.out.println( cdu[i].getCode() );
		}
		
		out.close();
	}
	
	public void loadCode( String name ) throws IOException
	{
		name = name;
		codes = new coder( name );
		updateText();
	}
	
	public void updateText()
	{
		for( int i = 0 ; i < codes.length ; i++ )
		{
			cdu[i].setCode( codes.codeList[i].encode() );
		}
	}
	
	public boolean validateCode()
	{
		boolean b = true;
		boolean t = true;
		String ss = "";
		for( int i = 0 ; i < codes.length ; i++ )
		{
			ss = ss + cdu[i].getCode();
		}
		System.out.println( codes.length * 3);
		System.out.println( ss.length() );
		if( ss.length() != ( codes.length * 3 ) )
		{
			JOptionPane.showMessageDialog( panel , "All codes must have 3 and only 3 characters!" );
			t = false;
			b = false;
		}
		String[] temp = new String[ codes.length ];
		if( t )
		{
			char[] s = ss.toCharArray();
			for( int i = 0 ; i < codes.length ; i++ )
			{
				temp[i] = s[i * 3] + "" + s[i * 3 + 1] + "" + s[i * 3 + 2];
				System.out.println( temp[i] );
			}
			
			int c = 0;
			for( int a = 0 ; a < codes.length ; a++ )
			{
				for( int bb = a + 1 ; bb < codes.length ; bb++ )
				{
					if( temp[a].equals( temp[bb] ) )
					{
						b = false;
						c++;
					}
				}
			}
			if( b == false )
			{
				JOptionPane.showMessageDialog( panel , c + " pairs of the codes are found the same!" );
			}
		}
		
		return b;
	}
}
