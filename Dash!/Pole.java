import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.imageio.*;
import java.net.*;

public class Pole
{
	private int topBorder;
	private int size;
	private int bottomBorder;
	private int x = 1200;

	Pole( Component comp )
	{
		topBorder = random( 540 ) - random( 540 );
		if( topBorder < 0 )
			topBorder = 0 - topBorder;
		size = 75 + random( 25 );
		bottomBorder = topBorder + size;
		//System.out.println( topBorder + " " + size + " " + bottomBorder );
	}
	
	Pole( Component comp , int i )
	{
		topBorder = random( 540 ) - random( 540 );
		if( topBorder < 0 )
			topBorder = 0 - topBorder;
		size = 50 + random( 20 );
		bottomBorder = topBorder + size;
		//System.out.println( topBorder + " " + size + " " + bottomBorder );
		
		x = 999999;
	}
	
	public static int random( int i )
	{
		double number = Math.random();
		number = number * i;
		return (int)(number);
	}
	
	public void moveLeft()
	{
		x = x - 5;
	}
	
	public void paintPole( Graphics g )
	{
		g.drawRect( x , 0 , 5 , topBorder );
		g.drawRect( x , bottomBorder , 5 , ( 615 - bottomBorder ) );
		g.fillRect( x , 0 , 5 , topBorder );
		g.fillRect( x , bottomBorder , 5 , ( 615 - bottomBorder ) );
	}
	
	public boolean isHere()
	{
		if( x == 100 )
			return true;
		else
			return false;
	}
	
	public int getTop()
	{
		return topBorder;
	}
	
	public int getBottom()
	{
		return bottomBorder;
	}
	
	public void leftDash()
	{
		x = x - 150;
	}
}
