import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class DashPanel extends JPanel
{
	Player player = new Player( this );
	private Pole pole1 = new Pole( this , 1 );
	private Pole pole2 = new Pole( this , 1 );
	private Pole pole3 = new Pole( this , 1 );
	private int poleTicker = 0;
	private int poleIndex = 1;
	private int score = 0;
	
	DashPanel()
	{
		setBackground( Color.WHITE );
		setLayout( null );

		repaint();
	}

//@override
	public void paintComponent( Graphics g )
	{	
		super.paintComponent( g );
		player.paintPlayer( g );	
		pole1.paintPole( g );
		pole2.paintPole( g );
		pole3.paintPole( g );
		
		g.drawLine( 0 , 615 , 1100 , 615 );
		g.drawString( "Score: " + score , 1000 , 640 );
		
	}
	
	public void replacePole( int i )
	{
		switch( i )
		{
			case 1 :
				pole1 = new Pole( this );
				break;
			case 2 :
				pole2 = new Pole( this );
				break;
			case 0 :
				pole3 = new Pole( this );
				break;
		}
	}
	
	public void tick()
	{
		poleTicker++;
		if( poleTicker == 100 )
		{
			poleIndex++;
			replacePole( poleIndex % 3 );
			poleTicker = 0;
		}
		
	}
	
	public void allPoleLeft()
	{
		pole1.moveLeft();
		pole2.moveLeft();
		pole3.moveLeft();
	}
	
	public int listenPole()
	{
		if( pole1.isHere() )
			return 1;
		else if( pole2.isHere() )
			return 2;
		else if( pole3.isHere() )
			return 3;
		else
			return 0;
	}
	
	public Pole getPole( int i )
	{	
		Pole tempPole = new Pole( this );
		switch( i )
		{
			case 1:
				tempPole = pole1;
				break;
			case 2:
				tempPole = pole2;
				break;
			case 3:
				tempPole = pole3;
				break;
		}
		return tempPole;
	}
	
	public void increaseScore()
	{
		score++;
	}
	
	public void reset()
	{
		pole1 = new Pole( this , 1 );
		pole2 = new Pole( this , 1 );
		pole3 = new Pole( this , 1 );
		poleTicker = 0;
		poleIndex = 1;
		repaint();
		score = 0;
		player.setY( 0 );
	}
	
	public int getScore()
	{
		return score;
	}
	
	public void setScore( int i )
	{
		score = i;
	}
	
	public void allLeftDash()
	{
		pole1.leftDash();
		pole2.leftDash();
		pole3.leftDash();
	}
	
}
