import javax.swing.JOptionPane;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.awt.*;

public class door
{
	public static int prize = 0;
	public static int chose = 0;
	public static int elses = 0;
	public static int empty = 0;
	public static boolean firstRun = true;
	public static String[] users = new String[3];
	public static String user = null;
	
	public static int sticks = 0;
	public static int changes = 0;
	public static int swin = 0;
	public static int slose = 0;
	public static int cwin = 0;
	public static int close = 0;
	
	public static String[] opt = {"Stick","Change"};
	public static String[] opt2 = {"DOOR 1","DOOR 2","DOOR 3"};
	public static String[] opt3 = {"Yes","No"};
	
	public static void main( String args[] ) throws IOException
	{
		if( firstRun == true )
		{
			loadProfile();
			user = chooseProfile();
			loadUser( user );
			
		}
		game();
		displaystats();
	}
	
	public static void game() throws IOException
	{
		//if( firstRun == false )
		//{
			
		//}
		//JFrame jf = creatJFrame();
		prize = generateDoor();
		chose = JOptionPane.showOptionDialog( null , "Please choose a door" , user , 0 , 1 , null , opt2 , null) + 1;
		empty = emptyDoor();
		//System.out.println( prize );
		for( int i = 1 ; i <= 3 ; i++ )
		{
			if( i != chose && i != empty )
				elses = i;
		}
		
		String message = "The door " + empty + " is opened! There is a sheep behind it. ";
		JOptionPane.showMessageDialog( null , message , user , 1);
		message = "Do you want to stick with door " + chose + " or change to door " + elses + "?" ;
		//message = message + "\n[1]Stick ";
		//message = message + "\n[2]Change ";
		//message = message + "What's your decision? ";
		int c = JOptionPane.showOptionDialog( null , message , user , 0 , 2 , null , opt , null);
		//System.out.println( c );
		if( c == 1 )
		{
			chose = elses;
			changes++;
		}
		else
			sticks++;
			
		if( chose == prize )
		{
			JOptionPane.showMessageDialog( null , "You won the car! \nThe car is behind door" + prize + "!" , user , 1 );
			if( c == 0 )
				swin++;
			else
				cwin++;
		}
		else
		{
			JOptionPane.showMessageDialog( null , "You lose! \nThe car is behind door" + prize + "!" , user , 1);
			if( c == 0 )
				slose++;
			else
				close++;
		}
			
		saveUser( user );
		playAgain();

	}
	
	public static int generateDoor()
	{
		double r = Math.random() * 3;
		int rr = (int)( r + 1 );
		return rr;
	}
	
	public static int emptyDoor()
	{
		int e = generateDoor();
		
		while( e == prize || e == chose )
		{
			e = generateDoor();
		}
		
		return e;
		
	}
	
	public static void loadProfile() throws IOException
	{
		File f = new File( "doorusers.txt" );
		FileReader fr = new FileReader( f );
		BufferedReader in = new BufferedReader( fr );
		int c = 0;
		while(in.ready())
		{
			String ss = in.readLine();
			users[c] = ss;
			c++;
		}
	}
	
	public static void creatUser( String ss ) throws IOException
	{
		ss = ss + ".txt";
		File f = new File( ss );
		FileWriter fw = new FileWriter( f );
		PrintWriter out = new PrintWriter( fw );
		
		for( int i = 0 ; i < 6 ; i++ )
			out.println( 0 );
		out.close();
	}
	
	public static String chooseProfile() throws IOException
	{
		int in = JOptionPane.showOptionDialog( null , "Please select a profile" , "Profiles" , 0 , 1 , null , users , null);
		if( users[in].equals( "Empty" ) )
		{
			String ss = JOptionPane.showInputDialog( "Please enter your name (no spaces!)" );
			users[in] = ss;
			creatUser( ss );
			saveProfile();
		}
		return users[in];
	}
	
	public static void saveProfile() throws IOException
	{
		File f = new File( "doorusers.txt" );
		FileWriter fw = new FileWriter( f );
		PrintWriter out = new PrintWriter( fw );
		
		for( int i = 0 ; i < 3 ; i++ )
			out.println( users[i] );
		out.close();
	}
	
	public static void saveUser( String ss ) throws IOException
	{
		ss = ss + ".txt";
		File f = new File( ss );
		FileWriter fw = new FileWriter( f );
		PrintWriter out = new PrintWriter( fw );
		
		out.println( sticks );
		out.println( changes );
		out.println( swin );
		out.println( slose );
		out.println( cwin );
		out.println( close );
		
		out.close();
	}
	
	public static void loadUser( String ss ) throws IOException
	{
		ss = ss + ".txt";
		File f = new File( ss );
		FileReader fr = new FileReader( f );
		BufferedReader in = new BufferedReader( fr );
		
		int[] tempint = new int[6];
		for( int i = 0 ; i < 6 ; i++ )
			tempint[i] = Integer.parseInt( in.readLine() );
			
		sticks = tempint[0];
		changes = tempint[1];
		swin = tempint[2];
		slose = tempint[3];
		cwin = tempint[4];
		close = tempint[5];
	}
	
	public static void playAgain() throws IOException
	{
		int c = JOptionPane.showOptionDialog( null , "Do you want to play again?" , user , 0 , 1 , null , opt3 , null);
		if( c == 0 )
			game();
	}
	
	public static void displaystats()
	{
		double srates = 1.0 * swin / sticks;
		double crates = 1.0 * cwin / changes;
		double srate = (int)( srates * 1000 ) / 10;
		double crate = (int)( crates * 1000 ) / 10;
		double trates = 1.0 * ( swin + cwin ) / ( sticks + changes );
		double trate = (int)( trates * 1000 ) / 10;
		
		String ss = "";
		ss = "Profile                         : " + user + "\n\n" +
			 "Sticks                          : " + sticks + "\n" +
			 "Wins by sticking       : " + swin + "\n" +
			 "Loses by sticking     : " + slose + "\n" +
			 "Stick win rate            : " + srate + "%\n" +
			 "Changes                     : " + changes + "\n" +
			 "Wins by changing     : " + cwin + "\n" +
			 "Loses by changing   : " + close + "\n" +
			 "Change win rate        : " + crate + "%" + "\n" +
			 "Total games                : " + (sticks + changes) + "\n" +
			 "Total win rate             : " + trate + "%";
		JOptionPane.showMessageDialog( null , ss , user , 1 );
	}
	
	public static JFrame creatJFrame()
	{
		JFrame frame = new JFrame("GameShow");
		frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		JLabel emptyLabel = new JLabel("");
		emptyLabel.setPreferredSize( new Dimension( 400 , 100 ) );
		frame.getContentPane().add( emptyLabel , BorderLayout.CENTER );
		
		frame.pack();
		frame.setVisible( true );
		return frame;
	}
	
//IBIO INPUT =======================================================================================
	static int inputInt(String prompt)
	{	int	result = 0;
		try
		{	result = Integer.valueOf(input(prompt).trim()).intValue() ;	}
		catch (Exception e)	{	System.out.println(">>> error in input"); result = 0;}
		return	result;
	}

	static int inputInt()
	{	return	inputInt("");	}

	static double inputDouble(String prompt)
	{	double	result = 0;
		try
		{	result = Double.valueOf(input(prompt).trim()).doubleValue() ;	}
		catch (Exception e)	{	result = 0;}
		return	result;
	}

	static double inputDouble()
	{	return	inputDouble("");	}
	
	static String input(String prompt)
	{	String	inputLine = "";
		System.out.print(prompt);
		try
		{	inputLine = (new java.io.BufferedReader(
							new java.io.InputStreamReader(System.in))).readLine();}
		catch (Exception e)
		{	String	err = e.toString();
			System.out.println(err);
			inputLine = "";
		}
		return	inputLine;
	}
	
}
