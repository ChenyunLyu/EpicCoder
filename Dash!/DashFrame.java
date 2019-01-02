import javax.swing.*;
import sun.audio.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;

public class DashFrame extends JFrame implements KeyListener, Runnable
{
	private DashPanel dp = new DashPanel();
	private boolean down = true;
	private boolean up = false;
	private int playerCoordinates;
	private int safeZoneTop;
	private int safeZoneBottom;
	boolean execute = true;
	boolean restart = false;
	private JButton playAgainButton = new JButton( "Play Again!" );
	private JLabel scoreLabel = new JLabel( " " , JLabel.CENTER );
	private int maxCharge = 720;
	private JProgressBar jpb = new JProgressBar( 0 , maxCharge );
	private int skillCharge = 0;

	DashFrame()
	{
		super("DASH!");
		add( dp );
		
		Font f = new Font( "Comic Sense" , Font.BOLD , 50 );
		Color c = new Color( 0x1071CF );
		
		dp.add( playAgainButton );
		dp.add( scoreLabel );
		dp.add( jpb );
		
		playAgainButton.setBounds( 400 , 325 , 300 , 50 );
		playAgainButton.setVisible( false );
		playAgainButton.setFocusable( false );
		scoreLabel.setVisible( false );
		scoreLabel.setBounds( 300 , 200 , 500 , 100 );
		scoreLabel.setForeground( c );
		scoreLabel.setFont( f );
		jpb.setBounds( 50 , 640 , 150 , 20 );
		jpb.setStringPainted( true );
		//jpb.setValue( maxCharge / 2 );
		

		playAgainButton.addActionListener
		(
			new ActionListener()
			{
				public void actionPerformed( ActionEvent e )
				{
					execute = true;
					playAgainButton.setVisible( false );
					scoreLabel.setVisible( false );
					dp.reset();
					skillCharge = 0;
					//restart = true;
					//repaint();
					//recreate().start();
					//run();
				}
			}
		);
/*		
		component.getInputMap().put( KeyStroke.getKeyStroke("SPACE"), "goUp" );
		component.getInputMap().put( "goUp" , upAction );
		Action upAction = new AbstractAction()
		{
			public void actionPerformed( ActionEvent e )
			{
				down = false;
				up = true;
			}
		};
		*/
		
		setContentPane( dp );
		addKeyListener( this );
		pack();
		
	}

//@override	
	public void keyPressed( KeyEvent e )
	{
		switch( e.getKeyCode() )
		{
			case KeyEvent.VK_SPACE:
				down = false;
				up = true;
				break;
			case KeyEvent.VK_C:
				dp.setScore( dp.getPole( 1 ).random( 10000 ) );
				break;
			case KeyEvent.VK_ESCAPE:
				System.exit( 0 );
				break;
			case KeyEvent.VK_Z:
				if( skillCharge == maxCharge )
					skillLeftDash();
				break;
				
		}	
	}
	
	public void keyTyped( KeyEvent e )
	{
				
	}

//@override	
	public void keyReleased( KeyEvent e )
	{
		switch( e.getKeyCode() )
		{
			case KeyEvent.VK_SPACE:
				up = false;
				down = true;
				break;
		}
	}
	
	public Thread recreate()
	{
		Thread t = new Thread( this );
		return t;
	}
	
	public void reset()
	{
		dp = new DashPanel();
	}
	
	public void skillLeftDash()
	{
		for( int i = 0 ; i < 30 ; i++ )
		{
			dp.tick();
		}
		dp.allLeftDash();
		skillCharge = 0;
	}

//@override	
	public void run()
	{
		while( true )
		{
			//addKeyListener( this );
			if( execute )
			{
				dp.tick();
				dp.allPoleLeft();
				dp.player.move();
			
				if( up )
					dp.player.upMotion();
				else
					dp.player.downMotion();
				
				if( skillCharge < maxCharge )
				{
					skillCharge++;
					jpb.setValue( skillCharge );
					double p = (double)(skillCharge)/maxCharge * 100;
					jpb.setString( (int)(p) +"%" );
				}
			}
				
			if( dp.listenPole() != 0 )
			{
				playerCoordinates = dp.player.getY();
				safeZoneTop = dp.getPole( dp.listenPole() ).getTop();
				safeZoneBottom = dp.getPole( dp.listenPole() ).getBottom() - 15;
				if( playerCoordinates < safeZoneTop || playerCoordinates > safeZoneBottom )
				{
					execute = false;
					playAgainButton.setVisible( true );
					scoreLabel.setVisible( true );
					scoreLabel.setText( "Your Score: " + dp.getScore() );
				}
				else
					dp.increaseScore();
					
			}
			
			repaint();
			
			try
			{
				Thread.sleep( 16 );
			}catch( InterruptedException e )
			{
				e.printStackTrace();
			}
			
		}
	}
}
