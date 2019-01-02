import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.image.*;
import java.awt.event.*;
import javax.imageio.*;
import java.net.*;

public class Player
{
	private double x = 100;
	private double y = 600;
	private double vel = 0;
	private Component c;
	private BufferedImage bi;
	
	Player( Component comp )
	{
		File f = new File( "charactor.png" );
		try
		{
			bi = ImageIO.read( f.toURI().toURL() );
		}catch( IOException e )
		{
			e.printStackTrace();
		}
	}
	
	public void upMotion()
	{
		vel = vel - 0.7;
		if( vel < -16 )
			vel = -16;
		if( y <= 0 && vel < 0 )
			vel = 0;
		if( y >= 600 && vel > 0 )
			vel = 0;
	}
	
	public void downMotion()
	{
		vel = vel + 0.98;
		if( vel > 16 )
			vel = 16;
		if( y >= 600 && vel > 0)
			vel = 0;
	}
	
	public void move()
	{
		y = y + vel;
		if( y < 0 )
			y = 0;
		if( y > 600 )
			y = 600;
		if( y <= 0 && vel < 0 )
			vel = 0;
		System.out.println( vel + " , " + y );
	}
	
	public void paintPlayer( Graphics g )
	{
		g.drawImage( bi , (int)(x) , (int)(y) , c );
	}
	
	public int getY()
	{
		return (int)(y);
	}
	
	public void setY( int i )
	{
		y = i;
	}
	
}
