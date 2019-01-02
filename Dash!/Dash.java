import javax.swing.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;


public class Dash
{
	public static void main( String args[] )
	{
		DashFrame df = new DashFrame();
		new Thread( df ).start();
		df.setSize( 1100 , 700 );
		df.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		df.setVisible( true );
		df.setResizable( false );
	}
}
